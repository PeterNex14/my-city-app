package com.gabsee.manadocity.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gabsee.manadocity.data.Category
import com.gabsee.manadocity.data.Place

@Composable
fun ListsAndDetails(
    place: List<Place>,
    selectedPlace: Place?,
    category: List<Category>,
    onCategoryClicked: (Category) -> Unit,
    onPlaceClicked: (Place) -> Unit,
    contentPadding: PaddingValues,
    selectedCategory: Category?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(contentPadding),
        horizontalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        CategoryLists(
            category = category,
            onClick = onCategoryClicked,
            modifier = Modifier.weight(1f),
            isCategorySelected = selectedCategory
        )
        PlacesLists(
            place = place,
            onClick = onPlaceClicked,
            modifier = Modifier.weight(1f),
            selectedPlace = selectedPlace
        )
        DetailsScreen(
            place = selectedPlace,
            modifier = Modifier.weight(1f)
        )
    }
}