package com.jge.newsstack.repos

import com.jge.newsstack.MainDispatcherRule
import com.jge.newsstack.repo.ArticleRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class ArticleRepositoryTest {

    @get:Rule
    var rule = HiltAndroidRule(this)

    @Inject
    lateinit var articleRepository: ArticleRepository

    @Before
    fun setUp(){
        rule.inject()
    }

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun testSuccessfulResponse(){
        GlobalScope.launch {
            assertTrue(articleRepository.getNewsArticles().isSuccessful )
        }
    }
}