package com.mashup.kkyuni.playlist.domain.usecase

import com.mashup.kkyuni.playlist.domain.repository.PlayListRepository
import com.mashup.kkyuni.playlist.domain.model.PlayList
import com.mashup.kkyuni.playlist.domain.toPlayList
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

    operator fun invoke(params: Params): Flow<List<PlayList>> {
        return playListRepository.fetchPlayList()
            .map { it.toPlayList() }
    }
}