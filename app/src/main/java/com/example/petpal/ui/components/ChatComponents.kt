package com.example.petpal.ui.components

import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.petpal.ui.models.MessageType
import com.example.petpal.ui.theme.LocalPetPalColors
import com.example.petpal.ui.theme.Typography
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun ChatListItem(
    name: String,
    initials: String,
    lastMessage: String,
    time: String,
    unreadCount: Int,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme
    val extraColors = LocalPetPalColors.current

    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        //Circular avatar
        Box (
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(extraColors.pinkFill),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = initials,
                style = Typography.titleLarge,
                color = colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        //name and message
        Column (
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = name,
                style = Typography.titleLarge,
                color = colorScheme.onBackground
            )

            Text(
                text = lastMessage,
                style = Typography.bodyMedium,
                color = extraColors.textSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        //timestamp and unread
        Column (
            horizontalAlignment = Alignment.End
        ){
            Text(
                text = time,
                style = Typography.labelMedium,
                color = extraColors.textSecondary
            )

            if (unreadCount>0){
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = unreadCount.toString(),
                        style = Typography.labelSmall,
                        color = colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun ChatBubble(
    message: String,
    time: String,
    type: MessageType
){
    val colorScheme = MaterialTheme.colorScheme
    val extraColors = LocalPetPalColors.current
    val isSender = type == MessageType.SENDER

    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isSender) Arrangement.End else Arrangement.Start
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (isSender) 16.dp else 4.dp,
                        bottomEnd = if (isSender) 4.dp else 16.dp
                    )
                )
                .background(if (isSender) colorScheme.primary else colorScheme.surfaceVariant)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ){
            Text(
                text = message,
                style = Typography.bodyLarge,
                color = if (isSender) colorScheme.onPrimary else colorScheme.onBackground
            )
            Text(
                text = time,
                style = Typography.labelSmall,
                color = if (isSender) colorScheme.onPrimary.copy(alpha = 0.7f) else extraColors.textSecondary,
                modifier = Modifier.align(Alignment.End).padding(top = 4.dp)
            )
        }
    }
}
