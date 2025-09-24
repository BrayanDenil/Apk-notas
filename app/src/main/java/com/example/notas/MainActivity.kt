package com.example.notas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.notas.vm.NoteViewModel

class MainActivity : ComponentActivity() {

    // TODO: obtener el ViewModel con delegado de Activity
    private val vm: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    // TODO: pasar el VM a la pantalla principal
                    MainScreen(vm = vm)
                }
            }
        }
    }}