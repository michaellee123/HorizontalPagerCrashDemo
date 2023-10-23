# HorizontalPagerCrashDemo

This is a demo to show how to make a crash in JetPack Compose HorizontalPager.

About this issue's details: [https://issuetracker.google.com/issues/305065943]()

There's a screen record: [Screen_recording_20231023_210123.mp4](Screen_recording_20231023_210123.mp4)

And there's a log file: [Sony-XQ-CT72-Android-13_2023-10-23_210139.logcat](Sony-XQ-CT72-Android-13_2023-10-23_210139.logcat)

## Something I thought

I think this issue is caused by the `HorizontalPager`'s state is not saved when the activity `onPause` and then `onResume`. You can see that, when I start a new activity, and back to the `MainActivity`, and switch tab, the scroll position will back to the zero. And when I send a event with the new activity to change something in the `HorizontalPager`'s list, the app will probably crash.