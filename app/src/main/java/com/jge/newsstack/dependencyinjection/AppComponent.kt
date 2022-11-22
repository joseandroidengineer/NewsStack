package com.jge.newsstack.dependencyinjection

import com.jge.newsstack.views.activities.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainFragment: MainFragment)
}