package com.example.condominio.ui.screens.dashboard;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.text.font.FontWeight;
import com.example.condominio.data.model.Payment;
import com.example.condominio.data.model.PaymentStatus;
import com.example.condominio.data.model.SolvencyStatus;
import androidx.compose.animation.core.*;
import androidx.compose.runtime.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aP\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a4\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a:\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007\u001a \u0010\u0019\u001a\u00020\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007\u001aB\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b$\u0010%\u001a$\u0010&\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a\u001e\u0010\'\u001a\u00020\u00012\u0006\u0010(\u001a\u00020)2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0007\u001a\u001a\u0010*\u001a\u00020\u00012\u0006\u0010(\u001a\u00020)2\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007\u001a\u001e\u0010+\u001a\u00020\u00012\u0006\u0010,\u001a\u00020\u001c2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006-"}, d2 = {"DashboardScreen", "", "onPayClick", "Lkotlin/Function0;", "onHistoryClick", "onPaymentClick", "Lkotlin/Function1;", "", "onProfileClick", "viewModel", "Lcom/example/condominio/ui/screens/dashboard/DashboardViewModel;", "HeaderSection", "userName", "building", "apartmentUnit", "MonthStatusItem", "month", "isPaid", "", "isCurrent", "isPast", "blinkingAlpha", "", "modifier", "Landroidx/compose/ui/Modifier;", "PaymentStatusGrid", "payments", "", "Lcom/example/condominio/data/model/Payment;", "QuickActionItem", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "color", "Landroidx/compose/ui/graphics/Color;", "onClick", "QuickActionItem-XO-JAsU", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;JLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;)V", "QuickActions", "SolvencyFlipCard", "solvencyStatus", "Lcom/example/condominio/data/model/SolvencyStatus;", "SolvencyStatusCard", "TransactionItem", "payment", "app_debug"})
public final class DashboardScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPayClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onHistoryClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onPaymentClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onProfileClick, @org.jetbrains.annotations.NotNull()
    com.example.condominio.ui.screens.dashboard.DashboardViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void HeaderSection(@org.jetbrains.annotations.NotNull()
    java.lang.String userName, @org.jetbrains.annotations.NotNull()
    java.lang.String building, @org.jetbrains.annotations.NotNull()
    java.lang.String apartmentUnit, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onProfileClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SolvencyFlipCard(@org.jetbrains.annotations.NotNull()
    com.example.condominio.data.model.SolvencyStatus solvencyStatus, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.condominio.data.model.Payment> payments) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SolvencyStatusCard(@org.jetbrains.annotations.NotNull()
    com.example.condominio.data.model.SolvencyStatus solvencyStatus, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PaymentStatusGrid(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.condominio.data.model.Payment> payments, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void MonthStatusItem(@org.jetbrains.annotations.NotNull()
    java.lang.String month, boolean isPaid, boolean isCurrent, boolean isPast, float blinkingAlpha, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuickActions(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPayClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onHistoryClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TransactionItem(@org.jetbrains.annotations.NotNull()
    com.example.condominio.data.model.Payment payment, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}