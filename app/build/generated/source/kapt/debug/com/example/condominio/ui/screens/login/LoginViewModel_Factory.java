package com.example.condominio.ui.screens.login;

import com.example.condominio.data.local.AppDatabase;
import com.example.condominio.data.repository.AuthRepository;
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
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<AppDatabase> databaseProvider;

  public LoginViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<AppDatabase> databaseProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public LoginViewModel get() {
    return newInstance(authRepositoryProvider.get(), databaseProvider.get());
  }

  public static LoginViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<AppDatabase> databaseProvider) {
    return new LoginViewModel_Factory(authRepositoryProvider, databaseProvider);
  }

  public static LoginViewModel newInstance(AuthRepository authRepository, AppDatabase database) {
    return new LoginViewModel(authRepository, database);
  }
}
