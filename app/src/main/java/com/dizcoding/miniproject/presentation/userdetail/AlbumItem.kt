package com.dizcoding.miniproject.presentation.userdetail

import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Photo

class AlbumItem {
    var title : String = ""
    var photos : List<Photo> = listOf()
    var itemViewType : Int = 0
}