package com.margelo.nitro.nitrosystemnavigationbar

import android.app.Activity
import android.view.Window
import com.facebook.proguard.annotations.DoNotStrip
import com.margelo.nitro.NitroModules
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.graphics.Color

@DoNotStrip
class NitroSystemNavigationBar : HybridNitroSystemNavigationBarSpec() {
  private val context = NitroModules.applicationContext ?: throw Exception("Context is null")

  // cache once, tránh check SDK lặp
  private val useInsetsController = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

  // cache nav height, update only on config change if cần
  private var cachedNavBarHeight: Int? = null

  private fun getActivity(): Activity? = context.currentActivity

  private fun getWindow(): Window? = getActivity()?.window

  private fun runSafe(block: (Window) -> Unit) {
    val window = getWindow() ?: return
    getActivity()?.runOnUiThread {
      block(window)
    }
  }

  override fun hideNavigationBar() {
    runSafe { window ->
      if (useInsetsController) {
        window.insetsController?.run {
          hide(WindowInsets.Type.navigationBars())
          systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
      } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (
          View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_FULLSCREEN
          )
      }
    }
  }

  override fun showNavigationBar() {
    runSafe { window ->
      if (useInsetsController) {
        window.insetsController?.show(WindowInsets.Type.navigationBars())
      } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
      }
    }
  }

  override fun enableImmersiveSticky() {
    runSafe { window ->
      if (useInsetsController) {
        window.insetsController?.run {
          hide(WindowInsets.Type.systemBars())
          systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
      } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (
          View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
          )
      }
    }
  }

  override fun exitImmersiveMode() {
    runSafe { window ->
      if (useInsetsController) {
        window.insetsController?.show(WindowInsets.Type.systemBars())
      } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
      }
    }
  }

  override fun setLightNavigationBar(isLight: Boolean) {
    runSafe { window ->
      if (useInsetsController) {
        window.insetsController?.run {
          val appearance = WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
          setSystemBarsAppearance(
            if (isLight) appearance else 0,
            appearance
          )
        }
      } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        @Suppress("DEPRECATION")
        val decor = window.decorView
        var vis = decor.systemUiVisibility
        vis = if (isLight) {
          vis or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        } else {
          vis and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
        }
        decor.systemUiVisibility = vis
      }
    }
  }

  override fun getNavigationBarHeight(): Double {
    // Nếu đã cache, trả luôn dưới dạng Double
    cachedNavBarHeight?.let { return it.toDouble() }

    // 1) API 30+ dùng WindowInsets
    val fromInsets = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      getActivity()
        ?.window
        ?.decorView
        ?.rootWindowInsets
        ?.getInsets(WindowInsets.Type.navigationBars())
        ?.bottom
    } else null

    // 2) API 29 (Android Q) fallback lấy từ resource
    val fromResource = if (fromInsets == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      val res = getActivity()?.resources
      val id = res?.getIdentifier("navigation_bar_height", "dimen", "android") ?: 0
      if (id > 0) res?.getDimensionPixelSize(id) else 0
    } else null

    // 3) Còn không có thì 0
    val height = fromInsets
      ?: fromResource
      ?: 0

    // Cache kết quả
    cachedNavBarHeight = height
    return height.toDouble()
  }

  override fun setNavigationBarColor(color: Double, isTranslucent: Boolean?) {
    val intColor = color.toInt()
    val translucent = isTranslucent == true
    runSafe { window ->
      // build a single mask
      val flagsToClear = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
      window.clearFlags(flagsToClear)

      val flagsToAdd = when {
        translucent  -> WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
        intColor == Color.TRANSPARENT -> WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        else -> 0
      }
      if (flagsToAdd != 0) {
        window.addFlags(flagsToAdd)
      }

      window.navigationBarColor = intColor
    }
  }
}
