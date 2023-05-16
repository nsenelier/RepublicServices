package com.example.republicservices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.republicservices.ui.theme.RepublicServicesTheme
import com.example.republicservices.viewModels.MainViewModel
import com.example.republicservices.views.MainScreen
import com.example.republicservices.views.RouteScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepublicServicesTheme {
                // A surface container using the 'background' color from the theme
                App()
            }
        }
    }
}

@Composable
fun App(){
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination ="MAIN_SCREEN" ){
        composable("MAIN_SCREEN"){
            MainScreen(navController, mainViewModel)
        }
        composable("ROUTE_SCREEN"){
            RouteScreen(navController, mainViewModel)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RepublicServicesTheme {
       App()
    }
}