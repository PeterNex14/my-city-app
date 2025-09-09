package com.gabsee.manadocity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    @DrawableRes val imageCategory: Int,
    @StringRes val textCategory: Int,
    val categoryId: Int
)
