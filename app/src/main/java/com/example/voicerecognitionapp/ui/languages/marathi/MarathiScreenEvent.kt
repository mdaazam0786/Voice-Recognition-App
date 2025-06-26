package com.example.voicerecognitionapp.ui.languages.marathi

import android.content.Context

sealed class MarathiScreenEvent {
    data class onTextFieldClick(val text: String) : MarathiScreenEvent()
    data class onSpeakClick(val text: String, val context: Context) : MarathiScreenEvent()
}