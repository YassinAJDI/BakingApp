# BakingApp
Your task is to create a Android Baking App that will allow Udacity’s resident baker-in-chief, Miriam, to share her recipes with the world. You will create an app that will allow a user to select a recipe and see video-guided steps for how to complete it.

## Project Overview
In this project, you will create an app to view video recipes. You will handle media loading, verify your user interfaces with UI tests, and integrate third party libraries. You'll also provide a complete user experience with a home screen widget. This will involve finding and handling error cases, adding accessibility features, allowing for localization, adding a widget.

## Why this Project?
As a working Android developer, you often have to create and implement apps where you are responsible for designing and planning the steps you need to take to create a production-ready app. Unlike Popular Movies where we gave you an implementation guide, it will be up to you to figure things out for the Baking App.

## What Will I Learn?
In this project you will:
*   Use MediaPlayer/Exoplayer to display videos.
*   Handle error cases in Android.
*   Add a widget to your app experience.
*   Leverage a third-party library in your app.
*   Use Fragments to create a responsive design that works on phones and tablets.

## Project Specification

### Common Project Requirements
- [x]   App is written solely in the Java Programming Language
- [x]   App utilizes stable release versions of all libraries, Gradle, and Android Studio.

### General App Usage
- [x]   App should display recipes from provided network resource.
- [x]   App should allow navigation between individual recipes and recipe steps.
- [x]   App uses RecyclerView and can handle recipe steps that include videos or images.
- [x]   App conforms to common standards found in the Android Nanodegree General Project Guidelines.

### Components and Libraries
- [x]   Application uses Master Detail Flow to display recipe steps and navigation between them.
- [x]   Application uses Exoplayer to display videos.
- [x]   Application properly initializes and releases video assets when appropriate.
- [x]   Application should properly retrieve media assets from the provided network links. It should properly handle network requests.
- [x]   Application makes use of Espresso to test aspects of the UI.
- [x]   Application sensibly utilizes a third-party library to enhance the app's features. That could be helper library to interface with Content Providers if you choose to store the recipes, a UI binding library to avoid writing findViewById a bunch of times, or something similar.

### Homescreen Widget
- [x] Application has a companion homescreen widget.
- [x] Widget displays ingredient list for desired recipe.

## Libraries
*   [AndroidX](https://developer.android.com/jetpack/androidx/) - Previously known as 'Android support Library'
*   [Glide](https://github.com/bumptech/glide) - for loading and caching images 
*   [Retrofit 2](https://github.com/square/retrofit) - Type-safe HTTP client for Android and Java by Square, Inc. 
*   [Gson](https://github.com/google/gson) - for serialization/deserialization Java Objects into JSON and back
*   [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
*   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
*   [Paging](https://developer.android.com/topic/libraries/architecture/paging/)
*   [DataBinding](https://developer.android.com/topic/libraries/data-binding/)
*   [OkHttp](https://github.com/square/okhttp)
*   [Timber](https://github.com/JakeWharton/timber)

## License
