package com.example.astute.data.repository

import com.example.astute.data.local.dao.HabitCompletionDao
import com.example.astute.data.local.entities.HabitCompletion
import java.time.LocalDate

class HabitCompletionRepository(
    private val habitCompletionDao: HabitCompletionDao
) {

    suspend fun addCompletion(
        completion: HabitCompletion
    ): Long {
        return habitCompletionDao.insertCompletion(completion)
    }

    suspend fun getCompletion(
        habitId: Long,
        date: LocalDate
    ): HabitCompletion? {
        return habitCompletionDao.getCompletion(habitId, date)
    }

    suspend fun getCompletionsForHabit(
        habitId: Long
    ): List<HabitCompletion> {
        return habitCompletionDao.getCompletionsForHabit(habitId)
    }

    suspend fun removeCompletion(
        habitId: Long,
        date: LocalDate
    ) {
        habitCompletionDao.deleteCompletion(habitId, date)
    }
}
