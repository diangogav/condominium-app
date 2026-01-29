package com.example.condominio;

import com.example.condominio.data.local.DatabaseSeeder;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class CondominioApp_MembersInjector implements MembersInjector<CondominioApp> {
  private final Provider<DatabaseSeeder> databaseSeederProvider;

  public CondominioApp_MembersInjector(Provider<DatabaseSeeder> databaseSeederProvider) {
    this.databaseSeederProvider = databaseSeederProvider;
  }

  public static MembersInjector<CondominioApp> create(
      Provider<DatabaseSeeder> databaseSeederProvider) {
    return new CondominioApp_MembersInjector(databaseSeederProvider);
  }

  @Override
  public void injectMembers(CondominioApp instance) {
    injectDatabaseSeeder(instance, databaseSeederProvider.get());
  }

  @InjectedFieldSignature("com.example.condominio.CondominioApp.databaseSeeder")
  public static void injectDatabaseSeeder(CondominioApp instance, DatabaseSeeder databaseSeeder) {
    instance.databaseSeeder = databaseSeeder;
  }
}
