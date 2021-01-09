import application.ReviewApplicationService
import domain.music.MusicId
import domain.reviewer.ReviewId
import domain.reviewer.ReviewerId
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expectThat
import strikt.assertions.hasSize
import strikt.assertions.isEqualTo
import strikt.assertions.isNotEmpty

object ReviewApplicationServiceFeature : Spek({
    Feature("Review Management") {

        val reviewApplicationService by memoized { ReviewApplicationService() }

        Scenario("review music") {
            lateinit var reviewerId: ReviewerId
            val targetMusicId = MusicId("1")
            val reviewText = "이 노래는 무난하면서도 적당히 속도감이 있는게 좋아요. 2분 30초즈음에 하이라이트 부분이 매력포인트죠."

            Given("register reviewer") {
                val nickName = "Las"
                val profileImage = "https://magical.dev/static/las.jpg"
                reviewApplicationService.registerReviewer(nickName, profileImage)
                reviewerId = reviewApplicationService.getReviewer(nickName)
            }

            When("reviewer review music") {
                reviewApplicationService.reviewMusic(reviewerId, targetMusicId, reviewText)
            }

            Then("it can find artist id using name") {
                val myReviewList = reviewApplicationService.findMyReviews(reviewerId)
                expectThat(myReviewList).isNotEmpty().hasSize(1)
            }
        }

        Scenario("fix music review") {
            lateinit var reviewerId: ReviewerId
            lateinit var reviewId: ReviewId
            val targetMusicId = MusicId("1")
            val wrongReviewText = "이 노래는 무난하면서도 재미있고. 2분 30초즈음에 하이라이트 부분이 매력포인트죠."
            val rightReviewText = "이 노래는 무난하면서도 적당히 속도감이 있는게 좋아요. 2분 30초즈음에 하이라이트 부분이 매력포인트죠."

            Given("register reviewer") {
                val nickName = "Las"
                val profileImage = "https://magical.dev/static/las.jpg"
                reviewApplicationService.registerReviewer(nickName, profileImage)
                reviewerId = reviewApplicationService.getReviewer(nickName)
            }

            Given("already review music") {
                reviewApplicationService.reviewMusic(reviewerId, targetMusicId, wrongReviewText)
                reviewId = reviewApplicationService.findMyReviews(reviewerId).first().getId()
            }

            When("fix review text") {
                reviewApplicationService.fixReviewText(reviewId, rightReviewText)
            }

            Then("review text change to right text") {
                val review = reviewApplicationService.findMyReviews(reviewerId).first()
                expectThat(review.getReviewText()).isEqualTo(rightReviewText)
            }
        }
    }
})
