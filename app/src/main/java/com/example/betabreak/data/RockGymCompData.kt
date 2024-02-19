package com.example.betabreak.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class RockGymCompData(
    /*
    Class Name: RockGymCompData
    Class Description: This class is a data class that is used to manage the data for the RockGymCompActivity class.
     */
    val rgCompID: Int,
    @StringRes val titleResourceID: Int,
    @StringRes val subtitleResourceID: Int,
    val dueInspectionCount: Int,
    val overdueInspectionCount: Int,
    val isOverdue: Boolean,
    @DrawableRes val imageResourceID: Int,
    @DrawableRes val imageBanner: Int,
    @StringRes val inspectionDetails: Int
)
