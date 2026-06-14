package com.example.petpal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petpal.domain.model.Booking
import com.example.petpal.domain.model.BookingStatus
import com.example.petpal.repository.BookingRepository
import com.example.petpal.repository.PetRepository
import com.example.petpal.repository.SitterRepository
import com.example.petpal.ui.components.BadgeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

//ui models
data class UpcomingBookingPageUiModel(
    val id: String,
    val title: String,
    val sitterName: String,
    val serviceType: String,
    val petName: String,
    val dateString: String,
    val statusText: String,
    val badgeType: BadgeType,
    val isActive: Boolean
)

data class PastBookingUiModel(
    val id: String,
    val sitterName: String,
    val initials: String,
    val serviceType: String,
    val dateString: String,
    val amountPaid: String,
    val rating: Int
)

data class BookingUiState(
    val isLoading: Boolean = true,
    val upcomingBookings: List<UpcomingBookingPageUiModel> = emptyList(),
    val pastBookings: List<PastBookingUiModel> = emptyList(),
    val errorMessage: String? = null
)

//mapper

fun Booking.toUpcomingUiModel(sitterName: String, petName: String):UpcomingBookingPageUiModel {
    val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
    val isConfirmedOrPending = this.status == BookingStatus.CONFIRMED || this.status == BookingStatus.PENDING

    return UpcomingBookingPageUiModel(
        id = this.bookingId,
        title = if (this.status == BookingStatus.CONFIRMED) "Active booking" else "Upcoming booking",
        sitterName = sitterName,
        serviceType = "Home sitting",
        petName = petName,
        dateString = "${dateFormat.format(this.startTime)} • ${this.totalPrice}",
        statusText = this.status.name.lowercase().replaceFirstChar { it.uppercase() },
        badgeType = when(this.status) {
            BookingStatus.CONFIRMED -> BadgeType.SUCCESS
            BookingStatus.PENDING -> BadgeType.PENDING
            else -> BadgeType.INFO
        },
        isActive = isConfirmedOrPending
    )
}

fun Booking.toPastUiModel(sitterName: String): PastBookingUiModel {
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val initials = sitterName.split(" ").mapNotNull { it.firstOrNull() }.joinToString("").uppercase()

    return PastBookingUiModel(
        id = this.bookingId,
        sitterName = sitterName,
        initials = initials,
        serviceType = "Sitting",
        dateString = dateFormat.format(this.startTime),
        amountPaid = "KES 1,800",
        rating = 5
    )
}

//ViewModel
@HiltViewModel
class BookingsViewModel @Inject constructor(
    private val bookingRepository: BookingRepository,
    private val sitterRepository: SitterRepository,
    private val petRepository: PetRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookingUiState())
    val uiState : StateFlow<BookingUiState> = _uiState.asStateFlow()

    init {
        loadBookings()
    }

    private fun loadBookings() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val currentUserId = "mock_user_id"
            val result = bookingRepository.getUpcomingBookingForUser(currentUserId)

            if (result.isSuccess) {
                val rawBookings = result.getOrDefault(emptyList())

                val upcomingRaw = rawBookings.filter {
                    it.status == BookingStatus.PENDING || it.status == BookingStatus.CONFIRMED
                }

                val pastRaw = rawBookings.filter {
                    it.status == BookingStatus.COMPLETED || it.status == BookingStatus.CANCELLED
                }

                //upcoming
                val upcomingUi = upcomingRaw.map { booking ->
                    val sitterName = sitterRepository.getSitterById(booking.sitterId)
                        .getOrNull()?.let { "${it.firstName} ${it.lastName}" } ?: "Unknown Sitter"

                    val petName = booking.petIds.firstOrNull()?.let { petId ->
                        petRepository.getPetById(petId).getOrNull()?.name
                    } ?: "Your Pet"

                    booking.toUpcomingUiModel(sitterName, petName)
                }

                //past
                val pastUi = pastRaw.map { booking ->
                    val sitterName = sitterRepository.getSitterById(booking.sitterId)
                        .getOrNull()?.let { "${it.firstName} ${it.lastName}" } ?: "Unknown Sitter"

                    booking.toPastUiModel(sitterName)
                }

                _uiState.value = BookingUiState(
                    isLoading = false,
                    upcomingBookings = upcomingUi,
                    pastBookings = pastUi
                )
            } else {
                _uiState.value = BookingUiState(
                    isLoading = false,
                    errorMessage = "Failed to load bookings"
                )
            }
        }
    }

    fun cancelActiveBooking(bookingId: String) {
        viewModelScope.launch {
            val result = bookingRepository.cancelBooking(bookingId)
            if (result.isSuccess) {
                loadBookings()
            }
        }
    }
}