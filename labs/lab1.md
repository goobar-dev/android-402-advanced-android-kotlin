# Lab 1: Create and Run a Jetpack Compose App
In this lab, we'll make sure our Android dev environment is setup properly, and we'll create and deploy a basic Jetpack Compose app.

- Checkout `lab1-start` in the git repo
- Open Android Studio
- Select `New Project`
- Choose `Empty Compose Activity`
- Name the project `Compose Score Keeper`
- Set `Save Location` to be the `compose-score-keeper` directory in this repo
- Click `Finish` to create the project
- Deploy the app to a device or emulator to verify everything is working
- Examine the `app/build.gradle` file and note both the Compose dependencies and the Compose-related Android Gradle Plugin configurations
- Modify the greeting displayed by the app.
    - Did you notice an immedate change reflected on your device when you changed the code?
- Use a `Box` Composable to center the greeting text in the middle of the screen