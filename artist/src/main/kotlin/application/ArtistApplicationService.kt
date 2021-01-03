package application

import domain.artist.ArtistId
import domain.music.Music
import domain.vocaloid.VocaloidId

class ArtistApplicationService {
    fun enrollNewArtist(name: String) {}

    fun searchArtistByName(name: String): ArtistId {
        return ArtistId("1")
    }

    fun getArtistMusicList(id: ArtistId): List<Music> {
        return emptyList()
    }

    fun changeName(id: ArtistId, newName: String) {}

    fun presentNewMusic(artistId: ArtistId, title: String, explain: String, sourceLink: String) {}

    fun presentNewMusicWithSeveralArtist(artistList: List<ArtistId>, title: String, explain: String, sourceLink: String) {}

    fun presentNewMusicWithVocaloid(artistId: ArtistId, vocaloidId: VocaloidId, title: String, explain: String, sourceLink: String) {}

    fun enrollNewVocaloid(name: String) {}

    fun findVocaloidByName(name: String): VocaloidId {
        return VocaloidId("1")
    }

    fun getVocaloidMusicList(vocaloidId: VocaloidId): List<Music> {
        return emptyList()
    }
}
