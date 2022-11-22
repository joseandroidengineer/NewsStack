package com.jge.newsstack.views.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jge.newsstack.R
import com.jge.newsstack.adapters.ArticleAdapter
import com.jge.newsstack.dependencyinjection.DaggerAppComponent
import com.jge.newsstack.listeners.OnArticleClickListener
import com.jge.newsstack.models.Article
import com.jge.newsstack.viewmodels.ViewModelArticle
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment:Fragment(), OnArticleClickListener {
    @Inject
    lateinit var viewModelArticle: ViewModelArticle

    private lateinit var textView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var progressBar: ProgressBar
    companion object{
        const val TAG = "MainFragment_TAG"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appComponent = DaggerAppComponent.builder().build()
        appComponent.inject(this)
        initViews(view)

        viewModelArticle.fetchArticles()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModelArticle.uiState.collect{
                    when(it){
                        is ViewModelArticle.ArticlesUIState.Error  -> showErrorScreen(it.exception)
                        is ViewModelArticle.ArticlesUIState.Loading-> showLoading(it.loading)
                        is ViewModelArticle.ArticlesUIState.Success-> showResults(it.articles)
                    }
                }
            }
        }
    }

    private fun initViews(view: View) {
        progressBar = view.findViewById(R.id.pb)
        textView = view.findViewById(R.id.text_view)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun showErrorScreen(exception: Throwable) {
        recyclerView.visibility = RecyclerView.GONE
        progressBar.visibility = RecyclerView.GONE
        textView.visibility = RecyclerView.VISIBLE
        textView.text = "An error has occurred. Please try again later ${exception.localizedMessage}"
    }

    private fun showLoading(loading: Boolean) {
        if(loading){
            progressBar.visibility = RecyclerView.VISIBLE
            recyclerView.visibility = RecyclerView.GONE
        }else{
            progressBar.visibility = RecyclerView.GONE
            recyclerView.visibility = RecyclerView.VISIBLE
        }
    }

    private fun showResults(articles: List<Article>) {
        articleAdapter = ArticleAdapter(articles,this)
        recyclerView.adapter = articleAdapter
    }

    override fun onArticleClick(article: Article) {
        val bundle = Bundle()
        bundle.putParcelable(DetailFragment.ARTICLE_KEY, article)
        activity?.supportFragmentManager?.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container,DetailFragment::class.java, bundle,TAG)
            addToBackStack(null)
        }
    }
}