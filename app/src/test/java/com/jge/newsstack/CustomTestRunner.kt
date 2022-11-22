package com.jge.newsstack

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication
/**I was confused here since I know I had to use HiltDagger to try and inject my dependencies
 * it was mentioned that I had to use a CustomTestRunner so that it could work. However, I ran into
 * compilation issues and errors. I thought I had to add this class in the test package, but then
 * I doubted myself and thought it was in the androidTest directory.
 * I feel like this was my roadblock in completing the tests and
 * I'm on the right direction, I just need a hint or 2 to figure it out.
 * **/
class CustomTestRunner:AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}