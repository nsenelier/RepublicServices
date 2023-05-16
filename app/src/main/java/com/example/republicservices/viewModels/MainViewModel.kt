package com.example.republicservices.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.republicservices.data.local.entity.DriverEntity
import com.example.republicservices.data.local.entity.RouteEntity
import com.example.republicservices.domain.model.DataResponse
import com.example.republicservices.domain.usecases.GetDriversAndRoutesUseCase
import com.example.republicservices.domain.usecases.GetLocalDriversUseCase
import com.example.republicservices.domain.usecases.GetLocalRoutesUseCase
import com.example.republicservices.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val getDriversAndRoutesUseCase: GetDriversAndRoutesUseCase,
    private val getLocalDriversUseCase: GetLocalDriversUseCase,
    private val getLocalRoutesUseCase: GetLocalRoutesUseCase
): ViewModel() {
    private val _uiState: MutableStateFlow<UIState<DataResponse>> = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<DataResponse>> get() = _uiState

    var tempDrivers: MutableList<DriverEntity> = mutableListOf()
    private var tempRoute: MutableList<RouteEntity> = mutableListOf()
    var displayRoute: MutableList<RouteEntity> = mutableListOf()
    var selectedDriver: Int = 0


    private fun getDriversAndRoutes(){
        viewModelScope.launch(dispatcher) {
            getDriversAndRoutesUseCase()
                .collect{
                    _uiState.value = it
                }
        }
    }


    fun getDrivers(){
        getDriversAndRoutes()
        tempDrivers.clear()

        viewModelScope.launch(dispatcher) {
            val listDriver = getLocalDriversUseCase.getData()
            tempDrivers.addAll(listDriver)
        }
    }

    fun getRoute(){
        displayRoute.clear()

        viewModelScope.launch(dispatcher) {
            val listRoute = getLocalRoutesUseCase.getData()
            tempRoute.addAll(listRoute)
        }

        //One form of logic but to long
        tempRoute.find { it.id == selectedDriver }?.let {
            if(!displayRoute.contains(it)){
                displayRoute.add(it)
            }
        }
        if(selectedDriver % 2 == 0) tempRoute.find { it.type == "R" }?.let {
            if(!displayRoute.contains(it)){
                displayRoute.add(it)
            }
        }
        if(selectedDriver % 5 == 0) tempRoute.find { it.type == "C" }?.let {
            if(!displayRoute.contains(it)){
                displayRoute.add(it)
            }
        }
        if(selectedDriver % 2 != 0 || selectedDriver % 5 != 0) tempRoute.find { it.type == "I" }?.let {
                if(!displayRoute.contains(it)){
                    displayRoute.add(it)
                }
            }


    }


}
//tempRoute.forEach{
//    if(it.id == selectedDriver) displayRoute.add(it)
//    if(selectedDriver % 2 == 0 && it.type == "R") displayRoute.add(it)
//    if(selectedDriver % 5 == 0 && it.type == "C") displayRoute.add(it)
//    if(selectedDriver % 2 != 0 || selectedDriver % 5 != 0 &&
//        it.type == "I") displayRoute.add(it)
//}