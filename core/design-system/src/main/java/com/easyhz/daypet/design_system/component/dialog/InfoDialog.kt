package com.easyhz.daypet.design_system.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.theme.Body2
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.Primary
import com.easyhz.daypet.design_system.theme.SubTextColor
import com.easyhz.daypet.design_system.theme.SubTitle

@Composable
fun InfoDialog(
    modifier: Modifier = Modifier,
    title: String,
    content: String?,
    negativeButton: DialogButton?,
    positiveButton: DialogButton,
    onDismissRequest: () -> Unit = positiveButton.onClick
) {
    val horizontalPadding = 12.dp
    val buttonWidth = 132.dp
    val buttonSpacing = 12.dp

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = false,
        )
    ) {
        Column(
            modifier = modifier
                .padding(24.dp)
                .widthIn(max = horizontalPadding * 2 + buttonWidth * 2 + buttonSpacing)
                .clip(RoundedCornerShape(16.dp))
                .background(MainBackground)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 24.dp).widthIn(max = 240.dp),
                text = title,
                style = SubTitle,
                textAlign = TextAlign.Center
            )
            content?.let {
                Text(
                    modifier = Modifier.widthIn(max = 240.dp),
                    text = it,
                    style = Body2,
                    color = SubTextColor,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                negativeButton?.let {
                    DialogButton(
                        modifier = Modifier.weight(1f),
                        text = negativeButton.text,
                        contentColor = negativeButton.contentColor,
                        containerColor = negativeButton.containerColor,
                        onClick = negativeButton.onClick
                    )
                }
                DialogButton(
                    modifier = Modifier.weight(1f),
                    text = positiveButton.text,
                    contentColor = positiveButton.contentColor,
                    containerColor = positiveButton.containerColor,
                    onClick = positiveButton.onClick
                )
            }
        }

    }
}

@Composable
private fun DialogButton(
    modifier: Modifier = Modifier,
    text: String = "",
    contentColor: Color = MainBackground,
    containerColor: Color = Primary,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(44.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(containerColor)
            .noRippleClickable { onClick() },
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            style = SubTitle,
            color = contentColor
        )
    }
}

@Preview(
    showBackground = true,
    device = "spec:shape=Normal,width=320,height=640, unit=dp, dpi= 480"
)
@Composable
fun InfoDialogPreview() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        InfoDialog(
            title = "그룹이 만들어졌어요!\n반려동물 정보를 추가할까요?",
            content = null,
            negativeButton = null,
            positiveButton = DialogButton(
                text = "완료",
                contentColor = MainBackground,
                containerColor = Primary,
                onClick = { }
            ),
        ) {

        }
    }
}