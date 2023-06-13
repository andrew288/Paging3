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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val countryDatabase = CountryDatabase.getInstance(this)
    }
}