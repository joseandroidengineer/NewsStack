package com.jge.newsstack.viewmodels

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jge.newsstack.MainDispatcherRule
import com.jge.newsstack.models.Article
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**I'm a little stuck writing tests as I'm implementing the HiltAndroidTest to
 * show the proper dependencies and test them out. According to the Android Development
 * website, its usually best practice to do that instead of mocking the objects out so we can get
 * more test coverage, especially if many objects depend on one another and
 * would need to be manually injected if I do decide to use Mockito
 *
 * Also the experimental coroutines api is new to me so testing coroutines is something I'll
 * keep researching on my own.
 * **/
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
internal class ViewModelArticleTest{
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val rule = HiltAndroidRule(this)

    @Inject
    lateinit var viewModelArticle: ViewModelArticle
    private lateinit var listOfArticles:List<Article>

    @Before
    fun setUp(){
        rule.inject()
        listOfArticles = buildListOfArticles()
    }
    @ExperimentalCoroutinesApi
    @Test
    fun checkIfResponseIsSuccessful(){
        viewModelArticle.fetchArticles()
    }

    //TODO: Implement response test if there is an error or when there is an empty response

    companion object{
         fun buildListOfArticles():List<Article>{
            val articleA = Article("null",
                "Un salon virtuel destiné aux MRE lancé par l'Agence urbaine de Rabat-Salé",
                "L’Agence Urbaine de Rabat-Salé (AURS) vient de lancer un salon virtuel dédié aux Marocains résidant à l’étranger (MRE) qui leur permettra d’accéder à des services digitalisés facilitant l’accès à l’information. Cette plateforme innovante et pratique, met ainsi à la disposition des MRE un accès par stand virtuel à plusieurs informations, notamment les plans d’aménagement et opportunités d’investissement, les autorisations de construire et les notes de renseignements urbanistiques, indique lagence dans un communiqué.",
                "https://www.yabiladi.com/articles/details/130565/salon-virtuel-destine-lance-l-agence.html",
                "yabiladi.com",
                "https://static.yabiladi.com/files/articles/b44cca1fdb879ee54738ff00d1bd818e20220805102253150.jpeg",
                "general",
                "ar",
                "ma",
                "2022-08-05T08:36:00+00:00")

            val articleB = Article("null",
                "Un salon virtuel destiné aux MRE lancé par l'Agence urbaine de Rabat-Salé",
                "L’Agence Urbaine de Rabat-Salé (AURS) vient de lancer un salon virtuel dédié aux Marocains résidant à l’étranger (MRE) qui leur permettra d’accéder à des services digitalisés facilitant l’accès à l’information. Cette plateforme innovante et pratique, met ainsi à la disposition des MRE un accès par stand virtuel à plusieurs informations, notamment les plans d’aménagement et opportunités d’investissement, les autorisations de construire et les notes de renseignements urbanistiques, indique lagence dans un communiqué.",
                "https://www.yabiladi.com/articles/details/130565/salon-virtuel-destine-lance-l-agence.html",
                "yabiladi.com",
                "https://static.yabiladi.com/files/articles/b44cca1fdb879ee54738ff00d1bd818e20220805102253150.jpeg",
                "general",
                "ar",
                "ma",
                "2022-08-05T08:36:00+00:00")
            val listOfArticle = ArrayList<Article>()
            listOfArticle.add(articleA)
            listOfArticle.add(articleB)
            return listOfArticle
        }
    }
}