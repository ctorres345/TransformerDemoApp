package com.pandaveloper.transformersdemo.di

import com.pandaveloper.data.repository.remote.RetrofitBuilder
import com.pandaveloper.data.repository.remote.TransformerServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideTransformerService(builder: RetrofitBuilder): TransformerServices =
        builder.transformerServices

}