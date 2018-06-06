package com.xmartlabs.xlpagingbypagenumber

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

/**
 * Data class that is necessary for a UI to show a listing and interact w/ the rest of the system
 * This class was taken from a [google example app](https://github.com/googlesamples/android-architecture-components/blob/5bd17584e9f7fdf1db67626d9abbf81ce4be96c4/PagingWithNetworkSample/app/src/main/java/com/android/example/paging/pagingwithnetwork/reddit/repository/Listing.kt).
 */
data class Listing<T>(
    // the LiveData of paged lists for the UI to observe
    val pagedList: LiveData<PagedList<T>>,
    // represents the network request status to show to the user
    val networkState: LiveData<NetworkState>,
    // represents the refresh status to show to the user. Separate from networkState, this
    // value is importantly only when refresh is requested.
    val refreshState: LiveData<NetworkState>,
    // refreshes the whole data and fetches it from scratch.
    val refresh: () -> Unit,
    // retries any failed requests.
    val retry: () -> Unit
)
