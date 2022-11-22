package com.jge.newsstack.dependencyinjection

import com.jge.newsstack.APIKEYHOLDER
import com.jge.newsstack.network.NewApi
import com.jge.newsstack.repo.ArticleRepository
import com.jge.newsstack.repo.ArticleRepositoryImpl
import com.jge.newsstack.viewmodels.ViewModelArticle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsArticleApi():NewApi{
        return Retrofit.Builder()
            .baseUrl(APIKEYHOLDER.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewApi::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(newsApi: NewApi):ArticleRepository{
        return ArticleRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun providesViewModelArticle(articleRepositoryImpl: ArticleRepositoryImpl):ViewModelArticle{
        return ViewModelArticle(articleRepositoryImpl)
    }
}