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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Bedtime
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.petpal.ui.components.BadgeType
import com.example.petpal.ui.components.EmptyBookingCard
import com.example.petpal.ui.components.PetPalHeader
import com.example.petpal.ui.components.ServiceCard
import com.example.petpal.ui.components.SitterListItem
import com.example.petpal.ui.components.TransactionCard
import com.example.petpal.ui.theme.LocalPetPalColors
import com.example.petpal.ui.theme.Typography


@Composable
fun HomeScreen() {
    val extraColors = LocalPetPalColors.current
    val colorScheme = MaterialTheme.colorScheme

    val hasUpcomingBooking = true

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ){
        //Header
        PetPalHeader(
            eyebrow = "Hello,",
            title = "Zawadi",
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = extraColors.textSecondary,
                    modifier = Modifier.size(28.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box (
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(extraColors.blueFill),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "ZR",
                        style = MaterialTheme.typography.titleLarge,
                        color = colorScheme.onSurfaceVariant
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        //Booking display section
        if (hasUpcomingBooking) {
            TransactionCard(
                title = "Upcoming booking",
                subtitle = "Nicole K. • Home sitting",
                details = "17 May • 8 AM – 6 PM • 1.2 km",
                badgeText = "Confirmed",
                badgeType = BadgeType.SUCCESS,
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

        SitterListItem(
            initials = "NM",
            avatarColor = extraColors.blueFill,
            name = "Nduta Maina",
            details = "0.8 km • Dogs",
            price = "1,200",
            matchPercentage = null
        )

        HorizontalDivider(
            color = extraColors.divider,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        SitterListItem(
            initials = "MJ",
            avatarColor = extraColors.pinkFill,
            name = "Michael Jackson",
            details = "1.4 km • All pets",
            price = "500",
            matchPercentage = null
        )
    }
}