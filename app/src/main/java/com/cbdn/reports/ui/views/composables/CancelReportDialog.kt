//Dialog code adapted from previous group's efforts in App.kt
// by Peter Judge on 3/3/2024

package com.cbdn.reports.ui.views.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.cbdn.reports.R
import com.cbdn.reports.ui.viewmodel.AppViewModel

@Composable
fun CancelReportDialog(
    appViewModel: AppViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
){
    Dialog(
        //
        onDismissRequest = { appViewModel.setIsCancelReportDialogShowing(false) }
    ) {
        Column(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.background)
                .padding(dimensionResource(id = R.dimen.moderate_spacing)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(Icons.Rounded.Warning, null)
            DialogHeader(textResource = R.string.cancel_report_header)
            Text(
                text = stringResource(id = R.string.cancel_report),
                modifier = Modifier.padding(top = 20.dp)
            )
            Row(

                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(top=32.dp)
            ) {
                FormButton(
                    onClick = {
                        appViewModel.setIsCancelReportDialogShowing(false)
                    },
                    labelResource = R.string.no_cancel_report,
                    modifier = Modifier.width(128.dp).height(88.dp)
                )
                FormButton(
                    onClick = {
                        appViewModel.setIsCancelReportDialogShowing(false)
                        appViewModel.setIsSaveReportDialogShowing(true)
                    },
                    labelResource = R.string.yes_cancel_report,
                    modifier = Modifier.width(128.dp).height(88.dp)
                )
            }
        }
    }
}