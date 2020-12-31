import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.*
import strikt.api.*
import strikt.assertions.*

object ArtistApplicationServiceFeature : Spek({
    Feature("ArtistApplicationService") {
        val applicationService by memoized { ArtistApplicationService() }

        Scenario("enroll new artist") {
            lateinit var createdArtistId: ArtistId

            When("enroll michael jackson") {
                createdArtistId = applicationService.enrollNewArtist("michael jackson")
            }

            Then("it can find artist id using name") {
                expectThat(createdArtistId).isA<ArtistId>()
            }
        }

        Scenario("change artist name") {
            lateinit var targetArtistId: ArtistId

            val enrolledName = "michael jackson"
            val newName = "Las"

            Given("already enrolled artist") {
                targetArtistId = applicationService.enrollNewArtist(enrolledName)
            }

            When("change artist name to '$newName'") {
                applicationService.changeName(targetArtistId, newName)
            }

            Then("it can find artist id using new name('$newName').") {
                val artistId = applicationService.searchByName(newName)
                expectThat(artistId)
                        .isA<ArtistId>()
                        .isEqualTo(targetArtistId)
            }

            Then("it can't find artist id using before name('$enrolledName')") {
//                 TODO: uncomment this assertion.
//                expectCatching { applicationService.searchByName(enrolledName) }
//                        .isFailure()
//                        .isA<NotFoundArtistByName>()
            }
        }

        Scenario("artist present new music") {
            lateinit var artistId: ArtistId
            lateinit var newMusicId: MusicId

            val artistName = "michael jackson"
            val title = "Billie Jean"
            val explain = "Billie Jean is a song in my first album."
            val sourceLink = "https://www.youtube.com/watch?v=Zi_XLOBDo_Y"

            Given("enroll artist('$artistName')") {
                artistId = applicationService.enrollNewArtist(artistName)
            }

            When("present new music('$title')") {
                newMusicId = applicationService.presentNewMusic(artistId, title, explain, sourceLink)
            }

            Then("return new music id") {
                expectThat(newMusicId).isA<MusicId>()
            }
        }

        Scenario("several artist present new music") {
            lateinit var artistList: List<ArtistId>
            lateinit var createdMusicId: MusicId

            val title = "Cooler Than the Cool"
            val explain = "Cooler Than the Cool은 4 the youth에 포함된 노래입니다."
            val sourceLink = "https://www.youtube.com/watch?v=myHhpAKor70"

            Given("enroll artist('JUSTHIS', 'Paloalto')") {
                val firstArtistId = applicationService.enrollNewArtist("JUSTHIS")
                val secondArtistId = applicationService.enrollNewArtist("Paloalto")
                artistList = listOf(firstArtistId, secondArtistId)
            }

            When("present new music with several artist") {
                createdMusicId = applicationService.presentNewMusicWithSeveralArtist(
                        artistList,
                        title,
                        explain,
                        sourceLink
                )
            }

            Then("it can find artist id using name") {
                expectThat(createdMusicId).isA<MusicId>()
            }
        }

        Scenario("artist present new music with other artist featuring") {
            lateinit var artistId: ArtistId
            lateinit var featuringArtistIdList: List<ArtistId>
            lateinit var newMusicId: MusicId

            val artistName = "JUSTHIS"
            val title = "Cooler Than the Cool"
            val explain = "Cooler Than the Cool은 4 the youth에 포함된 노래입니다."
            val sourceLink = "https://www.youtube.com/watch?v=myHhpAKor70"

            Given("enroll artist and featuring artist") {
                artistId = applicationService.enrollNewArtist(artistName)
                val featuringArtistId = applicationService.enrollNewArtist("Huckleberry P")
                featuringArtistIdList = listOf(featuringArtistId)
            }

            When("present new music('$title') with featuring artist") {
                newMusicId = applicationService.presentNewMusicWithFeaturing(
                        artistId,
                        featuringArtistIdList,
                        title,
                        explain,
                        sourceLink
                )
            }

            Then("return new music id") {
                expectThat(newMusicId).isA<MusicId>()
            }
        }

        Scenario("artist present new music that using vocaloid") {
            lateinit var artistId: ArtistId
            lateinit var newMusicId: MusicId
            lateinit var vocaloidId: VocaloidId

            val artistName = "Omoi"
            val title = "Teo"
            val explain = "Teo is an exciting band-type song."
            val sourceLink = "https://www.youtube.com/watch?v=bmkY2yc1K7Q"

            Given("enroll artist") {
                artistId = applicationService.enrollNewArtist(artistName)
            }

            Given("enroll vocaloid") {
                vocaloidId = applicationService.enrollNewVocaloid("Hatsune Miku")
            }

            When("present new vocaloid music") {
                newMusicId = applicationService.presentNewVocaloidSong(
                        artistId,
                        vocaloidId,
                        title,
                        explain,
                        sourceLink
                )
            }

            Then("return new music id") {
                expectThat(newMusicId).isA<MusicId>()
            }
        }
    }
})
