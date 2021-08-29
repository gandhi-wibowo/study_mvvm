package com.dizcoding.miniproject.presentation.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Comment
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Post
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.User
import com.dizcoding.miniproject.domain.postdetailusecase.PostDetailUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class PostDetailViewModel constructor(private val postDetailUseCase: PostDetailUseCase) :
    ViewModel() {

    private lateinit var _postsDetail: MutableLiveData<Post>
    private lateinit var _userDetail: MutableLiveData<User.Data>
    private lateinit var _postsComment: MutableLiveData<List<Comment>>


    fun getPostDetailData(): LiveData<Post> {
        _postsDetail = MutableLiveData()
        return _postsDetail
    }

    fun getUserDetailData(): LiveData<User.Data> {
        _userDetail = MutableLiveData()
        return _userDetail
    }

    fun getPostCommentData(): LiveData<List<Comment>> {
        _postsComment = MutableLiveData()
        return _postsComment
    }

    fun requestPostDetailData(postId: Int) {
        viewModelScope.launch {
            coroutineScope {
                val postDetail = async { postDetailUseCase.getPostBy(postId) }.await()
                _postsDetail.postValue(postDetail)
                postDetail.userId?.let {
                    requestUserData(it)
                }
            }
        }
    }

    private fun requestUserData(userId: Int) {
        viewModelScope.launch {
            coroutineScope {
                val postDetail = async { postDetailUseCase.getUsersBy(userId) }.await()
                _userDetail.postValue(postDetail)
            }
        }
    }

    fun requestPostDetailCommentData(postId: Int) {
        viewModelScope.launch {
            coroutineScope {
                val comment = async { postDetailUseCase.getPostCommentsBy(postId) }.await()
                _postsComment.postValue(comment)
            }
        }
    }
}