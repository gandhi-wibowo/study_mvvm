package com.dizcoding.miniproject.di

import com.dizcoding.miniproject.domain.postdetailusecase.PostDetailInteractor
import com.dizcoding.miniproject.domain.postdetailusecase.PostDetailUseCase
import com.dizcoding.miniproject.domain.postusecase.PostInteractor
import com.dizcoding.miniproject.domain.postusecase.PostUseCase
import com.dizcoding.miniproject.domain.userdetailusecase.UserDetailInteractor
import com.dizcoding.miniproject.domain.userdetailusecase.UserDetailUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<PostUseCase> { PostInteractor(get()) }
    factory<PostDetailUseCase> { PostDetailInteractor(get()) }
    factory<UserDetailUseCase> { UserDetailInteractor(get()) }
}