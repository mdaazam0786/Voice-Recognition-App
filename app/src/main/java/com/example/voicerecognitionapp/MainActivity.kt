package com.example.voicerecognitionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.voicerecognitionapp.ui.advanced.AdvancedScreen
import com.example.voicerecognitionapp.ui.basic.BasicScreen
import com.example.voicerecognitionapp.ui.core_words.CoreWordsScreen
import com.example.voicerecognitionapp.ui.getting_started.GettingStartedScreen
import com.example.voicerecognitionapp.ui.homescreen.HomeScreen
import com.example.voicerecognitionapp.ui.languages.LanguageScreen
import com.example.voicerecognitionapp.ui.languages.gujrati.GujratiScreen
import com.example.voicerecognitionapp.ui.languages.hindi.HindiScreen
import com.example.voicerecognitionapp.ui.languages.kannada.KannadaScreen
import com.example.voicerecognitionapp.ui.languages.marathi.MarathiScreen
import com.example.voicerecognitionapp.ui.languages.telugu.TeluguScreen
import com.example.voicerecognitionapp.ui.quick.QuickScreen
import com.example.voicerecognitionapp.ui.theme.VoiceRecognitionAppTheme
import com.example.voicerecognitionapp.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            VoiceRecognitionAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.HOME ) {
                    composable(Routes.HOME) {
                        HomeScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                    composable(Routes.QUICK) {
                        QuickScreen(
                            popBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(Routes.BASIC) {
                        BasicScreen(
                            popBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(Routes.GETTING_STARTED) {
                        GettingStartedScreen(
                            popBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(Routes.ADVANCED) {
                        AdvancedScreen(
                            popBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(Routes.CORE_WORDS) {
                        CoreWordsScreen(
                            popBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(Routes.LANGUAGES) {
                        LanguageScreen (
                            popBackStack = {
                                navController.popBackStack()
                            },
                            onNavigate = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                    composable(Routes.HINDI) {
                        HindiScreen(
                            popBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(Routes.TELUGU) {
                        TeluguScreen(
                            popBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(Routes.MARATHI) {
                        MarathiScreen(
                            popBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(Routes.KANNADA) {
                        KannadaScreen(
                            popBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(Routes.GUJRATI) {
                        GujratiScreen(
                            popBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

