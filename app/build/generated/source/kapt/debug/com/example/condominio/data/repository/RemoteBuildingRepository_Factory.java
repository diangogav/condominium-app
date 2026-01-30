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
public final class RemoteBuildingRepository_Factory implements Factory<RemoteBuildingRepository> {
  private final Provider<ApiService> apiServiceProvider;

  public RemoteBuildingRepository_Factory(Provider<ApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public RemoteBuildingRepository get() {
    return newInstance(apiServiceProvider.get());
  }

  public static RemoteBuildingRepository_Factory create(Provider<ApiService> apiServiceProvider) {
    return new RemoteBuildingRepository_Factory(apiServiceProvider);
  }

  public static RemoteBuildingRepository newInstance(ApiService apiService) {
    return new RemoteBuildingRepository(apiService);
  }
}
