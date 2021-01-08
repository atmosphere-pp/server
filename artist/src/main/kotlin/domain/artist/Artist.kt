package domain.artist

class Artist(var name: String) {
    private lateinit var id: ArtistId

    fun getId(): ArtistId {
        return this.id
    }

    fun isInitializeId(): Boolean {
        return this::id.isInitialized
    }

    fun setId(id: ArtistId) {
        this.id = id
    }

    fun changeName(newName: String) {
        this.name = newName
    }
}
