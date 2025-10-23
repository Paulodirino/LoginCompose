# Firebase Authentication App (Android)

## Summary

This is an Android project developed in Android Studio that implements a complete user authentication system. The main goal is to demonstrate a modern Android architecture using Firebase Authentication for the backend, Jetpack Compose for the UI, and a ViewModel to handle the logic and state.

## Implemented Features

*   **User Registration & Login:** Allows users to sign up and sign in using an email and password.
*   **Session Persistence:** The application remembers the user's login state. On restarting the app, if the user is already logged in, they are taken directly to the home screen.
*   **State Management:** A `ViewModel` is used to manage the authentication state, handle user interactions, and communicate with Firebase.
*   **Reactive UI:** The UI, built with Jetpack Compose, reacts to state changes from the ViewModel. For example, it automatically navigates the user upon login/logout.
*   **Navigation:** Uses `NavHost` from Navigation-Compose to manage transitions between the login and home screens.
*   **Real-time Error Handling:** Firebase authentication errors (e.g., "Invalid email or password") are displayed directly on the login screen to provide clear feedback to the user.
*   **Modern, Declarative UI:** The entire user interface is built with Jetpack Compose, without using any XML layouts.

## Code Structure

*   **`MainActivity.kt`**: The main Activity that hosts the Jetpack Compose content. It is responsible for:
    *   Setting up the UI theme.
    *   Initializing the `AuthViewModel`.
    *   Hosting the `AppNavigator` composable, which controls the screen flow.
*   **Composable Functions in `MainActivity.kt`**:
    *   `AppNavigator`: Manages the navigation graph, switching between `LoginScreen` and `HomeScreen` based on the user's authentication state.
    *   `LoginScreen` & `LoginScreenContent`: Define the UI for the login and registration page, including text fields and buttons.
    *   `HomeScreen`: Defines the simple welcome screen displayed after a successful login.
*   **`AuthViewModel.kt`**: The ViewModel class that contains all the authentication logic.
    *   It communicates with `FirebaseAuth` to handle user sign-up, sign-in, and sign-out operations.
    *   It exposes the current user and any potential errors as `StateFlow`s, allowing the UI to observe and react to changes.
*   **Firebase Integration**:
    *   The project is connected to Firebase via the `google-services.json` file.
    *   The `build.gradle.kts` files are configured with the necessary Firebase and Google Services plugins and dependencies.

## How to Compile and Use

1.  **Set up a Firebase Project:**
    *   Go to the [Firebase Console](https://console.firebase.google.com/) and create a new project.
    *   Add an Android app to the project with the package name `com.example.prj1`.
    *   In the **Authentication** section, go to the **Sign-in method** tab and enable the **Email/Password** provider.
    *   Download the `google-services.json` configuration file and place it in the `app/` directory of the project.
2.  Clone the repository or import the project into Android Studio.
3.  Allow Android Studio to sync the Gradle files and download all the required dependencies.
4.  Compile and run the application on an Android emulator or physical device.
5.  Interact with the app:
    *   Enter an email and password and tap **Register** to create an account.
    *   Tap **Logout** on the home screen.
    *   Use the same credentials and tap **Login** to sign in again.

## Potential Future Improvements

*   Implement a "Forgot Password" feature.
*   Add support for other sign-in providers, such as Google Sign-In.
*   Add client-side validation for email format and password strength.
*   Create a more detailed user profile screen.
*   Implement light and dark theme support.


<img width="255" height="600" alt="image" src="https://github.com/user-attachments/assets/f2a2e799-08d1-466f-b50a-24b857fabfd1" />
<img width="255" height="600" alt="image" src="https://github.com/user-attachments/assets/d7d2cd75-2088-44b2-b87a-e4de427f9bc1" />
<img width="255" height="600" alt="image" src="https://github.com/user-attachments/assets/e34f893e-4bd3-483c-94e7-84a7457cd95e" />

