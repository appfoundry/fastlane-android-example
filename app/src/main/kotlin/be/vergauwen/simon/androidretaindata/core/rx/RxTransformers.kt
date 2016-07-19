package be.vergauwen.simon.androidretaindata.core.rx

import rx.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RxTransformers : Transformers {
    override fun <T> applyComputationSchedulers(): Observable.Transformer<T, T> = Observable.Transformer<T, T> { it.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()) }
    override fun <T> applyIOSchedulers(): Observable.Transformer<T, T> = Observable.Transformer<T, T> { it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
    override fun <T> applyIOSingleSchedulers(): Single.Transformer<T, T> = Single.Transformer<T, T> { it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
    override fun <T> applyComputationSingleSchedulers(): Single.Transformer<T, T> = Single.Transformer<T, T> { it.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()) }
    override fun applyIOCompletableSchedulers(): Completable.CompletableTransformer = Completable.CompletableTransformer { it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
    override fun applyComputationCompletableSchedulers(): Completable.CompletableTransformer = Completable.CompletableTransformer { it.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()) }
}