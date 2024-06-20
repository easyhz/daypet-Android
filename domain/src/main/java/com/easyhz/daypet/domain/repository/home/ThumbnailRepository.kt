package com.easyhz.daypet.domain.repository.home

import com.easyhz.daypet.domain.model.home.Thumbnail
import com.easyhz.daypet.domain.param.home.ThumbnailParam

interface ThumbnailRepository {
    suspend fun fetchMonthlyThumbnail(data: ThumbnailParam): Result<Thumbnail>
}