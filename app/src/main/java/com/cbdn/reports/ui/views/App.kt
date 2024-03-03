// NavController Extension Function NavController.isOnBackStack() adapted for use in
// application navigation tree taken from Stack Overflow answer
// https://stackoverflow.com/questions/65529172/check-if-a-navigation-graph-is-on-the-backstack
// inputs modified for use with route strings
// by Peter Judge on 2/26/2024

package com.cbdn.reports.ui.views

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cbdn.reports.R
import com.cbdn.reports.ui.navigation.AppNavHost
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.FormButton
import com.cbdn.reports.ui.views.composables.DialogHeader
import com.cbdn.reports.ui.views.trucklandingpage.TruckLandingPage
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun App(
    navController: NavHostController = rememberNavController(),
    appViewModel: AppViewModel
) {
    val uiState by appViewModel.uiState.collectAsStateWithLifecycle()
    val reportState by appViewModel.reportState.collectAsStateWithLifecycle()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Destinations.valueOf(
        backStackEntry?.destination?.route ?: Destinations.AppMenu.name
    )
    val scope = rememberCoroutineScope()
    val snackbarHostState = uiState.snackbarHostState
    Scaffold(
        snackbarHost = {SnackbarHost(hostState = snackbarHostState)},
        topBar = {
            ReportsTopBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    if (currentScreen.name == Destinations.TruckLandingPage.name){
                        appViewModel.setRespondingTruck("")
                        navController.popBackStack()

                    } else if (
                        !navController.isOnBackStack(Destinations.AppMenu.name) ||
                        currentScreen.name == Destinations.AppMenu.name
                    ) {
                        if (currentScreen.name == Destinations.InputEmergency.name) appViewModel.resetUiMinusTruck()
                        navController.popBackStack()
                    } else {
                        navController.popBackStack(
                            route = Destinations.AppMenu.name,
                            inclusive = false
                        )
                    }
                },
                curRespondingTruck = reportState.respondingTruck
            )
        }
    ) { innerPadding ->
//        if(uiState.submitSuccessful){
//            scope.launch {
//                snackbarHostState.showSnackbar(
//                    message = "Report Submitted Successfully"
//                )
//            }
//            appViewModel.setSubmitSuccessful(false)
//        }
        if(uiState.submitSuccessful){
            val text = "Report Submitted Successfully!"
            val duration = Toast.LENGTH_LONG

            val toast = Toast.makeText(LocalContext.current, text, duration) // in Activity
            toast.show()
            appViewModel.setSubmitSuccessful(false)
        }


        if (currentScreen == Destinations.AppMenu) {
            when (uiState.backButtonPrev) {
                Destinations.NewReport.name -> {
                    if (!uiState.submitSuccessful) {
                        if (reportState.finalized) {
                            Dialog(onDismissRequest = {
//                                appViewModel.resetUI()
                                appViewModel.resetUiMinusTruck()
                            }) {
                                Column(
                                    modifier = Modifier
                                        .background(color = MaterialTheme.colorScheme.background)
                                        .padding(dimensionResource(id = R.dimen.moderate_spacing)),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Icon(Icons.Rounded.Warning, null)
                                    DialogHeader(R.string.not_submitted)
                                    Text(stringResource(id = R.string.not_submitted_message))
                                    Row {
                                        FormButton(
                                            onClick = {
//                                                appViewModel.resetUI()
                                                appViewModel.resetUiMinusTruck()
                                            },
                                            labelResource = R.string.discard
                                        )
                                        FormButton(
                                            onClick = {
                                                appViewModel.submitReport()
//                                                appViewModel.resetUI()
                                                appViewModel.resetUiMinusTruck()
                                            },
                                            labelResource = R.string.submit
                                        )
                                    }
                                }
                            }
                        } else {
                            Dialog(onDismissRequest = {
//                                appViewModel.resetUI()
                                appViewModel.resetUiMinusTruck()
                            }) {
                                Column(
                                    modifier = Modifier
                                        .background(color = MaterialTheme.colorScheme.background)
                                        .padding(dimensionResource(id = R.dimen.moderate_spacing)),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Icon(Icons.Rounded.Warning, null)
                                    DialogHeader(R.string.not_submitted)
                                    Text(stringResource(id = R.string.incomplete_message))
                                    Row {
                                        FormButton(
                                            onClick = {
//                                                appViewModel.resetUI()
                                                appViewModel.resetUiMinusTruck()
                                            },
                                            labelResource = R.string.discard
                                        )
                                        FormButton(
                                            onClick = {
                                                appViewModel.submitReport()
//                                                appViewModel.resetUI()
                                                appViewModel.resetUiMinusTruck()
                                            },
                                            labelResource = R.string.save_as_incomplete
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        Dialog(onDismissRequest = {
//                            appViewModel.resetUI()
                            appViewModel.resetUiMinusTruck()
                        }) {
                            Column(
                                modifier = Modifier
                                    .background(color = MaterialTheme.colorScheme.background)
                                    .padding(dimensionResource(id = R.dimen.moderate_spacing)),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Icon(Icons.Rounded.Done, null)
                                DialogHeader(R.string.submitted_successfully)
                                Text(stringResource(id = R.string.submit_successful_message))
                                FormButton(
                                    onClick = {
//                                        appViewModel.resetUI()
                                        appViewModel.resetUiMinusTruck()
                                    },
                                    labelResource = R.string.ok
                                )
                            }
                        }
                    }
                }
//                else -> { appViewModel.resetUI() }
            }
        }
        AppNavHost(
            navController = navController,
            appViewModel = appViewModel,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsTopBar(
    currentScreen: Destinations,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    curRespondingTruck: String?
) {
    val screenTitle: String
    if (currentScreen.title == R.string.truck_landing_page) {
        screenTitle = "Truck ${curRespondingTruck ?: ""} Dispatch"
    } else {
        screenTitle = stringResource(id = currentScreen.title)
    }
    CenterAlignedTopAppBar(
        title = {
            Text(
//                stringResource(id = currentScreen.title),
                text = screenTitle,
                color = MaterialTheme.colorScheme.onPrimary
            )
                },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = navigateUp,
                    colors = IconButtonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        contentColor = MaterialTheme.colorScheme.primary,
                        disabledContainerColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
//                        imageVector = Icons.Rounded.Menu,
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        }
    )
}

fun NavController.isOnBackStack(routeString: String): Boolean = try { getBackStackEntry(routeString); true } catch(e: Throwable) { false }