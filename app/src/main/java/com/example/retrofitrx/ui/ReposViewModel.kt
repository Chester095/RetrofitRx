package com.example.retrofitrx.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitrx.domain.GitProjectEntity
import com.example.retrofitrx.domain.ProjectsRepo
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class ReposViewModel(private val gitProjectRepo: ProjectsRepo) : ViewModel() {
    // список репозиториев
    private val _repos = MutableLiveData<List<GitProjectEntity>>()
    val repos: LiveData<List<GitProjectEntity>> = _repos

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress


    // метод отписки. чтобы от всех отписаться
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun onShowRepos(username: String) {
        _inProgress.postValue(true)

        // подписываемся, получаем результат и отправляем
        compositeDisposable.add(
            gitProjectRepo
                .observeReposForUser(username)
                // подписались
                .subscribeBy {
                    _inProgress.postValue(false)
                    // пихаем в repos данные (список)
                    // postValue - потокобезопасный метод
                    _repos.postValue(it)
                }
        )
    }

    // 01:24:00 отписаться кажется от RX
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}