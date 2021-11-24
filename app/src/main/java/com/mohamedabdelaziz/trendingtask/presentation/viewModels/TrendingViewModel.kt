package com.mohamedabdelaziz.trendingtask.presentation.viewModels

import android.util.Log

import com.mohamedabdelaziz.trendingtask.data.repository.TrendingRepositoryRemoteImp
import com.mohamedabdelaziz.trendingtask.trendinghome.data.repository.TrendingRepositoryLocalImp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.mohamedabdelaziz.trendingtask.domain.entities.TrendingResponse
import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
@HiltViewModel
class TrendingViewModel  @Inject  constructor(private var trendingRepositoryRemoteImp: TrendingRepositoryRemoteImp, private var trendingRepositoryLocalImp: TrendingRepositoryLocalImp) : ViewModel() {
    var trendingResponseMutableLiveData = MutableLiveData<List<TrendingResponse>>()
     lateinit var trendingLiveData: LiveData<List<TrendingResponse>>

      fun getTrendingListRemote() {
        trendingRepositoryRemoteImp.getTrendingList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ trendingList ->
                    trendingResponseMutableLiveData.value = trendingList
                    deleteAllTrendingList()
                    insertTrendingList(trendingList)
                }) { errors: Throwable -> Log.e(TAG, errors.message!!) }
    }

    private fun insertTrendingList(trendingList: List<TrendingResponse>) {
        trendingRepositoryLocalImp.insertTrendingList(trendingList)
    }

    private fun deleteAllTrendingList() {
        trendingRepositoryLocalImp.deleteAllTrendingList()
    }

    fun getTrendingListLocal(): LiveData<List<TrendingResponse>>{
            trendingLiveData = trendingRepositoryLocalImp.getTrendingList()
            return trendingLiveData
        }

    companion object {
        private const val TAG = "TrendingViewModel"
    }
}