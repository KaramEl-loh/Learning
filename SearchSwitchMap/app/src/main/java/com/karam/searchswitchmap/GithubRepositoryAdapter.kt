package com.karam.searchswitchmap

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karam.searchswitchmap.databinding.GithubRepoItemBinding
import com.karam.searchswitchmap.model.SearchResult


class GithubRepositoryAdapter(var repositoryDetailsList: List<SearchResult>) :
    RecyclerView.Adapter<GithubRepositoryAdapter.GithubRepositoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepositoryViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val githubRepoItemBinding: GithubRepoItemBinding = GithubRepoItemBinding.inflate(layoutInflater,parent,false)

        return GithubRepositoryViewHolder(githubRepoItemBinding)

    }

    override fun getItemCount(): Int {
        return repositoryDetailsList.size
    }

    override fun onBindViewHolder(holder: GithubRepositoryViewHolder, position: Int) {

        holder.bind(repositoryDetailsList.get(position))
}


    class GithubRepositoryViewHolder(val githubRepoItemBinding: GithubRepoItemBinding) : RecyclerView.ViewHolder(githubRepoItemBinding.root) {


            fun bind(searchResult: SearchResult) {

                githubRepoItemBinding.repoName.text = searchResult.full_name
                githubRepoItemBinding.executePendingBindings()
            }








    }

}