package com.gabsee.manadocity.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabsee.manadocity.data.Place
import com.gabsee.manadocity.data.local.Datasource
import com.gabsee.manadocity.ui.theme.ManadoCityTheme

@Composable
fun PlacesLists(
    place: List<Place>,
    selectedPlace: Place?,
    onClick: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(
                vertical = 24.dp,
                horizontal = 14.dp
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(place) { item ->
            PlacesCardItem(
                place = item,
                onCardClicked = onClick,
                isPlaceSelected = item == selectedPlace
            )
        }
    }
}

@Composable
fun PlacesCardItem(
    place: Place,
    isPlaceSelected: Boolean,
    onCardClicked: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = if (isPlaceSelected) {
            CardDefaults.cardColors(Color(0xFFb8cdff))
        } else {
            CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
        },
        onClick = { onCardClicked(place) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(place.placeImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp, 80.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(place.placeName),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlacesListsPreview() {
    ManadoCityTheme {
        PlacesLists(
            place = Datasource.listOfPlaces,
            onClick = {},
            selectedPlace = Datasource.listOfPlaces[1]
        )
    }
}