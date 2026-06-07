package com.example.petpal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.petpal.ui.theme.LocalPetPalColors

enum class BadgeType {SUCCESS, PENDING, ERROR, INFO}
@Composable
fun StatusBadge(
    text: String,
    type: BadgeType,
    modifier: Modifier = Modifier
){
    val extraColors = LocalPetPalColors.current

    val backgroundColor = when (type) {
        BadgeType.SUCCESS -> extraColors.statusGreenFill
        BadgeType.PENDING -> extraColors.statusAmberFill
        BadgeType.ERROR -> extraColors.statusRedFill
        BadgeType.INFO -> extraColors.blueFill
    }

    val textColor = when (type) {
        BadgeType.SUCCESS -> extraColors.statusGreenText
        BadgeType.PENDING -> extraColors.statusAmberText
        BadgeType.ERROR -> extraColors.statusRedText
        BadgeType.INFO -> MaterialTheme.colorScheme.primary
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = textColor
        )
    }
}