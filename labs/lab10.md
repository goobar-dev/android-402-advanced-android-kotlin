# Lab 10: Performing Background Tasks with WorkManager
In this lab, we'll use WorkManager to request featured Android repos from GitHub and save them to our local database to then display in the app

- Explore the updated Room database that has been prepared to store fetched GitHub repositories
- Explore the updated `TrendingViewModel`
- Open `FeaturedReposSyncWorker` and explore the setup for injecting dependencies into our worker
- Update the implemention of `doWork()` within `FeaturedReposSyncWorker` to fetch featured repos using `GitHubFeaturedReposService`
- Within `AndroidStudyGuideApplication.onCreate()` create a one time work request to run our `FeaturedReposSyncWorker`
- Update `TrendingViewModel` to load the repo data from our database, and expose it to `TrendingScreen`

# Lab 10 Challenges
- Update the work request to run on a repeated cadence, as long as there is wifi

# Useful Resources
- [Schedule Tasks with WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)
- [How To Define Complex Work Requests](https://developer.android.com/topic/libraries/architecture/workmanager/how-to/define-work)
- [Building an Offline-first App](https://developer.android.com/topic/architecture/data-layer/offline-first)