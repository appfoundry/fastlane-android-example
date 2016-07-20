package be.vergauwen.simon.androidretaindata.ui

import be.vergauwen.simon.androidretaindata.BuildConfig
import be.vergauwen.simon.androidretaindata.core.model.GithubRepo
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.android.synthetic.main.main_activity.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class MainActivityTest{

  @Before
  fun setUp(){}

  @Test
  fun preConditions() {
    val activity = Robolectric.buildActivity(MainActivity::class.java).get()
    assertNotNull(activity)
  }

  @Test
  fun testAddRepo() {
    val activity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().visible().get()
    val textView  = activity.text_view
    activity.showText("test")
    assertEquals(textView.text,"test")
  }

  @Test
  fun testShowError() {
    val activity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().visible().get()
    val textView  = activity.text_view
    activity.showError(Throwable("some error"))
    assertEquals(textView.text,"some error")
  }

}