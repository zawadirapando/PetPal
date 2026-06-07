package com.example.petpal.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.petpal.ui.theme.LocalPetPalColors
import com.example.petpal.ui.theme.Typography

@Composable
fun EmptyBookingCard(
    modifier: Modifier = Modifier
){
    val extraColors = LocalPetPalColors.current
    val colorScheme = MaterialTheme.colorScheme

  Card (
      modifier = modifier.fillMaxWidth(),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = colorScheme.surfaceVariant),
      elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
  ){
     Column(
         modifier = Modifier
             .fillMaxWidth()
             .padding(vertical = 24.dp, horizontal = 16.dp),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center
     ) {
         Icon(
             imageVector = Icons.Outlined.CalendarToday,
             contentDescription = "No bookings",
             tint = Color.Gray,
             modifier = Modifier.size(32.dp)
         )

         Spacer(modifier = Modifier.height(12.dp))

         Text(
             text = "No upcoming bookings",
             style = Typography.titleMedium,
             color = colorScheme.onSurface
         )

         Spacer(modifier = Modifier.height(4.dp))

         Text(
             text = "Your schedule is clear. Find a sitter today!",
             style = Typography.bodySmall,
             color = Color.Gray
         )
     }
  }
}