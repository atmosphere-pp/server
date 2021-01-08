package domain.music

import domain.artist.ArtistId
import domain.vocaloid.VocaloidId

class Music(val artistList: List<ArtistId>, val title: String, val explain: String, val sourceLink: String) {
    init {
        require(artistList.isNotEmpty())
        require(sourceLink.contains("youtube"))
    }

    private lateinit var id: MusicId
    private lateinit var vocaloidId: VocaloidId

    fun getId(): MusicId {
        return this.id
    }

    fun isInitializeId(): Boolean {
        return this::id.isInitialized
    }

    fun setId(id: MusicId) {
        this.id = id
    }

    fun useVocal(vocaloid: VocaloidId) {
        this.vocaloidId = vocaloid
    }

    fun isUsed(vocaloid: VocaloidId): Boolean {
        return this.vocaloidId == vocaloidId
    }
}
