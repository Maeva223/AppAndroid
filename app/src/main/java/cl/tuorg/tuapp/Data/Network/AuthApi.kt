package cl.tuorg.tuapp.Data.Network

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/register")
    suspend fun register(@Body body: RegisterRequest): RegisterResponse
}
