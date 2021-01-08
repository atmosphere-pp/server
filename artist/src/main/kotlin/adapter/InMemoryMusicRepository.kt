package adapter

import domain.artist.ArtistId
import domain.music.Music
import domain.music.MusicId
import domain.music.MusicRepository
import domain.music.NotFoundMusic
import domain.vocaloid.VocaloidId

class InMemoryMusicRepository : MusicRepository {
    private val inMemoryCache: MutableMap<MusicId, Music> = mutableMapOf()
    override fun save(music: Music) {
        if (!music.isInitializeId()) {
            val nextId = MusicId(this.inMemoryCache.values.size.toString())
            music.setId(nextId)
        }
        this.inMemoryCache[music.getId()] = music
    }

    override fun getMusicById(id: MusicId): Music {
        return this.inMemoryCache[id] ?: throw NotFoundMusic("can't find by id: $id")
    }

    override fun findMusicByName(name: String): List<Music> {
        TODO("Not yet implemented")
    }

    override fun findMusicByVocaloid(vocaloidId: VocaloidId): List<Music> {
        return this.inMemoryCache.values.filter { music -> music.isUsed(vocaloidId) }
    }

    override fun findMusicByArtistId(id: ArtistId): List<Music> {
        return this.inMemoryCache.values.filter { music -> music.artistList.contains(id) }
    }
}
