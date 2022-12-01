# Lab 7: Jetpack Compose Performance
In this lab, we'll learn about Composable recomposition and how to debug recompmositoin issues to imporve performance of our Composable functions.

- Examine our application using the LayoutInspector tool; looking for any recompositions that may happen
    - Notice the recomposition counts are 0 because the underlying data isn't changing
- Update the `topics` flow to emit continuously while adding a counter variable to the beggining of the title of the first loaded `Topic`
- Reexamine the recomposition behavior using LayoutInspector
    - Notice that the title is now being recomposed each time the data is changed
    - Also notice that the entire `TopicCard` is being recomposed each time; even for items whose data hasn't changed.
    - Why is this?  How we can find out?
- Use the Compose Compiler Metrics tool to generate a report of Composable stability
- Notice that `TopicCard` in not skippable.
    - Why?
- To avoid instability with `Topic`, we can create a `TopicViewItem` class, map `Topics` to `TopicViewItems` and expose those to our UI instead
- Re-run the metrics report and examine `TopicCard` again.
    - `TopicCard` is still not skippable
    - Why?
- `TopicViewItem` is marked unstable; even though it's using read-only properties and in our main :app module
    - This is because Kotlin's `List` type is not truly immutable, and thus, considered unstable by the Compose Compiler
    - How to fix this?
    - Can add the `@Immutable` annotation to `TopicViewItem`
    - Could also use the `kotlinx.ImmutableList` type rather than `List` to expose the categories
- Re-run the metrics report and examine `TopicViewItem` again
    - It should now be marked stable
- Redeploy the app and examine the recomposition behavior again
    - `TopicCard` is still being recomposed every time
    - Why?
    - Creating new lambdas each time causes the Composable to run again
- We can address this in a couple of ways
    - Use remembered lambdas instead of recreating each time
    - Use method references to forward events to the `ViewModel`
- Use method references and redeploy one more time
    - See now that only the changing card is recomposing as we would expect


## Useful Resources
- [Inspect Compose Using LayoutInspector](https://developer.android.com/studio/debug/layout-inspector#inspect-compose-semantics)