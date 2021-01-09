package application

import domain.music.Music
import domain.music.MusicId
import domain.reviewer.ReviewerId

class MusicApplicationService {
    fun showAllMusic(): List<Music> {
        return emptyList()
    }

    fun starMusic(reviewerId: ReviewerId, musicId: MusicId) {
    }

    fun showStaredMusic(reviewerId: ReviewerId): List<Music> {
        return emptyList()
    }
}
