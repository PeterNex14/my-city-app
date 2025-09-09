package com.gabsee.manadocity.ui

import com.gabsee.manadocity.data.Category
import com.gabsee.manadocity.data.Place
import com.gabsee.manadocity.data.local.Datasource

data class ManadoUiState(
    val categories: List<Category> = emptyList(),
    val selectedCategory: Category? = null,
    val places: List<Place> = emptyList(),
    val selectedPlace: Place? = null
)