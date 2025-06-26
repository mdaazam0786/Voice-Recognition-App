package com.example.voicerecognitionapp.ui.languages.kannada

import android.content.Context

sealed class KannadaScreenEvent {
    data class onTextFieldClick(val text: String) : KannadaScreenEvent()
    data class onSpeakClick(val text: String, val context: Context) : KannadaScreenEvent()
}