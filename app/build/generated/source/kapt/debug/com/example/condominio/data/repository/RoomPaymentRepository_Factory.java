package com.example.condominio.data.repository;

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
public final class RoomPaymentRepository_Factory implements Factory<RoomPaymentRepository> {
  private final Provider<PaymentDao> paymentDaoProvider;

  public RoomPaymentRepository_Factory(Provider<PaymentDao> paymentDaoProvider) {
    this.paymentDaoProvider = paymentDaoProvider;
  }

  @Override
  public RoomPaymentRepository get() {
    return newInstance(paymentDaoProvider.get());
  }

  public static RoomPaymentRepository_Factory create(Provider<PaymentDao> paymentDaoProvider) {
    return new RoomPaymentRepository_Factory(paymentDaoProvider);
  }

  public static RoomPaymentRepository newInstance(PaymentDao paymentDao) {
    return new RoomPaymentRepository(paymentDao);
  }
}
