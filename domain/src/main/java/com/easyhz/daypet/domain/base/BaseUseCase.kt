package com.easyhz.daypet.domain.base

abstract class BaseUseCase<D, R> {
    abstract suspend operator fun invoke(data: D): Result<R>
}