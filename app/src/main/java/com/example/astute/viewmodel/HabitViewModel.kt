package com.example.astute.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astute.data.local.entities.Habit
import com.example.astute.data.repository.HabitRepository
import com.example.astute.data.repository.HabitCompletionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class HabitViewModel(
    private val habitRepository: HabitRepository,
    private val habitCompletionRepository: HabitCompletionRepository): ViewModel(){
    private val _habits = MutableStateFlow<List<Habit>>(emptyList())
    val habits: StateFlow<List<Habit>> = _habits.asStateFlow()

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    fun selectedDate(date: LocalDate){
        _selectedDate.value = date
        loadHabitsForDate(date)
    }
    fun loadHabitsForDate(date: LocalDate = _selectedDate.value){
        viewModelScope.launch {
            _habits.value = habitRepository.getHabitsForDate(date)
        }
    }

    fun addHabit(habit: Habit){
        viewModelScope.launch{
            habitRepository.addHabit(habit)
            loadHabitsForDate()
        }
    }

    fun updateHabit(habit: Habit){
        viewModelScope.launch {
            habitRepository.updateHabit(habit)
            loadHabitsForDate()
        }
    }

    fun deleteHabit(habitId: Long){
        viewModelScope.launch {
            habitRepository.deleteHabit(
                habitId = habitId,
                deletedAt = _selectedDate.value
            )
            loadHabitsForDate()
        }
    }


    fun testGetHabitsForDate() {
        viewModelScope.launch {

            val today = LocalDate.now()

            val testHabit = Habit(
                name = "Date Test Habit",
                description = "Testing date retrieval",
                icon = "📅",
                color = "#2196F3",
                frequency = com.example.astute.data.local.entities.Frequency.DAILY,
                scheduleDays = emptyList(),
                startDate = today,
                endDate = null,
                isDeleted = false,
                deletedAt = null,
                reminderEnabled = false,
                reminderTime = null,
                createdAt = java.time.LocalDateTime.now()
            )

            val id = habitRepository.addHabit(testHabit)

            val habitsForToday =
                habitRepository.getHabitsForDate(today)

            println("DATE TEST: $habitsForToday")
        }
    }


    fun testSoftDelete() {
        viewModelScope.launch {

            val startDate = LocalDate.of(2026, 9, 25)
            val deleteDate = LocalDate.of(2026, 9, 27)

            val testHabit = Habit(
                name = "Delete Test Habit",
                description = "Testing soft delete",
                icon = "🗑️",
                color = "#F44336",
                frequency = com.example.astute.data.local.entities.Frequency.DAILY,
                scheduleDays = emptyList(),
                startDate = startDate,
                endDate = null,
                isDeleted = false,
                deletedAt = null,
                reminderEnabled = false,
                reminderTime = null,
                createdAt = java.time.LocalDateTime.now()
            )

            val habitId = habitRepository.addHabit(testHabit)

            val beforeDelete =
                habitRepository.getHabitsForDate(
                    LocalDate.of(2026, 9, 26)
                )

            println("DELETE TEST BEFORE: $beforeDelete")

            habitRepository.deleteHabit(
                habitId = habitId,
                deletedAt = deleteDate
            )

            val onDeleteDate =
                habitRepository.getHabitsForDate(deleteDate)

            val afterDelete =
                habitRepository.getHabitsForDate(
                    LocalDate.of(2026, 9, 28)
                )

            println("DELETE TEST ON DATE: $onDeleteDate")
            println("DELETE TEST AFTER: $afterDelete")
        }
    }



    fun testHabitCompletion() {
        viewModelScope.launch {

            val today = LocalDate.now()

            // Create a test habit
            val testHabit = Habit(
                name = "Completion Test Habit",
                description = "Testing habit completion",
                icon = "✅",
                color = "#4CAF50",
                frequency = com.example.astute.data.local.entities.Frequency.DAILY,
                scheduleDays = emptyList(),
                startDate = today,
                endDate = null,
                isDeleted = false,
                deletedAt = null,
                reminderEnabled = false,
                reminderTime = null,
                createdAt = java.time.LocalDateTime.now()
            )

            val habitId = habitRepository.addHabit(testHabit)

            // Create a completion
            val completion = com.example.astute.data.local.entities.HabitCompletion(
                habitId = habitId,
                date = today,
                completedAt = java.time.LocalDateTime.now()
            )

            val completionId =
                habitCompletionRepository.addCompletion(completion)

            println("COMPLETION TEST INSERTED: id=$completionId")

            // Read the completion back
            val savedCompletion =
                habitCompletionRepository.getCompletion(
                    habitId = habitId,
                    date = today
                )

            println("COMPLETION TEST READ: $savedCompletion")

            // Remove the completion
            habitCompletionRepository.removeCompletion(
                habitId = habitId,
                date = today
            )

            // Check whether it was removed
            val removedCompletion =
                habitCompletionRepository.getCompletion(
                    habitId = habitId,
                    date = today
                )

            println("COMPLETION TEST AFTER REMOVE: $removedCompletion")
        }
    }


}