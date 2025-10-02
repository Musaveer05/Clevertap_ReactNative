package com.awesomeproject

import android.app.Application
import com.facebook.react.PackageList
import com.facebook.react.ReactApplication
import com.facebook.react.ReactHost
import com.facebook.react.ReactNativeApplicationEntryPoint.loadReactNative
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.defaults.DefaultReactHost.getDefaultReactHost
import com.facebook.react.defaults.DefaultReactNativeHost
import com.clevertap.react.CleverTapPackage // <-- import CleverTap package
import com.clevertap.android.sdk.ActivityLifecycleCallback // <-- import CleverTap lifecycle
import com.clevertap.react.CleverTapApplication;
import com.clevertap.android.sdk.CleverTapAPI
import com.facebook.react.soloader.OpenSourceMergedSoMapping
import com.facebook.soloader.SoLoader



class MainApplication : CleverTapApplication(), ReactApplication {


    override val reactNativeHost: ReactNativeHost =
        object : DefaultReactNativeHost(this) {
            override fun getPackages(): List<ReactPackage> =
                PackageList(this).packages.apply {
                    add(CleverTapPackage())
                }

            override fun getJSMainModuleName(): String = "index"

            override fun getUseDeveloperSupport(): Boolean = BuildConfig.DEBUG

            override val isNewArchEnabled: Boolean = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED
            override val isHermesEnabled: Boolean = BuildConfig.IS_HERMES_ENABLED
        }

    override val reactHost: ReactHost
        get() = getDefaultReactHost(applicationContext, reactNativeHost)

    
  override fun onCreate() {
        super.onCreate()

        // Enable CleverTap verbose logs
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE)

        // Register CleverTap lifecycle callbacks
        ActivityLifecycleCallback.register(this)

        // Load React Native
        loadReactNative(this)
      
    }



}
