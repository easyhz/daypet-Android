package com.easyhz.daypet.data.mapper.memory

import com.easyhz.daypet.common.extension.convertToTimeStamp
import com.easyhz.daypet.common.extension.toFormattedTime
import com.easyhz.daypet.common.extension.toTimeStamp
import com.easyhz.daypet.data.model.request.home.SetThumbnailRequest
import com.easyhz.daypet.data.model.request.memory.MemoryRequest
import com.easyhz.daypet.data.model.request.memory.UploadMemoryRequest
import com.easyhz.daypet.data.model.response.memory.MemoryResponse
import com.easyhz.daypet.domain.model.memory.Memory
import com.easyhz.daypet.domain.model.memory.MemoryDetail
import com.easyhz.daypet.domain.param.memory.MemoryParam
import com.easyhz.daypet.domain.param.upload.UploadMemoryParam
import com.google.firebase.Timestamp

fun MemoryResponse.toModel(id: String): Memory = Memory(
    documentId = id,
    title = this.title,
    imageUrl = this.thumbnailUrl,
    time = this.memoryDate.toFormattedTime(),
    creationTime = this.creationTime.toFormattedTime(pattern = "yyyy.MM.dd HH:mm")
)

fun MemoryResponse.toDetail(id: String): MemoryDetail = MemoryDetail(
    documentId = id,
    title = this.title,
    content = this.content,
    membersId = this.memberIds,
    petsId = this.petIds,
    imageUrl = this.imageUrls,
    thumbnailUrl = this.thumbnailUrl,
    date = this.creationTime.toFormattedTime(pattern = "yyyy.MM.dd HH:mm")
)

fun MemoryParam.toRequest(): MemoryRequest = MemoryRequest(
    startDate = this.startDate.toTimeStamp(),
    endDate = this.startDate.toTimeStamp(1),
    groupId = this.groupId
)

fun UploadMemoryParam.toRequest(imageUrls: List<String>, thumbnail: String): UploadMemoryRequest = UploadMemoryRequest(
    title = this.title,
    content = this.content,
    creationTime = Timestamp.now(),
    groupId = this.groupId,
    fcmTokens = emptyList(), // FIXME fcmToken
    imageUrls = imageUrls,
    isPublic = true, // FIXME
    members = this.users,
    pets = this.pets,
    thumbnailUrl = thumbnail,
    uploaderId = this.uploaderId,
    memoryDate = convertToTimeStamp(this.date, this.time)
)

fun UploadMemoryParam.toSetThumbnail(thumbnail: String): SetThumbnailRequest = SetThumbnailRequest(
    groupId = this.groupId,
    startDate = this.date.withDayOfMonth(1).toTimeStamp(),
    endDate = this.date.withDayOfMonth(date.lengthOfMonth()).toTimeStamp(),
    monthDate = this.date.withDayOfMonth(1).toTimeStamp(),
    day = this.date.dayOfMonth.toString(),
    url =  thumbnail
)