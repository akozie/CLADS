package com.decagonhq.clads.data.remote

import com.decagonhq.clads.data.domain.GenericResponseClass
import com.decagonhq.clads.data.domain.login.UserRole
import com.decagonhq.clads.data.domain.profile.UserProfile
import com.decagonhq.clads.data.domain.profileimage.UserProfileImage
import com.decagonhq.clads.data.remote.login.LoginCredentialsDTO
import com.decagonhq.clads.data.remote.profile.UserProfileDTO
import com.decagonhq.clads.data.remote.registration.UserRegistrationDTO
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    /*Register User */
    @POST("artisans/register")
    suspend fun registerUser(
        @Body user: UserRegistrationDTO
    ): GenericResponseClass<String>

    /*Email Login*/
    @POST("login")
    suspend fun login(
        @Body loginCredentials: LoginCredentialsDTO
    ): GenericResponseClass<String>

    /*Google Login*/
    @POST("login/google")
    suspend fun googleLogin(
        @Body userRole: UserRole
    ): GenericResponseClass<String>

    /*Upload Profile Picture*/
    @Multipart
    @POST("upload")
    suspend fun uploadImage(@Part image: MultipartBody.Part): GenericResponseClass<UserProfileImage>

    /*Get User Profile*/
    @GET("me/profile")
    suspend fun getUserProfile(): GenericResponseClass<UserProfile>

    /*Update User Profile*/
    @PATCH("me/profile")
    suspend fun updateUserProfile(@Body userProfile: UserProfileDTO): GenericResponseClass<UserProfile>
}
