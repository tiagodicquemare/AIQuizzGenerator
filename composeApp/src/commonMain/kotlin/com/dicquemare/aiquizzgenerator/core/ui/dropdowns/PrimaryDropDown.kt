package com.dicquemare.aiquizzgenerator.core.ui.dropdowns

import aiquizzgenerator.composeapp.generated.resources.Res
import aiquizzgenerator.composeapp.generated.resources.ic_arrow_down
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import org.jetbrains.compose.resources.painterResource


@Composable
fun <T> PrimaryDropDown(
    hint: String? = null,
    items: Map<String, T>,
    itemSelectedName: String,
    onItemSelected: (T) -> Unit,
) {

    val focused = itemSelectedName.isNotEmpty()
    val dropDownExpanded = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                dropDownExpanded.value = true
            }
            .background(if (focused) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onBackground)
            .padding(horizontal = 16.dp)
            .height(56.dp),
    ) {
        ChooseCheckoutDropDown(
            expanded = dropDownExpanded.value,
            items = items,
            onExpandedChange = {
                dropDownExpanded.value = it
            },
            onItemSelected = {
                onItemSelected(it)
            })
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            AnimatedContent(
                targetState = focused,
                transitionSpec = {
                    fadeIn() + slideInVertically(animationSpec = tween(400),
                        initialOffsetY = { fullHeight -> fullHeight / 2 - 12 }) togetherWith
                            fadeOut(animationSpec = tween(10))
                },
                modifier = Modifier
                    .fillMaxHeight(),
                contentAlignment = Alignment.CenterStart, label = ""
            ) { focused ->
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = if (focused) Arrangement.Top else Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    if (focused) {
                        Text(
                            text = hint ?: "",
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp, color = MaterialTheme.colorScheme.secondary),
                            modifier = Modifier
                                .padding(start = 4.dp, top = 4.dp),
                        )
                    } else {
                        Text(
                            text = hint ?: "",
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                            modifier = Modifier
                                .padding(start = 4.dp),
                        )
                    }
                }
            }
            Text(
                text = itemSelectedName,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                modifier = Modifier
                    .padding(
                        start = 4.dp,
                        end = 4.dp,
                        top = if (focused) 0.dp else 0.dp
                    ),
            )
        }
        Image(
            painter = painterResource(Res.drawable.ic_arrow_down),
            contentDescription = "Chevron image",
            modifier = Modifier
                .height(48.dp)
                .align(alignment = Alignment.CenterEnd),
            contentScale = ContentScale.FillWidth,
            colorFilter = ColorFilter.tint(
                MaterialTheme.colorScheme.onSurface
            )
        )
    }
}

@Composable
fun <T> ChooseCheckoutDropDown(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    items: Map<String, T>,
    onItemSelected: (T) -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onExpandedChange(false) },
        properties = PopupProperties(
            dismissOnClickOutside = true,
            focusable = true
        ),
        modifier = Modifier.wrapContentSize()
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                text = {
                    Text(
                        item.key,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                    )
                },
                onClick = {
                    onExpandedChange(false)
                    onItemSelected(item.value)
                }
            )
        }
    }
}
