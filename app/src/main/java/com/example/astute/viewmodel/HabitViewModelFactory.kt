
package com.example.astute.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astute.data.repository.HabitCompletionRepository
import com.example.astute.data.repository.HabitRepository

class HabitViewModelFactory(
    private val habitRepository: HabitRepository,
    private val habitCompletionRepository: HabitCompletionRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(HabitViewModel::class.java)) {
            return HabitViewModel(
                habitRepository,
                habitCompletionRepository
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class: ${modelClass.name}"
        )
    }
}
