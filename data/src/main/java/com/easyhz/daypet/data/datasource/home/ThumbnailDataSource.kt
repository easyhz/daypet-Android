package com.easyhz.daypet.data.datasource.home

import com.easyhz.daypet.data.model.request.home.SetThumbnailRequest
import com.easyhz.daypet.data.model.request.home.ThumbnailRequest
import com.easyhz.daypet.data.model.response.home.ThumbnailResponse

interface ThumbnailDataSource {
    suspend fun fetchMonthlyThumbnail(data: ThumbnailRequest): Result<List<ThumbnailResponse>>
    suspend fun setThumbnail(data: SetThumbnailRequest): Result<Unit>

}