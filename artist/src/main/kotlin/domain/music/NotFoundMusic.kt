package domain.music

class NotFoundMusic(reason: String) : Exception("can't found music. because $reason")
