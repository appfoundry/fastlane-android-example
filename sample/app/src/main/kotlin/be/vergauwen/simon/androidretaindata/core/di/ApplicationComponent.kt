package be.vergauwen.simon.androidretaindata.core.di

import be.vergauwen.simon.androidretaindata.core.data.RxDataRepository
import be.vergauwen.simon.androidretaindata.core.rx.Transformers
import dagger.Component

@ApplicationScope
@Component(modules = arrayOf(ApplicationModule::class,ServiceModule::class))
interface ApplicationComponent {
  val  transfomers : Transformers
  val dataRepository : RxDataRepository
}