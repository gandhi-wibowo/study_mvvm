package com.dizcoding.miniproject.domain.userdetailusecase

import com.dizcoding.miniproject.data.network.jsonplaceholder.repository.JsonPlaceholderRepository
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Album
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Photo
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.User

class UserDetailInteractor constructor(private val jsonPlaceholderDataStore: JsonPlaceholderRepository) :
    UserDetailUseCase {
    override suspend fun getUsersBy(userId: Int): User.Data = jsonPlaceholderDataStore.getUsersBy(userId)

    override suspend fun getAlbumsBy(userId: Int): List<Album> = jsonPlaceholderDataStore.getAlbumsBy(userId)

    override suspend fun getPhotos(): List<Photo> = jsonPlaceholderDataStore.getPhotos()


}