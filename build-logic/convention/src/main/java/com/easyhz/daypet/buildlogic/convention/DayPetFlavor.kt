package com.easyhz.daypet.buildlogic.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor


@Suppress("EnumEntryName")
enum class DayPetFlavor(val appLabel: String, val applicationIdSuffix: String? = null) {
    prod("데이펫"),
    dev("데이펫 dev", ".dev")
}

fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: DayPetFlavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += "build-type"
        productFlavors {
            DayPetFlavor.values().forEach { flavor ->
                create(flavor.name) {
                    dimension = "build-type"
                    flavorConfigurationBlock(this, flavor)
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (flavor.applicationIdSuffix != null) {
                            applicationIdSuffix = flavor.applicationIdSuffix
                        }
                    }
                    manifestPlaceholders["appLabel"] = flavor.appLabel

                }
            }
        }
    }
}