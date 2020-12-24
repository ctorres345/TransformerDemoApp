package com.pandaveloper.transformersdemo.di

import com.pandaveloper.data.repository.remote.TransformerRemoteRepository
import com.pandaveloper.domain.executor.DefaultDispatcherProvider
import com.pandaveloper.domain.executor.DispatcherProvider
import com.pandaveloper.domain.repository.TransformerRepository
import com.pandaveloper.domain.usecase.DeleteTransformerUseCase
import com.pandaveloper.domain.usecase.EditTransformerUseCase
import com.pandaveloper.domain.usecase.GetAllSparkTokenUseCase
import com.pandaveloper.domain.usecase.GetRecruitableTransformersUseCase
import com.pandaveloper.domain.usecase.GetTransformersUseCase
import com.pandaveloper.domain.usecase.RegisterTransformerUseCase
import com.pandaveloper.domain.usecase.TeamBattleResultUseCase
import com.pandaveloper.domain.usecase.ValidateVersusTeamsUseCase
import com.pandaveloper.domain.usecase.impl.DeleteTransformerUseCaseImpl
import com.pandaveloper.domain.usecase.impl.EditTransformerUseCaseImpl
import com.pandaveloper.domain.usecase.impl.GetAllSparkTokenUseCaseImpl
import com.pandaveloper.domain.usecase.impl.GetRecruitableTransformersUseCaseImpl
import com.pandaveloper.domain.usecase.impl.GetTransformersUseCaseImpl
import com.pandaveloper.domain.usecase.impl.RegisterTransformerUseCaseImpl
import com.pandaveloper.domain.usecase.impl.TeamBattleResultUseCaseImpl
import com.pandaveloper.domain.usecase.impl.ValidateVersusTeamsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun providesGetTokenUseCase(useCase: GetAllSparkTokenUseCaseImpl): GetAllSparkTokenUseCase

    @Binds
    abstract fun providesGetTransformersUseCase(useCase: GetTransformersUseCaseImpl): GetTransformersUseCase

    @Binds
    abstract fun providesGetRecruitableTransformersUseCase(useCase: GetRecruitableTransformersUseCaseImpl): GetRecruitableTransformersUseCase

    @Binds
    abstract fun providesRegisterTransformerUseCase(useCase: RegisterTransformerUseCaseImpl): RegisterTransformerUseCase

    @Binds
    abstract fun providesDeleteTransformerUseCase(useCase: DeleteTransformerUseCaseImpl): DeleteTransformerUseCase

    @Binds
    abstract fun providesEditTransformerUseCase(useCase: EditTransformerUseCaseImpl): EditTransformerUseCase

    @Binds
    abstract fun providesValidateVersusTeamsUseCase(useCase: ValidateVersusTeamsUseCaseImpl): ValidateVersusTeamsUseCase

    @Binds
    abstract fun providesTeamBattleResultUseCase(useCase: TeamBattleResultUseCaseImpl): TeamBattleResultUseCase

    @Binds
    abstract fun providesTransformerRepository(repository: TransformerRemoteRepository): TransformerRepository

}