package com.example.voicerecognitionapp.ui.languages.hindi

import android.content.Context

sealed class HindiScreenEvent {
    data class onTextFieldClick(val text: String) : HindiScreenEvent()
    data class onSpeakClick(val text: String, val context: Context) : HindiScreenEvent()
}