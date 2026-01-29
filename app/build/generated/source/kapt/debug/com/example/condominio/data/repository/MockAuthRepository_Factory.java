package com.example.condominio.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class MockAuthRepository_Factory implements Factory<MockAuthRepository> {
  @Override
  public MockAuthRepository get() {
    return newInstance();
  }

  public static MockAuthRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MockAuthRepository newInstance() {
    return new MockAuthRepository();
  }

  private static final class InstanceHolder {
    private static final MockAuthRepository_Factory INSTANCE = new MockAuthRepository_Factory();
  }
}
