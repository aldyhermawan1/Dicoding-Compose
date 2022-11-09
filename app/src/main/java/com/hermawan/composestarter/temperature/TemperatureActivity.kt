package com.hermawan.composestarter.temperature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hermawan.composestarter.R
import com.hermawan.composestarter.temperature.Scale.CELSIUS
import com.hermawan.composestarter.temperature.Scale.FAHRENHEIT
import com.hermawan.composestarter.temperature.ui.theme.ComposeStarterTheme

class TemperatureActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStarterTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column {
                        StatefulTemperatureInput()
                        ConverterApp()
                        TwoWayConverterApp()
                    }
                }
            }
        }
    }
}

// STATEFUL START
@Composable
fun StatefulTemperatureInput(
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }
    Column(modifier.padding(16.dp)) {
        Text(text = stringResource(id = R.string.stateful_converter), style = MaterialTheme.typography.h5)
        OutlinedTextField(
            value = input,
            onValueChange = {
                input = it
                output = convertToFahrenheit(it)
            },
            label = { Text(text = stringResource(id = R.string.enter_celsius)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = stringResource(id = R.string.temperature_fahrenheit, output))
    }
}
// STATEFUL END

// STATELESS START
@Composable
fun StatelessTemperatureInput(
    input: String,
    output: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(16.dp)) {
        Text(text = stringResource(id = R.string.stateless_converter), style = MaterialTheme.typography.h5)
        OutlinedTextField(
            value = input,
            onValueChange = onValueChange,
            label = { Text(text = stringResource(id = R.string.enter_celsius)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = stringResource(id = R.string.temperature_fahrenheit, output))
    }
}

@Composable
private fun ConverterApp(
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }
    Column(modifier) {
        StatelessTemperatureInput(
            input = input,
            output = output,
            onValueChange = {
                input = it
                output = convertToFahrenheit(it)
            }
        )
    }
}
// STATELESS END

//TWO WAY CONVERTER START
@Composable
fun GeneralTemperatureInput(
    scale: Scale,
    input: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        OutlinedTextField(
            value = input,
            onValueChange = onValueChange,
            label = { Text(text = stringResource(id = R.string.enter_temperature, scale.scaleName)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
private fun TwoWayConverterApp(
    modifier: Modifier = Modifier
) {
    var celsius by remember { mutableStateOf("") }
    var fahrenheit by remember { mutableStateOf("") }
    Column(modifier.padding(16.dp)) {
        Text(text = stringResource(id = R.string.two_way_converter), style = MaterialTheme.typography.h5)
        GeneralTemperatureInput(scale = CELSIUS, input = celsius, onValueChange = {
            celsius = it
            fahrenheit = convertToFahrenheit(it)
        })
        GeneralTemperatureInput(scale = FAHRENHEIT, input = fahrenheit, onValueChange = {
            fahrenheit = it
            celsius = convertToCelsius(it)
        })
    }
}
//TWO WAY CONVERTER END

@Preview(showBackground = true)
@Composable
fun TemperaturePreview() {
    ComposeStarterTheme {
        Column {
            StatefulTemperatureInput()
            ConverterApp()
            TwoWayConverterApp()
        }
    }
}

private fun convertToFahrenheit(celsius: String) = celsius.toDoubleOrNull()?.let {
    (it * 9 / 5) + 32
}.toString()

private fun convertToCelsius(fahrenheit: String) = fahrenheit.toDoubleOrNull()?.let {
    (it - 32) * 5 / 9
}.toString()

enum class Scale(val scaleName: String) {
    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit")
}