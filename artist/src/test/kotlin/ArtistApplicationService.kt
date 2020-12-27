import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.*
import strikt.api.*
import strikt.assertions.*

object ArtistApplicationServiceFeature : Spek({
    Feature("ArtistApplicationService") {
        val applicationService by memoized { ArtistApplicationService() }

        Scenario("enroll new artist") {
            When("enroll michael jackson") {
                applicationService.enrollNewArtist("michael jackson")
            }

            Then("it can find artist id using name") {
                val artistId = applicationService.searchByName("michael jackson")
                expectThat(artistId).isA<ArtistId>()
            }
        }
    }
})
