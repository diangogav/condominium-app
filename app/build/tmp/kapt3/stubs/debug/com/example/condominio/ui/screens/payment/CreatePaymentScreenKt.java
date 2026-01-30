package com.example.condominio.ui.screens.payment;

import android.app.DatePickerDialog;
import android.net.Uri;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.layout.ExperimentalLayoutApi;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.KeyboardType;
import com.example.condominio.data.model.PaymentMethod;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import kotlinx.coroutines.Dispatchers;
import com.example.condominio.util.FileUtils;
import java.io.File;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0007"}, d2 = {"CreatePaymentScreen", "", "onBackClick", "Lkotlin/Function0;", "onSubmitSuccess", "viewModel", "Lcom/example/condominio/ui/screens/payment/CreatePaymentViewModel;", "app_debug"})
public final class CreatePaymentScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class, androidx.compose.foundation.layout.ExperimentalLayoutApi.class})
    @androidx.compose.runtime.Composable()
    public static final void CreatePaymentScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBackClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSubmitSuccess, @org.jetbrains.annotations.NotNull()
    com.example.condominio.ui.screens.payment.CreatePaymentViewModel viewModel) {
    }
}