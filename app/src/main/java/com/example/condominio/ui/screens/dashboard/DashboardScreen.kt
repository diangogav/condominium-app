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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.condominio.data.model.Payment
import com.example.condominio.data.model.PaymentStatus
import com.example.condominio.data.model.SolvencyStatus
import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun DashboardScreen(
    onPayClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onPaymentClick: (String) -> Unit,
    onProfileClick: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.refresh()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

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
                HeaderSection(
                    userName = uiState.userName,
                    building = uiState.userBuilding,
                    apartmentUnit = uiState.userApartment,
                    onProfileClick = onProfileClick
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Text(
                    text = "Monthly Status (2024)",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                SolvencyFlipCard(
                    solvencyStatus = uiState.solvencyStatus,
                    payments = uiState.recentPayments,
                    pendingPeriods = uiState.pendingPeriods,
                    paidPeriods = uiState.paidPeriods
                )
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
fun HeaderSection(
    userName: String,
    building: String = "",
    apartmentUnit: String = "",
    onProfileClick: () -> Unit = {}
) {
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
            if (building.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$building • Apt $apartmentUnit",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Medium
                )
            }
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
fun SolvencyFlipCard(
    solvencyStatus: SolvencyStatus,
    payments: List<Payment>,
    pendingPeriods: List<String>,
    paidPeriods: List<String>
) {
    var isFlipped by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing),
        label = "flip"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clickable { isFlipped = !isFlipped }
    ) {
        // Back side (shown when flipped)
        if (rotation > 90f) {
            PaymentStatusGrid(
                payments = payments,
                pendingPeriods = pendingPeriods,
                paidPeriods = paidPeriods,
                modifier = Modifier.graphicsLayer {
                    rotationY = 180f + rotation
                    cameraDistance = 12f * density
                }
            )
        } else {
            // Front side (solvency status)
            SolvencyStatusCard(
                solvencyStatus = solvencyStatus,
                modifier = Modifier.graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 12f * density
                }
            )
        }
    }
}

@Composable
fun SolvencyStatusCard(
    solvencyStatus: SolvencyStatus,
    modifier: Modifier = Modifier
) {
    val isUpToDate = solvencyStatus == SolvencyStatus.SOLVENT
    val backgroundColor = if (isUpToDate) {
        Color(0xFF4CAF50).copy(alpha = 0.15f) // Green
    } else {
        Color(0xFFFF6D00).copy(alpha = 0.15f) // Orange
    }
    val accentColor = if (isUpToDate) {
        Color(0xFF4CAF50)
    } else {
        Color(0xFFFF6D00)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp))
            .border(2.dp, accentColor.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = if (isUpToDate) Icons.Default.CheckCircle else Icons.Default.Payment,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (isUpToDate) "¡Estás al día!" else "Pago Pendiente",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = accentColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (isUpToDate) "Todos tus pagos están al corriente" else "Tienes pagos pendientes por realizar",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Tap para ver detalles",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                fontSize = 11.sp
            )
        }
    }
}

@Composable
fun PaymentStatusGrid(
    payments: List<Payment>,
    pendingPeriods: List<String>,
    paidPeriods: List<String>,
    modifier: Modifier = Modifier
) {
    // Use backend-calculated paid periods
    val paidSet = paidPeriods.toSet()

    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonthIndex = calendar.get(Calendar.MONTH) // 0-indexed
    
    // Blinking animation for current pending month
    val infiniteTransition = rememberInfiniteTransition(label = "blinking")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Grid of 2 rows x 6 columns
        val months = (0..11).toList()
        val rows = months.chunked(6) // 6 months per row
        
        rows.forEach { rowMonths ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowMonths.forEach { monthIndex ->
                    val periodId = String.format(Locale.US, "%d-%02d", currentYear, monthIndex + 1)
                    val isPaid = paidSet.contains(periodId) // Use paidPeriods directly
                    val isCurrent = monthIndex == currentMonthIndex
                    val isPast = monthIndex < currentMonthIndex
                    
                    // Use 3-letter abbreviation
                    val monthName = SimpleDateFormat("MMM", Locale.US).format(
                        Calendar.getInstance().apply { set(Calendar.MONTH, monthIndex) }.time
                    ).uppercase()
                    
                    MonthStatusItem(
                        month = monthName,
                        isPaid = isPaid,
                        isCurrent = isCurrent,
                        isPast = isPast,
                        blinkingAlpha = if (isCurrent && !isPaid) alpha else 1f,
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
    isCurrent: Boolean,
    isPast: Boolean,
    blinkingAlpha: Float,
    modifier: Modifier = Modifier
) {
    // Determine color based on status
    val dotColor = when {
        isPaid -> Color(0xFF4CAF50) // Green for paid
        isCurrent -> Color.Yellow // Yellow for current pending
        isPast -> Color.Red // Red for overdue
        else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f) // Grey for future
    }
    
    val borderColor = when {
        isPaid || isCurrent || isPast -> Color.Transparent
        else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Indicator Dot
        Box(
            modifier = Modifier
                .size(12.dp)
                .graphicsLayer { alpha = if (isCurrent && !isPaid) blinkingAlpha else 1f }
                .clip(CircleShape)
                .background(dotColor)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = CircleShape
                )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = month,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp,
                fontWeight = if (isPaid || isCurrent) FontWeight.Bold else FontWeight.Normal,
                letterSpacing = 0.5.sp
            ),
            color = if (isPaid || isCurrent) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
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
        // Profile removed as requested
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
