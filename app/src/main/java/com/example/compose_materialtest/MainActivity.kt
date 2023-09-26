package com.example.compose_materialtest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_materialtest.ui.theme.Compose_MaterialTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_MaterialTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 使用Scaffold將底部欄位的參數設為剛剛建立的導覽條函式
                    Scaffold(
                        bottomBar = { BottomNavigation(context = this) }
                    ){padding ->
                        GreetingBackground()
                    }
                }
            }
        }
    }
}
const val TAG : String = "SootheBottomNavigation"
// 底部導覽條
@Composable
private fun BottomNavigation(modifier: Modifier = Modifier, context:Context) {
    val resources = context.resources
    NavigationBar(
        modifier = modifier,
        contentColor = Color.White,
        containerColor = Color.Magenta
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_home)
                )
            },
            selected = true,
            onClick = {
                Log.e(TAG, "SootheBottomNavigation: Home" )
                Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                selectedTextColor = Color.Green,
                unselectedIconColor = Color.Red,
                unselectedTextColor = Color.Red
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_Search)
                )
            },
            selected = true,
            onClick = {
                Log.e(TAG, "SootheBottomNavigation: Search" )
                Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                selectedTextColor = Color.Green,
                unselectedIconColor = Color.Red,
                unselectedTextColor = Color.Red
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_profile)
                )
            },
            selected = false,
            onClick = {
                Log.e(TAG, "SootheBottomNavigation: Account" )
                Toast.makeText(context, "Account", Toast.LENGTH_SHORT).show()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                selectedTextColor = Color.Green,
                unselectedIconColor = Color.Red,
                unselectedTextColor = Color.Red
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_Settings)
                )
            },
            selected = false,
            onClick = {
                Log.e(TAG, "SootheBottomNavigation: Settings" )
                Toast.makeText(context, "Setting", Toast.LENGTH_SHORT).show()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Green,
                selectedTextColor = Color.Green,
                unselectedIconColor = Color.Red,
                unselectedTextColor = Color.Red
            )
        )
    }
}

@Composable
fun GreetingMessage(greetingMes: String, greetName:String) {

    // 如果想在重組後保留狀態，請使用 remember「記住」可變動狀態。
    val expanded = remember {mutableStateOf(false)}
    val extraPadding = if (expanded.value) 72.dp else 0.dp

    Column(modifier = Modifier.padding(24.dp)) {
        Row(modifier = Modifier
            .padding(bottom = extraPadding)
            .background(color = Color.LightGray)
        ){
            Text(text = greetingMes, Modifier
                .align(androidx.compose.ui.Alignment.Top)
                .padding(5.dp)
                .weight(4f))
            Text(text = greetName, modifier = Modifier
                .align(androidx.compose.ui.Alignment.CenterVertically)
                .padding(5.dp)
                .weight(1f))
        }
        ElevatedButton(onClick = {
            Log.e("ElevatedButton", "GreetingMessage: " )
            expanded.value =! expanded.value

        }) {
            Text(if (expanded.value) "簡略" else "詳細")
        }
    }
}

@Composable
fun GreetingBackground(){
    val image = painterResource(id = R.drawable.ic_launcher_foreground)
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column (Modifier.padding(10.dp)){
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                Modifier
                    .align(androidx.compose.ui.Alignment.CenterHorizontally)
                    .padding(12.dp)
            )
            GreetingMessage(
                greetingMes = "ITHome鐵人賽第15屆_Day16 Compose底部導覽條說明",
                greetName = "Jay"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val context = LocalContext.current // 獲取context在Preview中顯示
    Compose_MaterialTestTheme {
        Scaffold(
            bottomBar = { BottomNavigation(context = context) }
        ){padding ->
            GreetingBackground()
        }
    }
}