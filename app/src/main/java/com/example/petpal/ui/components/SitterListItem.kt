package com.example.petpal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.petpal.ui.theme.LocalPetPalColors
import com.example.petpal.ui.theme.Typography

@Composable
fun SitterListItem(
    initials: String,
    avatarColor: Color,
    name: String,
    details: String,
    price: String,
    modifier: Modifier = Modifier,
    matchPercentage: String? = null
){
    val extraColors = LocalPetPalColors.current
    val colorScheme = MaterialTheme.colorScheme

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        //Avatar circle
        Box (
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(avatarColor),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = initials,
                style = MaterialTheme.typography.titleLarge,
                color = colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        //name, details and match
        Column (
            modifier =  modifier.weight(1f)
        ){
            Text(
                text = name,
                style = Typography.titleLarge,
                color = colorScheme.onBackground
            )

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = details,
                    style = Typography.labelMedium,
                    color = extraColors.textSecondary
                )

                if (matchPercentage != null){
                    Spacer(modifier = Modifier.width(8.dp))
                    StatusBadge(
                        text = matchPercentage,
                        type = BadgeType.INFO
                    )
                }
            }
        }

        //price
        Text(
            text = price,
            style = Typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = colorScheme.primary
        )
    }
}