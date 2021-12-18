package com.fakhry.katakerjaapps.core.helper

import com.fakhry.katakerjaapps.core.data.source.remote.response.login.LoginData
import com.fakhry.katakerjaapps.core.data.source.remote.response.register.RegisterData
import com.fakhry.katakerjaapps.core.data.source.remote.response.user.detail.UserDetailsData
import com.fakhry.katakerjaapps.core.data.source.remote.response.user.update.UserUpdateData
import com.fakhry.katakerjaapps.core.domain.model.Login as LoginDomain
import com.fakhry.katakerjaapps.core.domain.model.Register as RegisterDomain
import com.fakhry.katakerjaapps.core.domain.model.User as UserDomain


object UserDataMapper {
    object User {
        fun mapResponseToDomain(userDetailData: UserDetailsData): UserDomain =
            UserDomain(
                idUser = userDetailData.id,
                email = userDetailData.email,
                idRole = userDetailData.idRole,
                name = userDetailData.name,
                avatar = userDetailData.avatar ?: "",
                fullAddress = "",
                bornDate = userDetailData.tglLahir,
                phoneNumber = userDetailData.telp,
                memberSince = userDetailData.memberSejak,
                staffSince = userDetailData.staffSejak,
            )

        fun mapResponseToDomain(userDetailData: UserUpdateData): UserDomain =
            UserDomain(
                idUser = userDetailData.id,
                email = userDetailData.email,
                idRole = userDetailData.idRole.toInt(),
                name = userDetailData.name,
                avatar = userDetailData.avatar ?: "",
                fullAddress = "",
                bornDate = userDetailData.tglLahir,
                phoneNumber = userDetailData.telp,
                memberSince = userDetailData.memberSejak,
                staffSince = userDetailData.staffSejak,
            )
    }

    object Login {
        fun mapResponseToDomain(loginData: LoginData): LoginDomain =
            LoginDomain(
                token = loginData.token,
                id = loginData.id,
                name = loginData.name,
                idRole = loginData.idRole,
            )
    }

    object Register {
        fun mapResponseToDomain(registerData: RegisterData): RegisterDomain =
            RegisterDomain(
                token = registerData.token,
                id = registerData.id,
                name = registerData.name,
                idRole = registerData.idRole,
            )
    }
}