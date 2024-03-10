package com.cbdn.reports.ui.navigation

import androidx.annotation.StringRes
import com.cbdn.reports.R

enum class Destinations(
    @StringRes val title: Int
    ) {
    StartScreen(
        title = R.string.start_screen
    ),
    AppMenu(
        title = R.string.app_name
    ),
    NewReport(
        title = R.string.submit_new_header
    ),
    FinishReport(
        title = R.string.finish_report
    ),
    SearchReports(
        title = R.string.search_reports
    ),
    DispatchMap(
        title = R.string.dispatch_map
    ),
    ReturnToBase(
        title = R.string.return_to_base
    )
//    ViewStatistics(
//        title = R.string.view_statistics
//    )
}