package com.easyhz.daypet.upload_memory.util

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val IMAGE_VIEW_SIZE = 140
internal fun Modifier.imageViewHeight(): Modifier = this.height(IMAGE_VIEW_SIZE.dp)
internal fun Modifier.imageViewWidth(): Modifier = this.width(IMAGE_VIEW_SIZE.dp)
internal fun Modifier.imageViewSize(): Modifier = this.size(IMAGE_VIEW_SIZE.dp)

