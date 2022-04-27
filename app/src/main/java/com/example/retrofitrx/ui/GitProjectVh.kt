package com.example.retrofitrx.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitrx.databinding.ItemGitProjectBinding
import com.example.retrofitrx.domain.GitProjectEntity

class GitProjectVh(private val binding: ItemGitProjectBinding) :
    RecyclerView.ViewHolder(binding.root) {
    // binding.root - ссылка на корневой view разметки
    // companion object - для обращения к объекту через имя класса без наименования объекта
    companion object {
        // метод получает на вход parent и возращает binding
        fun create(parent: ViewGroup): GitProjectVh {
            val inflater = LayoutInflater.from(parent.context)
            return GitProjectVh(ItemGitProjectBinding.inflate(inflater))
        }
    }

    // раздуваем item
    fun bind(item: GitProjectEntity) {
        binding.itemGitRepoId.text = item.id.toString()
        binding.itemGitRepoName.text = item.name
    }
}