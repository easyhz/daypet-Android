package com.easyhz.daypet.design_system.component.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.easyhz.daypet.design_system.extension.shadowEffect
import com.easyhz.daypet.design_system.theme.NoRippleTheme
import com.easyhz.daypet.design_system.util.fab.FabButtonItem
import com.easyhz.daypet.design_system.util.fab.FabButtonState
import com.easyhz.daypet.design_system.util.fab.FabOption
import com.easyhz.daypet.design_system.util.fab.rememberMultiFabState

@Composable
fun ExpandedFloatingActionButton(
    modifier: Modifier = Modifier,
    items: List<FabButtonItem>,
    fabState: MutableState<FabButtonState> = rememberMultiFabState(),
    mainMenu: FabButtonItem,
    subMenuOption: FabOption,
    onFabItemClicked: (fabItem: FabButtonItem) -> Unit,
    stateChanged: (fabState: FabButtonState) -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(
            visible = fabState.value.isExpanded(),
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            LazyColumn( // 서브 아이템 부분
                modifier = Modifier.width(100.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items.size) { index ->
                    FabSubItem(
                        item = items[index],
                        fabOption = subMenuOption,
                        onFabItemClicked = onFabItemClicked
                    )
                }
                item {} // 스페이싱
            }
        }
        DayPetFloatingActionButton(
            fabState = fabState,
            imageVector = mainMenu.imageVector,
            label = mainMenu.label,
            fabOption = mainMenu.fapOption,
            iconRotate = mainMenu.iconRotate
        ) {
            fabState.value = fabState.value.toggleValue()
            stateChanged(fabState.value)
        }
    }

}

@Stable
@Composable
private fun FabSubItem(
    item: FabButtonItem,
    fabOption: FabOption,
    onFabItemClicked: (item: FabButtonItem) -> Unit
) {
    DayPetFloatingActionButton(
        imageVector = item.imageVector,
        label = item.label,
        fabOption = fabOption
    ) { onFabItemClicked(item) }
}

@Stable
@Composable
private fun DayPetFloatingActionButton(
    fabState: MutableState<FabButtonState>? = null,
    iconRotate: Float? = null,
    imageVector: ImageVector,
    label: String,
    fabOption: FabOption,
    onClick: () -> Unit,
) {
    val rotation by animateFloatAsState(
        if (fabState?.value == FabButtonState.Expand) {
            iconRotate ?: 0f
        } else {
            0f
        }, label = "rotation"
    )
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme()) {
        FloatingActionButton(
            onClick = { onClick() },
            shape = CircleShape,
            modifier = Modifier
                .size(56.dp)
                .padding(end = 4.dp, bottom = 4.dp)
                .shadowEffect(
                    shadowColor = Color.Black.copy(alpha = 0.1f),
                    shadowBlurRadius = 4.dp,
                    shadowOffsetY = 2.dp
                ),
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                focusedElevation = 0.dp,
                hoveredElevation = 0.dp
            ),

            containerColor = fabOption.backgroundColor,
            contentColor = fabOption.iconColor
        ) {
            Icon(
                modifier = Modifier.size(32.dp).graphicsLayer { rotationZ = rotation },
                imageVector = imageVector,
                contentDescription = label,
                tint = fabOption.iconColor
            )
        }
    }
}
