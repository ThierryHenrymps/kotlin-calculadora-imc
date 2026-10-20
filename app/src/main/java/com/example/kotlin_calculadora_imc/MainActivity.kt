package com.example.kotlin_calculadora_imc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale
import kotlin.math.round

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IMCLayout()
                }
            }
        }
    }
}

@Composable
fun IMCLayout() {

    var pesoInput by remember { mutableStateOf("") }
    var alturaInput by remember { mutableStateOf("") }
    var arredondar by remember { mutableStateOf(false) }

    var imcResultado by remember { mutableStateOf<Double?>(null) }
    var classificacao by remember { mutableStateOf("") }
    var mensagemErro by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Calculadora de IMC",
            fontSize = 26.sp,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(Alignment.Start)
        )

        TextField(
            value = pesoInput,
            onValueChange = {
                pesoInput = it
                mensagemErro = ""
            },
            label = { Text("Peso (kg)") },
            placeholder = { Text("Ex.: 72,5") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        TextField(
            value = alturaInput,
            onValueChange = {
                alturaInput = it
                mensagemErro = ""
            },
            label = { Text("Altura (m)") },
            placeholder = { Text("Ex.: 1,80") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Arredondar resultado",
                fontSize = 16.sp
            )

            Switch(
                checked = arredondar,
                onCheckedChange = {
                    arredondar = it
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                val peso = pesoInput
                    .replace(",", ".")
                    .toDoubleOrNull()

                val altura = alturaInput
                    .replace(",", ".")
                    .toDoubleOrNull()

                if (
                    peso == null ||
                    altura == null ||
                    peso <= 0.0 ||
                    altura <= 0.0
                ) {
                    imcResultado = null
                    classificacao = ""
                    mensagemErro = "Digite um peso e uma altura"
                } else {

                    val imc = peso / (altura * altura)

                    imcResultado = if (arredondar) {
                        round(imc * 100) / 100
                    } else {
                        imc
                    }

                    classificacao = classificarIMC(imc)
                    mensagemErro = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calcular")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (mensagemErro.isNotEmpty()) {
            Text(
                text = mensagemErro,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        imcResultado?.let { resultado ->

            Text(
                text = "Seu IMC: ${
                    String.format(Locale("pt", "BR"), "%.2f", resultado)
                }",
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Classificacao: $classificacao",
                fontSize = 20.sp
            )
        }
    }
}

fun classificarIMC(imc: Double): String {

    return when {
        imc < 18.5 -> "Abaixo do peso"
        imc < 25.0 -> "Peso normal"
        imc < 30.0 -> "Sobrepeso"
        else -> "Obesidade"
    }
}
