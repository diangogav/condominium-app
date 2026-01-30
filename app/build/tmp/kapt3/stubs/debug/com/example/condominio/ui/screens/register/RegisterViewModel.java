package com.example.condominio.ui.screens.register;

import androidx.lifecycle.ViewModel;
import com.example.condominio.data.model.Building;
import com.example.condominio.data.repository.AuthRepository;
import com.example.condominio.data.repository.BuildingRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0012J\u000e\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0012J\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0012J\u0006\u0010\u0019\u001a\u00020\u000fJ\u000e\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u0012R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u001c"}, d2 = {"Lcom/example/condominio/ui/screens/register/RegisterViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/example/condominio/data/repository/AuthRepository;", "buildingRepository", "Lcom/example/condominio/data/repository/BuildingRepository;", "(Lcom/example/condominio/data/repository/AuthRepository;Lcom/example/condominio/data/repository/BuildingRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/condominio/ui/screens/register/RegisterUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadBuildings", "", "onBuildingChange", "buildingId", "", "onEmailChange", "email", "onNameChange", "name", "onPasswordChange", "password", "onRegisterClick", "onUnitChange", "unit", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class RegisterViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.condominio.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.condominio.data.repository.BuildingRepository buildingRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.condominio.ui.screens.register.RegisterUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.condominio.ui.screens.register.RegisterUiState> uiState = null;
    
    @javax.inject.Inject()
    public RegisterViewModel(@org.jetbrains.annotations.NotNull()
    com.example.condominio.data.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.example.condominio.data.repository.BuildingRepository buildingRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.condominio.ui.screens.register.RegisterUiState> getUiState() {
        return null;
    }
    
    private final void loadBuildings() {
    }
    
    public final void onNameChange(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void onEmailChange(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
    }
    
    public final void onUnitChange(@org.jetbrains.annotations.NotNull()
    java.lang.String unit) {
    }
    
    public final void onBuildingChange(@org.jetbrains.annotations.NotNull()
    java.lang.String buildingId) {
    }
    
    public final void onPasswordChange(@org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    public final void onRegisterClick() {
    }
}