package com.example.condominio.ui.screens.payment;

import com.example.condominio.data.repository.PaymentRepository;
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
public final class CreatePaymentViewModel_Factory implements Factory<CreatePaymentViewModel> {
  private final Provider<PaymentRepository> paymentRepositoryProvider;

  public CreatePaymentViewModel_Factory(Provider<PaymentRepository> paymentRepositoryProvider) {
    this.paymentRepositoryProvider = paymentRepositoryProvider;
  }

  @Override
  public CreatePaymentViewModel get() {
    return newInstance(paymentRepositoryProvider.get());
  }

  public static CreatePaymentViewModel_Factory create(
      Provider<PaymentRepository> paymentRepositoryProvider) {
    return new CreatePaymentViewModel_Factory(paymentRepositoryProvider);
  }

  public static CreatePaymentViewModel newInstance(PaymentRepository paymentRepository) {
    return new CreatePaymentViewModel(paymentRepository);
  }
}
