package com.gabsee.manadocity.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gabsee.manadocity.R
import com.gabsee.manadocity.ui.screen.CategoryLists
import com.gabsee.manadocity.ui.screen.DetailsScreen
import com.gabsee.manadocity.ui.screen.ListsAndDetails
import com.gabsee.manadocity.ui.screen.PlacesLists
import com.gabsee.manadocity.ui.utils.CityContentType
import com.gabsee.manadocity.ui.utils.ManadoCityScreen


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
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
    )
}


@Composable
fun ManadoCityApp(
    windowSize: WindowWidthSizeClass,
    viewModel: ManadoViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val contentType: CityContentType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            contentType = CityContentType.ListOnly
        }
        WindowWidthSizeClass.Expanded -> {
            contentType = CityContentType.ListAndDetail
        }
        else -> {
            contentType = CityContentType.ListOnly
        }
    }

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

        if(contentType == CityContentType.ListAndDetail) {
            ListsAndDetails(
                place = uiState.places,
                selectedPlace = uiState.selectedPlace,
                category = uiState.categories,
                onCategoryClicked = {
                    viewModel.selectCategory(it)
                },
                onPlaceClicked = {
                    viewModel.selectPlace(it)
                },
                contentPadding = innerPadding,
                selectedCategory = uiState.selectedCategory
            )
        } else {
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
                        },
                        isCategorySelected = uiState.selectedCategory
                    )
                }
                composable(route = ManadoCityScreen.Recommended.name) {
                    PlacesLists(
                        place = uiState.places,
                        onClick = {
                            viewModel.selectPlace(it)
                            navController.navigate(ManadoCityScreen.Details.name)
                        },
                        selectedPlace = uiState.selectedPlace
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
}





