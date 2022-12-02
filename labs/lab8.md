# Lab 8: Saving Data Using an Encrypted Room Database
In this lab, we'll finish inplementing a "Save Note" feature using a Room database, and then work through how to encrypt that database on our device.

- Finish implementing the "Save Note" feature so that when the "Save" `Button` is pressed, the note is saved to the database and the "Add Note" screen is popped off the navigation stack
- Use the App Inspection tool to explore your database content
- Add the `net.zetetic:android-database-sqlcipher:4.5.0` dependency to your `app/build.gradle` file
    - This will help us encrypt our database when we construct our Room database instance
- Set up a `BuildConfig` property named `DATABASE_PASSWORD` to hold a password for the database
- Within `DatabaseModule.provideAppDatabase()` create an instance of `SupportFactory`; this will be used to set a password on our database
    - Pass the following to the `SupportFactory` constructor: `SQLiteDatabase.getBytes(BuildConfig.DATABASE_PASSWORD.toCharArray())`
- Call `openHelperFactory()` on the `DatabaseBuilder` and pass the `SupportFactory` instance
- Uninstall, and reinstall, the app and try examining your database as you did before
    - Is the data visible as it was before?

## Lab 8 Challenges
- Add a "Delete Note" feature that removes saved notes from the database
- Add an "Edit Note" feature that allows for updating the content of a previously saved note
- Add a "Note Details" screen that displays all the saved information for a note when a note list item is clicked

## Useful Resources
- [Protecting Your Room Database with SQLCipher](https://sonique6784.medium.com/protect-your-room-database-with-sqlcipher-on-android-78e0681be687)
- [Encrypting a Room Database Using SQLCipher](https://waynestalk.com/en/android-room-sqlcipher-en/)