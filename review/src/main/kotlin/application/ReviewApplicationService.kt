package application

import domain.music.MusicId
import domain.reviewer.Review
import domain.reviewer.ReviewId
import domain.reviewer.ReviewerId

class ReviewApplicationService {
    fun registerReviewer(nickName: String, profileImage: String) {
    }

    fun getReviewer(nickName: String): ReviewerId {
        return ReviewerId()
    }

    fun reviewMusic(reviewerId: ReviewerId, musicId: MusicId, reviewText: String) {
    }

    fun findMyReviews(myId: ReviewerId): List<Review> {
        return emptyList()
    }

    fun fixReviewText(reviewId: ReviewId, reviewText: String) {
    }
}
