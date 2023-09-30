package com.example.compose_materialtest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_materialtest.ui.theme.AppTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Background()
                }
            }
        }
    }
}


@Composable
fun HomeInfo(greetingMes: String, greetName:String) {

    // 如果想在重組後保留狀態，請使用 remember「記住」可變動狀態。
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 72.dp else 0.dp
    
    Column(modifier = Modifier
        .padding(24.dp)
        .background(MaterialTheme.colorScheme.onBackground)) {
        Row(modifier = Modifier
            .padding(bottom = extraPadding)
            .background(color = MaterialTheme.colorScheme.onBackground)
        ){
            Text(text = greetingMes, Modifier
                .align(Alignment.Top)
                .padding(5.dp)
                .weight(4f), color = MaterialTheme.colorScheme.background)
            Text(text = greetName, modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(5.dp)
                .weight(1f), color = MaterialTheme.colorScheme.background)
        }
        ElevatedButton(onClick = {
            Log.e("ElevatedButton", "GreetingMessage: " )
            expanded.value =! expanded.value

        },Modifier
            .background(MaterialTheme.colorScheme.onBackground),
            shape = MaterialTheme.shapes.extraLarge,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 20.dp,
                focusedElevation = 30.dp,
                hoveredElevation = 30.dp,
                disabledElevation = 0.dp
            )){
            Text(if (expanded.value) "簡略" else "詳細")
        }
    }
}

@Composable
fun Background(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(top = 30.dp)) {
        Column (Modifier.padding(10.dp)){
            HomeInfo(
                greetingMes = "HomePage",
                greetName = "Jay"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeActivityPreview() {
    AppTheme {
        Background()
    }
}