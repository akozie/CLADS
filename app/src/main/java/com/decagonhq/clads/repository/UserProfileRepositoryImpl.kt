package com.decagonhq.clads.repository

import com.decagonhq.clads.data.domain.GenericResponseClass
import com.decagonhq.clads.data.local.UserProfileDao
import com.decagonhq.clads.data.local.UserProfileEntityMapper
import com.decagonhq.clads.data.remote.ApiService
import com.decagonhq.clads.data.remote.profile.UserProfileDTOMapper
import com.decagonhq.clads.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserProfileRepositoryImpl(
    apiService: ApiService,
    userProfileDTOMapper: UserProfileDTOMapper,
    userProfileEntityMapper: UserProfileEntityMapper,
    userProfileDao: UserProfileDao
) : UserProfileRepository {

    override suspend fun getUserProfile(): Flow<Resource<GenericResponseClass>> = flow {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserProfile(): Flow<Resource<GenericResponseClass>> = flow {
        TODO("Not yet implemented")
    }
}
