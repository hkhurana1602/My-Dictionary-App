package com.example.dictionaryapp.data.repository

import android.app.Application
import com.example.dictionaryapp.R
import com.example.dictionaryapp.data.api.DictionaryApi
import com.example.dictionaryapp.data.mapper.toWordItem
import com.example.dictionaryapp.domain.model.WordItem
import com.example.dictionaryapp.domain.repository.DictionaryRepository
import com.example.dictionaryapp.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class DictionaryRepositoryImplementation @Inject constructor(
    val dictionaryApi: DictionaryApi,
    private val application : Application
) : DictionaryRepository {
    override suspend fun getWordResult(
        word: String
    ): Flow<Result<WordItem>> {
        return flow{
            emit(
                Result.Loading(true)
            )

            val remoteWordResultDto = try {
                dictionaryApi.getWordResult(word)
            } catch (e : HttpException){
                e.printStackTrace()
                emit(Result.Error(application.getString(R.string.can_t_get_result)))
                emit(Result.Loading(false))
                return@flow
            } catch (e : IOException){
                e.printStackTrace()
                emit(Result.Error(application.getString(R.string.can_t_get_result)))
                emit(Result.Loading(false))
                return@flow
            } catch (e : Exception){
                e.printStackTrace()
                emit(Result.Error(application.getString(R.string.can_t_get_result)))
                emit(Result.Loading(false))
                return@flow
            }

            remoteWordResultDto?.let {wordResultDto ->
                wordResultDto[0]?.let { wordItemDto ->
                    emit(
                        Result.Success(wordItemDto.toWordItem())
                    )
                    emit(
                        Result.Loading(false)
                    )
                    return@flow
                }
            }
            emit(
                Result.Loading(false)
            )
        }
    }
}