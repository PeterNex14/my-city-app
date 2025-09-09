package com.gabsee.manadocity.ui

import androidx.lifecycle.ViewModel
import com.gabsee.manadocity.data.Category
import com.gabsee.manadocity.data.Place
import com.gabsee.manadocity.data.local.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ManadoViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ManadoUiState())
    val uiState: StateFlow<ManadoUiState> = _uiState

    init {
        initializeUiState()
    }

    private fun initializeUiState() {
        _uiState.value = _uiState.value.copy(
            categories = Datasource.getCategories()
        )
    }

    fun selectCategory(category: Category) {
        _uiState.value = _uiState.value.copy(
            selectedCategory = category,
            places = Datasource.getPlacesByCategory(category.categoryId)
        )
    }

    fun selectPlace(place: Place) {
        _uiState.value = _uiState.value.copy(
            selectedPlace = place
        )
    }


}