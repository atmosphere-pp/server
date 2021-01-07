package domain.artist

class NotFoundArtistByName(artistName: String) : Exception("can't found artist using name: $artistName")
