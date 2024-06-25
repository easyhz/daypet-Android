package com.easyhz.daypet.domain.base

abstract class BaseUseCase<P, R> {
    abstract suspend operator fun invoke(param: P): Result<R>
}