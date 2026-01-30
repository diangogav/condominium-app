package com.example.condominio.data.repository;

import com.example.condominio.data.local.TokenManager;
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
public final class RemoteAuthRepository_Factory implements Factory<RemoteAuthRepository> {
  private final Provider<ApiService> apiServiceProvider;

  private final Provider<TokenManager> tokenManagerProvider;

  public RemoteAuthRepository_Factory(Provider<ApiService> apiServiceProvider,
      Provider<TokenManager> tokenManagerProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.tokenManagerProvider = tokenManagerProvider;
  }

  @Override
  public RemoteAuthRepository get() {
    return newInstance(apiServiceProvider.get(), tokenManagerProvider.get());
  }

  public static RemoteAuthRepository_Factory create(Provider<ApiService> apiServiceProvider,
      Provider<TokenManager> tokenManagerProvider) {
    return new RemoteAuthRepository_Factory(apiServiceProvider, tokenManagerProvider);
  }

  public static RemoteAuthRepository newInstance(ApiService apiService, TokenManager tokenManager) {
    return new RemoteAuthRepository(apiService, tokenManager);
  }
}
