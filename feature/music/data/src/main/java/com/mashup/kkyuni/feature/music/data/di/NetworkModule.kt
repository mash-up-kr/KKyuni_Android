package com.mashup.kkyuni.feature.music.data.di

import com.mashup.kkyuni.feature.music.data.VideoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideVideoService(@Named("youtube_api") retrofit: Retrofit): VideoService =
        retrofit.create(VideoService::class.java)
}