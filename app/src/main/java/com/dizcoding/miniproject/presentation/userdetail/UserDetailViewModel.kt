package com.dizcoding.miniproject.presentation.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dizcoding.miniproject.R
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.User
import com.dizcoding.miniproject.domain.userdetailusecase.UserDetailUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class UserDetailViewModel constructor(private val useCase: UserDetailUseCase) : ViewModel() {

    private lateinit var _userDetail: MutableLiveData<User.Data>
    private lateinit var _albumItem: MutableLiveData<List<AlbumItem>>


    fun getUserData(): LiveData<User.Data> {
        _userDetail = MutableLiveData()
        return _userDetail
    }

    fun getAlbumItemData(): LiveData<List<AlbumItem>> {
        _albumItem = MutableLiveData()
        return _albumItem
    }

    fun requestGetAlbumData(userId: Int) {
        viewModelScope.launch {
            coroutineScope {
                val photos = async { useCase.getPhotos() }.await()
                val albums = async { useCase.getAlbumsBy(userId) }.await()

                photos.filter { it.albumId == 1 }

                val albumItems: ArrayList<AlbumItem> = arrayListOf()

                albums.forEach { albumItem ->
                    val album = AlbumItem()
                    album.title = albumItem.title.toString()
                    album.itemViewType = R.layout.item_album_title
                    albumItems.add(album)

                    val albumPhotos = AlbumItem()
                    albumPhotos.photos = photos.filter { it.albumId == albumItem.id }
                    albumPhotos.itemViewType = R.layout.item_album
                    albumItems.add(albumPhotos)
                }

                _albumItem.postValue(albumItems)
            }
        }
    }

    fun requestGetUserData(userId: Int) {
        viewModelScope.launch {
            coroutineScope {
                val userData = async { useCase.getUsersBy(userId) }.await()
                _userDetail.postValue(userData)
            }
        }
    }
}