package com.kakaohealthcare.compose_ui_practice.data

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    title: String,
) {
  Text(
      text = title,
      modifier = modifier,
      fontStyle = FontStyle.Italic,
      fontSize = 10.sp)
}