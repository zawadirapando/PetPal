package com.example.petpal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.petpal.ui.components.PetPalHeader
import com.example.petpal.ui.models.ChatThreadUiModels
import com.example.petpal.ui.theme.LocalPetPalColors
import androidx.compose.ui.unit.dp
import com.example.petpal.ui.components.ChatListItem

@Composable
fun ChatListScreen (
    onNavigateToChatDetail: (String) -> Unit
){
    val colorScheme = MaterialTheme.colorScheme
    val extraColors = LocalPetPalColors.current

    //mock data
    val threads = listOf(
        ChatThreadUiModels(
            "t1",
            "Nicole Korir",
            "NK",
            "Absolutely! How about tomorrow at 3pm instead of 5?",
            "15:45",
            1,
            true
        ),
        ChatThreadUiModels(
            "t2",
            "Nduta Maina",
            "NM",
            "I will be there at 9 AM sharp",
            "Yesterday",
            0,
            false
        ),
        ChatThreadUiModels(
            "t3",
            "Zahra Isiaho",
            "ZI",
            "Thanks for trusting me with Max!",
            "Mon",
            0,
            false
        )
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(20.dp)
            .systemBarsPadding()
    ){
        //Header
        PetPalHeader(
            eyebrow = "My",
            title = "Messages"
        )

        //List
        LazyColumn {
            itemsIndexed(threads) { index, thread ->
                ChatListItem(
                    name = thread.sitterName,
                    initials = thread.initials,
                    lastMessage = thread.lastMessage,
                    time = thread.timestamp,
                    unreadCount = thread.unreadCount,
                    modifier = Modifier.clickable { onNavigateToChatDetail(thread.threadId) }
                )

                if (index < threads.lastIndex) {
                    HorizontalDivider(color =  extraColors.divider, thickness = 1.dp)
                }
            }
        }
    }

}