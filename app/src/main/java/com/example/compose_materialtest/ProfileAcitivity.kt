package com.example.compose_materialtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_materialtest.ui.theme.AppTheme

class ProfileAcitivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileBackground()
                }
            }
        }
    }
}

@Composable
fun ProfileInfo(name: String,
                birthday: String,
                schoolExp: String,
                projects: String,
                elementary: String,
                junior: String,
                senior: String,
                college: String){
    Column (Modifier.fillMaxWidth()){

        Row(Modifier.fillMaxWidth()){
            Icon(imageVector = Icons.Default.AccountBox,
                contentDescription = null,
                Modifier
                    .weight(1f)
                    .size(50.dp))
            Text(text = name,
                Modifier
                    .weight(5f)
                    .size(50.dp), fontSize = MaterialTheme.typography.headlineLarge.fontSize)
        }
        Row(Modifier.fillMaxWidth()){
            Text(text = "生日：",
                Modifier.weight(1f))
            Text(text = birthday,
                Modifier.weight(3f))
        }
        Row(Modifier.fillMaxWidth()){
            Text(text = "學歷：",
                Modifier.weight(1f))
            Text(text = schoolExp,
                Modifier.weight(3f))
        }
        Row(Modifier.fillMaxWidth()){
            Text(text = "作品：",
                Modifier.weight(1f))
            Text(text = projects,
                Modifier.weight(3f))
        }
        Row(Modifier.fillMaxWidth()){
            Text(text = "就學歷程：",
                Modifier.weight(1f)
                    .align(Alignment.CenterVertically))
            Column (Modifier.weight(3f)){
                Row(){
                    Text(text = "小學：")
                    Text(text = elementary)
                }
                Row(){
                    Text(text = "國中：")
                    Text(text = junior)
                }
                Row(){
                    Text(text = "高中：")
                    Text(text = senior)
                }
                Row(){
                    Text(text = "大學：")
                    Text(text = college)
                }
            }
        }
    }
}

@Composable
fun ProfileBackground(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Column (Modifier.padding(10.dp)){
            ProfileInfo(name = "Jay Chen",
                "2023/09/30",
                "科技大學",
                "Android App",
                "臺北市立XX高級中學",
                "臺北市立XX高級中學",
                "臺北市立XX高級中學",
                "國立XX科技大學")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    AppTheme {
        ProfileBackground()
    }
}