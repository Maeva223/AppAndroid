package cl.tuorg.tuapp.Data.Repository

import cl.tuorg.tuapp.Data.Network.AuthApi
import cl.tuorg.tuapp.Data.Network.RegisterRequest

class AuthRepository(private val api: AuthApi) {
    suspend fun register(name: String, email: String, pass: String): Result<String> {
        return try {
            val body = RegisterRequest(name, email, pass)
            val response = api.register(body)
            if (!response.success) {
                return Result.failure(Exception(response.message))
            }
            Result.success(response.message)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
