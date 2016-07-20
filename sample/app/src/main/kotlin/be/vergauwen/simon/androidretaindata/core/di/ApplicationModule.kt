package be.vergauwen.simon.androidretaindata.core.di

import android.app.Application
import be.vergauwen.simon.androidretaindata.core.rx.RxTransformers
import be.vergauwen.simon.androidretaindata.core.rx.Transformers
import dagger.Module
import dagger.Provides

@Module
open class ApplicationModule(private val application: Application) {

  @ApplicationScope
  @Provides
  open fun provideTransfomers(): Transformers = RxTransformers()
}