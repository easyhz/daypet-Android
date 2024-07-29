package com.easyhz.daypet.data.mapper.home

import com.easyhz.daypet.common.extension.toHashMap
import com.easyhz.daypet.common.extension.toLocalDate
import com.easyhz.daypet.common.extension.toTimeStamp
import com.easyhz.daypet.data.model.request.home.CreateThumbnail
import com.easyhz.daypet.data.model.request.home.SetThumbnailRequest
import com.easyhz.daypet.data.model.request.home.ThumbnailRequest
import com.easyhz.daypet.data.model.response.home.ThumbnailResponse
import com.easyhz.daypet.domain.model.home.Thumbnail
import com.easyhz.daypet.domain.param.home.ThumbnailParam


fun List<ThumbnailResponse>.toModel(): Thumbnail {
    val monthSet = mutableSetOf<String>()
    val thumbnailUrls = mutableMapOf<String, String>()

    this.forEach { thumbnail ->
        val monthPart = thumbnail.monthDate.toLocalDate().toString().substring(0, 7)
        monthSet.add(monthPart)
        thumbnail.thumbnailUrlDict.forEach { (key, value) ->
            thumbnailUrls["$monthPart-${key.padStart(2, '0')}"] = value
        }
    }

    return Thumbnail(month = monthSet.toHashSet(), thumbnailUrls = thumbnailUrls.toHashMap())
}
fun ThumbnailParam.toRequest(): ThumbnailRequest = ThumbnailRequest(
    startDate = this.startDate.toTimeStamp(),
    endDate = this.endDate.toTimeStamp(1),
    groupId = this.groupId
)

fun SetThumbnailRequest.toCreateThumbnail(): CreateThumbnail = CreateThumbnail(
    groupId = this.groupId,
    monthDate = this.monthDate,
    thumbnailUrlDict = mapOf(this.day to this.url)
)