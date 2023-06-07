package com.example.paging3.data.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3.data.db.CountryDao
import com.example.paging3.data.model.Country
import kotlinx.coroutines.flow.Flow

class CountryViewModel(private val dao: CountryDao): ViewModel() {

    fun items(): Flow<PagingData<Country>> {
        val pager = Pager(PagingConfig(pageSize = 20, enablePlaceholders = false, initialLoadSize = 20)){
            CountryPagingSource(dao)
        }.flow.cachedIn(viewModelScope)

        return pager
    }
}

class CountryViewModelFactory(
    private val dao: CountryDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CountryViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}