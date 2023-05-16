package com.example.republicservices.views

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.republicservices.data.local.entity.DriverEntity
import com.example.republicservices.utils.UIState
import com.example.republicservices.viewModels.MainViewModel
import com.google.android.material.search.SearchBar

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
){
    val state by viewModel.uiState.collectAsState()

    val drivers = viewModel.tempDrivers

    LaunchedEffect(key1 = viewModel, block = { viewModel.getDrivers()})
    val sortedList = drivers.sortedBy { it.name.split(" ")[1] }

   when(state) {
       is UIState.Loading -> {
           Column(
               horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier.fillMaxWidth()
           ) {
               CircularProgressIndicator()
               //CustomCircularProgressBar() could use custom Material 3 provides
           }
       }
       is UIState.Failure -> {
           Text(
               text = "${(state as UIState.Failure).errorMessage}",
               color = MaterialTheme.colorScheme.error,
               textAlign = TextAlign.Center,
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = 20.dp)
           )
           Toast.makeText(LocalContext.current, "Drivers not found", Toast.LENGTH_LONG).show()

       }
       is UIState.Success -> {
           LazyColumn(
               modifier = Modifier
                   .padding(12.dp)
           ){
               items(drivers.size){i ->
                   val driver = sortedList[i]
                   DriverItem(driver = driver, modifier = Modifier,
                       navController = navController)
                   {
                       viewModel.selectedDriver = it.id
                   }
                   if (i < drivers.size) {
                       Divider(
                           modifier = Modifier.padding(
                               horizontal = 10.dp
                           )
                       )
                   }
               }
           }
       }
   }
 
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverItem(
    driver: DriverEntity,
    modifier: Modifier,
    navController: NavController,
    selectedItem: (DriverEntity) -> Unit
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = {
            selectedItem.invoke(driver)
            navController.navigate("ROUTE_SCREEN")
        }
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(alignment = Alignment.Center),
                text = driver.name,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 22.sp
            )

        }
    }
}


