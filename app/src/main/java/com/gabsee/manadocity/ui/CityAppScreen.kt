package com.gabsee.manadocity.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gabsee.manadocity.R
import com.gabsee.manadocity.data.Category
import com.gabsee.manadocity.data.Place
import com.gabsee.manadocity.data.local.Datasource
import com.gabsee.manadocity.ui.theme.ManadoCityTheme

enum class ManadoCityScreen() {
    Start,
    Recommended,
    Details
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManadoCityTopAppBar(
    @StringRes currentScreen: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(currentScreen)) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        modifier = modifier
    )
}


@Composable
fun ManadoCityApp(
    viewModel: ManadoViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    Scaffold(
        modifier = modifier,
        topBar = {
            ManadoCityTopAppBar(
                currentScreen = when(currentDestination?.route) {
                    ManadoCityScreen.Start.name -> R.string.app_name
                    ManadoCityScreen.Recommended.name -> uiState.selectedCategory?.textCategory ?: R.string.recommended_screen
                    ManadoCityScreen.Details.name -> uiState.selectedPlace?.placeName ?: R.string.detail_screen
                    else -> R.string.app_name
                },
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->


        NavHost(
            navController = navController,
            startDestination = ManadoCityScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ManadoCityScreen.Start.name) {
                CategoryLists(
                    category = uiState.categories,
                    onClick = {
                        viewModel.selectCategory(it)
                        navController.navigate(ManadoCityScreen.Recommended.name)
                    }
                )
            }
            composable(route = ManadoCityScreen.Recommended.name) {
                PlacesLists(
                    place = uiState.places,
                    onClick = {
                        viewModel.selectPlace(it)
                        navController.navigate(ManadoCityScreen.Details.name)
                    }
                )
            }
            composable(route = ManadoCityScreen.Details.name) {
                DetailsScreen(
                    place = uiState.selectedPlace
                )
            }
        }


    }
}

//@Composable
//fun ListsAndDetails(
//    category: List<Category>,
//    onCategoryClicked: ()
//    modifier: Modifier = Modifier
//) {
//    Row(
//        modifier = modifier,
//        horizontalArrangement = Arrangement.spacedBy(18.dp)
//    ) {
//        CategoryLists(
//            category = category,
//            onClick = ,
//            modifier = Modifier.weight(1f)
//        )
//        PlacesLists(
//            modifier = Modifier.weight(1f)
//        )
//        DetailsScreen(
//            modifier = Modifier.weight(1f)
//        )
//    }
//}

@Composable
fun DetailsScreen(
    place: Place?,
    modifier: Modifier = Modifier
) {
    place?.let {
        Column(
            modifier = modifier
        ) {
            Image(
                painter = painterResource(it.placeImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(12.dp)
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

@Composable
fun PlacesLists(
    place: List<Place>,
    onClick: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(place) { item ->
            PlacesCardItem(
                place = item,
                onCardClicked = onClick
            )
        }
    }
}

@Composable
fun PlacesCardItem(
    place: Place,
    onCardClicked: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
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

@Composable
fun CategoryLists(
    category: List<Category>,
    onClick: (Category) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(category) { item ->
            CategoryCardItem(
                category = item,
                onCardClicked = onClick
            )
        }
    }
}

@Composable
fun CategoryCardItem(
    category: Category,
    onCardClicked: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
        onClick = { onCardClicked(category) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(category.imageCategory),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp, 80.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(category.textCategory),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardItemPreview() {
    ManadoCityTheme {
        CategoryCardItem(Datasource.listsOfCategory[0], {})
    }
}


//@Preview(showBackground = true, widthDp = 1000)
//@Composable
//fun ListsAndDetailPreview() {
//    ManadoCityTheme {
//        ListsAndDetails(
//            category = Datasource.listsOfCategory
//        )
//    }
//}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    ManadoCityTheme {
        DetailsScreen(
            place = Datasource.listOfPlaces[0]
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryListsPreview() {
    ManadoCityTheme {
        CategoryLists(
            category = Datasource.listsOfCategory,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlacesListsPreview() {
    ManadoCityTheme {
        PlacesLists(
            place = Datasource.listOfPlaces,
            onClick = {}
        )
    }
}