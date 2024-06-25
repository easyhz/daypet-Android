package com.easyhz.daypet.data.mapper.memory

import com.easyhz.daypet.common.extension.toFormattedTime
import com.easyhz.daypet.common.extension.toTimeStamp
import com.easyhz.daypet.data.model.request.memory.MemoryRequest
import com.easyhz.daypet.data.model.response.memory.MemoryResponse
import com.easyhz.daypet.domain.model.memory.Memory
import com.easyhz.daypet.domain.param.memory.MemoryParam

fun MemoryResponse.toModel(): Memory = Memory(
    title = this.title,
    imageUrl = this.thumbnailUrl,
    time = this.creationTime.toFormattedTime()
)

fun MemoryParam.toRequest(): MemoryRequest = MemoryRequest(
    startDate = this.startDate.toTimeStamp(),
    endDate = this.startDate.toTimeStamp(1)
)