# Room-Database-in-Android

An **Android** (Java) app demonstrating the **Room persistence library** — a full CRUD flow (add, list, edit, delete) for a simple `User` entity, backed by SQLite via Room, with results shown in a `RecyclerView`.

## What it demonstrates

- Defining a Room `@Entity` (`User`) with an auto-generated primary key
- A `@Dao` interface (`UserDao`) with `@Insert`, `@Query`, `@Update`, and `@Delete` methods
- A singleton `RoomDatabase` (`UserDatabase`) built via `Room.databaseBuilder()`
- Wiring Room results into a `RecyclerView` with a custom `UserAdapter`
- Inline edit/delete actions per row, using an `AlertDialog` with an `EditText` for editing

## Tech Stack

Java · Android SDK (compileSdk 35, minSdk 29) · Room 2.6.1 (runtime + compiler) · RecyclerView · Gradle (Kotlin DSL)

## Project Structure

```
app/src/main/java/com/example/roomdb_in_android/
├── User.java           # @Entity — id (auto-generated PK), name
├── UserDao.java        # @Dao — insert, getAllUsers, update, delete
├── UserDatabase.java   # Singleton RoomDatabase accessor
├── UserAdapter.java     # RecyclerView.Adapter with per-row edit/delete
└── MainActivity.java    # Wires EditText + Button + RecyclerView to the DB
app/src/main/res/layout/
├── activity_main.xml
└── item_user.xml        # Row layout: name TextView + Edit/Delete buttons
```

## How it works

1. On launch, `MainActivity` gets the `UserDatabase` singleton and loads all existing users into a `RecyclerView`.
2. Typing a name and tapping **Add** inserts a new `User` row and refreshes the list.
3. Each row's **Edit** button opens an `AlertDialog` with a pre-filled `EditText`; confirming updates that row in the database and the adapter.
4. Each row's **Delete** button removes the row from both the database and the adapter.

## Setup & Run

```bash
git clone https://github.com/lavishtembhare/Room-Database-in-Android.git
```

Open the project in Android Studio, let Gradle sync, and run on an emulator or device with API 29+. No external services or credentials are required — everything is local SQLite via Room.

## Known Limitations

- **All database queries run on the main thread** (`allowMainThreadQueries()` is explicitly enabled in `UserDatabase`, with a comment noting it should be moved to a background thread in production). For anything beyond a demo, queries should go through Room's suspend functions, `LiveData`, or an executor.
- No input validation beyond checking the name field isn't empty.
- No confirmation dialog before delete — tapping **Delete** removes the row immediately.

## License

Licensed under **CC0 1.0 Universal** (public domain dedication) — see [LICENSE](LICENSE).
