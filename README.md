# Loader View for Android

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Loader%20View%20for%20Android-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/4243)

## What It Is
Provide both TextView and ImageView the ability to show shimmer (animation loader) before any text or image is shown. Useful when waiting for data to be loaded from the network. Example below

![Loader Image](https://static.wixstatic.com/media/d748c3_28381c0f110f4dc68fcd340b503f86a2~mv2.gif)

## Features / Usage

1. Loader View for TextView defined in layout XML
    ```xml
    <com.elyeproj.loaderviewlibrary.LoaderTextView
         android:layout_width="match_parent"
         android:layout_height="wrap_content" />
    ```

2. Loader View for ImageView defined in layout XML
    ```xml
    <com.elyeproj.loaderviewlibrary.LoaderImageView
         android:layout_width="100dp"
         android:layout_height="100dp" />
    ```

3. Define the % width of the TextView that shows the loading animation with `width_weight`
    ```xml
    <com.elyeproj.loaderviewlibrary.LoaderTextView
         android:layout_width="match_parent"
         android:layout_height="wrap_content"
         app:width_weight="0.4" />
    ```

4. Define the % height of the TextView that shows the loading animation with `height_weight`
    ```xml
    <com.elyeproj.loaderviewlibrary.LoaderTextView
         android:layout_width="match_parent"
         android:layout_height="wrap_content"
         app:height_weight="0.8" />
    ```

5. Define use gradient of the TextView or ImageView that shows the gradient with `use_gradient`
    ```xml
    <com.elyeproj.loaderviewlibrary.LoaderTextView
         android:layout_width="match_parent"
         android:layout_height="wrap_content"
         app:use_gradient="true" />
    ```

6. Define rectangle round radius using `corner`. The default corner is 0.
    ```xml
    <com.elyeproj.loaderviewlibrary.LoaderTextView
         android:layout_width="match_parent"
         android:layout_height="wrap_content"
         app:corners="16" />
    ```

7. Setting the Text Style as BOLD would darken the loading shimmer

8. Other feature of TextView and ImageView is still applicable.

9. Use a custom shimmer color (note: if set, point 7 will not apply, your color will be used even if the Text Style is BOLD)
    ```xml
    <com.elyeproj.loaderviewlibrary.LoaderTextView
         android:layout_width="match_parent"
         android:layout_height="wrap_content"
         app:custom_color="@android:color/holo_green_dark" />
    ```
    
10. Reset and show shimmer (animation loader) again by calling the below API
    ```java
    myLoaderTextView.resetLoader();
    myLoaderImageView.resetLoader();
    ```

## Requirement
Android SDK API Version 15 and above.

## Importing the Library
On your root `build.gradle`, add `maven { url  "https://dl.bintray.com/elye-project/maven" }` to the `allprojects` section e.g.

    allprojects {
        repositories {
            google()
            maven {
                url  "https://dl.bintray.com/elye-project/maven"
            }
            mavenCentral()
        }
    }

On your module `build.gradle`, add

    dependencies {
        implementation 'com.elyeproj.libraries:loaderviewlibrary:2.0.0'
    }

## What's new in 2.0.0
1. Migrate to AndroidX
2. Fix issue of preventing partial overlay if view reused quickly in RecyclerView

## What's new in 1.5.0
1. Add custom color attribute

## What's new in 1.4.x
1. (1.4.1) Fix memory leak issue
2. (1.4.0) Support Corner Rectangle shimmer

## What's new in 1.3.0
1. Support AppCompat
2. Support TextView drawable


## Licence

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
