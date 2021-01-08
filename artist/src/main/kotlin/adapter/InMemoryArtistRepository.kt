package adapter

import domain.artist.Artist
import domain.artist.ArtistId
import domain.artist.ArtistRepository
import domain.artist.NotFoundArtistById

class InMemoryArtistRepository : ArtistRepository {
    private val inMemoryCache: MutableMap<ArtistId, Artist> = mutableMapOf()

    override fun save(artist: Artist) {
        if (!artist.isInitializeId()) {
            val nextId = ArtistId(this.inMemoryCache.values.size.toString())
            artist.setId(nextId)
        }
        this.inMemoryCache[artist.getId()] = artist
    }

    override fun getArtistById(id: ArtistId): Artist {
        return inMemoryCache[id] ?: throw NotFoundArtistById(id)
    }

    override fun findArtistByName(name: String): List<Artist> {
        return this.inMemoryCache.values.filter { artist -> artist.name == name }
    }
}
