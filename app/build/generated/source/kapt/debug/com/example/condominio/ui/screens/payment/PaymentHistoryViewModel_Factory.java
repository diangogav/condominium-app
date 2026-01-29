package com.example.condominio.ui.screens.payment;

import com.example.condominio.data.repository.RoomPaymentRepository;
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
public final class PaymentHistoryViewModel_Factory implements Factory<PaymentHistoryViewModel> {
  private final Provider<RoomPaymentRepository> paymentRepositoryProvider;

  public PaymentHistoryViewModel_Factory(
      Provider<RoomPaymentRepository> paymentRepositoryProvider) {
    this.paymentRepositoryProvider = paymentRepositoryProvider;
  }

  @Override
  public PaymentHistoryViewModel get() {
    return newInstance(paymentRepositoryProvider.get());
  }

  public static PaymentHistoryViewModel_Factory create(
      Provider<RoomPaymentRepository> paymentRepositoryProvider) {
    return new PaymentHistoryViewModel_Factory(paymentRepositoryProvider);
  }

  public static PaymentHistoryViewModel newInstance(RoomPaymentRepository paymentRepository) {
    return new PaymentHistoryViewModel(paymentRepository);
  }
}
