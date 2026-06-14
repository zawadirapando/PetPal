package com.example.petpal.di

import com.example.petpal.repository.AuthRepository
import com.example.petpal.repository.AuthRepositoryImpl
import com.example.petpal.repository.BookingRepository
import com.example.petpal.repository.BookingRepositoryImpl
import com.example.petpal.repository.PetRepository
import com.example.petpal.repository.PetRepositoryImpl
import com.example.petpal.repository.SitterRepository
import com.example.petpal.repository.SitterRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(auth)
    }

    @Provides
    @Singleton
    fun provideSitterRepository(firestore: FirebaseFirestore): SitterRepository{
        return SitterRepositoryImpl(firestore)
    }

    @Provides
    @Singleton
    fun provideBookingRepository(firestore: FirebaseFirestore): BookingRepository {
        return BookingRepositoryImpl(firestore)
    }

    @Provides
    @Singleton
    fun providePetRepository(firestore: FirebaseFirestore): PetRepository {
        return PetRepositoryImpl(firestore)
    }
}