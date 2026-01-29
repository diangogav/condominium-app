package com.example.condominio.data.repository;

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

  public RoomAuthRepository_Factory(Provider<UserDao> userDaoProvider) {
    this.userDaoProvider = userDaoProvider;
  }

  @Override
  public RoomAuthRepository get() {
    return newInstance(userDaoProvider.get());
  }

  public static RoomAuthRepository_Factory create(Provider<UserDao> userDaoProvider) {
    return new RoomAuthRepository_Factory(userDaoProvider);
  }

  public static RoomAuthRepository newInstance(UserDao userDao) {
    return new RoomAuthRepository(userDao);
  }
}
