class ArtistApplicationService {
    fun enrollNewArtist(name: String): ArtistId {
        return ArtistId("1")
    }

    fun searchByName(name: String): ArtistId {
        return ArtistId("1")
    }

    fun changeName(id: ArtistId, newName: String) {}

    fun presentNewMusic(artistId: ArtistId, title: String, explain: String, sourceLink: String): MusicId {
        return MusicId("1")
    }

    fun presentNewMusicWithSeveralArtist(artistList: List<ArtistId>, title: String, explain: String, sourceLink: String): MusicId {
        return MusicId("1")
    }

    fun presentNewMusicWithFeaturing(artistId: ArtistId, featuringArtistIdList: List<ArtistId>, title: String, explain: String, sourceLink: String): MusicId {
        return MusicId("1")
    }

    fun enrollNewVocaloid(name: String): VocaloidId {
        return VocaloidId("1")
    }

    fun presentNewVocaloidSong(artistId: ArtistId, vocaloidId: VocaloidId, title: String, explain: String, sourceLink: String): MusicId {
        return MusicId("1")
    }
}
