package ir.amir.isnta.di.repository

import dagger.hilt.DefineComponent
import dagger.hilt.android.components.ViewModelComponent

@DefineComponent(parent = ViewModelComponent::class)
interface RepositoryComponent{

    @DefineComponent.Builder
    interface Builder {
        fun build() : RepositoryComponent
    }
}