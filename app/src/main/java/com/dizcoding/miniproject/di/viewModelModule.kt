package com.dizcoding.miniproject.di

import com.dizcoding.miniproject.presentation.post.PostViewModel
import com.dizcoding.miniproject.presentation.postdetail.PostDetailViewModel
import com.dizcoding.miniproject.presentation.userdetail.UserDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PostViewModel(get()) }
    viewModel { PostDetailViewModel(get()) }
    viewModel { UserDetailViewModel(get()) }

}