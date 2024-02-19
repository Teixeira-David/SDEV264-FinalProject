package com.example.betabreak.data

import com.example.betabreak.R

object DashboardCompData {
    /*
    Class Name: DashboardCompData
    Class Description: This class is used to provide data for the DashboardFragment class.
     */
    val dashboardData = getDashboardData()[0]

    fun getDashboardData(): List<RockGymCompData> {
        return listOf(
            RockGymCompData(
                rgCompID = 1,
                titleResourceID = R.string.carabiners_screen,
                subtitleResourceID = R.string.carabiner_dashboard_title,
                dueInspectionCount = 5,
                overdueInspectionCount = 2,
                isOverdue = true,
                imageResourceID = R.drawable.ic_carabiner_square,
                imageBanner = R.drawable.ic_carabiner_banner,
                inspectionDetails = R.string.carabiner_inspection_description
            ),
            RockGymCompData(
                rgCompID = 2,
                titleResourceID = R.string.ropes_screen,
                subtitleResourceID = R.string.rope_dashboard_title,
                dueInspectionCount = 3,
                overdueInspectionCount = 6,
                isOverdue = true,
                imageResourceID = R.drawable.ic_rope_square,
                imageBanner = R.drawable.ic_rope_banner,
                inspectionDetails = R.string.rope_inspection_description,
            ),
            RockGymCompData(
                rgCompID = 3,
                titleResourceID = R.string.harness_screen,
                subtitleResourceID = R.string.harness_dashboard_title,
                dueInspectionCount = 10,
                overdueInspectionCount = 5,
                isOverdue = true,
                imageResourceID = R.drawable.ic_harness_square,
                imageBanner = R.drawable.ic_harness_banner,
                inspectionDetails = R.string.harness_inspection_description
            ),
            RockGymCompData(
                rgCompID = 4,
                titleResourceID = R.string.belay_device_screen,
                subtitleResourceID = R.string.belay_device_dashboard_title,
                dueInspectionCount = 8,
                overdueInspectionCount = 1,
                isOverdue = true,
                imageResourceID = R.drawable.ic_belay_device_square,
                imageBanner = R.drawable.ic_belay_device_banner,
                inspectionDetails = R.string.belay_device_inspection_description
            ),
            RockGymCompData(
                rgCompID = 5,
                titleResourceID = R.string.auto_belay_screen,
                subtitleResourceID = R.string.auto_belay_dashboard_title,
                dueInspectionCount = 2,
                overdueInspectionCount = 1,
                isOverdue = true,
                imageResourceID = R.drawable.ic_auto_belay_square,
                imageBanner = R.drawable.ic_auto_belay_banner,
                inspectionDetails = R.string.auto_belay_inspection_description
            ),
        )
    }
}