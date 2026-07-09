package com.example.petpal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Bedtime
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.petpal.ui.components.BadgeType
import com.example.petpal.ui.components.EmptyBookingCard
import com.example.petpal.ui.components.PetPalHeader
import com.example.petpal.ui.components.ServiceCard
import com.example.petpal.ui.components.SitterListItem
import com.example.petpal.ui.components.TransactionCard
import com.example.petpal.ui.theme.LocalPetPalColors
import com.example.petpal.ui.theme.Typography
import com.example.petpal.viewmodels.HomeViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToChats: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val extraColors = LocalPetPalColors.current
    val colorScheme = MaterialTheme.colorScheme

    //spinner
    if (uiState.isLoading) {
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator(color = colorScheme.primary)
        }
        return
    }

    //error state
    if (uiState.errorMessage != null) {
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = uiState.errorMessage ?: "Unknown error occurred",
                color = colorScheme.error,
                style = Typography.bodyLarge
            )
        }
        return
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ){
        //Header
        uiState.user?.let { user ->
            PetPalHeader(
                eyebrow = "Hello,",
                title = user.firstName,
                trailingContent = {
                    IconButton(onClick = onNavigateToChats) {
                        Icon(
                            imageVector = Icons.Default.MailOutline,
                            contentDescription = "Messages",
                            tint = extraColors.textSecondary,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box (
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(extraColors.blueFill),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = user.initials,
                            style = MaterialTheme.typography.titleLarge,
                            color = colorScheme.onSurfaceVariant
                        )
                    }
                }
            )
        } ?: run {
            Spacer(modifier = Modifier.height(48.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        //Booking display section
        if (uiState.upcomingBooking != null) {
            val booking = uiState.upcomingBooking!!
            TransactionCard(
                title = "Upcoming booking",
                subtitle = "${booking.sitterName}• ${booking.serviceType}",
                details = booking.dateString,
                badgeText = booking.statusText,
                badgeType = booking.badgeType,
                onCancelClick = null
            )
        }else {
            EmptyBookingCard()
        }

        Spacer(modifier = Modifier.height(24.dp))

        //Services grid
        Text(
            text = "Services",
            style = Typography.titleLarge,
            color = colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column (
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){
                ServiceCard(
                    icon = Icons.Outlined.Home,
                    title = "Home Sitting",
                    subtitle = "from KES 1,200",
                    modifier = Modifier.weight(1f)
                )
                ServiceCard(
                    icon = Icons.Outlined.Pets,
                    title = "Dog walking",
                    subtitle = "from KES 500/hr",
                    modifier = Modifier.weight(1f)
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){
                ServiceCard(
                    icon = Icons.Outlined.Bedtime,
                    title = "Overnight",
                    subtitle = "from KES 1,800",
                    modifier = Modifier.weight(1f)
                )
                ServiceCard(
                    icon = Icons.Outlined.WarningAmber,
                    title = "Rescue",
                    subtitle = "Stray animals",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        //Nearby sitters
        Text(
            text = "Nearby sitters",
            style = Typography.titleLarge,
            color = colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (uiState.nearbySitters.isEmpty()) {
            Text(
                text = "No sitters found nearby.",
                style = MaterialTheme.typography.bodyMedium,
                color = extraColors.textSecondary
            )
        } else {
            uiState.nearbySitters.forEachIndexed{ index, sitter ->
                SitterListItem(
                    initials = sitter.initials,
                    avatarColor = if (sitter.verified) extraColors.blueFill else extraColors.pinkFill,
                    name = sitter.name,
                    details = sitter.details,
                    price = sitter.price,
                    matchPercentage = sitter.matchPercentage
                )

                if (index < uiState.nearbySitters.lastIndex) {
                    HorizontalDivider(
                        color = extraColors.divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}