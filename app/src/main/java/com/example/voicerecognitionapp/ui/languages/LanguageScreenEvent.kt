package com.example.voicerecognitionapp.ui.languages

import android.content.Context

sealed class LanguageScreenEvent {
    object onHindiClick : LanguageScreenEvent()
    object onMarathiClick : LanguageScreenEvent()
    object onGujratiClick : LanguageScreenEvent()
    object onKannadaClick : LanguageScreenEvent()
    object onTeluguClick : LanguageScreenEvent()
    data class onTextFieldClick(val text: String) : LanguageScreenEvent()
    data class onSpeakClick(val text: String, val context: Context) : LanguageScreenEvent()
}