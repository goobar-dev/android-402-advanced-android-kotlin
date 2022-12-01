# Lab 4: Building Composable Previews
In this lab, we'll explore the `@Preview` annotation for Composable functions and how we can use it to quickly preview how our app will look across a variety of configurations.

- Within `MainScreen.kt` create a preview composable using the `@Preview` annotation
- Update your preview to display what it would look like on a Nexus 5 by setting the `device` parameter of the `@Preview` annotation to `Devices.NEXUS_5`
- Generate a 2nd preview by adding an additional `@Preview` annotation with a different device setting
- Add `group = "phones"` to each `@Preview` annotation and observe the added `"phones"` group selectable from the dropdown in the previews tool window
- Generate a preview of your dark them by adding a `@Preview` annotation that uses the `UI_MODE_NIGHT_YES` value for `uiMode`
- Use the `PreviewParameterProvider<Int>` interface to provide preview values of 0, 5, and 20 so we can observe how our app looks when the score is set to thoses different values
    - Update your `MainScreen()` Composable to take an initial score value so we can pass that to our previews
    - Create a class `ScorePreviewParameterProvider` that implements `PreviewParameterProvider<Int>` and provides a `Sequence` of preview values
    - Update your preview function to take a `score:Int` parameter and annotate it with `@PreviewParameter(ScorePreviewParameterProvider::class)`
    - Observe the newly generated previews

## Lab 4 Challenges
- Generate a preview group for tablets that displays previews for a large and small tablet
- Generate a preview group for `fontScale` values of 1f, 1.5f, and 2f
- Explore generating previews for other configurations, or combinations of configurations

## Useful Resources
- [Creating Composable Previews](https://developer.android.com/jetpack/compose/tooling#preview)
- [Deploying Previews to a Device](https://developer.android.com/jetpack/compose/tooling#preview-deploy)