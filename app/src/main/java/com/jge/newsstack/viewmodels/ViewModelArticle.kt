package com.jge.newsstack.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jge.newsstack.models.Article
import com.jge.newsstack.repo.ArticleRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
/**I have to admit I've never worked with StateFLows before, but after I spoke with Sam
 * I just had to try it! After implementing it I saw how easy and readable it was.
 * I took advantage of this coding exercise to not only show what I
 * can do, but also learn as well.
 * **/
class ViewModelArticle @Inject constructor(
    private val repository: ArticleRepository): ViewModel() {

    private val _uiState = MutableStateFlow<ArticlesUIState>(ArticlesUIState.Loading(true))
    val uiState:StateFlow<ArticlesUIState> = _uiState

    fun fetchArticles(){
        _uiState.value = ArticlesUIState.Loading(true)
        viewModelScope.launch{
            try{
                val response = repository.getNewsArticles()
                _uiState.value = ArticlesUIState.Success(response.body()!!.listOfArticles)
            }catch (exception: Exception){
                _uiState.value = ArticlesUIState.Error(exception)
            }
            _uiState.value = ArticlesUIState.Loading(false)
        }
    }

    sealed class ArticlesUIState{
        data class Success(val articles: List<Article>):ArticlesUIState()
        data class Error(val exception: Throwable):ArticlesUIState()
        data class Loading(val loading:Boolean):ArticlesUIState()
    }
}