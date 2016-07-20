package be.vergauwen.simon.androidretaindata.core.rx

import rx.*
import rx.schedulers.Schedulers

class TestTransformers : Transformers {
  override fun <T> applyComputationSchedulers(): Observable.Transformer<T, T> = Observable.Transformer { it.subscribeOn(Schedulers.immediate()).observeOn(Schedulers.immediate()) }

  override fun <T> applyIOSchedulers(): Observable.Transformer<T, T> = Observable.Transformer { it.subscribeOn(Schedulers.immediate()).observeOn(Schedulers.immediate()) }

    override fun <T> applyIOSingleSchedulers(): Single.Transformer<T, T> = Single.Transformer { it.subscribeOn(Schedulers.immediate()).observeOn(Schedulers.immediate()) }

    override fun <T> applyComputationSingleSchedulers(): Single.Transformer<T, T> = Single.Transformer { it.subscribeOn(Schedulers.immediate()).observeOn(Schedulers.immediate()) }

    override fun applyIOCompletableSchedulers(): Completable.CompletableTransformer = Completable.CompletableTransformer { it.subscribeOn(Schedulers.immediate()).observeOn(Schedulers.immediate()) }

    override fun applyComputationCompletableSchedulers(): Completable.CompletableTransformer = Completable.CompletableTransformer { it.subscribeOn(Schedulers.immediate()).observeOn(Schedulers.immediate()) }
}