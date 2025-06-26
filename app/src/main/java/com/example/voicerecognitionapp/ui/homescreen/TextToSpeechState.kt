package com.example.voicerecognitionapp.ui.homescreen

data class TextToSpeechState(
    val text : String = "",
    val translatedText : String = "",
    val isButtonEnabled : Boolean = true
)