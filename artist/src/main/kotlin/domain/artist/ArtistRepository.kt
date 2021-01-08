package domain.artist

interface ArtistRepository {
    fun save(artist: Artist)
    fun getArtistById(id: ArtistId): Artist
    fun findArtistByName(name: String): List<Artist>
}
