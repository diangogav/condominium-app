package com.example.condominio.data.local

import com.example.condominio.data.local.dao.PaymentDao
import com.example.condominio.data.local.entity.PaymentEntity
import com.example.condominio.data.model.PaymentMethod
import com.example.condominio.data.model.PaymentStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseSeeder @Inject constructor(
    private val paymentDao: PaymentDao
) {
    fun seedIfEmpty() {
        CoroutineScope(Dispatchers.IO).launch {
            val existingPayments = paymentDao.getPayments().firstOrNull()
            if (existingPayments.isNullOrEmpty()) {
                seedPayments()
            }
        }
    }

    private suspend fun seedPayments() {
        val calendar = Calendar.getInstance()
        
        val samplePayments = listOf(
            PaymentEntity(
                id = "1",
                amount = 150.00,
                date = calendar.apply { add(Calendar.DAY_OF_MONTH, -5) }.timeInMillis,
                status = PaymentStatus.VERIFIED.name,
                description = "Condominium Fee - January 2024",
                method = PaymentMethod.PAGO_MOVIL.name,
                reference = "123456789",
                bank = "Banco Provincial",
                phone = "0414-1234567",
                proofUrl = null
            ),
            PaymentEntity(
                id = "2",
                amount = 150.00,
                date = calendar.apply { add(Calendar.DAY_OF_MONTH, -35) }.timeInMillis,
                status = PaymentStatus.VERIFIED.name,
                description = "Condominium Fee - December 2023",
                method = PaymentMethod.TRANSFERENCIA.name,
                reference = "987654321",
                bank = "Banesco",
                phone = null,
                proofUrl = null
            ),
            PaymentEntity(
                id = "3",
                amount = 150.00,
                date = calendar.apply { add(Calendar.DAY_OF_MONTH, -65) }.timeInMillis,
                status = PaymentStatus.PENDING.name,
                description = "Condominium Fee - November 2023",
                method = PaymentMethod.EFECTIVO.name,
                reference = null,
                bank = null,
                phone = null,
                proofUrl = "receipt_3.jpg"
            )
        )

        samplePayments.forEach { payment ->
            paymentDao.insertPayment(payment)
        }
    }
}
