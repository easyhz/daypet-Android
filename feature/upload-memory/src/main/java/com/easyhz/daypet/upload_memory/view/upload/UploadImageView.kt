package com.easyhz.daypet.upload_memory.view.upload

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.component.main.DayPetRow
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.theme.FadeColor
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubBody2
import com.easyhz.daypet.design_system.theme.SubHeading1
import com.easyhz.daypet.design_system.theme.SubTextColor
import com.easyhz.daypet.upload_memory.util.imageViewHeight
import com.easyhz.daypet.upload_memory.util.imageViewSize
import com.easyhz.daypet.upload_memory.util.imageViewWidth

@Composable
internal fun UploadImageView(
    modifier: Modifier = Modifier,
    imageUrls: List<Uri>,
    onClickNoImage: () -> Unit,
    onClickDelete: (Uri) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = stringResource(id = R.string.upload_image),
            style = SubBody2,
            color = Primary
        )
        ImageRow(
            imageUrls = imageUrls,
            onClickDelete = onClickDelete,
            onClickNoImage = onClickNoImage
        )
    }
}

@Composable
private fun ImageRow(
    imageUrls: List<Uri>,
    onClickDelete: (Uri) -> Unit,
    onClickNoImage: () -> Unit
) {
    if (imageUrls.isEmpty()) {
        NoImageView(onClick = onClickNoImage)
    } else {
        DayPetRow(
            modifier = Modifier
                .fillMaxWidth()
                .imageViewHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(imageUrls) {item ->
                ImageItem(imageUrl = item) {
                    onClickDelete(item)
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ImageItem(
    modifier: Modifier = Modifier,
    imageUrl: Uri,
    onClickDelete: () -> Unit
) {
    Box(
        modifier = modifier
            .imageViewSize()
            .clip(RoundedCornerShape(8.dp))
    ) {
        GlideImage(
            modifier = Modifier
                .imageViewSize(),
            model = imageUrl,
            contentDescription = null,
            loading = placeholder(ColorPainter(SubTextColor)),
            failure = placeholder(ColorPainter(SubTextColor)),
            contentScale = ContentScale.Crop,
            transition = CrossFade
        )
        Box(
            modifier = Modifier
                .height(24.dp)
                .imageViewWidth()
                .align(Alignment.TopCenter)
                .background(FadeColor)
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(2.dp),
                imageVector = Icons.Outlined.Close,
                contentDescription = "Close"
            )
        }
        Box(modifier = Modifier.size(36.dp).align(Alignment.TopEnd).noRippleClickable { onClickDelete() })
    }
}

@Composable
private fun NoImageView(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .imageViewHeight()
            .noRippleClickable { onClick() }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.upload_image_placeholder),
            style = SubHeading1,
            color = SubTextColor
        )
    }
}