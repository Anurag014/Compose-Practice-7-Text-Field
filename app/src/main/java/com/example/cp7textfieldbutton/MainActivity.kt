package com.example.cp7textfieldbutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetMe()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetMe() {
    val scaffoldState = rememberScaffoldState()
    var textFieldState by remember{ mutableStateOf("")}
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            TextField(
                value = textFieldState,
                label = {
                        Text(text = "Enter your name")
                },
                onValueChange = {
                    textFieldState = it
                },
                placeholder = {
                              Text(text = "John Doe")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = colorResource(id = R.color.orange),
                    focusedLabelColor = colorResource(id = R.color.orange),
                    disabledLabelColor = colorResource(id = R.color.orange),
                    errorLabelColor = colorResource(id = R.color.orange),
                    cursorColor = colorResource(id = R.color.orange),
                    focusedIndicatorColor = colorResource(id = R.color.orange)
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.orange),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Greet Me")
            }
        }
    }
}