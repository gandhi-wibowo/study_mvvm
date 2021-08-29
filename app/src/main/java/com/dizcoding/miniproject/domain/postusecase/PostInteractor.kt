package com.dizcoding.miniproject.domain.postusecase

import com.dizcoding.miniproject.data.network.jsonplaceholder.repository.JsonPlaceholderRepository
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Post
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.User

class PostInteractor constructor(private val jsonPlaceholderDataStore: JsonPlaceholderRepository) :
    PostUseCase {

    override suspend fun getPosts(): List<Post> = jsonPlaceholderDataStore.getPosts()
    override suspend fun getUsers(): List<User.Data> = jsonPlaceholderDataStore.getUsers()

}