package com.example.republicservices.views

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.republicservices.data.local.entity.RouteEntity
import com.example.republicservices.viewModels.MainViewModel

@Composable
fun RouteScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
){
    viewModel.getRoute()
    val routes = viewModel.displayRoute

    LaunchedEffect(key1 = viewModel, block = {})

    if(routes.isNotEmpty()){
        LazyColumn(
            modifier = Modifier
                .padding(12.dp)
        ){
            items(routes.size){i ->
                val route = routes[i]
                RouteItem(modifier = Modifier, route = route)
                if (i < routes.size) {
                    Divider(
                        modifier = Modifier.padding(
                            horizontal = 10.dp
                        )
                    )
                }
            }
        }
    }
    else{
        Toast.makeText(LocalContext.current, "Item not found", Toast.LENGTH_LONG).show()

    }
}

@Composable
fun RouteItem(
    modifier: Modifier,
    route: RouteEntity
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),

    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(alignment = Alignment.Center),
                text = route.name,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 22.sp
            )

        }
    }
}