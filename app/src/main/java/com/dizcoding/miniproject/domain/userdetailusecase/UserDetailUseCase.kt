package com.dizcoding.miniproject.domain.userdetailusecase

import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Album
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Photo
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.User

interface UserDetailUseCase {
    suspend fun getUsersBy(userId: Int): User.Data
    suspend fun getAlbumsBy(userId: Int): List<Album>
    suspend fun getPhotos(): List<Photo>
}