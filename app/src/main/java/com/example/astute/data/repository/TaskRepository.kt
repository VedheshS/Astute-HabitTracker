
package com.example.astute.data.repository

import com.example.astute.data.local.dao.TaskDao
import com.example.astute.data.local.entities.Task
import java.time.LocalDate
import java.time.LocalDateTime

class TaskRepository(
    private val taskDao: TaskDao
) {

    suspend fun addTask(task: Task): Long {
        return taskDao.insertTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun getTaskById(taskId: Long): Task? {
        return taskDao.getTaskById(taskId)
    }

    suspend fun getTasksForDate(date: LocalDate): List<Task> {
        return taskDao.getTasksForDate(date)
    }

    suspend fun getAllActiveTasks(): List<Task> {
        return taskDao.getAllActiveTasks()
    }

    suspend fun getOverdueTasks(today: LocalDate): List<Task> {
        return taskDao.getOverdueTasks(today)
    }

    suspend fun completeTask(
        taskId: Long,
        completedAt: LocalDateTime
    ) {
        taskDao.completeTask(taskId, completedAt)
    }

    suspend fun undoTaskCompletion(taskId: Long) {
        taskDao.undoTaskCompletion(taskId)
    }

    suspend fun deleteTask(
        taskId: Long,
        deletedAt: LocalDate
    ) {
        taskDao.softDeleteTask(taskId, deletedAt)
    }
}
