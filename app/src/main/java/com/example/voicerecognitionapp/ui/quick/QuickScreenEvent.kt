package com.example.voicerecognitionapp.ui.quick

import android.content.Context

sealed class QuickScreenEvent {
    data class onPhraseClick(val context: Context, val text : String) : QuickScreenEvent()
    data class onTextFieldClick(val text : String) : QuickScreenEvent()
    object onGreetingsAndMannerClick : QuickScreenEvent()
    object onWishesClick : QuickScreenEvent()
    object onNeedHelpClick : QuickScreenEvent()
    object onRequestsClick : QuickScreenEvent()
    object onUnderstandingsClick : QuickScreenEvent()
    object onQuestionsClick : QuickScreenEvent()
    object onAboutMeClick : QuickScreenEvent()
    object onStarterPhrasesClick : QuickScreenEvent()
    object onResponseClick : QuickScreenEvent()

}