
package com.example.astute.data.repository

import com.example.astute.data.local.dao.HabitDao
import com.example.astute.data.local.entities.Habit
import java.time.LocalDate

class HabitRepository(
    private val habitDao: HabitDao
) {

    suspend fun addHabit(habit: Habit): Long {
        return habitDao.insertHabit(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        habitDao.updateHabit(habit)
    }

    suspend fun getHabitById(habitId: Long): Habit? {
        return habitDao.getHabitById(habitId)
    }

    suspend fun getHabitsForDate(date: LocalDate): List<Habit> {
        return habitDao.getHabitForDate(date)
    }

    suspend fun deleteHabit(
        habitId: Long,
        deletedAt: LocalDate
    ) {
        habitDao.softDeleteHabit(habitId, deletedAt)
    }
}
