package com.app.walk_in_clinic.data.repository

import com.app.walk_in_clinic.data.local.review.ReviewDao
import com.app.walk_in_clinic.data.local.review.ReviewEntity
import com.app.walk_in_clinic.domain.models.Review
import javax.inject.Inject

class ReviewRepository
@Inject constructor(
    private val reviewDao: ReviewDao
) {

    fun getReview(appointmentId: Int): Review? {
        return reviewDao.getReview(appointmentId)?.asDomainModel()
    }

    fun saveReview(review: Review) {
        reviewDao.saveReview(review.asEntityModel())
    }
}

fun ReviewEntity.asDomainModel(): Review {
    return Review(
        id = this.id,
        appointmentId = this.appointmentId,
        rating = this.rating,
        review = this.review
    )
}

fun Review.asEntityModel(): ReviewEntity {
    return ReviewEntity(
        id = this.id,
        appointmentId = this.appointmentId,
        rating = this.rating,
        review = this.review
    )
}