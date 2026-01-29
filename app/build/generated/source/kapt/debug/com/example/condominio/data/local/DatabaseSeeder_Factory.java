package com.example.condominio.data.local;

import com.example.condominio.data.local.dao.PaymentDao;
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
public final class DatabaseSeeder_Factory implements Factory<DatabaseSeeder> {
  private final Provider<PaymentDao> paymentDaoProvider;

  public DatabaseSeeder_Factory(Provider<PaymentDao> paymentDaoProvider) {
    this.paymentDaoProvider = paymentDaoProvider;
  }

  @Override
  public DatabaseSeeder get() {
    return newInstance(paymentDaoProvider.get());
  }

  public static DatabaseSeeder_Factory create(Provider<PaymentDao> paymentDaoProvider) {
    return new DatabaseSeeder_Factory(paymentDaoProvider);
  }

  public static DatabaseSeeder newInstance(PaymentDao paymentDao) {
    return new DatabaseSeeder(paymentDao);
  }
}
