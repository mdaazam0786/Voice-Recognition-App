package com.example.voicerecognitionapp.ui.languages.marathi


import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voicerecognitionapp.ui.homescreen.TextToSpeechState
import com.example.voicerecognitionapp.util.UiEvent
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class MarathiScreenViewModel @Inject constructor()
    : ViewModel() {
    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var _state = mutableStateOf(TextToSpeechState())
    var state : State<TextToSpeechState> = _state

    private var textToSpeech : TextToSpeech? = null


    fun onMarathiScreenEvent(event: MarathiScreenEvent){
        when(event){
            is MarathiScreenEvent.onSpeakClick -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        translatedText = event.text
                    )
                    marathiToEnglish(event.text, event.context)
                }
            }
            is MarathiScreenEvent.onTextFieldClick -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        text = event.text
                    )
                }
            }
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
                    txtToSpeech.language = Locale.forLanguageTag("mr-IN")
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
    private fun marathiToEnglish(text : String, context: Context){
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.MARATHI)
            .build()

        val englishMarathiTranslator = Translation.getClient(options)

        englishMarathiTranslator.translate(text).addOnSuccessListener {
            _state.value = state.value.copy(
                text = it
            )
            textToSpeech(context)
        }
            .addOnFailureListener {
                downloadModelIfNotAvailable(englishMarathiTranslator,context)
                Toast.makeText(
                    context,
                    "Download Started",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
    private fun downloadModelIfNotAvailable(
        translator : Translator,
        context: Context
    ){
        _state.value = state.value.copy(
            isButtonEnabled = false
        )
        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()

        translator.downloadModelIfNeeded(conditions).addOnSuccessListener {
            _state.value = state.value.copy(
                isButtonEnabled = true
            )

        }
            .addOnFailureListener {
                Toast.makeText(
                    context,
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()

            }



    }
}