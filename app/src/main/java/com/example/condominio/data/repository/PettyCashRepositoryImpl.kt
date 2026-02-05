package com.example.condominio.data.repository

import com.example.condominio.data.model.*
import com.example.condominio.data.remote.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PettyCashRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PettyCashRepository {

    override suspend fun getBalance(buildingId: String): Result<PettyCashBalanceDto> {
        return try {
            val response = apiService.getPettyCashBalance(buildingId)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed to get balance"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getHistory(
        buildingId: String,
        type: PettyCashTransactionType?,
        category: PettyCashCategory?,
        page: Int,
        limit: Int
    ): Result<List<PettyCashTransactionDto>> {
        return try {
            val response = apiService.getPettyCashHistory(
                buildingId = buildingId,
                type = type?.name,
                category = category?.name,
                page = page,
                limit = limit
            )
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed to get history"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun registerIncome(
        buildingId: String,
        amount: Double,
        description: String
    ): Result<PettyCashTransactionDto> {
        return try {
            val request = RegisterIncomeRequest(buildingId, amount, description)
            val response = apiService.registerPettyCashIncome(request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed to register income"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun registerExpense(
        buildingId: String,
        amount: Double,
        description: String,
        category: PettyCashCategory,
        evidencePath: String?
    ): Result<PettyCashTransactionDto> {
        return try {
            val buildingIdBody = buildingId.toRequestBody("text/plain".toMediaTypeOrNull())
            val amountBody = amount.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val descriptionBody = description.toRequestBody("text/plain".toMediaTypeOrNull())
            val categoryBody = category.name.toRequestBody("text/plain".toMediaTypeOrNull())

            val evidencePart = evidencePath?.let {
                val file = File(it)
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("evidence_image", file.name, requestFile)
            }

            val response = apiService.registerPettyCashExpense(
                buildingIdBody,
                amountBody,
                descriptionBody,
                categoryBody,
                evidencePart
            )

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed to register expense"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
