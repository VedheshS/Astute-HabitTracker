package com.example.astute.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.astute.data.local.entities.Habit
import java.time.LocalDate

@Dao
interface HabitDao {
    @Insert
    suspend fun insertHabit(habit: Habit): Long

    @Update
    suspend fun updateHabit(habit: Habit)

    @Query("SELECT * FROM habits WHERE id = :habitId")
    suspend fun getHabitById(habitId: Long): Habit?

    @Query("""SELECT * FROM habits
         WHERE startDate<= :date 
         AND (isDeleted == 0 OR deletedAt > :date)
    """)
    suspend fun getHabitForDate(date: LocalDate): List<Habit>
    @Query("""
        UPDATE habits 
        SET isDeleted = 1, deletedAt = :deletedAt
        WHERE id = :habitId
    """)
    suspend fun softDeleteHabit(
        habitId: Long,
        deletedAt: LocalDate
    )
}