package com.fakhry.katakerjaapps.di

import com.fakhry.katakerjaapps.core.domain.usecase.book.BookInteractor
import com.fakhry.katakerjaapps.core.domain.usecase.book.BookUseCase
import com.fakhry.katakerjaapps.core.domain.usecase.user.UserInteractor
import com.fakhry.katakerjaapps.core.domain.usecase.user.UserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideUserUseCase(userInteractor: UserInteractor): UserUseCase

    @Binds
    @Singleton
    abstract fun provideBookUseCase(bookInteractor: BookInteractor): BookUseCase
}