package com.cbdn.reports.ui.views.newreport

import OnSecondaryIcon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.cbdn.reports.R
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.CancelReportDialog
import com.cbdn.reports.ui.views.composables.OnPrimaryTextButton
import com.cbdn.reports.ui.views.composables.OnSecondaryText
import com.cbdn.reports.ui.views.composables.SaveReportDialog

@Composable
fun NewReport (
    navController: NavHostController,
    appViewModel: AppViewModel
) {
//    val reportState by appViewModel.reportState.collectAsStateWithLifecycle()
    val uiState by appViewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            SubmitNewTopBar(
                updateCurrentScreen = { appViewModel.setCurrentScreen(it) },
                dispatchComplete = uiState.dispatchDetailsComplete,
                locationComplete = uiState.locationDetailsComplete,
                onSceneComplete = uiState.siteDetailsComplete,
                submitComplete = uiState.submittalDetailsComplete,
                currentScreen = uiState.currentScreen
            )
        },
        bottomBar = {
            SubmitNewBottomBar(
                currentScreen = uiState.currentScreen,
                submitReady = uiState.reportComplete,
//                submitClick = {
//                    appViewModel.submitReport()
//                    navController.popBackStack(
//                        route = Destinations.AppMenu.name,
//                        inclusive = false)
//                              },
                submitClick = {
                    appViewModel.submitReport()
                    navController.popBackStack(
                        route = Destinations.TruckLandingPage.name,
                        inclusive = false
                    )
                    appViewModel.resetUiReportSubmit()
                },
                updateCurrentScreen = { appViewModel.setCurrentScreen(it) },
                shouldShowWaiting = uiState.shouldShowWaitingDialog,
                appViewModel = appViewModel
            )
        }
    ) {innerPadding ->
        when (uiState.currentScreen) {
            DetailSections.DispatchDetails.name -> { DispatchDetails(
                viewModel = appViewModel,
                navController = navController,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(dimensionResource(id = R.dimen.thin_spacing))
            ) }
            DetailSections.LocationDetails.name -> { LocationDetails(
                viewModel = appViewModel,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(dimensionResource(id = R.dimen.thin_spacing))
            ) }
            DetailSections.SiteDetails.name -> { SiteDetails(
                viewModel = appViewModel,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(dimensionResource(id = R.dimen.thin_spacing))
            ) }
            DetailSections.SubmittalDetails.name -> { SubmittalDetails(
                viewModel = appViewModel,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(dimensionResource(id = R.dimen.thin_spacing))
            ) }

        }
    }
    if (uiState.isCancelReportDialogShowing) {
        CancelReportDialog(appViewModel = appViewModel, navController = navController)
    }
    if (uiState.isSaveReportDialogShowing) {
        SaveReportDialog(appViewModel = appViewModel, navController = navController )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubmitNewTopBar(
    updateCurrentScreen: (String) -> Unit,
    dispatchComplete: Boolean,
    locationComplete: Boolean,
    onSceneComplete: Boolean,
    submitComplete: Boolean,
    currentScreen: String?
) {
    CenterAlignedTopAppBar(
        title = {},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
            ),
        actions = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
                TextButton(
                    modifier = Modifier
                        .then(
                            if (currentScreen==DetailSections.SiteDetails.name) Modifier.background(color= Color(0xFF777777)) else Modifier
                        ),
                    onClick = { updateCurrentScreen(DetailSections.SiteDetails.name) }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                        if (onSceneComplete) {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_24)
                        } else {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_outline_blank_24)
                        }
                        OnSecondaryText(textResource = R.string.on_scene)
                    }
                }
                TextButton(
                    modifier = Modifier
                        .then(
                            if (currentScreen==DetailSections.LocationDetails.name) Modifier.background(color= Color(0xFF777777)) else Modifier
                        ),
                    onClick = { updateCurrentScreen(DetailSections.LocationDetails.name) }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        if (locationComplete) {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_24)
                        } else {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_outline_blank_24)
                        }
                        OnSecondaryText(textResource = R.string.location)
                    }
                }
                TextButton(
                    modifier = Modifier
                        .then(
                            if (currentScreen==DetailSections.DispatchDetails.name) Modifier.background(color= Color(0xFF777777)) else Modifier
                        ),
                    onClick = { updateCurrentScreen(DetailSections.DispatchDetails.name) }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        if (dispatchComplete) {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_24)
                        } else {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_outline_blank_24)
                        }
                        OnSecondaryText(textResource = R.string.dispatch)
                    }
                }


                TextButton(
                    modifier = Modifier
                        .then(
                            if (currentScreen==DetailSections.SubmittalDetails.name) Modifier.background(color= Color(0xFF777777)) else Modifier
                        ),
                    onClick = { updateCurrentScreen(DetailSections.SubmittalDetails.name) }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        if (submitComplete) {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_24)
                        } else {
                            OnSecondaryIcon(iconResource = R.drawable.baseline_check_box_outline_blank_24)
                        }
                        OnSecondaryText(textResource = R.string.submit)
                    }
                }
            }
        }
    )
}

@Composable
fun SubmitNewBottomBar(
    currentScreen: String?,
    submitReady: Boolean,
    submitClick: () -> Unit,
    updateCurrentScreen: (String) -> Unit,
    shouldShowWaiting: Boolean,
    appViewModel: AppViewModel
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        actions = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ){
                OnPrimaryTextButton(
                    enabled = currentScreen != DetailSections.SiteDetails.name,
                    onClick = {
                        when(currentScreen) {
                            DetailSections.LocationDetails.name ->
                                updateCurrentScreen(DetailSections.SiteDetails.name)
                            DetailSections.DispatchDetails.name ->
                                updateCurrentScreen(DetailSections.LocationDetails.name)
                            DetailSections.SubmittalDetails.name ->
                                updateCurrentScreen(DetailSections.DispatchDetails.name)
                        }
                    },
                    labelResource = R.string.previous,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(dimensionResource(id = R.dimen.thin_spacing))
                )
                OnPrimaryTextButton(
                    enabled = submitReady,
                    onClick = submitClick,
                    labelResource = R.string.submit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(dimensionResource(id = R.dimen.thin_spacing))
                )
                OnPrimaryTextButton(
                    enabled = currentScreen != DetailSections.SubmittalDetails.name,
                    onClick = {
                        when(currentScreen) {
                            DetailSections.SiteDetails.name ->
                                updateCurrentScreen(DetailSections.LocationDetails.name)
                            DetailSections.LocationDetails.name ->
                                updateCurrentScreen(DetailSections.DispatchDetails.name)
                            DetailSections.DispatchDetails.name ->
//                                updateCurrentScreen(DetailSections.SubmittalDetails.name)
//                                navController.navigate(Destinations.WaitingScreen.name)
                                if (shouldShowWaiting){
                                    appViewModel.setIsWaitingDialogShowing(true)
                                } else {
                                    updateCurrentScreen(DetailSections.SubmittalDetails.name)
                                }

                        }
                    },
                    labelResource = R.string.next,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(dimensionResource(id = R.dimen.thin_spacing))
                )
            }
        },
    )
}