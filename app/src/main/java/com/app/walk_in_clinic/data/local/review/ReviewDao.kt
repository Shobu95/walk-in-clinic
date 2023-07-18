package com.app.walk_in_clinic.data.local.review

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveReview(reviewEntity: ReviewEntity)

    @Query("SELECT * FROM review_table where appointmentId = :appointId")
    fun getReview(appointId: Int): ReviewEntity?
}