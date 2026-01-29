package com.example.condominio.data.repository

import com.example.condominio.data.model.Payment
import com.example.condominio.data.model.PaymentMethod
import com.example.condominio.data.model.PaymentStatus
import kotlinx.coroutines.delay
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

interface PaymentRepository {
    suspend fun getPayments(): List<Payment>
    suspend fun getPayment(id: String): Payment?
    suspend fun createPayment(
        amount: Double, 
        date: Date, 
        description: String,
        method: PaymentMethod,
        reference: String? = null,
        bank: String? = null,
        phone: String? = null,
        proofUrl: String? = null,
        paidPeriods: List<String> = emptyList()
    ): Result<Payment>
}

@Singleton
class MockPaymentRepository @Inject constructor() : PaymentRepository {
    private val mockPayments = mutableListOf(
        Payment(
            "1", 
            1250.00, 
            Date(), 
            PaymentStatus.VERIFIED, 
            "Monthly Fee - October", 
            PaymentMethod.TRANSFERENCIA,
            reference = "987654",
            bank = "Banesco"
        ),
        Payment(
            "2", 
            1250.00, 
            Date(System.currentTimeMillis() - 86400000L * 30), 
            PaymentStatus.VERIFIED, 
            "Monthly Fee - September", 
            PaymentMethod.PAGO_MOVIL,
            reference = "123456",
            bank = "Mercantil",
            phone = "0414-1234567"
        ),
        Payment(
            "3", 
            500.00, 
            Date(System.currentTimeMillis() - 86400000L * 15), 
            PaymentStatus.PENDING, 
            "Maintenance", 
            PaymentMethod.EFECTIVO,
            proofUrl = "proof_003.jpg"
        )
    )

    override suspend fun getPayments(): List<Payment> {
        delay(500)
        return mockPayments
    }

    override suspend fun getPayment(id: String): Payment? {
        delay(200)
        return mockPayments.find { it.id == id }
    }

    override suspend fun createPayment(
        amount: Double, 
        date: Date, 
        description: String,
        method: PaymentMethod,
        reference: String?,
        bank: String?,
        phone: String?,
        proofUrl: String?,
        paidPeriods: List<String>
    ): Result<Payment> {
        delay(1000)
        val newPayment = Payment(
            id = (mockPayments.size + 1).toString(),
            amount = amount,
            date = date,
            status = PaymentStatus.PENDING,
            description = description,
            method = method,
            reference = reference,
            bank = bank,
            phone = phone,
            proofUrl = proofUrl,
            paidPeriods = paidPeriods
        )
        mockPayments.add(0, newPayment)
        return Result.success(newPayment)
    }
}
