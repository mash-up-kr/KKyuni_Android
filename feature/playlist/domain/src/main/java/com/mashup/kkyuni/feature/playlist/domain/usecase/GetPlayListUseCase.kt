package com.mashup.kkyuni.feature.playlist.domain.usecase

import com.mashup.kkyuni.feature.playlist.domain.model.MusicModel
import com.mashup.kkyuni.feature.playlist.domain.toPlayList
import com.mashup.kkyuni.playlist.data.repository.PlayListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPlayListUseCase @Inject constructor(
    private val playListRepository: PlayListRepository
) {

    data class Params(
        val year: Int,
        val month: Int
    )

    operator fun invoke(params: Params): Flow<List<MusicModel>> {
        return playListRepository.fetchPlayList(
            date = "${params.year}-${params.month}"
        ).map { it.toPlayList() }
    }
}