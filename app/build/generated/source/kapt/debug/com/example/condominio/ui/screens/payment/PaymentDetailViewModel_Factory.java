package com.example.condominio.ui.screens.payment;

import androidx.lifecycle.SavedStateHandle;
import com.example.condominio.data.repository.PaymentRepository;
import com.example.condominio.data.utils.PdfService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class PaymentDetailViewModel_Factory implements Factory<PaymentDetailViewModel> {
  private final Provider<PaymentRepository> paymentRepositoryProvider;

  private final Provider<PdfService> pdfServiceProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public PaymentDetailViewModel_Factory(Provider<PaymentRepository> paymentRepositoryProvider,
      Provider<PdfService> pdfServiceProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.paymentRepositoryProvider = paymentRepositoryProvider;
    this.pdfServiceProvider = pdfServiceProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public PaymentDetailViewModel get() {
    return newInstance(paymentRepositoryProvider.get(), pdfServiceProvider.get(), savedStateHandleProvider.get());
  }

  public static PaymentDetailViewModel_Factory create(
      Provider<PaymentRepository> paymentRepositoryProvider,
      Provider<PdfService> pdfServiceProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new PaymentDetailViewModel_Factory(paymentRepositoryProvider, pdfServiceProvider, savedStateHandleProvider);
  }

  public static PaymentDetailViewModel newInstance(PaymentRepository paymentRepository,
      PdfService pdfService, SavedStateHandle savedStateHandle) {
    return new PaymentDetailViewModel(paymentRepository, pdfService, savedStateHandle);
  }
}
