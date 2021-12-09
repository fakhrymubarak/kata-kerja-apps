package com.fakhry.katakerjaapps.core.data.source.repository

import com.fakhry.katakerjaapps.core.data.source.remote.RemoteUserDataSource
import com.fakhry.katakerjaapps.core.domain.repository.IUserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val remoteUserDataSource: RemoteUserDataSource,
) : IUserRepository {

}