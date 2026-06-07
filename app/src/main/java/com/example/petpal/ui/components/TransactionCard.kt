package com.example.petpal.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.petpal.ui.theme.LocalPetPalColors
import com.example.petpal.ui.theme.Typography

@Composable
fun TransactionCard(
    title: String,
    subtitle: String,
    details: String,
    badgeType: BadgeType,
    badgeText: String,
    onChatClick: () -> Unit = {},
    onCancelClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
){
    val extraColors = LocalPetPalColors.current
    val colorScheme = MaterialTheme.colorScheme

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = extraColors.blueFill),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = title,
                    color = colorScheme.primary,
                    style = Typography.titleLarge
                )

                StatusBadge(
                    text = badgeText,
                    type = badgeType
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = subtitle,
                color = colorScheme.onBackground,
                style = Typography.bodyMedium
            )

            Text(
                text = details,
                color = extraColors.textSecondary,
                style = Typography.labelMedium
            )

            if (onCancelClick != null){
                Spacer(modifier = Modifier.height(12.dp))

                Row (
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    Button(
                        onClick = onChatClick,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Chat")
                    }
                    OutlinedButton(
                        onClick = onCancelClick,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}