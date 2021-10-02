package com.mashup.kkyuni.feature.music.data

import com.mashup.kkyuni.feature.music.domain.MusicRepository
import com.mashup.kkyuni.feature.music.domain.model.Videos
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(private val service: VideoService): MusicRepository {

    override suspend fun getMusic(query: String): Videos {
        return service.searchVideo(query = query).toEntity()
    }

}