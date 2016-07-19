package be.vergauwen.simon.androidretaindata.core.di

import android.app.Application
import be.vergauwen.simon.androidretaindata.core.rx.TestTransformers
import be.vergauwen.simon.androidretaindata.core.rx.Transformers

class TestApplicationModule(private val application : Application) : ApplicationModule(application){
  override fun provideTransfomers(): Transformers = TestTransformers()
}