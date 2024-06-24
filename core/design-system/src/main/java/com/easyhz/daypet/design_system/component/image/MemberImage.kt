package com.easyhz.daypet.design_system.component.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.ImageFadeBackgroundColor
import com.easyhz.daypet.design_system.theme.ImageFadeColor
import com.easyhz.daypet.design_system.theme.SubHeading1
import com.easyhz.daypet.design_system.theme.TextColor

@Stable
enum class MemberSelectType {
    NONE, BOX, CHECK, DELETE, ADD
}

@Composable
fun MemberImage(
    selectType: MemberSelectType,
    imageUrl: String,
    name: String,
    isChecked: Boolean = false,
    onClick: () -> Unit
) {
    val imageComposable: @Composable (String, String, Boolean, () -> Unit) -> Unit =
        when (selectType) {
            MemberSelectType.NONE -> { mImageUrl, mName, _, mOnClick ->
                MemberImageView(
                    imageUrl = mImageUrl,
                    name = mName,
                    onClick = mOnClick
                )
            }
            MemberSelectType.BOX -> { mImageUrl, mName, mIsChecked, mOnClick ->
                MemberBoxImage(
                    imageUrl = mImageUrl,
                    name = mName,
                    isChecked = mIsChecked,
                    onClick = mOnClick
                )
            }
            MemberSelectType.CHECK -> { mImageUrl, mName, mIsChecked, mOnClick ->
                MemberCheckImage(
                    imageUrl = mImageUrl,
                    name = mName,
                    isChecked = mIsChecked,
                    onClick = mOnClick
                )
            }
            MemberSelectType.DELETE -> { mImageUrl, mName, mIsChecked, mOnClick ->
                MemberDeleteImage(
                    imageUrl = mImageUrl,
                    name = mName,
                    isChecked = mIsChecked,
                    onClick = mOnClick
                )
            }
            MemberSelectType.ADD -> { _, mName, _, mOnClick ->
                MemberAdd(name = mName, onClick = onClick)
            }
        }
    imageComposable(imageUrl, name, isChecked, onClick)
}

@Composable
private fun MemberImageView(
    modifier: Modifier = Modifier,
    imageUrl: String,
    name: String,
    onClick: () -> Unit = { }
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .noRippleClickable {
                onClick()
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Box(modifier = Modifier.size(56.dp)) {
                ImageCircle(
                    modifier = Modifier.size(56.dp),
                    imageUrl = imageUrl
                )
            }
            Text(
                text = name,
                style = SubHeading1,
                color = TextColor
            )
        }
    }
}

@Composable
private fun MemberCheckImage(
    imageUrl: String,
    name: String,
    isChecked: Boolean = false,
    onClick: () -> Unit = { }
) {
    Box(
        modifier = Modifier.noRippleClickable { onClick() }
    ) {
        MemberImageView(
            imageUrl = imageUrl,
            name = name,
            onClick = onClick
        )
        if (isChecked) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(ImageFadeColor)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Check,
                    contentDescription = "check",
                    modifier = Modifier.align(Alignment.Center),
                    tint = ButtonShapeColor
                )
            }
        }
    }
}

@Composable
private fun MemberBoxImage(
    imageUrl: String,
    name: String,
    isChecked: Boolean = false,
    onClick: () -> Unit = { }
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
    ) {
        if (isChecked) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(4.dp))
                    .background(ImageFadeBackgroundColor)
            )
        }
        MemberImageView(
            imageUrl = imageUrl,
            name = name,
            onClick = onClick
        )
    }
}

@Composable
private fun MemberDeleteImage(
    imageUrl: String,
    name: String,
    isChecked: Boolean = false,
    onClick: () -> Unit = { }
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
    ) {
        MemberImageView(
            imageUrl = imageUrl,
            name = name,
            onClick = onClick
        )
        if (isChecked) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(ButtonShapeColor)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "check",
                    modifier = Modifier
                        .size(12.dp)
                        .align(Alignment.Center),
                    tint = TextColor
                )
            }
        }
    }
}

@Composable
private fun MemberAdd(
    modifier: Modifier = Modifier,
    name: String = "",
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .noRippleClickable {
                onClick()
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Box(modifier = Modifier.size(56.dp).clip(CircleShape).background(ButtonShapeColor)) {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "add"
                )
            }
            Text(
                text = name,
                style = SubHeading1,
                color = TextColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MemberImagePrev() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MemberImage(selectType = MemberSelectType.NONE, imageUrl = "", name = "보리") { }
        MemberImage(
            selectType = MemberSelectType.BOX,
            imageUrl = "",
            name = "보리",
            isChecked = true
        ) { }
        MemberImage(
            selectType = MemberSelectType.CHECK,
            imageUrl = "",
            name = "보리",
            isChecked = true
        ) { }
        MemberImage(
            selectType = MemberSelectType.DELETE,
            imageUrl = "",
            name = "보리",
            isChecked = true
        ) { }
        MemberImage(
            selectType = MemberSelectType.ADD,
            imageUrl = "",
            name = "",
            isChecked = true
        ) { }
    }
}