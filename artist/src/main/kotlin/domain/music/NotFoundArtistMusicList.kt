package domain.music

import domain.artist.ArtistId

class NotFoundArtistMusicList(artistId: ArtistId) : Exception("can't found music list using artist: $artistId")
