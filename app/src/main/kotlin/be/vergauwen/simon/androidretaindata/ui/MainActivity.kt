package be.vergauwen.simon.androidretaindata.ui

import android.os.Bundle
import be.vergauwen.simon.androidretaindata.KotlinApplication
import be.vergauwen.simon.androidretaindata.R
import be.vergauwen.simon.androidretaindata.core.model.GithubRepo
import be.vergauwen.simon.himurakotlin.MVPDaggerActivity
import kotlinx.android.synthetic.main.main_activity.*

/**
 * Created by Simon Vergauwen. github.com/nomisRev
 */

class MainActivity : MVPDaggerActivity<MainContract.View, MainPresenter, MainComponent>(), MainContract.View {

  override fun createComponent(): MainComponent = DaggerMainComponent.builder().applicationComponent(
      (application as KotlinApplication).component).build()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)

    //Least complex example. SavedInstance == null --> config change so use old network data
    presenter.loadRepos(savedInstanceState==null)
  }

  override fun showText(string: String) {
    text_view.text = string
  }

  override fun showError(t: Throwable) {
    text_view.text = t.message
  }
}