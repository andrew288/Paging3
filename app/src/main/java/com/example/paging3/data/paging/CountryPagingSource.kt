package com.example.paging3.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3.data.db.CountryDao
import com.example.paging3.data.model.Country
import kotlinx.coroutines.delay

class CountryPagingSource(private val dao: CountryDao): PagingSource<Int, Country>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Country> {
        val page = params.key ?: 0

        return try {
            val entities = dao.getPagedList()

            // simulate page loading
            if (page != 0) delay(1000)

            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Country>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}