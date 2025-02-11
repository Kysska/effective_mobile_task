package com.example.effective_mobile_task.di

import com.example.effective_mobile_task.MainActivity
import dagger.Subcomponent

@Subcomponent
interface MainActivityComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }

    fun inject(activity: MainActivity)
}
