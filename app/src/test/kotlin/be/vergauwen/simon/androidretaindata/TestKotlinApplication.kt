package be.vergauwen.simon.androidretaindata

import be.vergauwen.simon.androidretaindata.core.di.ApplicationComponent
import be.vergauwen.simon.androidretaindata.core.di.DaggerApplicationComponent
import be.vergauwen.simon.androidretaindata.core.di.TestApplicationModule
import be.vergauwen.simon.androidretaindata.core.di.TestServiceModule

class TestKotlinApplication : KotlinApplication() {
  override fun createComponent(): ApplicationComponent = DaggerApplicationComponent.builder().applicationModule(
      TestApplicationModule(this)).serviceModule(TestServiceModule()).build()
}