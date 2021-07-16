package com.castro.visitascard.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VisitasCardDAO {

    @Query("Select * FROM VisitasCard")
    fun getAll() : LiveData<List<VisitasCard>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(visitasCard: VisitasCard)






}