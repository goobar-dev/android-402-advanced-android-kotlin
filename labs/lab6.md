# Lab 6: Working with Coroutines and Flows
In this lab, we'll use coroutines and flows to load data from the network and expose it to our Composables via a flow

- Create a Retrofit service interface `StudyGuideService` with a single `/topics` route that returns `List<Topic>`
- Create a `NetworkModule` class to inject a Retrofit instance of our `StudyGuideService` with Hilt
- Update `Topic` class to support network desrialization with Retrofit and Moshi by adding `@JsonClass(generateAdapter = true)
- Add a constructor property `val service: StudyGuideService` to `TopicsViewModel`
- Fetch topics using within `TopicsViewModel.init{}` by launching a new coroutine into `viewModelScope`
- Update `topics` property to have a backing `MutableStateFlow` and emit the loaded topics once the request has completed
- Simplify the loading by leveraging the `stateIn()` function in confunction with the `flow{}` builder

## Useful Resources
- [Kotlin Coroutine Basics](https://kotlinlang.org/docs/coroutines-basics.html)
- [Controlling Coroutine Lifecycle & Threading](https://kotlinlang.org/docs/coroutine-context-and-dispatchers.html)
- [Coroutine Exception Handling](https://kotlinlang.org/docs/exception-handling.html)