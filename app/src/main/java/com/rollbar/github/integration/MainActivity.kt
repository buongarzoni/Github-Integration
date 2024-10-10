package com.rollbar.github.integration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rollbar.android.Rollbar
import com.rollbar.github.integration.ui.theme.GithubIntegrationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rollbar = Rollbar.init(this, BuildConfig.ROLLBAR_API_KEY, BuildConfig.BUILD_TYPE)
        enableEdgeToEdge()
        setContent {
            GithubIntegrationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding).fillMaxSize().padding(24.dp),
                        verticalArrangement = Arrangement.Bottom,
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                            onClick = {
                                rollbar.error("Some error message")
                            },
                        ) {
                            Text("Log an Error")
                        }
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                            onClick = {
                                throw RuntimeException("An uncaught RuntimeException")
                            },
                        ) {
                            Text("Throw an Exception")
                        }
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                throw Exception("An uncaught Exception")
                            },
                        ) {
                            Text("Throw an Exception")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GithubIntegrationTheme {
        Greeting("Android")
    }
}