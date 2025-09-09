package com.gabsee.manadocity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    @StringRes val placeName: Int,
    @StringRes val placeDescription: Int,
    @DrawableRes val placeImage: Int,
    val categoryId: Int
)
