package com.example.learningapp.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningapp.data.QuizState
import com.example.learningapp.data.SuggestionState
import com.example.learningapp.utils.ApiService
import com.example.learningapp.utils.Message
import com.example.learningapp.utils.OpenAIRequestBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class GPTViewModel : ViewModel() {
    val messages = mutableStateListOf<Message>()
    var responseMessage = mutableStateOf("")

    val myMutableState: MutableState<String>
        get() = responseMessage

    private val _responseState = MutableStateFlow(SuggestionState())
    val responseState: StateFlow<SuggestionState> = _responseState.asStateFlow()

    fun sendMessage(text: String) {
        messages.add(Message(text, "user"))
        viewModelScope.launch {
            try {
                val response = ApiService.openAIApi.generateResponse(OpenAIRequestBody(messages = messages))
                messages.clear()
                responseMessage.value = response.choices.first().message.content

                Log.d("response", myMutableState.value)

            } catch (e: HttpException) {
                // Handle HTTP errors (status codes other than 2xx)
                val errorBody = e.response()?.errorBody()?.string()
                Log.e("GPTViewModel", "HTTP error: ${e.code()}, ${errorBody ?: "No error body"}")
            } catch (e: Exception) {
                // Handle other exceptions
                Log.e("GPTViewModel", "Error sending message to GPT", e)
            }
        }
    }

    fun getResponseMessage(): String {
        return _responseState.value.gptResponseMessage
    }
}