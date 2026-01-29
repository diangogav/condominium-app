package com.example.condominio.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Support
import androidx.compose.material3.*
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.condominio.data.model.Payment
import com.example.condominio.data.model.PaymentStatus
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun DashboardScreen(
    onPayClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onPaymentClick: (String) -> Unit,
    onProfileClick: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                HeaderSection(userName = uiState.userName, onProfileClick = onProfileClick)
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Text(
                    text = "Monthly Status (2024)",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                PaymentStatusGrid(payments = uiState.recentPayments)
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                QuickActions(
                    onPayClick = onPayClick,
                    onHistoryClick = onHistoryClick
                )
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                Text(
                    text = "Recent Transactions",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            items(uiState.recentPayments) { payment ->
                TransactionItem(payment = payment, onClick = { onPaymentClick(payment.id) })
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun HeaderSection(userName: String, onProfileClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Welcome back,",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Text(
                text = userName,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
        }
        // Avatar - Clickable to open profile
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                .clickable(onClick = onProfileClick),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = userName.take(1).uppercase(),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun PaymentStatusGrid(payments: List<Payment>) {
    // Determine paid months
    // Get all paid periods from all payments that are not REJECTED
    val paidPeriods = payments
        .filter { it.status != PaymentStatus.REJECTED }
        .flatMap { it.paidPeriods }
        .toSet()

    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)) // Darker background
            .padding(16.dp)
    ) {
        // Grid of 2 rows x 6 columns
        val months = (0..11).toList()
        val rows = months.chunked(6) // 6 months per row
        
        rows.forEach { rowMonths ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowMonths.forEach { month ->
                    val periodId = String.format(Locale.US, "%d-%02d", currentYear, month + 1)
                    val isPaid = paidPeriods.contains(periodId)
                    // Use 3-letter abbreviation
                    val monthName = SimpleDateFormat("MMM", Locale.US).format(
                        Calendar.getInstance().apply { set(Calendar.MONTH, month) }.time
                    ).uppercase()
                    
                    MonthStatusItem(
                        month = monthName,
                        isPaid = isPaid,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun MonthStatusItem(
    month: String,
    isPaid: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Minimalist Indicator
        Box(
            modifier = Modifier
                .size(12.dp) // Smaller dot
                .clip(CircleShape)
                .background(
                    if (isPaid) MaterialTheme.colorScheme.primary 
                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f) // Subtle grey for pending
                )
                .border(
                    width = 1.dp,
                    color = if (isPaid) Color.Transparent else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                    shape = CircleShape
                )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = month,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp,
                fontWeight = if (isPaid) FontWeight.Bold else FontWeight.Normal,
                letterSpacing = 0.5.sp
            ),
            color = if (isPaid) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
        )
    }
}

@Composable
fun QuickActions(onPayClick: () -> Unit, onHistoryClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        QuickActionItem(
            icon = Icons.Default.Payment,
            label = "Pay Now",
            color = Color(0xFFFF6D00), // Orange
            onClick = onPayClick,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(12.dp))
        QuickActionItem(
            icon = Icons.Default.History,
            label = "History",
            color = Color(0xFF0091EA), // Blue
            onClick = onHistoryClick,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(12.dp))
        QuickActionItem(
            icon = Icons.Default.Support,
            label = "Support",
            color = Color(0xFF43A047), // Green
            onClick = { },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(12.dp))
        QuickActionItem(
            icon = Icons.Default.Person,
            label = "Profile",
            color = Color(0xFF757575), // Grey
            onClick = { },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun QuickActionItem(
    icon: ImageVector,
    label: String,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(color.copy(alpha = 0.1f), CircleShape)
                .border(1.dp, color.copy(alpha = 0.2f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = color
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun TransactionItem(payment: Payment, onClick: () -> Unit) {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Payment,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = payment.description,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = dateFormat.format(payment.date),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
        Text(
            text = "$${String.format("%.2f", payment.amount)}",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}
