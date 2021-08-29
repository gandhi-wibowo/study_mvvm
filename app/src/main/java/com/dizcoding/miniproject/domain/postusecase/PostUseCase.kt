package com.dizcoding.miniproject.domain.postusecase

import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Post
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.User


interface PostUseCase {
    suspend fun getPosts(): List<Post>
    suspend fun getUsers(): List<User.Data>
}