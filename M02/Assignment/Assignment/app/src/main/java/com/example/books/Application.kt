package com.example.books

import android.app.Application
import timber.log.Timber



//TODO 4.1: changing the repo interface to refs
val repo: BookRepoInterface by lazy {
    App.repo!!
}

// TODO: 3. Extend Timber to include class, method, line numbers!
class MyDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return String.format(
            "[C:%s] [M:%s] [L:%s]",
            super.createStackElementTag(element),
            element.methodName,
            element.lineNumber
        )
    }
}

class App : Application() {

    // TODO: 4. Provide an Application-wide Shared Preferences
    companion object {
        var prefs: Prefs? = null
        var repo: BookRepoInterface? = null
    }

    override fun onCreate() {
        super.onCreate()

     //   prefs = Prefs(applicationContext)
        repo = BookRepo(applicationContext)
        // TODO: 2. Configure Timber logging
        // "Timber" Library
        if (BuildConfig.DEBUG) {
            Timber.plant(MyDebugTree())
        }
    }
}