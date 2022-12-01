# Lab 5: Hoist Composable State to an Android ViewModel
In this lab, we'll integrate the Android Architecture Component ViewModel class to store our UI state that is rendered by Composables.

- In the `topics` package, create a class `TopicsViewModel` that extends `ViewModel`
- Define a primary constructor property `val logger: Logger`
- Add the `@HiltViewModel` annotation to the `ViewModel`, and an `@Inject` annotation to its constructor
- Inject an instance of `TopicsViewModel` into our `TopicsScreen()` Composable using the `viewModel()` function
- Add the `@AndroidEntryPoint` annotation to `MainActivity`
- Examine the `AnalyticsModule` to see how Hilt knows how to satisfy the `Logger` dependency of `TopicsViewModel`
- Expose a property `val topics: State<List<Topic>>` as mutable state from `TopicsViewModel()`
- Within the `TopicsScreen()` Composable, update the topics variable to pull from the `ViewModel` using the `by viewModel.topics` delegate
- Forward click events to your instance of `TopicsViewModel` and move the logging into the click handler in the `ViewModel`
- Define a sealed class `Event` with a single data class type `ShowTopicClickedMessage(val message: String)`
- Expose a `SharedFlow` to emit `Event` instances when a `Topic` is clicked
- Update your `ViewModel` to expose the list of `Topic`s as a `Flow`

## Useful Resources
- [StateFlow and SharedFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
- [State holdersa nd UI State](https://developer.android.com/topic/architecture/ui-layer/stateholders)