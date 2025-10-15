package com.awesomeproject

import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.fabricEnabled
import com.facebook.react.defaults.DefaultReactActivityDelegate
import com.clevertap.react.CleverTapRnAPI;
import android.os.Bundle;
import android.content.Intent
import android.util.Log
import android.os.Build
import com.clevertap.android.sdk.CleverTapAPI

class MainActivity : ReactActivity() {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  override fun getMainComponentName(): String = "AwesomeProject"

  /**
   * Returns the instance of the [ReactActivityDelegate]. We use [DefaultReactActivityDelegate]
   * which allows you to enable New Architecture with a single boolean flags [fabricEnabled]
   */
  override fun createReactActivityDelegate(): ReactActivityDelegate =
      DefaultReactActivityDelegate(this, mainComponentName, fabricEnabled)

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && intent != null) {
                val cleverTap = CleverTapAPI.getDefaultInstance(this)
                if (cleverTap != null) {
                    cleverTap.pushNotificationClickedEvent(intent.extras)
                    Log.d("CleverTapEvent", "Notification click forwarded to CleverTap for Android 12+")
                } else {
                    throw Exception("CleverTap instance is null")
                }
            } else {
                throw Exception("Intent is null or SDK < Android 12")
            }
        } catch (e: Exception) {
            Log.e("CleverTapEvent", "Failed to forward notification click: ${e.message}", e)
        }
    }

   override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    CleverTapRnAPI.setInitialUri(intent?.data) // From v3.0.0+
    }


}
