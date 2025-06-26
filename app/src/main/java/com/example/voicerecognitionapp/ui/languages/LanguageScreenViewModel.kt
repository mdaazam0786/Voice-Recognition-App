package com.example.voicerecognitionapp.ui.languages

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voicerecognitionapp.ui.homescreen.TextToSpeechState
import com.example.voicerecognitionapp.util.Routes
import com.example.voicerecognitionapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LanguageScreenViewModel @Inject constructor()
    : ViewModel() {
    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var _state = mutableStateOf(TextToSpeechState())
    var state : State<TextToSpeechState> = _state

    private var textToSpeech : TextToSpeech? = null

    fun onLanguageEvent(event: LanguageScreenEvent){
        when(event){
            LanguageScreenEvent.onGujratiClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.GUJRATI))
            }
            LanguageScreenEvent.onHindiClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.HINDI))
            }
            LanguageScreenEvent.onKannadaClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.KANNADA))
            }
            LanguageScreenEvent.onMarathiClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.MARATHI))
            }
            LanguageScreenEvent.onTeluguClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.TELUGU))
            }

            is LanguageScreenEvent.onSpeakClick -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        text = event.text
                    )
                    textToSpeech(event.context)
                }
            }
            is LanguageScreenEvent.onTextFieldClick -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        text = event.text
                    )
                }
            }
        }
    }
    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
    private fun textToSpeech(context: Context){
        _state.value = state.value.copy(
            isButtonEnabled = false
        )
        textToSpeech = TextToSpeech(
            context
        ) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.US
                    txtToSpeech.setSpeechRate(1.0f)
                    txtToSpeech.speak(
                        _state.value.text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
            _state.value = state.value.copy(
                isButtonEnabled = true
            )
        }
    }
}