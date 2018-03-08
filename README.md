# Loader View for Android

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Loader%20View%20for%20Android-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/4243)

## What It Is
Provide both TextView and ImageView the ability to show shimmer (animation loader) before any text or image is shown. Useful when waiting for data to be loaded from the network. Example below

![Timer Image](https://static.wixstatic.com/media/d748c3_28381c0f110f4dc68fcd340b503f86a2~mv2.gif)

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
    
    Alternatively, you can set a placeholder which will be used to measure the required minimum width, so the shimmer will
    almost be as large as your text
    ```xml
    <com.elyeproj.loaderviewlibrary.LoaderTextView
         android:layout_width="wrap_content"
         android:layout_height="wrap_content"
         app:placeholder_int_argument="1"
         app:placeholder_resource="@string/resourceWithIntArgument" />
    ```
    
    Referencing a string resource makes sure the shimmer respects text width in different
    languages. You can optionally pass one `String`, `int` or `float` argument. If you need more than
    one argument you have to use `setPlaceholder(String)` programmatically.

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

9. Reset and show shimmer (animation loader) again by calling the below API
    ```java
    myLoaderTextView.resetLoader();
    myLoaderImageView.resetLoader();
    ```

## Requirement
Android SDK API Version 15 and above.

## Importing the Library
On your module `build.gradle`, add

    dependencies {
        implementation 'com.elyeproj.libraries:loaderviewlibrary:1.4.1'
    }

## What's new in 1.4.1
1. Fix memory leak issue

## What's new in 1.4.0
1. Support Corner Rectangle shimmer

## What's new in 1.3.0
1. Support AppCompat
2. Support TextView drawable


## Licence

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.