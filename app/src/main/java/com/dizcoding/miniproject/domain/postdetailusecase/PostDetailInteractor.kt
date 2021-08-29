package com.dizcoding.miniproject.domain.postdetailusecase

import com.dizcoding.miniproject.data.network.jsonplaceholder.repository.JsonPlaceholderRepository
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Comment
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Post
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.User

class PostDetailInteractor constructor(private val jsonPlaceholderDataStore: JsonPlaceholderRepository) :
    PostDetailUseCase {

    override suspend fun getPostBy(postId: Int): Post = jsonPlaceholderDataStore.getPostBy(postId)

    override suspend fun getPostCommentsBy(postId: Int): List<Comment> =
        jsonPlaceholderDataStore.getPostCommentsBy(postId)

    override suspend fun getUsersBy(userId: Int): User.Data =
        jsonPlaceholderDataStore.getUsersBy(userId)


}