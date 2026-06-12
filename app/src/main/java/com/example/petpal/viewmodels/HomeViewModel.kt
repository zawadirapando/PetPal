package com.example.petpal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petpal.domain.model.Booking
import com.example.petpal.domain.model.BookingStatus
import com.example.petpal.domain.model.Sitter
import com.example.petpal.repository.AuthRepository
import com.example.petpal.repository.BookingRepository
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

//UI models
data class UserProfile(
    val firstName: String,
    val initials: String
)

data class UpcomingBookingUiModel(
    val id: String,
    val sitterName: String,
    val serviceType: String,
    val dateString: String,
    val statusText: String,
    val badgeType: BadgeType
)

data class SitterUiModel(
    val id: String,
    val name: String,
    val initials: String,
    val details: String,
    val price: String,
    val matchPercentage: String?,
    val isVerified: Boolean
)

data class HomeUiState(
    val isLoading: Boolean = true,
    val user: UserProfile? = null,
    val upcomingBooking: UpcomingBookingUiModel? = null,
    val nearbySitters: List<SitterUiModel> = emptyList(),
    val errorMessage: String? = null
)

//UI mappers from sitter (domain data) to Ui model data
fun Sitter.toUiModel(): SitterUiModel {
    val initials = "${firstName.firstOrNull() ?: ""}${lastName.firstOrNull() ?: ""}".uppercase()
    val matchStr = if (matchScore != null) "$matchScore% match" else null

    return SitterUiModel(
        id = this.id,
        name = "${this.firstName} ${this.lastName}",
        initials = initials,
        details = "${this.distanceKm} km • ${this.acceptedPets}",
        price = this.price,
        matchPercentage = matchStr,
        isVerified = this.isVerified
    )
}

fun Booking.toUpcomingBookingUiModel(sitterName: String): UpcomingBookingUiModel {
    val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
    val timeFormat = SimpleDateFormat("h a", Locale.getDefault())

    return UpcomingBookingUiModel(
        id = this.bookingId,
        sitterName = sitterName,
        serviceType = "Home sitting",
        dateString = "${dateFormat.format(this.startTime)} • ${timeFormat.format(this.startTime)} – ${timeFormat.format(this.endTime)}",
        statusText = this.status.name.lowercase().replaceFirstChar { it.uppercase() },
        badgeType = when(this.status) {
            BookingStatus.CONFIRMED -> BadgeType.SUCCESS
            BookingStatus.PENDING -> BadgeType.PENDING
            BookingStatus.CANCELLED -> BadgeType.ERROR
            else -> BadgeType.INFO
        }
    )
}

//VIEWMODEL
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val bookingRepository: BookingRepository,
    private val sitterRepository: SitterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val currentUserId = "mock_user_id"
            val userProfile = UserProfile("Zawadi", "ZR")

            val sittersResult = sitterRepository.getNearbySitters()
            val bookingsResult = bookingRepository.getUpcomingBookingForUser(currentUserId)

            if (sittersResult.isSuccess && bookingsResult.isSuccess) {
                val sitters = sittersResult.getOrDefault(emptyList()).map { it.toUiModel() }

                val rawBookings = bookingsResult.getOrDefault(emptyList())
                val nextBooking = rawBookings.firstOrNull()?.toUpcomingBookingUiModel("Nicole K.")

                _uiState.value = HomeUiState(
                    isLoading = false,
                    user = userProfile,
                    upcomingBooking = nextBooking,
                    nearbySitters = sitters
                )
            } else {
                _uiState.value = HomeUiState(
                    isLoading = false,
                    errorMessage = "Failed to load data"
                )
            }
        }
    }
}
