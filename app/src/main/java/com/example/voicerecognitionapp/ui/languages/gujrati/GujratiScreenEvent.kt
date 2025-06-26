package com.example.voicerecognitionapp.ui.languages.gujrati

import android.content.Context

sealed class GujratiScreenEvent {
    data class onTextFieldClick(val text: String) : GujratiScreenEvent()
    data class onSpeakClick(val text: String, val context: Context) : GujratiScreenEvent()
}