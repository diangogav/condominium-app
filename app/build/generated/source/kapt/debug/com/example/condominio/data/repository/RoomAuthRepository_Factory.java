package com.example.condominio.data.repository;

import com.example.condominio.data.local.dao.PaymentDao;
import com.example.condominio.data.local.dao.UserDao;
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
public final class RoomAuthRepository_Factory implements Factory<RoomAuthRepository> {
  private final Provider<UserDao> userDaoProvider;

  private final Provider<PaymentDao> paymentDaoProvider;

  public RoomAuthRepository_Factory(Provider<UserDao> userDaoProvider,
      Provider<PaymentDao> paymentDaoProvider) {
    this.userDaoProvider = userDaoProvider;
    this.paymentDaoProvider = paymentDaoProvider;
  }

  @Override
  public RoomAuthRepository get() {
    return newInstance(userDaoProvider.get(), paymentDaoProvider.get());
  }

  public static RoomAuthRepository_Factory create(Provider<UserDao> userDaoProvider,
      Provider<PaymentDao> paymentDaoProvider) {
    return new RoomAuthRepository_Factory(userDaoProvider, paymentDaoProvider);
  }

  public static RoomAuthRepository newInstance(UserDao userDao, PaymentDao paymentDao) {
    return new RoomAuthRepository(userDao, paymentDao);
  }
}
