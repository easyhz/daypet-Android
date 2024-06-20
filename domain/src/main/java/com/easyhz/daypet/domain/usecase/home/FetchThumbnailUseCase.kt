package com.easyhz.daypet.domain.usecase.home

import com.easyhz.daypet.domain.base.BaseUseCase
import com.easyhz.daypet.domain.model.home.Thumbnail
import com.easyhz.daypet.domain.param.home.ThumbnailParam
import com.easyhz.daypet.domain.repository.home.ThumbnailRepository
import javax.inject.Inject

class FetchThumbnailUseCase @Inject constructor(
    private val thumbnailRepository: ThumbnailRepository
): BaseUseCase<ThumbnailParam, Thumbnail>() {
    override suspend fun invoke(data: ThumbnailParam): Result<Thumbnail> =
        thumbnailRepository.fetchMonthlyThumbnail(data)
}