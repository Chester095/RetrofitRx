package com.example.retrofitrx.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitrx.app
import com.example.retrofitrx.databinding.ActivityReposBinding

class ReposActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReposBinding
    // подключаем гугловский вьюмодель
    // Урок 5 01:17:35 описание процесса. Смысл таков, что ВьюМодель создастся после первого вызова
    private val viewModel: ReposViewModel by viewModels { ReposViewModelFactory(app.gitProjectsRepo) }
    private val adapter = GitProjectsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReposBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initOutgoingEvents()
        initIncomingEvents()
    }

    private fun initViews() {
        binding.reposRecyclerView.layoutManager = LinearLayoutManager(this)
        // TODO Урок 5 01:21:27 не разобрал речь
        // приложение само начнёт что-то там считать
        adapter.setHasStableIds(true)
        binding.reposRecyclerView.adapter = adapter
    }

    private fun initOutgoingEvents() {
        binding.showButton.setOnClickListener {
            // передаём в onShowRepos username
            val username = binding.usernameEditText.text.toString()
            viewModel.onShowRepos(username)
        }
    }

    private fun initIncomingEvents() {
        // подписываемся на вьюмодель
        viewModel.repos.observe(this) {
            // передаём в адаптер те данные, которые пришли
            // обновляем каждый раз адаптер, когда к нам приходят новые данные
            adapter.setData(it)
        }
        // подписываемся на состояние загрузки
        viewModel.inProgress.observe(this) { inProgress ->
            binding.showButton.isEnabled = !inProgress
            binding.usernameEditText.isEnabled = !inProgress
            binding.progressBarLayout.isVisible = inProgress
        }
    }

}