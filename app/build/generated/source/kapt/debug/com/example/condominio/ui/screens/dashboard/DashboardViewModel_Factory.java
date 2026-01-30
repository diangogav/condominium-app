package com.example.condominio.ui.screens.dashboard;

import com.example.condominio.data.repository.AuthRepository;
import com.example.condominio.data.repository.DashboardRepository;
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

  private final Provider<DashboardRepository> dashboardRepositoryProvider;

  public DashboardViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<DashboardRepository> dashboardRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.dashboardRepositoryProvider = dashboardRepositoryProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(authRepositoryProvider.get(), dashboardRepositoryProvider.get());
  }

  public static DashboardViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<DashboardRepository> dashboardRepositoryProvider) {
    return new DashboardViewModel_Factory(authRepositoryProvider, dashboardRepositoryProvider);
  }

  public static DashboardViewModel newInstance(AuthRepository authRepository,
      DashboardRepository dashboardRepository) {
    return new DashboardViewModel(authRepository, dashboardRepository);
  }
}
