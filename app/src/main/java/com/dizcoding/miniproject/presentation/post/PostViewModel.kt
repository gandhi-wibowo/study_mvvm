package com.dizcoding.miniproject.presentation.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dizcoding.miniproject.domain.postusecase.PostUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class PostViewModel constructor(private val postUseCase: PostUseCase) : ViewModel() {

    private lateinit var _posts: MutableLiveData<List<PostItem>>

    fun getPostData(): LiveData<List<PostItem>> {
        _posts = MutableLiveData()
        return _posts
    }

    fun requestPostData() {
        viewModelScope.launch {
            coroutineScope {
                val posts = async { postUseCase.getPosts() }
                val users = async { postUseCase.getUsers() }

                val postData = posts.await()
                val userData = users.await()

                val postItems = arrayListOf<PostItem>()

                postData.forEach { postItem ->
                    val post: PostItem = PostItem()
                    postItem.id?.let {
                        post.id = it
                    }
                    post.title = postItem.title.toString()
                    post.body = postItem.body.toString()
                    userData.forEach { userData ->

                        if (postItem.userId == userData.id) {
                            post.authorName = userData.name.toString()
                            userData.company?.let {
                                post.authorCompanyName = it.name.toString()
                            }
                        }
                    }
                    postItems.add(post)
                }

                _posts.postValue(postItems)

            }
        }
    }
}