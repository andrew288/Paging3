package com.example.paging3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.adapters.CountryAdapter
import com.example.paging3.data.db.CountryDao
import com.example.paging3.data.db.CountryDatabase
import com.example.paging3.data.paging.CountryViewModel
import com.example.paging3.data.paging.CountryViewModelFactory
import com.example.paging3.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var dao: CountryDao
    private val viewModel: CountryViewModel by viewModels { CountryViewModelFactory(dao) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dao = CountryDatabase.getInstance(this).countryDao()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val pagingAdapter = CountryAdapter()

        recyclerView.adapter = pagingAdapter
        recyclerView.apply {
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

        lifecycleScope.launch {
            viewModel.items().collectLatest { pageData ->
                pagingAdapter.submitData(pageData)
            }
        }
    }
}