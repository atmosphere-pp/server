package domain.vocaloid

class Vocaloid(var name: String) {
    private lateinit var id: VocaloidId

    fun getId(): VocaloidId {
        return this.id
    }

    fun isInitializeId(): Boolean {
        return this::id.isInitialized
    }

    fun setId(id: VocaloidId) {
        this.id = id
    }
}
