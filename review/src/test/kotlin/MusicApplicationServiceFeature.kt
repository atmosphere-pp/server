import application.MusicApplicationService
import domain.music.Music
import domain.music.MusicId
import domain.reviewer.ReviewerId
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import strikt.api.expectThat
import strikt.assertions.hasSize
import strikt.assertions.isNotEmpty

object MusicApplicationServiceFeature : Spek({
    Feature("Music Search Feature") {

        val musicApplicationService by memoized { MusicApplicationService() }

        Scenario("show all music") {
            lateinit var allMusic: List<Music>

            Given("enroll 1 music") {
                // TODO: implement enroll music in adapter.
            }

            When("show all music") {
                allMusic = musicApplicationService.showAllMusic()
            }

            Then("music list size is 1") {
                expectThat(allMusic).isNotEmpty().hasSize(1)
            }
        }

        Scenario("star music") {
            lateinit var reviewerId: ReviewerId
            lateinit var musicId: MusicId

            Given("get enrolled music") {
                // TODO: implement enroll music in adapter.
            }

            When("show all music") {
                musicApplicationService.starMusic(reviewerId, musicId)
            }

            Then("can find music in stared music list") {
                val staredMusicList = musicApplicationService.showStaredMusic(reviewerId)
                expectThat(staredMusicList).isNotEmpty().hasSize(1)
            }
        }
    }
})
