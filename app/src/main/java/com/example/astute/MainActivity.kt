package com.example.astute

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.astute.data.repository.RepositoryProvider
import com.example.astute.viewmodel.HabitViewModel
import com.example.astute.viewmodel.HabitViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var habitViewModel: HabitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        val habitRepository =
            RepositoryProvider.getHabitRepository(applicationContext)

        val habitCompletionRepository =
            RepositoryProvider.getHabitCompletionRepository(applicationContext)

        val factory = HabitViewModelFactory(
            habitRepository,
            habitCompletionRepository
        )

        habitViewModel = ViewModelProvider(
            this,
            factory
        )[HabitViewModel::class.java]

        habitViewModel.testSoftDelete()
    }
}
