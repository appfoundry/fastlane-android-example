package be.vergauwen.simon.himurakotlin

/**
 * Created by Simon Vergauwen. github.com/nomisRev
 */

interface MVPContract {
  interface View

  interface Presenter<V : View> {
    val view : V?
    fun attachView(view: V)
    fun detachView()
  }

  interface Component<V : View, out P : Presenter<V>> {
    val presenter: P
  }
}