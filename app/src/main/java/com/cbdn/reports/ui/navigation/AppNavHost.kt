package com.cbdn.reports.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.startscreen.StartScreen
import com.cbdn.reports.ui.views.AppMenu
import com.cbdn.reports.ui.views.finishreport.FinishReport
import com.cbdn.reports.ui.views.inputemergency.InputEmergency
import com.cbdn.reports.ui.views.login.LogInPage
import com.cbdn.reports.ui.views.newreport.NewReport
import com.cbdn.reports.ui.views.searchreports.SearchReports
import com.cbdn.reports.ui.views.dispatchmappage.DispatchMap
import com.cbdn.reports.ui.views.returnToBasePage.ReturnToBase
import com.cbdn.reports.ui.views.trucklandingpage.TruckLandingPage
import com.cbdn.reports.ui.views.waitingscreen.WaitingScreen
import com.cbdn.reports.ui.views.admin_landing.AdminLanding


@Composable
fun AppNavHost(
    navController: NavHostController,
    appViewModel: AppViewModel,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
//        startDestination = Destinations.AppMenu.name,
        startDestination = Destinations.StartScreen.name,
        modifier = modifier
    ) {
        composable(route = Destinations.StartScreen.name) {
            StartScreen(
                appViewModel = appViewModel,
                navController = navController
            )
        }
        composable(route = Destinations.DispatchMap.name) {
            DispatchMap(
                appViewModel = appViewModel,
                navController = navController
                )
        }
        composable(route = Destinations.TruckLandingPage.name) {
            TruckLandingPage(
                appViewModel = appViewModel,
                navController = navController
            )
        }
        composable(route = Destinations.InputEmergency.name){
            InputEmergency(
                appViewModel = appViewModel,
                navController = navController,
            )
        }
        composable(route = Destinations.AppMenu.toString()) {
            AppMenu(navController = navController)
        }
        composable(route = Destinations.NewReport.name) {
            appViewModel.setPrevDestination(Destinations.NewReport.name)
            NewReport(
                appViewModel = appViewModel,
                navController = navController,
            )
        }
        composable(route = Destinations.FinishReport.name) {
            appViewModel.setPrevDestination(Destinations.FinishReport.name)
            FinishReport(
                appViewModel = appViewModel,
                navController = navController,
            )
        }
        composable(route = Destinations.WaitingScreen.name){
            WaitingScreen(
                appViewModel =appViewModel ,
                navController = navController
            )
        }
        composable(route = Destinations.LogInPage.name) {
            appViewModel.setPrevDestination(Destinations.LogInPage.name)
            LogInPage(
                appViewModel = appViewModel,
                navController = navController,
            )
        }
        composable(route = Destinations.AdminLoginLanding.name) {
            appViewModel.setPrevDestination(Destinations.AdminLoginLanding.name)
            AdminLanding(
                appViewModel = appViewModel,
                navController = navController,
            )
        }
        composable(route = Destinations.SearchReports.name) {
            appViewModel.setPrevDestination(Destinations.SearchReports.name)
            SearchReports(
                appViewModel = appViewModel,
                navController = navController,
            )
        }
        composable(route = Destinations.ReturnToBase.name) {
            ReturnToBase(
                appViewModel = appViewModel,
                navController = navController
            )
        }
//        composable(route = Destinations.ViewStatistics.name) {
//            appViewModel.setPrevDestination(Destinations.ViewStatistics.name)
//            ViewStatistics()
//        }
    }
}
