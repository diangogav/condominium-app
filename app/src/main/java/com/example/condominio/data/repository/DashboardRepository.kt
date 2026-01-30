package com.example.condominio.data.repository

import com.example.condominio.data.model.DashboardSummary
import com.example.condominio.data.model.DashboardSummaryDto
import com.example.condominio.data.model.toDomain
import com.example.condominio.data.remote.ApiService
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

interface DashboardRepository {
    suspend fun getDashboardSummary(): Result<DashboardSummary>
}

@Singleton
class RemoteDashboardRepository @Inject constructor(
    private val apiService: ApiService
) : DashboardRepository {
    
    override suspend fun getDashboardSummary(): Result<DashboardSummary> {
        return try {
            val response = apiService.getDashboardSummary()
            
            if (response.isSuccessful && response.body() != null) {
                val dto = response.body()!!
                
                // Parse last payment date
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
                val lastPaymentDate = try {
                    if (dto.lastPaymentDate != null) {
                        dateFormat.parse(dto.lastPaymentDate)
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    null
                }
                
                val summary = DashboardSummary(
                    solvencyStatus = dto.solvencyStatus,
                    lastPaymentDate = lastPaymentDate,
                    pendingPeriods = dto.pendingPeriods,
                    recentTransactions = dto.recentTransactions.map { it.toDomain() }
                )
                
                Result.success(summary)
            } else {
                Result.failure(Exception("Failed to fetch dashboard summary"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
