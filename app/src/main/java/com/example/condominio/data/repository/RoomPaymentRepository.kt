package com.example.condominio.data.repository

import com.example.condominio.data.local.dao.PaymentDao
import com.example.condominio.data.local.entity.toEntity
import com.example.condominio.data.local.entity.toDomain
import com.example.condominio.data.model.Payment
import com.example.condominio.data.model.PaymentMethod
import com.example.condominio.data.model.PaymentStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomPaymentRepository @Inject constructor(
    private val paymentDao: PaymentDao
) : PaymentRepository {

    override suspend fun getPayments(): List<Payment> {
        // This method is still needed for compatibility, but we'll use the Flow version
        delay(500)
        // For now, we can't easily convert Flow to List here without collecting
        // We'll keep this as a fallback but the DashboardViewModel should use Flow directly
        throw NotImplementedError("Use getPaymentsFlow() instead")
    }

    fun getPaymentsFlow(): Flow<List<Payment>> {
        return paymentDao.getPayments().map { list -> 
            list.map { it.toDomain() }
        }
    }

    override suspend fun getPayment(id: String): Payment? {
        delay(200)
        return paymentDao.getPayment(id)?.toDomain()
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
            id = System.currentTimeMillis().toString(),
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
        
        paymentDao.insertPayment(newPayment.toEntity())
        return Result.success(newPayment)
    }
}
