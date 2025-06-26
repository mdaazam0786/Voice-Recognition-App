package com.example.voicerecognitionapp.ui.core_words

import android.content.Context

sealed class CoreWordsEvent {
    data class onPhrasesClick(val context: Context, val text : String): CoreWordsEvent()
    data class onTextfieldClick(val text : String) : CoreWordsEvent()
}