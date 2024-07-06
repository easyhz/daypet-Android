package com.easyhz.daypet.design_system.component.button

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.extension.noRippleClickable
import com.easyhz.daypet.design_system.theme.Body2
import com.easyhz.daypet.design_system.theme.ButtonShapeColor
import com.easyhz.daypet.design_system.theme.MainBackground
import com.easyhz.daypet.design_system.theme.SubHeading1

interface SegmentedType {
    @get:StringRes
    val labelId: Int
}

/**
 * 세그먼트 버튼
 *
 * [SegmentedType] 인터페이스를 구현한 [Enum] 클래스의 enumValues를 사용하여
 * 세그먼트 버튼을 생성한다. 각 버튼을 클릭하면 해당 enum 값이 onClick 이벤트로 전달된다.
 *
 * @param modifier [Modifier]
 * @param outerPadding 바깥쪽 패딩
 * @param options [SegmentedType]을 상속 받은 [Enum] 클래스의 배열. (`enumValues` 사용)
 * @param onClick 클릭 이벤트.
 */
@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun <T> DayPetSegmentedButton(
    modifier: Modifier = Modifier,
    outerPadding: Int = 20,
    options: Array<T>,
    onClick: (T) -> Unit
) where T: Enum<T>, T: SegmentedType {
    val selectedIndex = remember { mutableIntStateOf(0) }
    val cornerRadius = 6.93
    val itemWidth = ((LocalConfiguration.current.screenWidthDp - (outerPadding * 2) - 4).dp / options.size)
    val selectedOffset by animateDpAsState(targetValue = itemWidth * selectedIndex.intValue,
        label = "selected"
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(8.91.dp))
            .background(ButtonShapeColor)
            .padding(2.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(itemWidth)
                .offset(x = selectedOffset)
                .clip(RoundedCornerShape(cornerRadius.dp))
                .background(MainBackground)
        )
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            options.forEachIndexed { index, item ->
                Box(
                    modifier = modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(cornerRadius.dp))
                        .noRippleClickable {
                            selectedIndex.intValue = index
                            onClick(item)
                        }
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = item.labelId),
                        style = if (selectedIndex.intValue == index) {
                            Body2
                        } else {
                            SubHeading1
                        }
                    )
                }
            }
        }
    }
}