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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    Scaffold(
                        bottomBar = { BottomNavigation(context = this) }
                    ){padding ->
                        HomeBackground()
                    }
                }
            }
        }
    }
}

// 底部導覽條
@Composable
private fun BottomNavigation(modifier: Modifier = Modifier, context: Context) {
    val resources = context.resources
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
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
                selectedIconColor = MaterialTheme.colorScheme.secondary,
                selectedTextColor = MaterialTheme.colorScheme.secondary,
                unselectedIconColor = MaterialTheme.colorScheme.tertiary,
                unselectedTextColor = MaterialTheme.colorScheme.tertiary
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
                selectedIconColor = MaterialTheme.colorScheme.secondary,
                selectedTextColor = MaterialTheme.colorScheme.secondary,
                unselectedIconColor = MaterialTheme.colorScheme.tertiary,
                unselectedTextColor = MaterialTheme.colorScheme.tertiary
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
                selectedIconColor = MaterialTheme.colorScheme.secondary,
                selectedTextColor = MaterialTheme.colorScheme.secondary,
                unselectedIconColor = MaterialTheme.colorScheme.tertiary,
                unselectedTextColor = MaterialTheme.colorScheme.tertiary
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
                selectedIconColor = MaterialTheme.colorScheme.secondary,
                selectedTextColor = MaterialTheme.colorScheme.secondary,
                unselectedIconColor = MaterialTheme.colorScheme.tertiary,
                unselectedTextColor = MaterialTheme.colorScheme.tertiary
            )
        )
    }
}


@Composable
fun HomeInfo(greetingMes: String, greetName:String) {

    // 如果想在重組後保留狀態，請使用 remember「記住」可變動狀態。
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 72.dp else 0.dp

    Column(modifier = Modifier.padding(24.dp).background(MaterialTheme.colorScheme.onBackground)) {
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
fun HomeBackground(){
    val image = painterResource(id = R.drawable.ic_launcher_foreground)
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Column (Modifier.padding(10.dp)){
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(12.dp)
            )
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
    val context = LocalContext.current // 獲取context在Preview中顯示
    AppTheme {
        Scaffold(
            bottomBar = { BottomNavigation(context = context) }
        ){padding ->
            HomeBackground()
        }
    }
}