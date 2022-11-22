package com.jge.newsstack.views.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jge.newsstack.R
import com.jge.newsstack.models.Article

class DetailFragment:Fragment() {
    companion object{
        const val TAG = "DetailFragment_TAG"
        const val ARTICLE_KEY = "article_bundle"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAndPopulate(view)
    }

    private fun initAndPopulate(view: View) {
        val article = requireArguments().getParcelable<Article>(ARTICLE_KEY)
        val textViewTitle = view.findViewById<TextView>(R.id.title_tv)
        val textViewContent = view.findViewById<TextView>(R.id.content_tv)
        val webView = view.findViewById<WebView>(R.id.article_wv)
        val thumbnailIV = view.findViewById<ImageView>(R.id.thumbnail_iv)

        context?.let {
            Glide.with(it)
                .load(article?.image)
                .into(thumbnailIV)
        }

        textViewTitle.text = article?.title
        textViewContent.text = article?.description
        webView.loadUrl(article!!.url)
    }
}