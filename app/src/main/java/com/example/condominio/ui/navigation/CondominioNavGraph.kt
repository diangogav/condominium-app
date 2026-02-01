package com.example.condominio.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.condominio.ui.screens.login.LoginScreen
import com.example.condominio.ui.screens.register.RegisterScreen
import com.example.condominio.ui.screens.dashboard.DashboardScreen
import com.example.condominio.ui.screens.payment.PaymentHistoryScreen
import com.example.condominio.ui.screens.payment.PaymentDetailScreen
import com.example.condominio.ui.screens.payment.CreatePaymentScreen
import com.example.condominio.ui.screens.profile.ProfileScreen
import com.example.condominio.ui.screens.profile.EditProfileScreen
import com.example.condominio.ui.screens.profile.NotificationSettingsScreen
import com.example.condominio.ui.screens.profile.ChangePasswordScreen
import com.example.condominio.ui.screens.auth.PendingApprovalScreen

@Composable
fun CondominioNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("dashboard") }, 
                onPendingApproval = { navController.navigate("pending_approval") },
                onRegisterClick = { navController.navigate("register") }
            )
        }
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate("pending_approval") {
                    popUpTo("login") { inclusive = true }
                }}, 
                onLoginClick = { navController.popBackStack() }
            )
        }
        composable("pending_approval") {
            PendingApprovalScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        composable("dashboard") {
            DashboardScreen(
                onPayClick = { navController.navigate("create_payment") },
                onHistoryClick = { navController.navigate("payment_history") },
                onPaymentClick = { paymentId -> navController.navigate("payment_detail/$paymentId") },
                onProfileClick = { navController.navigate("profile") }
            )
        }
        composable("payment_history") {
            PaymentHistoryScreen(
                onBackClick = { navController.popBackStack() },
                onPaymentClick = { paymentId -> navController.navigate("payment_detail/$paymentId") }
            )
        }
        composable("create_payment") {
            CreatePaymentScreen(
                onBackClick = { navController.popBackStack() },
                onSubmitSuccess = { navController.popBackStack() }
            )
        }
        composable(
            route = "payment_detail/{paymentId}"
        ) { 
            PaymentDetailScreen(onBackClick = { navController.popBackStack() })
        }
        composable("profile") {
            ProfileScreen(
                onBackClick = { navController.popBackStack() },
                onEditProfileClick = { navController.navigate("edit_profile") },
                onNotificationSettingsClick = { navController.navigate("notification_settings") },
                onChangePasswordClick = { navController.navigate("change_password") },
                onLogoutSuccess = { 
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        composable("edit_profile") {
            EditProfileScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("notification_settings") {
            NotificationSettingsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("change_password") {
            ChangePasswordScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
