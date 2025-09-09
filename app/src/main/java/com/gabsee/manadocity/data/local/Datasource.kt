package com.gabsee.manadocity.data.local

import com.gabsee.manadocity.R
import com.gabsee.manadocity.data.Category
import com.gabsee.manadocity.data.Place

object Datasource {
    val listsOfCategory = listOf<Category>(
        Category(
            imageCategory = R.drawable.starbucks,
            textCategory = R.string.coffee_shop,
            categoryId = 1
        ),
        Category(
            imageCategory = R.drawable.mcd,
            textCategory = R.string.food_and_beverages,
            categoryId = 2
        ),
        Category(
            imageCategory = R.drawable.klabat,
            textCategory = R.string.mountain_hike,
            categoryId = 3
        ),
        Category(
            imageCategory = R.drawable.paal,
            textCategory = R.string.beach,
            categoryId = 4
        )
    )

    val listOfPlaces = listOf<Place>(
        Place(
            placeName = R.string.black_cup,
            placeDescription = R.string.black_cup_description,
            placeImage = R.drawable.black_cup,
            categoryId = 1
        ),
        Place(
            placeName = R.string.esspecto_coffee,
            placeDescription = R.string.esspecto_coffee_description,
            placeImage = R.drawable.esspectocoffee,
            categoryId = 1
        ),
        Place(
            placeName = R.string.wacana_coffee,
            placeDescription = R.string.wacana_coffee_description,
            placeImage = R.drawable.wacana,
            categoryId = 1
        ),
        Place(
            placeName = R.string.fore_coffee,
            placeDescription = R.string.fore_coffee_description,
            placeImage = R.drawable.fore,
            categoryId = 1
        ),
        Place(
            placeName = R.string.starbucks_coffee,
            placeDescription = R.string.starbucks_coffee_description,
            placeImage = R.drawable.starbucks,
            categoryId = 1
        ),
        Place(
            placeName = R.string.redo_coffee,
            placeDescription = R.string.redo_coffee_description,
            placeImage = R.drawable.redo,
            categoryId = 1
        ),
        Place(
            placeName = R.string._20,
            placeDescription = R.string._20_description,
            placeImage = R.drawable._20,
            categoryId = 2
        ),
        Place(
            placeName = R.string.burger_king,
            placeDescription = R.string.burger_king_description,
            placeImage = R.drawable.burgerking,
            categoryId = 2
        ),
        Place(
            placeName = R.string.mcdonalds,
            placeDescription = R.string.mcdonalds_description,
            placeImage = R.drawable.mcd,
            categoryId = 2
        ),
        Place(
            placeName = R.string.lokon,
            placeDescription = R.string.lokon_description,
            placeImage = R.drawable.lokon,
            categoryId = 3
        ),
        Place(
            placeName = R.string.empung,
            placeDescription = R.string.empung_description,
            placeImage = R.drawable.empung,
            categoryId = 3
        ),
        Place(
            placeName = R.string.klabat,
            placeDescription = R.string.klabat_description,
            placeImage = R.drawable.klabat,
            categoryId = 3
        ),
        Place(
            placeName = R.string.soputan,
            placeDescription = R.string.soputan_description,
            placeImage = R.drawable.gunung_soputan,
            categoryId = 3
        ),
        Place(
            placeName = R.string.tampusu,
            placeDescription = R.string.tampusu_description,
            placeImage = R.drawable.tampusu,
            categoryId = 3
        ),
        Place(
            placeName = R.string.paal,
            placeDescription = R.string.paal_description,
            placeImage = R.drawable.paal,
            categoryId = 4
        ),
        Place(
            placeName = R.string.pulisan,
            placeDescription = R.string.pulisan_description,
            placeImage = R.drawable.pulisan,
            categoryId = 4
        ),
        Place(
            placeName = R.string.triple_m,
            placeDescription = R.string.triple_m_description,
            placeImage = R.drawable.triple_m,
            categoryId = 4
        ),
        Place(
            placeName = R.string.kebasaran,
            placeDescription = R.string.kebasaran_description,
            placeImage = R.drawable.kebasaran_beach,
            categoryId = 4
        )

    )

    fun getCategories() : List<Category> {
        return listsOfCategory
    }

    fun getPlacesByCategory(categoryId: Int): List<Place> {
        return listOfPlaces.filter {
            it.categoryId == categoryId
        }
    }
}