package com.dizcoding.miniproject.domain.postdetailusecase

import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Comment
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Post
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.User

interface PostDetailUseCase {
    suspend fun getPostBy(postId: Int): Post
    suspend fun getPostCommentsBy(postId: Int): List<Comment>
    suspend fun getUsersBy(userId: Int): User.Data
}