package adapter

import domain.vocaloid.NotFoundVocaloid
import domain.vocaloid.Vocaloid
import domain.vocaloid.VocaloidId
import domain.vocaloid.VocaloidRepository

class InMemoryVocaloidRepository : VocaloidRepository {
    private val inMemoryCache: MutableMap<VocaloidId, Vocaloid> = mutableMapOf()
    override fun save(vocaloid: Vocaloid) {
        if (!vocaloid.isInitializeId()) {
            val nextId = VocaloidId(this.inMemoryCache.values.size.toString())
            vocaloid.setId(nextId)
        }
        this.inMemoryCache[vocaloid.getId()] = vocaloid
    }

    override fun getVocaloidById(id: VocaloidId): Vocaloid {
        return this.inMemoryCache[id] ?: throw NotFoundVocaloid("can't find by id: $id")
    }

    override fun findVocaloidByName(name: String): List<Vocaloid> {
        return this.inMemoryCache.values.filter { vocaloid -> vocaloid.name == name }
    }
}
