package application

import domain.artist.Artist
import domain.artist.ArtistId
import domain.artist.ArtistRepository
import domain.artist.NotFoundArtistByName
import domain.music.Music
import domain.music.MusicRepository
import domain.music.NotFoundArtistMusicList
import domain.vocaloid.NotFoundVocaloidByName
import domain.vocaloid.Vocaloid
import domain.vocaloid.VocaloidId
import domain.vocaloid.VocaloidRepository

class ArtistApplicationService(
    private val musicRepository: MusicRepository,
    private val artistRepository: ArtistRepository,
    private val vocaloidRepository: VocaloidRepository,
) {
    fun enrollNewArtist(name: String) {
        val newArtist = Artist(name)
        this.artistRepository.save(newArtist)
    }

    fun searchArtistByName(name: String): ArtistId {
        val artistList = this.artistRepository.findArtistByName(name)
        if (artistList.isEmpty()) {
            throw NotFoundArtistByName(name)
        }
        return artistList.first().getId()
    }

    fun changeName(id: ArtistId, newName: String) {
        val artist = this.artistRepository.getArtistById(id)
        artist.changeName(newName)
        this.artistRepository.save(artist)
    }

    fun getArtistMusicList(id: ArtistId): List<Music> {
        val musicList = this.musicRepository.findMusicByArtistId(id)
        if (musicList.isEmpty()) {
            throw NotFoundArtistMusicList(id)
        }
        return musicList
    }

    fun presentNewMusic(artistId: ArtistId, title: String, explain: String, sourceLink: String) {
        val newMusic = Music(listOf(artistId), title, explain, sourceLink)
        this.musicRepository.save(newMusic)
    }

    fun presentNewMusicWithSeveralArtist(artistList: List<ArtistId>, title: String, explain: String, sourceLink: String) {
        val newMusic = Music(artistList, title, explain, sourceLink)
        this.musicRepository.save(newMusic)
    }

    fun presentNewMusicWithVocaloid(artistId: ArtistId, vocaloidId: VocaloidId, title: String, explain: String, sourceLink: String) {
        val newMusic = Music(listOf(artistId), title, explain, sourceLink)
        newMusic.useVocal(vocaloidId)
        this.musicRepository.save(newMusic)
    }

    fun enrollNewVocaloid(name: String) {
        val newVocaloid = Vocaloid(name)
        this.vocaloidRepository.save(newVocaloid)
    }

    fun findVocaloidByName(name: String): VocaloidId {
        val vocaloidList = this.vocaloidRepository.findVocaloidByName(name)
        if (vocaloidList.isEmpty()) {
            throw NotFoundVocaloidByName(name)
        }
        return vocaloidList.first().getId()
    }

    fun getVocaloidMusicList(vocaloidId: VocaloidId): List<Music> {
        return this.musicRepository.findMusicByVocaloid(vocaloidId)
    }
}
