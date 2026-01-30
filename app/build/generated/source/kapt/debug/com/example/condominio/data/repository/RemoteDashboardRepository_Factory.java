package com.example.condominio.data.repository;

import com.example.condominio.data.remote.ApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class RemoteDashboardRepository_Factory implements Factory<RemoteDashboardRepository> {
  private final Provider<ApiService> apiServiceProvider;

  public RemoteDashboardRepository_Factory(Provider<ApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public RemoteDashboardRepository get() {
    return newInstance(apiServiceProvider.get());
  }

  public static RemoteDashboardRepository_Factory create(Provider<ApiService> apiServiceProvider) {
    return new RemoteDashboardRepository_Factory(apiServiceProvider);
  }

  public static RemoteDashboardRepository newInstance(ApiService apiService) {
    return new RemoteDashboardRepository(apiService);
  }
}
