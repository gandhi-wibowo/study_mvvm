package com.dizcoding.miniproject.data.network.jsonplaceholder.repository

import com.dizcoding.miniproject.data.network.jsonplaceholder.response.*

interface JsonPlaceholderRepository {
    suspend fun getPosts(): List<Post>
    suspend fun getPostBy(postId: Int): Post
    suspend fun getPostCommentsBy(postId: Int): List<Comment>
    suspend fun getComments(): List<Comment>
    suspend fun getCommentBy(commentId: Int): Comment
    suspend fun getUsers(): List<User.Data>
    suspend fun getUsersBy(userId: Int): User.Data
    suspend fun getAlbumsBy(userId: Int): List<Album>
    suspend fun getPhotos(): List<Photo>
}