package com.example.condominio.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.condominio.data.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val apartmentUnit: String,
    val building: String = ""
)

fun UserEntity.toDomain() = User(
    id = id,
    name = name,
    email = email,
    apartmentUnit = apartmentUnit,
    building = building
)

fun User.toEntity() = UserEntity(
    id = id,
    name = name,
    email = email,
    apartmentUnit = apartmentUnit,
    building = building
)
