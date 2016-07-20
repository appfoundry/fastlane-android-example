package be.vergauwen.simon.androidretaindata.ui

import be.vergauwen.simon.androidretaindata.core.di.ActivityScope
import be.vergauwen.simon.androidretaindata.core.di.ApplicationComponent
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface MainComponent : MainContract.Component<MainContract.View, MainPresenter>{
  override val presenter : MainPresenter
}