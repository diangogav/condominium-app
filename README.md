# Condo Payment App (Condominio)

Welcome to the **Condominio** Android application project! This app is designed to streamline condominium payment management, featuring user authentication, payment processing, transaction history, and an insightful dashboard.

## 📱 Features

-   **User Authentication**: Login and Registration with form validation.
-   **Dashboard**: Real-time view of current balance and recent activities.
-   **Make Payment**: Register new payments with amount, description, date, and proof of payment upload.
-   **Payment History**: Scrollable list of all past transactions.
-   **Payment Details**: In-depth view of individual payments with status verification (Verified/Pending).
-   **Modern UI**: Built entirely with **Jetpack Compose** and Material 3, featuring a warm, premium color palette.

## 🛠 Tech Stack

-   **Language**: Kotlin 1.9.22
-   **UI Framework**: Jetpack Compose (BOM 2024.02.01)
-   **Architecture**: MVVM (Model-View-ViewModel) + Clean Architecture principles
-   **Dependency Injection**: Hilt (Dagger)
-   **Navigation**: Jetpack Navigation Compose
-   **State Management**: Kotlin Coroutines & StateFlow
-   **Build System**: Gradle 8.4 (via Wrapper) + Version Catalogs (`libs.versions.toml`)
-   **Java Compatibility**: Java 17

## 📂 Project Structure

```text
app/src/main/java/com/example/condominio/
├── CondominioApp.kt        # Hilt Application Entry Point
├── MainActivity.kt         # Main Activity & Navigation Host
├── data/
│   ├── model/              # Data classes (User, Payment, etc.)
│   └── repository/         # Repository interfaces & Mock implementations
├── di/                     # Dependency Injection Modules
└── ui/
    ├── screens/            # Composable Screens & ViewModels
    │   ├── dashboard/
    │   ├── login/
    │   ├── payment/
    │   └── register/
    └── theme/              # Material 3 Theme & Color Palette
```

## 🚀 Getting Started

### Prerequisites
-   **Android Studio**: Iguana or later recommended.
-   **JDK**: Version 17 or 21 (Project is configured to support JDK 21).

### Setup and Run
1.  **Clone the repository**.
2.  Open the project in **Android Studio**.
3.  Sync Gradle (the project will automatically download Gradle 8.4).
4.  Select the `app` configuration and run on an Emulator or Physical Device.

### Login Credentials (Mock Mode)
The app currently runs with **Mock Data** to facilitate testing without a backend.
-   **Email**: Any valid email (e.g., `admin@condo.com`)
-   **Password**: Any string with **6+ characters**.

## 🔧 Troubleshooting

### Compilation Errors with JDK 21
If you encounter `IllegalAccessError` related to `kapt` or `com.sun.tools.javac`, ensuring the project allows access to internal JDK modules.
This requires specific flags in `gradle.properties`, which have already been configured:
```properties
org.gradle.jvmargs=... --add-opens=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED ...
kotlin.daemon.jvmargs=... --add-opens=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED ...
```
If errors persist, try **File > Invalidate Caches / Restart**.

### Compose Compiler Compatibility
The project uses:
-   Kotlin: `1.9.22`
-   Compose Compiler: `1.5.8`
These versions must match. If you change the Kotlin version, verify the compatible Compose Compiler version [here](https://developer.android.com/jetpack/androidx/releases/compose-kotlin).

## 🤝 Contribution Guidelines

1.  Follow the **MVVM** pattern used in the project.
2.  Create new features in their own package under `ui/screens/`.
3.  Use **Material 3** components and the defined `MaterialTheme.colorScheme` (avoid hardcoded colors).
4.  Update the `libs.versions.toml` for any new dependencies.

## 📄 License
[Proprietary - For Internal Use]
