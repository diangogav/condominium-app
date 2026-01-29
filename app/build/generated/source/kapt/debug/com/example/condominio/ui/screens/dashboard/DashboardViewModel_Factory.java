package com.example.condominio.ui.screens.dashboard;

import com.example.condominio.data.repository.AuthRepository;
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<RoomPaymentRepository> paymentRepositoryProvider;

  public DashboardViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<RoomPaymentRepository> paymentRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.paymentRepositoryProvider = paymentRepositoryProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(authRepositoryProvider.get(), paymentRepositoryProvider.get());
  }

  public static DashboardViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<RoomPaymentRepository> paymentRepositoryProvider) {
    return new DashboardViewModel_Factory(authRepositoryProvider, paymentRepositoryProvider);
  }

  public static DashboardViewModel newInstance(AuthRepository authRepository,
      RoomPaymentRepository paymentRepository) {
    return new DashboardViewModel(authRepository, paymentRepository);
  }
}
