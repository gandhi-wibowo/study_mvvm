package com.dizcoding.miniproject.data.network.jsonplaceholder.repository

import com.dizcoding.miniproject.data.network.jsonplaceholder.response.*
import com.dizcoding.miniproject.data.network.jsonplaceholder.services.JsonPlaceholderServices

class JsonPlaceholderDataStore constructor(private val nService: JsonPlaceholderServices) :
    JsonPlaceholderRepository {

    override suspend fun getPosts(): List<Post> = nService.getPosts()

    override suspend fun getPostBy(postId: Int): Post = nService.getPostBy(postId)

    override suspend fun getPostCommentsBy(postId: Int): List<Comment> =
        nService.getPostCommentsBy(postId)

    override suspend fun getComments(): List<Comment> = nService.getComments()

    override suspend fun getCommentBy(commentId: Int): Comment = nService.getCommentBy(commentId)

    override suspend fun getUsers(): List<User.Data> = nService.getUsers()

    override suspend fun getUsersBy(userId: Int): User.Data = nService.getUsersBy(userId)

    override suspend fun getAlbumsBy(userId: Int): List<Album> = nService.getAlbumsBy(userId)

    override suspend fun getPhotos(): List<Photo> = nService.getPhotos()

}