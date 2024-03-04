//Dialog code adapted from previous group's efforts in App.kt
// by Peter Judge on 3/3/2024

package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.cbdn.reports.R
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.viewmodel.AppViewModel

@Composable
fun SaveReportDialog(
    appViewModel: AppViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
){
    val reportState by appViewModel.reportState.collectAsStateWithLifecycle()
    if (reportState.finalized){
        Dialog(onDismissRequest = {
            appViewModel.setIsSaveReportDialogShowing(false)
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
                            appViewModel.setIsSaveReportDialogShowing(false)
                            appViewModel.resetUiMinusTruck()
                            navController.popBackStack(
                                route = Destinations.TruckLandingPage.name,
                                inclusive = false
                            )
                        },
                        labelResource = R.string.discard
                    )
                    FormButton(
                        onClick = {
                            appViewModel.submitReport()
                            appViewModel.resetUiReportSubmit()
                            appViewModel.setIsSaveReportDialogShowing(false)
                            navController.popBackStack(
                                route = Destinations.TruckLandingPage.name,
                                inclusive = false
                            )

//                                                appViewModel.resetUI()

                        },
                        labelResource = R.string.submit
                    )
                }
            }
        }
    } else {
        Dialog(onDismissRequest = {
            appViewModel.setIsSaveReportDialogShowing(false)
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
                            appViewModel.setIsSaveReportDialogShowing(false)
                            navController.popBackStack(
                                route = Destinations.TruckLandingPage.name,
                                inclusive = false
                            )
                            appViewModel.resetUiMinusTruck()
                        },
                        labelResource = R.string.discard
                    )
                    FormButton(
                        onClick = {
                            appViewModel.submitReport()
                            appViewModel.setIsSaveReportDialogShowing(false)
                            navController.popBackStack(
                                route = Destinations.TruckLandingPage.name,
                                inclusive = false
                            )
                            appViewModel.resetUiReportSubmit()
                        },
                        labelResource = R.string.save_as_incomplete
                    )
                }
            }
        }
    }
}