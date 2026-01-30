package com.example.condominio.ui.screens.register;

import com.example.condominio.data.repository.AuthRepository;
import com.example.condominio.data.repository.BuildingRepository;
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
public final class RegisterViewModel_Factory implements Factory<RegisterViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<BuildingRepository> buildingRepositoryProvider;

  public RegisterViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<BuildingRepository> buildingRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.buildingRepositoryProvider = buildingRepositoryProvider;
  }

  @Override
  public RegisterViewModel get() {
    return newInstance(authRepositoryProvider.get(), buildingRepositoryProvider.get());
  }

  public static RegisterViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<BuildingRepository> buildingRepositoryProvider) {
    return new RegisterViewModel_Factory(authRepositoryProvider, buildingRepositoryProvider);
  }

  public static RegisterViewModel newInstance(AuthRepository authRepository,
      BuildingRepository buildingRepository) {
    return new RegisterViewModel(authRepository, buildingRepository);
  }
}
