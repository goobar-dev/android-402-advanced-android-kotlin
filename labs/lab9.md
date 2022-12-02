# Lab 9: Saving User Preferences Using DataStore
In this lab, we'll use DataStore to save a user setting for the last used note category.

- Add the `datastore-preference` dependency
- Create a Hilt module named `PreferencesModule` where we will provide a singleton instance of `DataStore<Preference>`
- Inject an instance of `DataStore<Preference>` into `AddNoteViewModel` as a constructor property named `settings`
- Define a top-level property named `LAST_CATEGORY` using `stringPreferenceKey("last_category")` within `AddNoteViewModel.kt`
- Create a property on `AddNoteViewModel` named `selectedCategory` that exposes a `StateFlow<String>` representing the last saved category
    - use `settings.data` to get a flow of all preferences
    - call `map{}` on that flow, and use the preferencekey to access the specific `"last_category"` preference
- Collect the `selectedCategory` flow from within `AddNoteScreen` using `collectAsState()` and pass that value to the `CategoryDropdown` composable
- Within `AddNoteViewModel.onCategoryClicked()` save the clicked category to `DataStore`
    - Launch a new coroutine using `viewModelScope`
    - Within that coroutine call `settings.edit { preferences -> preferences[LAST_CATEGORY] = newCategory }`
- Test the usage of `DataStore` by changing your selected category, leaving the screen, and returning to the "Save Note" screen
    - Was the last used category selected by default this time?

# Useful Resources
- [Store Key-Value Pairs with Preferences DataStore](https://developer.android.com/topic/libraries/architecture/datastore#preferences-datastore)
- [Using DataStore in Synchronous Code](https://developer.android.com/topic/libraries/architecture/datastore#synchronous)