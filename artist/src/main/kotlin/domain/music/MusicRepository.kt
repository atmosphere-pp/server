package domain.music

import domain.artist.ArtistId
import domain.vocaloid.VocaloidId

interface MusicRepository {
    fun save(music: Music)
    fun getMusicById(id: MusicId): Music
    fun findMusicByName(name: String): List<Music>
    fun findMusicByVocaloid(vocaloidId: VocaloidId): List<Music>
    fun findMusicByArtistId(id: ArtistId): List<Music>
}
