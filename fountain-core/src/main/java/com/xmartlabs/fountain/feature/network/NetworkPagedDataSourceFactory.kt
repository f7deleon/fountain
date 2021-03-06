package com.xmartlabs.fountain.feature.network

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PagedList
import com.xmartlabs.fountain.ListResponse
import com.xmartlabs.fountain.adapter.BaseNetworkDataSourceAdapter
import java.util.concurrent.Executor

internal class NetworkPagedDataSourceFactory<T, ServiceResponse : ListResponse<out T>> (
    private val firstPage: Int,
    private val ioServiceExecutor: Executor,
    private val pagedListConfig: PagedList.Config,
    private val networkDataSourceAdapter: BaseNetworkDataSourceAdapter<ServiceResponse>
) : DataSource.Factory<Int, T>() {
  val sourceLiveData = MutableLiveData<NetworkPagedDataSource<T, ServiceResponse>>()
  private val resetDataList = MutableLiveData<ServiceResponse>()

  fun resetData() = sourceLiveData.value?.resetData(resetDataList)

  fun retry() = sourceLiveData.value?.retry?.invoke()

  override fun create(): DataSource<Int, T> {
    val source = NetworkPagedDataSource(
        firstPage = firstPage,
        ioServiceExecutor = ioServiceExecutor,
        pagedListConfig = pagedListConfig,
        networkDataSourceAdapter = networkDataSourceAdapter,
        initData = resetDataList.value
    )
    resetDataList.postValue(null)
    sourceLiveData.postValue(source)
    return source
  }
}
