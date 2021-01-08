package domain.vocaloid

class NotFoundVocaloidByName(vocaloidName: String) : Exception("can't found vocaloid using name: $vocaloidName")
