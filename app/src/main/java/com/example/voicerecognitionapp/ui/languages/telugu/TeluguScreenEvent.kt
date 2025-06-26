package com.example.voicerecognitionapp.ui.languages.telugu

import android.content.Context

sealed class TeluguScreenEvent {
    data class onTextFieldClick(val text: String) : TeluguScreenEvent()
    data class onSpeakClick(val text: String, val context: Context) : TeluguScreenEvent()
}