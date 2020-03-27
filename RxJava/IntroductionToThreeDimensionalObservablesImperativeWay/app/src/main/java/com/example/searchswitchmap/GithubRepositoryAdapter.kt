package com.karam.searchswitchmap


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchswitchmap.R
import com.karam.searchswitchmap.model.SearchResult

class GithubRepositoryAdapter(var repositoryDetailsList: MutableList<SearchResult>) :


    RecyclerView.Adapter<GithubRepositoryAdapter.GithubRepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):GithubRepositoryViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        return GithubRepositoryViewHolder(layoutInflater.inflate(R.layout.github_repo_item,parent,false))

    }

    class GithubRepositoryViewHolder(view:View): RecyclerView.ViewHolder(view) {

        val repoName = view.findViewById<TextView>(R.id.repo_name)

    }
    override fun getItemCount(): Int {

        return repositoryDetailsList.size
    }

    override fun onBindViewHolder(holder: GithubRepositoryViewHolder, position: Int) {
        holder.repoName.text = repositoryDetailsList[position].full_name
    }

}