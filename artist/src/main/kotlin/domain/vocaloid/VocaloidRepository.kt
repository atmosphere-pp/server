package domain.vocaloid

interface VocaloidRepository {
    fun save(vocaloid: Vocaloid)
    fun getVocaloidById(id: VocaloidId): Vocaloid
    fun findVocaloidByName(name: String): List<Vocaloid>
}
