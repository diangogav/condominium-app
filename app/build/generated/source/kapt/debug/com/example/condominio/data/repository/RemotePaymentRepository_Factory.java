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
public final class RemotePaymentRepository_Factory implements Factory<RemotePaymentRepository> {
  private final Provider<ApiService> apiServiceProvider;

  public RemotePaymentRepository_Factory(Provider<ApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public RemotePaymentRepository get() {
    return newInstance(apiServiceProvider.get());
  }

  public static RemotePaymentRepository_Factory create(Provider<ApiService> apiServiceProvider) {
    return new RemotePaymentRepository_Factory(apiServiceProvider);
  }

  public static RemotePaymentRepository newInstance(ApiService apiService) {
    return new RemotePaymentRepository(apiService);
  }
}
