# Card visit app with MVVM
The main purpose of this app is to show MVVM sample implementation using the new Google [Android Architecture Components][1] (View Model, Data Binding, Live Data, lifecycle aware) along with [Room][2] database and [Dagger 2][3] dependency injection to build a robust application (scalable, testable, easy to maintain and lanscape mode, never leak data when screen rotation with [LiveData][8]). Unit test with JUnit4, Mockito and user interface and interactions: Use an Android UI instrumentation test with [Espresso][7].

## App
This Java version is a result form "be Corporation Vietnam" Android Dev homework test.
1. UI requirements:
- Click on app icon > Show list of card visit (support paging, load 50 item/page)
- Click card item > Show card detail
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
<img src="/screenshots/7.png" width="500" height="375"/>

* di: Dagger 2 implementation to provide ineed ojbect for Retrofit API and ViewModel constructor.
* service: REST full implement and Room DB to making a Repository access both Web and Local data sources.
* util: some app util and constants.
* view: UI components with databinding, adapter and screen activity.
* viewmodel: Implement ViewModel class content LiveData and data logic to show at Views.


### Testing
JUnit 4, [Mockito][4], [Espresso][7] for automated UI testing.
<img src="/screenshots/7.png" width="500" height="316"/>
Make a robust application with Unit test

** Instrument test:
- Room DB test.
- Espresso for UI screen flow test.

** Unit test:
- Model basic test.
- ViewModel unit test with Mockito and livdata Test lib.


## Reference
* [Google Architecture Components sample][5] project
* [MVVM official guide][6]

## Screenshots
| Home | 2 Segments | Search filter
|:-:|:-:|:-:|
| ![First](/screenshots/0.jpg?raw=true) | ![Sec](/screenshots/1.jpg?raw=true)  | ![Third](/screenshots/2.jpg?raw=true) |

| Card Details | Add new Card | Add new Card full fill
|:-:|:-:|:-:|
| ![Fourth](/screenshots/3.jpg?raw=true) | ![Fiveth](/screenshots/4.jpg?raw=true) | ![Sixth](/screenshots/5.jpg?raw=true) |



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