package com.easyhz.daypet.data.repository.home

import com.easyhz.daypet.data.datasource.home.ThumbnailDataSource
import com.easyhz.daypet.data.mapper.home.toModel
import com.easyhz.daypet.data.mapper.home.toRequest
import com.easyhz.daypet.domain.model.home.Thumbnail
import com.easyhz.daypet.domain.param.home.ThumbnailParam
import com.easyhz.daypet.domain.repository.home.ThumbnailRepository
import javax.inject.Inject

class ThumbnailRepositoryImpl @Inject constructor(
    private val thumbnailDataSource: ThumbnailDataSource
): ThumbnailRepository {
    override suspend fun fetchMonthlyThumbnail(param: ThumbnailParam): Result<Thumbnail> =
        thumbnailDataSource.fetchMonthlyThumbnail(param.toRequest()).map { it.toModel() }

}