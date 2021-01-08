package domain.vocaloid

class NotFoundVocaloid(reason: String) : Exception("can't found vocaloid. because $reason")
