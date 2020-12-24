package com.pandaveloper.data.repository.remote

import com.pandaveloper.data.model.RecruitableResponseDTO
import com.pandaveloper.data.model.ResponseDTO
import com.pandaveloper.data.model.TransformerRequestDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TransformerServices {

    @GET("/allspark")
    suspend fun getToken() : String

    @GET("/transformers")
    suspend fun getTransformers() : ResponseDTO

    @GET("https://run.mocky.io/v3/c0978e18-6f68-4b33-9df3-0ecabbe5268b")
    suspend fun getRecruitableTransformers() : RecruitableResponseDTO

    @GET("/transformers/{transformerId}")
    suspend fun getTransformersById(@Path("transformerId") transformerId: Int) : ResponseDTO

    @POST("/transformers")
    suspend fun registerTransformer(@Body transformer: TransformerRequestDTO)

    @PUT("/transformers")
    suspend fun updateTransformer(@Body transformer: TransformerRequestDTO)

    @DELETE("/transformers/{transformerId}")
    suspend fun deleteTransformerById(@Path("transformerId") transformerId: String) : Response<Unit>
}