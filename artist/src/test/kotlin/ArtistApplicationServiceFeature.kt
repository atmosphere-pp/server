import adapter.InMemoryArtistRepository
import adapter.InMemoryMusicRepository
import adapter.InMemoryVocaloidRepository
import application.ArtistApplicationService
import domain.artist.ArtistId
import domain.vocaloid.VocaloidId
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.*
import strikt.api.*
import strikt.assertions.*

object ArtistApplicationServiceFeature : Spek({
    Feature("Management Artist") {
        val applicationService by memoized {
            ArtistApplicationService(
                InMemoryMusicRepository(),
                InMemoryArtistRepository(),
                InMemoryVocaloidRepository(),
            )
        }

        Scenario("enroll new artist") {

            When("enroll michael jackson") {
                applicationService.enrollNewArtist("michael jackson")
            }

            Then("it can find artist id using name") {
                val createdArtistId = applicationService.searchArtistByName("michael jackson")
                expectThat(createdArtistId).isA<ArtistId>()
            }
        }

        Scenario("change artist name") {
            lateinit var targetArtistId: ArtistId

            val enrolledName = "michael jackson"
            val newName = "Las"

            Given("already enrolled artist") {
                applicationService.enrollNewArtist(enrolledName)
                targetArtistId = applicationService.searchArtistByName(enrolledName)
            }

            When("artist change name to others") {
                applicationService.changeName(targetArtistId, newName)
            }

            Then("it can find artist id using new name") {
                val artistId = applicationService.searchArtistByName(newName)
                expectThat(artistId)
                    .isA<ArtistId>()
                    .isEqualTo(targetArtistId)
            }

            Then("it can't find artist id using before name('$enrolledName')") {
//                 TODO: uncomment this assertion.
//                expectCatching { applicationService.searchByName(enrolledName) }
//                        .isFailure()
//                        .isA<domain.artist.NotFoundArtistByName>()
            }
        }
    }

    Feature("Present Music") {
        val applicationService by memoized {
            ArtistApplicationService(
                InMemoryMusicRepository(),
                InMemoryArtistRepository(),
                InMemoryVocaloidRepository(),
            )
        }

        Scenario("artist present new music") {
            lateinit var artistId: ArtistId

            val artistName = "michael jackson"
            val title = "Billie Jean"
            val explain = "Billie Jean is a song in my first album."
            val sourceLink = "https://www.youtube.com/watch?v=Zi_XLOBDo_Y"

            Given("artist already enrolled") {
                applicationService.enrollNewArtist(artistName)
                artistId = applicationService.searchArtistByName(artistName)
            }

            When("present new music") {
                applicationService.presentNewMusic(artistId, title, explain, sourceLink)
            }

            Then("music can find artist's music list") {
                val musicList = applicationService.getArtistMusicList(artistId)
                expectThat(musicList).hasSize(1)
            }
        }

        Scenario("several artist present new music") {
            lateinit var artistList: List<ArtistId>

            val title = "Cooler Than the Cool"
            val explain = "Cooler Than the Cool은 4 the youth에 포함된 노래입니다."
            val sourceLink = "https://www.youtube.com/watch?v=myHhpAKor70"

            Given("both artist already enrolled") {
                applicationService.enrollNewArtist("JUSTHIS")
                applicationService.enrollNewArtist("Paloalto")
                val firstArtistId = applicationService.searchArtistByName("JUSTHIS")
                val secondArtistId = applicationService.searchArtistByName("Paloalto")
                artistList = listOf(firstArtistId, secondArtistId)
            }

            When("present new music with several artist") {
                applicationService.presentNewMusicWithSeveralArtist(
                    artistList,
                    title,
                    explain,
                    sourceLink
                )
            }

            Then("music can find both artist's music list") {
                val firstMusicList = applicationService.getArtistMusicList(artistList[0])
                expectThat(firstMusicList).hasSize(1)

                val secondMusicList = applicationService.getArtistMusicList(artistList[1])
                expectThat(secondMusicList).hasSize(1)
            }
        }

        Scenario("artist present new music that using vocaloid") {
            lateinit var artistId: ArtistId
            lateinit var vocaloidId: VocaloidId

            val title = "Teo"
            val explain = "Teo is an exciting band-type song."
            val sourceLink = "https://www.youtube.com/watch?v=bmkY2yc1K7Q"

            Given("enroll artist") {
                val artistName = "Omoi"
                applicationService.enrollNewArtist(artistName)
                artistId = applicationService.searchArtistByName(artistName)
            }

            Given("enroll vocaloid") {
                val vocaloidName = "Hatsune Miku"
                applicationService.enrollNewVocaloid(vocaloidName)
                vocaloidId = applicationService.findVocaloidByName(vocaloidName)
            }

            When("present new vocaloid music") {
                applicationService.presentNewMusicWithVocaloid(
                    artistId,
                    vocaloidId,
                    title,
                    explain,
                    sourceLink
                )
            }

            Then("music can find artist's music list") {
                val musicList = applicationService.getArtistMusicList(artistId)
                expectThat(musicList).hasSize(1)
            }

            Then("music can find vocaloid music list") {
                val musicList = applicationService.getVocaloidMusicList(vocaloidId)
                expectThat(musicList).hasSize(1)
            }
        }
    }
})
