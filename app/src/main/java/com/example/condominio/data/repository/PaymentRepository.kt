package com.example.condominio.data.repository

import com.example.condominio.data.model.Payment
import com.example.condominio.data.model.PaymentMethod
import com.example.condominio.data.model.PaymentStatus
import com.example.condominio.data.model.PaymentSummary
import com.example.condominio.data.model.SolvencyStatus
import kotlinx.coroutines.delay
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

interface PaymentRepository {
    suspend fun getPayments(): List<Payment>
    suspend fun getPayment(id: String): Payment?
// New method
    suspend fun getPaymentSummary(): Result<PaymentSummary>

    suspend fun createPayment(
        amount: Double, 
        date: Date, 
        description: String,
        method: PaymentMethod,
        reference: String? = null,
        bank: String? = null,
        phone: String? = null,
        proofUrl: String? = null,
        paidPeriods: List<String> = emptyList(),
        buildingId: String? = null // New parameter
    ): Result<Payment>
}


