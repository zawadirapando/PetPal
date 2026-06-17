package com.example.petpal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.petpal.ui.components.PetPalHeader
import com.example.petpal.ui.theme.LocalPetPalColors
import com.example.petpal.ui.theme.Typography
import com.example.petpal.viewmodels.BookingsViewModel
import androidx.compose.material3.Tab
import com.example.petpal.ui.components.EmptyBookingCard
import com.example.petpal.viewmodels.PastBookingUiModel
import com.example.petpal.viewmodels.UpcomingBookingPageUiModel
import kotlinx.coroutines.launch
import com.example.petpal.ui.components.TransactionCard
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import com.example.petpal.ui.components.SitterListItem

@Composable
fun BookingsScreen(
    viewModel: BookingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val colorScheme = MaterialTheme.colorScheme
    val extraColors = LocalPetPalColors.current

    //tab config
    val tabs = listOf("Upcoming", "Past")
    val pagerState = rememberPagerState(pageCount = {tabs.size})
    val coroutineScope = rememberCoroutineScope()

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
                text = uiState.errorMessage ?: "Failed to load bookings",
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
    ){
        //header
        PetPalHeader(
            eyebrow = "Overview",
            title = "My Bookings"
        )

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = colorScheme.background,
            contentColor = colorScheme.primary,
            indicator = {tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = colorScheme.primary
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = title,
                            style = Typography.titleMedium,
                            color = if (pagerState.currentPage == index) colorScheme.primary else extraColors.textSecondary
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            page ->
            when (page) {
                0 -> UpcomingTabContent(
                    bookings = uiState.upcomingBookings,
                    onCancelClick = {viewModel.cancelActiveBooking(it)}
                )
                1 -> PastTabContent(
                    bookings = uiState.pastBookings
                )
            }
        }
    }
}



@Composable
fun UpcomingTabContent(
    bookings: List<UpcomingBookingPageUiModel>,
    onCancelClick: (String) -> Unit
) {
    val extraColors = LocalPetPalColors.current

    if (bookings.isEmpty()) {
        EmptyBookingCard()
    } else {
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(bookings) { booking ->
                TransactionCard(
                    title = booking.title,
                    subtitle = "${booking.sitterName} • ${booking.serviceType}",
                    details = "${booking.petName} • ${booking.dateString}",
                    badgeType = booking.badgeType,
                    badgeText = booking.statusText,
                    onCancelClick = if (booking.isActive) { { onCancelClick(booking.id) } } else null
                )
            }
        }
    }
}

@Composable
fun PastTabContent(
    bookings: List<PastBookingUiModel>
) {
    val extraColors = LocalPetPalColors.current

    if (bookings.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No historical booking information available.", color = extraColors.textSecondary)
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical  = 20.dp)
        ) {
            itemsIndexed(bookings) { index, booking ->
                SitterListItem(
                    initials = booking.initials,
                    avatarColor = extraColors.blueFill, // Historical avatars default to verified blue color for now
                    name = booking.sitterName,
                    details = "${booking.serviceType} • ${booking.dateString}",
                    price = booking.amountPaid,
                    rating = booking.rating
                )

                if (index < bookings.lastIndex) {
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
