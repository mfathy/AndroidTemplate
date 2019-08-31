# Doctors List

By [Mohammed Fathy](mailto:dev.mfathy@gmail.com)

## Instructions

1. Download, UnZip & Navigate to task directory.
2. Start Android studio, then open the project, from open dialog.
3. Wait until the project syncs and builds successfully.

## Discussion

I used the following libraries:
*   [AndroidX Library](https://developer.android.com/jetpack/androidx/) - AndroidX is a major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index). Like the Support Library, AndroidX ships separately from the Android OS and provides backwards-compatibility across Android releases. AndroidX fully replaces the Support Library by providing feature parity and new libraries.
*   [Mockito](http://site.mockito.org/) - A mocking framework used to implement unit tests.
*   [Dagger](https://github.com/google/dagger) - for dependency Injection
*   [Gson](https://github.com/google/gson) - a json serialize and deserialize library.
*   [RxJava](https://github.com/ReactiveX/RxJava) - Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM. 
*   [Okhttp](http://square.github.io/okhttp/) - An HTTP+HTTP/2 client for Android and Java applications.
*   [Hamcrest](http://hamcrest.org/JavaHamcrest/) -  Junit Matchers
*   [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
*   [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) - LiveData & ViewModel.
*   [Picasso](https://github.com/square/picasso) - An image loading and caching library for Android focused on smooth scrolling.


## Requirements

##### Implementing an app to search for doctors using test:
* The task is implemented using MVVM architecture.
* The data layer is implemented using repository pattern, so we can add more data source easily.
* The chosen fetch of data is: Return remote copy.
* Remote data source: uses Okhttp or Retrofit API to call the Backend API. 

