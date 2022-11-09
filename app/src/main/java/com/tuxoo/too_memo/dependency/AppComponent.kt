package com.tuxoo.too_memo.dependency

import android.content.Context
import androidx.fragment.app.Fragment
import com.tuxoo.too_memo.screen.notes.NotesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class
    ]
)
interface AppComponent {

    fun inject(fragment: NotesFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(context: Context): Builder

        fun build(): AppComponent
    }
}