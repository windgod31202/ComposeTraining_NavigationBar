package com.example.compose_materialtest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_materialtest.ui.theme.AppTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SettingBackground()
                }
            }
        }
    }
}

@Composable
fun SettingsSelect() {
    val context = LocalContext.current
    Column (Modifier.fillMaxWidth()){
        Text(text = "設定",
            Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontStyle = MaterialTheme.typography.headlineLarge.fontStyle,
            textAlign = MaterialTheme.typography.headlineLarge.textAlign,
            color = MaterialTheme.colorScheme.onBackground)
        ElevatedButton(onClick = {
            Toast.makeText(context, "SelfInfo Modify!", Toast.LENGTH_SHORT).show()
        },Modifier.fillMaxWidth()) {
            Icon(imageVector = Icons.Default.AccountCircle,
                contentDescription = null)
            Text(text = "個人資料修改")
        }
        ElevatedButton(onClick = {
            Toast.makeText(context, "Schedule", Toast.LENGTH_SHORT).show()
        },Modifier.fillMaxWidth()) {
            Icon(imageVector = Icons.Default.DateRange,
                contentDescription = null)
            Text(text = "行事曆")
        }
        ElevatedButton(onClick = {
            Toast.makeText(context, "password reset!", Toast.LENGTH_SHORT).show()
        },Modifier.fillMaxWidth()) {
            Icon(imageVector = Icons.Default.Lock,
                contentDescription = null)
            Text(text = "密碼重製")
        }
        ElevatedButton(onClick = {
            Toast.makeText(context, "Logout your account!", Toast.LENGTH_SHORT).show()
            Log.e(TAG, "SettingsSelect: Logout your account!" )
        },Modifier.fillMaxWidth()) {
            Icon(imageVector = Icons.Default.ExitToApp,
                contentDescription = null)
            Text(text = "登出")
        }
    }
}

@Composable
fun SettingBackground(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Column (Modifier.padding(10.dp)){
            SettingsSelect()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    AppTheme {
        SettingBackground()
    }
}