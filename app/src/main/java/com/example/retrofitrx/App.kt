package com.example.retrofitrx

import android.app.Application
import android.content.Context
import com.example.retrofitrx.data.retrofit.RetrofitProjectsRepoImpl
import com.example.retrofitrx.domain.ProjectsRepo

class App : Application() {
    // для проверки можно использовать временный класс MockProjectsRepoImpl
    // вместо RetrofitProjectsRepoImpl
    val gitProjectsRepo: ProjectsRepo by lazy { RetrofitProjectsRepoImpl() }
}

// способ получить Application, нужное поле, нужного типа
val Context.app: App
    get() = applicationContext as App