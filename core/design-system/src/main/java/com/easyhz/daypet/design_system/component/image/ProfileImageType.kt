package com.easyhz.daypet.design_system.component.image

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.Primary

sealed class ProfileImageType {
    data object User: ProfileImageType()
    data object Pet: ProfileImageType()
}

sealed class UpdateImageType: ProfileImageType() {
    data object Pet: UpdateImageType()
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    imageUri: Uri,
    type: ProfileImageType,
    onClick: () -> Unit
) {
    val painterId = when(type) {
        is ProfileImageType.User -> { R.drawable.ic_profile }
        is ProfileImageType.Pet -> { R.drawable.ic_pet_profile }
        is UpdateImageType.Pet -> { R.drawable.ic_profile }     /* TODO : icon 바꾸기 */
    }
    val (cameraBackground, cameraIconColor) = when (type) {
        is UpdateImageType -> Pair(ButtonShapeColor, Primary)
        else -> Pair(Primary, ButtonShapeColor)
    }

    Box(modifier = modifier.noRippleClickable { onClick() }) {
        if (imageUri == Uri.EMPTY) {
            Image(
                modifier = Modifier
                    .align(Alignment.Center),
                painter = painterResource(id = painterId),
                contentDescription = "profile"
            )
        } else {
            GlideImage(
                modifier = Modifier
                    .size(148.dp)
                    .align(Alignment.Center)
                    .clip(CircleShape),
                model = imageUri,
                contentDescription = null,
                loading = placeholder(painterResource(id = painterId)),
                failure = placeholder(painterResource(id = painterId)),
                contentScale = ContentScale.Crop,
                transition = CrossFade
            )
        }
        Box(modifier = Modifier
            .padding(end = 4.dp, bottom = 4.dp)
            .size(32.dp)
            .clip(CircleShape)
            .align(Alignment.BottomEnd)
            .background(cameraBackground)
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.ic_change_camera),
                contentDescription = "changeCamera",
                tint = cameraIconColor
            )
        }
    }
}

@Preview
@Composable
private fun ProfileImagePrev() {
    ProfileImage(imageUri = Uri.EMPTY, type = ProfileImageType.Pet) {

    }
}