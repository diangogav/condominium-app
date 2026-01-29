package com.example.condominio.ui.screens.profile;

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
public final class EditProfileViewModel_Factory implements Factory<EditProfileViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public EditProfileViewModel_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public EditProfileViewModel get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static EditProfileViewModel_Factory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new EditProfileViewModel_Factory(authRepositoryProvider);
  }

  public static EditProfileViewModel newInstance(AuthRepository authRepository) {
    return new EditProfileViewModel(authRepository);
  }
}
