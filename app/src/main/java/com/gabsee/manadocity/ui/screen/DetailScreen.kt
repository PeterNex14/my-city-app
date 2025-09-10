package com.gabsee.manadocity.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabsee.manadocity.data.Place
import com.gabsee.manadocity.data.local.Datasource
import com.gabsee.manadocity.ui.theme.ManadoCityTheme

@Composable
fun DetailsScreen(
    place: Place?,
    modifier: Modifier = Modifier
) {
    place?.let {
        Column(
            modifier = modifier
                .padding(
                    vertical = 24.dp,
                    horizontal = 14.dp
                )
        ) {
            Image(
                painter = painterResource(it.placeImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Text(
                text = stringResource(it.placeDescription),
                modifier = Modifier
                    .padding(12.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    ManadoCityTheme {
        DetailsScreen(
            place = Datasource.listOfPlaces[0]
        )
    }
}
