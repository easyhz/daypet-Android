package com.easyhz.daypet.sign.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.extension.screenHorizonPadding
import com.easyhz.daypet.design_system.theme.Red
import com.easyhz.daypet.design_system.theme.SubBody3
import com.easyhz.daypet.design_system.theme.TextColor

@Composable
internal fun ImageBottomSheetContent(
    modifier: Modifier = Modifier,
    items: List<BottomSheetItem>,
    onClick: (BottomSheetItem) -> Unit
) {
    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .screenHorizonPadding()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach {
            Row(
                modifier = Modifier
                    .noRippleClickable { onClick(it) }
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = it.imageVector,
                    contentDescription = stringResource(id = it.labelId),
                    tint = it.contentColor
                )
                Text(
                    text = stringResource(id = it.labelId),
                    style = SubBody3,
                    color = it.contentColor
                )
            }
        }
    }
}

enum class BottomSheetItem(
    @StringRes val labelId: Int,
    val imageVector: ImageVector,
    val contentColor: Color
) {
    SELECT(
        R.string.select_image_bottom_sheet_select,
        Icons.Outlined.PhotoLibrary,
        TextColor
    ), DELETE(
        R.string.select_image_bottom_sheet_delete,
        Icons.Outlined.Delete,
        Red
    )
}

@Preview(showBackground = true)
@Composable
private fun ImageBottomSheetContentPrev() {
    ImageBottomSheetContent(
        items = BottomSheetItem.entries
    ) {

    }
}