package com.example.retrofitrx.data.retrofit

import com.example.retrofitrx.domain.GitProjectEntity
import com.example.retrofitrx.domain.ProjectsRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitProjectsRepoImpl : ProjectsRepo {
    // 01:25:20 retrofit должен сконвертировать это в RX

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: GitHubApi = retrofit.create(GitHubApi::class.java)

    override fun observeReposForUser(username: String): Single<List<GitProjectEntity>> {
        return api.listRepos(username)
    }

}