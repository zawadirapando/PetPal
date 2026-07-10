package com.example.petpal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.petpal.ui.theme.LocalPetPalColors
import com.example.petpal.ui.theme.Typography

@Composable
fun ChatDetailsScreen(
    onNavigateBack: () -> Unit
){
    val extraColors = LocalPetPalColors.current
    val colorScheme = MaterialTheme.colorScheme

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .systemBarsPadding()
            .padding(20.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            //back button
            IconButton(
                onClick = onNavigateBack
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = colorScheme.onBackground
                )
            }

            //avatar
            Box (
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(extraColors.pinkFill),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "SN",
                    style = Typography.titleLarge,
                    color = colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            //name and status
            Column (
                modifier = Modifier.weight(1f),

            ){
                Text(
                    text = "Sharon Njoroge",
                    style = Typography.titleLarge,
                    color = colorScheme.onBackground
                )

                Text(
                    text = "Online",
                    style = Typography.labelMedium,
                    color = colorScheme.primary
                )
            }

            IconButton(
                onClick = { /*TODO: Call action*/ }
            ) {
                Icon(
                    Icons.Outlined.Phone,
                    contentDescription = "Call",
                    tint = colorScheme.onBackground
                )
            }
        }

    }
}