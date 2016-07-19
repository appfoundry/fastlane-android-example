package be.vergauwen.simon.androidretaindata.core.rx

import rx.*

interface Transformers {
  fun <T> applyComputationSchedulers(): Observable.Transformer<T, T>
  fun <T> applyIOSchedulers(): Observable.Transformer<T, T>
  fun <T> applyIOSingleSchedulers() : Single.Transformer<T, T>
  fun <T> applyComputationSingleSchedulers() : Single.Transformer<T, T>
  fun applyIOCompletableSchedulers() : Completable.CompletableTransformer
  fun applyComputationCompletableSchedulers() : Completable.CompletableTransformer
}