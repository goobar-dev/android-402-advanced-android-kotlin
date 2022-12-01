# Lab 2: Build a Game Counter App Using Jetpack Compose
In this lab, we'll expand on our basic Compose app to build a simple score keeper app.  This lab, and it's challenges, will introduce fundamental concepts of Jetpack Compose such as State Hoisting.

- Create a new file named `MainScreen.kt`
- Within `MainScreen.kt` create a new empty Composable function named `MainScreen()`
- Update `MainActivity` to call `MainScreen()` within the call to `ComposeScoreKeeperTheme{}`
- Remove the existing `Greeting` related code
- Using the `remember{}` and `mutableStateOf()` functions, create a variable to store the user's current score
- Using a `Column`, update `MainScreen()` to display
    - a label that says `"Score"`
    - a label that shows the user's current score
    - a button that says `"+1"`
- Update the `Button` click handler so that it increments the score variable by one each time the `Button`
- Define a variable that defines the max score a user can reach
- Update the UI so that when the max score is reached the following things happen
    - a third label is show that displays `"You Won!"`
    - the `"+1` `Button` is replaced wit ha `"Reset"` `Button`
    - clicking the `"Reset"` `Button` will change the score back to 0 and hide the `"You Won!"` label

## Lab 2 Challenges
- Update the color of the score label when the user reaches the max score
- Use the `AnimatedVisibility` Composable to animate in/out the `"You Won!` label
- Update the app to provide an alternative layout when in landscape orientation
- Update the current score variable so that its value is persisted across orientation changes

## Useful Resources
- [Storing State in Composable Functions](https://developer.android.com/jetpack/compose/state#state-in-composables)
- [Animated Content Visibility with AnimatedVisibility()](https://developer.android.com/jetpack/compose/animation#animatedvisibility)
- [Detecting Screen Orientation in Compose](https://stackoverflow.com/questions/64753944/orientation-on-jetpack-compose)
- [Restoring State in Compose](https://developer.android.com/jetpack/compose/state#restore-ui-state)