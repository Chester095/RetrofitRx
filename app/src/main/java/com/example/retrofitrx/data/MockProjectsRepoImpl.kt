package com.example.retrofitrx.data

import com.example.retrofitrx.domain.GitProjectEntity
import com.example.retrofitrx.domain.ProjectsRepo
import io.reactivex.rxjava3.core.Single

// временный класс для проверки работы UI
class MockProjectsRepoImpl : ProjectsRepo {

    override fun observeReposForUser(username: String): Single<List<GitProjectEntity>> {
        val dummyList = listOf(
            GitProjectEntity(0, "!!!"),
            GitProjectEntity(1, "Ololo"),
            GitProjectEntity(2, "Fljenfljwnfe"),
            GitProjectEntity(3, "wevlkwnev"),
            GitProjectEntity(4, "otmknm"),
            GitProjectEntity(5, "dflkbm;slmfbv"),
        )

        return Single.just(dummyList)
    }
}