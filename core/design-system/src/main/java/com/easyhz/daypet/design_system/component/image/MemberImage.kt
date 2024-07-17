package com.easyhz.daypet.design_system.component.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.R
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.ImageFadeBackgroundColor
import com.easyhz.daypet.design_system.theme.ImageFadeColor
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubHeading1
import com.easyhz.daypet.design_system.theme.TextColor

@Stable
enum class MemberSelectType {
    NONE, BOX, CHECK, DELETE, ADD
}

@Stable
enum class MemberType(
    @DrawableRes val resId: Int
) {
    PERSON(resId = R.drawable.ic_profile),
    PET(resId = R.drawable.ic_pet_profile),
    NONE(resId = R.drawable.ic_daypet_logo)
}

@Composable
fun MemberImage(
    selectType: MemberSelectType,
    imageUrl: String,
    name: String,
    memberType: MemberType,
    isChecked: Boolean = false,
    onClick: () -> Unit
) {
    val imageComposable: @Composable (String, String, MemberType, Boolean, () -> Unit) -> Unit =
        when (selectType) {
            MemberSelectType.NONE -> { mImageUrl, mName, mMemberType, _, mOnClick ->
                MemberImageView(
                    imageUrl = mImageUrl,
                    name = mName,
                    memberType = mMemberType,
                    onClick = mOnClick
                )
            }

            MemberSelectType.BOX -> { mImageUrl, mName, mMemberType, mIsChecked, mOnClick ->
                MemberBoxImage(
                    imageUrl = mImageUrl,
                    name = mName,
                    memberType = mMemberType,
                    isChecked = mIsChecked,
                    onClick = mOnClick
                )
            }

            MemberSelectType.CHECK -> { mImageUrl, mName, mMemberType, mIsChecked, mOnClick ->
                MemberCheckImage(
                    imageUrl = mImageUrl,
                    name = mName,
                    memberType = mMemberType,
                    isChecked = mIsChecked,
                    onClick = mOnClick
                )
            }

            MemberSelectType.DELETE -> { mImageUrl, mName, mMemberType, mIsChecked, mOnClick ->
                MemberDeleteImage(
                    imageUrl = mImageUrl,
                    name = mName,
                    memberType = mMemberType,
                    isChecked = mIsChecked,
                    onClick = mOnClick
                )
            }

            MemberSelectType.ADD -> { _, mName, _, _, mOnClick ->
                MemberAdd(name = mName, onClick = onClick)
            }
        }
    imageComposable(imageUrl, name, memberType, isChecked, onClick)
}

@Composable
private fun MemberImageView(
    modifier: Modifier = Modifier,
    imageUrl: String,
    name: String,
    memberType: MemberType,
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
                if (imageUrl.isBlank()) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.Center),
                        painter = painterResource(id = memberType.resId),
                        contentDescription = "profile"
                    )
                } else {
                    ImageCircle(
                        modifier = Modifier.size(56.dp),
                        imageUrl = imageUrl
                    )
                }
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
    memberType: MemberType,
    isChecked: Boolean = false,
    onClick: () -> Unit = { }
) {
    Box(
        modifier = Modifier.noRippleClickable { onClick() }
    ) {
        MemberImageView(
            imageUrl = imageUrl,
            name = name,
            memberType = memberType,
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
    memberType: MemberType,
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
            memberType = memberType,
            onClick = onClick
        )
    }
}

@Composable
private fun MemberDeleteImage(
    imageUrl: String,
    name: String,
    memberType: MemberType,
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
            memberType = memberType,
            onClick = onClick
        )
        if (isChecked) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Primary)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "check",
                    modifier = Modifier
                        .size(12.dp)
                        .align(Alignment.Center),
                    tint = MainBackground
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
            Box(modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(ButtonShapeColor)) {
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
        MemberImage(selectType = MemberSelectType.NONE, imageUrl = "", memberType = MemberType.PERSON, name = "보리") { }
        MemberImage(
            selectType = MemberSelectType.BOX,
            imageUrl = "",
            name = "보리",
            memberType = MemberType.PET,
            isChecked = true
        ) { }
        MemberImage(
            selectType = MemberSelectType.CHECK,
            imageUrl = "",
            name = "보리",
            memberType = MemberType.PERSON,
            isChecked = true
        ) { }
        MemberImage(
            selectType = MemberSelectType.DELETE,
            imageUrl = "",
            name = "보리",
            memberType = MemberType.PET,
            isChecked = true
        ) { }
        MemberImage(
            selectType = MemberSelectType.ADD,
            imageUrl = "",
            name = "",
            memberType = MemberType.PET,
            isChecked = true
        ) { }
    }
}