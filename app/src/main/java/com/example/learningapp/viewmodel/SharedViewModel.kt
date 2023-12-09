package com.example.learningapp.viewmodel

import com.example.learningapp.data.ConversationDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SharedViewModel {
    private val _detailConvoState = MutableStateFlow(ConversationDetailState())
    val detailConvoState: StateFlow<ConversationDetailState> = _detailConvoState.asStateFlow()

    fun OnConvoClick(index: Int) {
        _detailConvoState.update { uiState ->
            uiState.copy(
                currentConversationIndex = index
            )
        }
    }

    fun getIndex(): Int {
       return _detailConvoState.value.currentConversationIndex
    }
}