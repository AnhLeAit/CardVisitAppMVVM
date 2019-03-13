# Card visit app with MVVM & Unit Test
The main purpose of this app is to show MVVM sample implementation using the new Google [Android Architecture Components][1] (ViewModel, DataBinding, LiveData, ConstraintLayout and AndroidX inside) along with [Room][2] database and [Dagger 2][3] dependency injection to build a robust application (scalable, testable, easy to maintain and landscape mode, never leak data when screen rotation with [LiveData][8]). Unit test with JUnit4, Mockito. Use an Android UI instrumentation test with [Espresso][7].

## APK
For quick look you can download the APK here!

[<img src="/.assets/direct-apk-download.png"
      alt="Direct apk download"
      height="80">](/.assets/apk/card-visit-app-mvvm-debug.apk?raw=true)

## App
This Java version is my homework test for Android Dev at **be Corporation Vietnam**
1. UI requirements:
- Click on app icon > Show list of card visit (support paging, load 50 item/page)
- Click card item > Show card details
- Click add new card > Show screen add new card
- Click save button > save card in local storage then back to list screen
and display new card at the top of the list
- In home screen user can filter by name or phone number

2. Rest API and segment show in List order:
API get list of card visit from 2 API as following
- List card saved by Room DB (Segment 1)
- http://demo8104666.mockable.io/cards (Segment 2)
- http://demo7527907.mockable.io/cards/<pageIndex​ from 1-4>  (Segment 3)


### Architecture overview

<img src="/.assets/mvvm-architecture.png" width="500" height="375"/>

- **di:** Dagger 2 implementation to provide in need object for Retrofit API and ViewModel constructor.
- **service:** REST full implement and Room DB to making a Repository access both Web and Local data sources.
- **util:** Some app utils and constants.
- **view:** UI components with DataBinding, ConstraintLayout, Adapters and screen activities.
- **viewmodel:** Implement ViewModel class content LiveData and data logic to show at Views.


### Testing

JUnit 4, [Mockito][4], [Espresso][7] for automated UI testing.

<img src="/.assets/unit-test.jpg" width="500" height="316"/>

Make a robust application with Unit test

#### JUnit [test](/app/src/test)
- Models basic test.
- ViewModel unit test with Mockito and [LiveData Testing](https://github.com/jraska/livedata-testing) library.

#### User interface and interactions [test](/app/src/androidTest)
- Room DB test.
- Espresso for UI screen flow test.

## Screenshots
| Home | 2 Segments | Search filter
|:-:|:-:|:-:|
| ![First](/.assets/screenshots/0.jpg?raw=true) | ![Sec](/.assets/screenshots/1.jpg?raw=true)  | ![Third](/.assets/screenshots/2.jpg?raw=true) |

| Card Details | Add new Card | Add new Card full fill
|:-:|:-:|:-:|
| ![Fourth](/.assets/screenshots/3.jpg?raw=true) | ![Fiveth](/.assets/screenshots/4.jpg?raw=true) | ![Sixth](/.assets/screenshots/5.jpg?raw=true) |



## Reference
* [Google Architecture Components sample][5] project
* [MVVM official guide][6]


[1]: https://developer.android.com/topic/libraries/architecture/index.html
[2]: https://developer.android.com/topic/libraries/architecture/room.html
[3]: https://github.com/google/dagger
[4]: https://github.com/mockito/mockito
[5]: https://github.com/googlesamples/android-architecture-components
[6]: https://developer.android.com/jetpack/docs/guide
[7]: https://developer.android.com/training/testing/espresso/index.html
[8]: https://developer.android.com/topic/libraries/architecture/livedata.html

License
=======

    Copyright 2019 Anh Le (anhle.ait@gmail.com).

Licensed under the the [APACHE](http://www.apache.org/licenses/LICENSE-2.0.txt) license.
    
See the [LICENSE](/LICENSE) file for the whole license text.
