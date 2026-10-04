package com.example.astute.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.astute.data.local.entities.Task
import java.time.LocalDate

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task: Task): Long

    @Update
    suspend fun updateTask(task: Task)

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Long): Task?

    @Query("""
        SELECT * FROM tasks
        WHERE dueDate = :date
        AND (isDeleted = 0 OR deletedAt > :date)
        ORDER BY dueTime ASC
    """)
    suspend fun getTasksForDate(date: LocalDate): List<Task>

    @Query("""
        SELECT * FROM tasks
        WHERE isDeleted = 0
        ORDER BY dueDate ASC, dueTime ASC
    """)
    suspend fun getAllActiveTasks(): List<Task>

    @Query("""
        SELECT * FROM tasks
        WHERE isDeleted = 0
        AND isCompleted = 0
        AND dueDate < :today
        ORDER BY dueDate ASC, dueTime ASC
    """)
    suspend fun getOverdueTasks(today: LocalDate): List<Task>

    @Query("""
        UPDATE tasks
        SET isCompleted = 1, completedAt = :completedAt
        WHERE id = :taskId
    """)
    suspend fun completeTask(
        taskId: Long,
        completedAt: java.time.LocalDateTime
    )

    @Query("""
        UPDATE tasks
        SET isCompleted = 0, completedAt = NULL
        WHERE id = :taskId
    """)
    suspend fun undoTaskCompletion(taskId: Long)

    @Query("""
        UPDATE tasks
        SET isDeleted = 1, deletedAt = :deletedAt
        WHERE id = :taskId
    """)
    suspend fun softDeleteTask(
        taskId: Long,
        deletedAt: LocalDate
    )
}