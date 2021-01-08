package domain.artist

class NotFoundArtistById(artistId: ArtistId) : Exception("can't found artist using id: $artistId")
