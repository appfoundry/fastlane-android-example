package be.vergauwen.simon.androidretaindata

import android.app.Application
import android.content.Context
import be.vergauwen.simon.androidretaindata.core.di.ApplicationComponent
import be.vergauwen.simon.androidretaindata.core.di.ApplicationModule
import be.vergauwen.simon.androidretaindata.core.di.DaggerApplicationComponent
import be.vergauwen.simon.androidretaindata.core.di.ServiceModule
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

/**
 * Created by Simon Vergauwen. github.com/nomisRev
 */

open class KotlinApplication : Application(){

  var refWatcher : RefWatcher? = null
  val component by lazy { createComponent() }

  override fun onCreate() {
    super.onCreate()
    refWatcher = LeakCanary.install(this)
  }

  fun watch(context : Context) = refWatcher?.watch(context)

  open fun createComponent() : ApplicationComponent = DaggerApplicationComponent.builder()
      .serviceModule(ServiceModule())
      .applicationModule(ApplicationModule(this))
      .build()
}