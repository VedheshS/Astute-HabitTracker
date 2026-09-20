package com.example.astute.data.local.converters

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import com.example.astute.data.local.entities.Frequency

class Converters {
    @TypeConverter
    fun fromLocalDate(date: LocalDate): String{
        return date.toString();
    }

    @TypeConverter
    fun toLocalDate(date: String): LocalDate{
        return LocalDate.parse(date);
    }

    @TypeConverter
    fun toLocalTime(time: String): LocalTime{
        return LocalTime.parse(time);
    }
    @TypeConverter
    fun fromLocalTime(time: LocalTime) : String{
        return time.toString();
    }


    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime): String{
        return dateTime.toString();
    }
    @TypeConverter
    fun toLocalDateTime(dateTime: String): LocalDateTime{
        return LocalDateTime.parse(dateTime);
    }

    @TypeConverter
    fun fromFrequency(frequency: Frequency): String{
        return frequency.name;
    }
    @TypeConverter
    fun toFrequency(value: String): Frequency{
        return Frequency.valueOf(value);
    }

    @TypeConverter
    fun fromIntList(list: List<Int>): String{
        return list.joinToString(",");
    }
    @TypeConverter
    fun toIntList(list: String): List<Int>{
        if(list.isBlank()) return emptyList();
        return list.split(",").map{it.toInt()};
    }
}