package com.fakhry.katakerjaapps.core.di

import com.fakhry.katakerjaapps.core.data.source.repository.BookRepository
import com.fakhry.katakerjaapps.core.data.source.repository.UserRepository
import com.fakhry.katakerjaapps.core.domain.repository.IBookRepository
import com.fakhry.katakerjaapps.core.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideUserRepository(userRepository: UserRepository): IUserRepository

    @Binds
    abstract fun provideBookRepository(bookRepository: BookRepository): IBookRepository
}