ProgressButton
==============

Simple Android widget that display's a loading animation when a user clicks the button

![ScreenShot](https://raw.github.com/SundeepK/ProgressButton/master/ScreenCaps/device-2013-11-24-022111.png) 

After user has clicked button:

![ScreenShot](https://raw.github.com/SundeepK/ProgressButton/master/ScreenCaps/device-2013-11-24-022153.png)


**Layout code**

Simply define a ProgressButton:

  ``` xml

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context=".MainActivity" >

    <com.sun.progressbutton.ProgressButton
        android:id="@+id/progressView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:clickable="true"
        android:padding="10dp"
        android:src="@drawable/progress_view"
        />

</RelativeLayout>

  ```
