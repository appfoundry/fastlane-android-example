package be.vergauwen.simon.himurakotlin

import rx.*
import rx.subscriptions.*

/**
 * Created by Simon Vergauwen. github.com/nomisRev
 */

abstract class MVPRxPresenter<V : MVPContract.View> : MVPContract.Presenter<V> {
  override var view: V? = null
  private var compositeSubscription = CompositeSubscription()


  override fun attachView(view: V) {
    this.view = view
  }

  override fun detachView() {
    this.view = null
    this.compositeSubscription.unsubscribe()
    this.compositeSubscription = CompositeSubscription()
  }

  protected fun <T> add(observable: Observable<T>, onError: (Throwable) -> Unit, onNext: (T) -> Unit, onComplete: (() -> Unit)? = null, unsubscribeAutomatically: Boolean = true): Subscription {

    val sub = if (onComplete == null) {
      observable.subscribe(onNext, onError)
    } else {
      observable.subscribe(onNext, onError, onComplete)
    }

    if (unsubscribeAutomatically) {
      compositeSubscription.add(sub)
    }

    return sub
  }

  protected fun <T> add(single: Single<T>, onSuccess: (T) -> Unit, onFailure: (Throwable) -> Unit): Subscription {
    val sub = single.subscribe(onSuccess, onFailure)
    compositeSubscription.add(sub)
    return sub
  }
}