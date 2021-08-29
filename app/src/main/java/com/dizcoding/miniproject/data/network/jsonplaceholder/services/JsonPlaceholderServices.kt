package com.dizcoding.miniproject.data.network.jsonplaceholder.services

import com.dizcoding.miniproject.data.network.jsonplaceholder.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonPlaceholderServices {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{postId}")
    suspend fun getPostBy(@Path("postId") postId: Int): Post

    @GET("posts/{postId}/comments")
    suspend fun getPostCommentsBy(@Path("postId") postId: Int): List<Comment>

    @GET("comments")
    suspend fun getComments(): List<Comment>

    @GET("comments/{commentId}")
    suspend fun getCommentBy(@Path("commentId") commentId: Int): Comment

    @GET("users")
    suspend fun getUsers(): List<User.Data>

    @GET("users/{userId}")
    suspend fun getUsersBy(@Path("userId") userId: Int): User.Data

    @GET("albums")
    suspend fun getAlbumsBy(@Query("userId") userId: Int): List<Album>

    @GET("photos")
    suspend fun getPhotos(): List<Photo>


}