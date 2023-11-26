# FreeToGame
This is a KMP project for Android, IOS, Desktop and Wear Os with shared UI using Compose UI. The project consumes data from the [FreeToGame Api](https://www.freetogame.com/api-doc).
The project supports Dynamic colors for Android, and light and dark thems for all platforms

`Note`

`
The plans to complete the Desktop and Web features are underway. You can also take this as an opportunity to contribute by working on these features 🥳`

## Platforms
Android | IOS | Wear OS| Desktop | Web |
:----: | :----: | :----: | :----: | :----:
✅ | ✅ | ✅ | 



## Screenshots
#### Android
| | |
|:-------------------------:|:-------------------------:|
|<img src="./screenshots/android_home.png" width="300"> | <img src="./screenshots/android_details.png" width="300"> 

#### IOS
| | |
|:-------------------------:|:-------------------------:|
|<img src="./screenshots/ios_home.png" width="300"> | <img src="./screenshots/ios_details.png" width="300"> 

#### Wear OS
| | |
|:-------------------------:|:-------------------------:|
|<img src="./screenshots/wear_home.png" width="300"> | <img src="./screenshots/wear_details.png" width="300"> 

#### Desktop && Web
The plans to complete the `Desktop` and `Web` features are underway. You can also take this as an opportunity to contribute by working on these features 🥳


## Demo
#### Android

https://github.com/Felix-Kariuki/FreeToGame/assets/61313608/6a4400b3-1e19-4cf9-86cf-b3bda85fc011


#### IOS


https://github.com/Felix-Kariuki/FreeToGame/assets/61313608/78a60290-e0e7-4582-9be6-4d35fd34d0f5


#### Wear OS


https://github.com/Felix-Kariuki/FreeToGame/assets/61313608/9ab6ff77-55e8-4218-b40c-ed5a1a1bcc89


#### Desktop && Web
The plans to complete the `Desktop` and `Web` features are underway. You can also take this as an opportunity to contribute by working on these features 🥳


## Project structure

### Modules

### 1.shared

This is a Kotlin module that contains the logic common for both Android and iOS applications, that is, the code you share between platforms.

This `shared` module is also where all the  Compose Multiplatform code is witten.

It uses Gradle as the build system. You can add dependencies and change settings in `shared/build.gradle.kts`.
The `shared` module builds into an Android library and an iOS framework.

### 2.androidApp

This is a Kotlin module that builds into an Android application. 
The `androidApp` module depends on and uses the `shared` module as a regular Android library.

### 3.iosApp

This is an Xcode project that builds into an iOS application.
It depends on and uses the `shared` module as a CocoaPods dependency.

### 4.Wear
This is a Kotlin module that builds into an Wear OS application. 
The `wear` module depends on and uses the `shared` module as a regular Android library.

### 5.desktopApp


### 6.Web


## License and Copyright ©️
[LICENSE](https://github.com/Felix-Kariuki/FreeToGame/blob/main/LICENSE)

  ```
     Copyright©️ 2023 Felix Kariuki

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
  
  ```

 ## Reach Out 

  * [Twitter](https://twitter.com/felixkariuki_)

  * [LinkedIn](https://www.linkedin.com/in/felix-kariuki/)
