package com.mohamedabdelaziz.trendingtask.presentation.ui


import dagger.hilt.android.AndroidEntryPoint
import com.mohamedabdelaziz.trendingtask.core.presentation.ui.BaseActivity
import com.mohamedabdelaziz.trendingtask.presentation.viewModels.TrendingViewModel
import com.mohamedabdelaziz.trendingtask.presentation.adapters.TrendingAdapter
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamedabdelaziz.trendingtask.R
import com.mohamedabdelaziz.trendingtask.core.presentation.utils.constants.ConstantSort
import androidx.databinding.DataBindingUtil
import android.view.Menu
import com.mohamedabdelaziz.trendingtask.domain.entities.TrendingResponse
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import com.mohamedabdelaziz.trendingtask.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private var binding: ActivityMainBinding? = null
     private var trendingAdapter: TrendingAdapter? = null
    private var isConnectedForMenu = false
    private  val trendingViewModel by viewModels<TrendingViewModel>()
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        binding?.trendRecyclerView?.layoutManager = LinearLayoutManager(this)
        binding?.trendSwipeRefresh?.setColorSchemeResources(R.color.black)
        binding?.trendRecyclerView?.adapter = trendingAdapter
        binding?.trendSwipeRefresh?.setOnRefreshListener {
            if (isConnectedForMenu) getTrendingDataRemote(ConstantSort.DEFAULT_SORT) else getTrendingDataLocal(ConstantSort.DEFAULT_SORT)
            binding?.trendSwipeRefresh?.isRefreshing = false
        }
        binding?.retryBtn?.setOnClickListener {
            if (isConnectedForMenu) {
                visibleTrendRecyclerView()
                getTrendingDataRemote(ConstantSort.DEFAULT_SORT)
            } else visibleNoInternet()
        }
        binding?.offlineModeBtn?.setOnClickListener {
            visibleTrendRecyclerView()
            getTrendingDataLocal(ConstantSort.DEFAULT_SORT)
        }
    }

    override fun onConnectionSuccess() {
        visibleNoInternet()
        isConnectedForMenu = true
        visibleTrendRecyclerView()
    }

    override fun onConnectionFailed() {
        isConnectedForMenu = false
        visibleNoInternet()
    }

    private fun visibleTrendRecyclerView() {
        binding?.noInternetConstraintLayout?.visibility = View.GONE
        binding?.shimmerLayout?.visibility = View.VISIBLE
        binding?.trendRecyclerView?.visibility = View.VISIBLE
        binding?.shimmerLayout?.startShimmer()
        getTrendingDataRemote(ConstantSort.DEFAULT_SORT)
    }

    private fun visibleNoInternet() {
        binding?.noInternetConstraintLayout?.visibility = View.VISIBLE
        binding?.shimmerLayout?.visibility = View.GONE
        binding?.trendRecyclerView?.visibility = View.GONE
    }

    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding?.trendingToolbar)
        trendingAdapter = TrendingAdapter(this)
    }

     private fun getTrendingDataRemote(sortType: Int) {
        binding?.shimmerLayout?.startShimmer()
        trendingViewModel.getTrendingListRemote()
        trendingViewModel.trendingResponseMutableLiveData.observe(this, { trendingList: List<TrendingResponse> ->
            when (sortType) {
                ConstantSort.DEFAULT_SORT -> trendingAdapter?.setList(trendingList)
                ConstantSort.SORT_NAME -> {
                    val sortedWithName = trendingList.sortedWith(compareBy({ it.name }, { it.name }))
                    trendingAdapter?.setList(sortedWithName)
                }
                ConstantSort.SORT_STAR -> {
                    val sortedWithStars = trendingList.sortedWith(compareBy({ it.stars }, { it.stars }))
                    trendingAdapter?.setList(sortedWithStars )
                }
            }
            binding?.shimmerLayout?.stopShimmer()
            binding?.shimmerLayout?.visibility = View.GONE
        })
    }

     private fun getTrendingDataLocal(sortType: Int) {
        binding?.shimmerLayout?.startShimmer()
        trendingViewModel.getTrendingListLocal()
        trendingViewModel.getTrendingListLocal().observe(this, { trendingList: List<TrendingResponse> ->
            when (sortType) {
                ConstantSort.DEFAULT_SORT -> trendingAdapter?.setList(trendingList )
                ConstantSort.SORT_NAME -> {
                     val sortedWithName = trendingList.sortedWith(compareBy({ it.name }, { it.name }))
                    trendingAdapter!!.setList(sortedWithName )
                }
                ConstantSort.SORT_STAR -> {
                    val sortedWithStars = trendingList.sortedWith(compareBy({ it.stars }, { it.stars }))
                    trendingAdapter!!.setList(sortedWithStars )
                }
            }
            binding?.shimmerLayout?.stopShimmer()
            binding?.shimmerLayout?.visibility = View.GONE
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.sort_menu, menu)
        return true
    }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == R.id.sortByName) {
            if (isConnectedForMenu) getTrendingDataRemote(ConstantSort.SORT_NAME) else getTrendingDataLocal(ConstantSort.SORT_NAME)
            return true
        } else if (itemId == R.id.sortByStar) {
            if (isConnectedForMenu) getTrendingDataRemote(ConstantSort.SORT_STAR) else getTrendingDataLocal(ConstantSort.SORT_STAR)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}