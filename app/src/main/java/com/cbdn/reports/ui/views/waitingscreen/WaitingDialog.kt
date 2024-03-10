package com.cbdn.reports.ui.views.waitingscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.cbdn.reports.R
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.FormButton
import com.cbdn.reports.ui.views.composables.MenuButton
import com.cbdn.reports.ui.views.newreport.DetailSections

@Composable
fun WaitingDialog(
    appViewModel: AppViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = {
            appViewModel.setShouldShowWaitingDialog(false)
            appViewModel.setIsWaitingDialogShowing(false)
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.secondary)
                .padding(16.dp)
        ) {
            MenuButton(
                // this should open up the menu to choose which truck you're on
                // and then once that's submitted, move to the landing page

//            onClick = { navController.navigate(Destinations.AppMenu.name) },
                onClick = {
                    appViewModel.setIsWaitingDialogShowing(false)
                    appViewModel.setShouldShowWaitingDialog(false)
                    navController.navigate(Destinations.ReturnToBase.name)
//                    appViewModel.setCurrentScreen(DetailSections.SubmittalDetails.name)

                },
                labelResource = R.string.return_to_base,
                subLabelResource = R.string.return_to_base_description,
                icon =  ImageVector.vectorResource(R.drawable.return_to_base),
                modifier = Modifier.height(200.dp),
                isRound = true
            )
            Spacer(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.secondary)
            )

            MenuButton(
                onClick = {
                    appViewModel.setIsWaitingDialogShowing(false)
                    appViewModel.setShouldShowWaitingDialog(false)
                    appViewModel.setCurrentScreen(DetailSections.SubmittalDetails.name)

                },
                labelResource = R.string.finish_report_now,
                subLabelResource = R.string.finish_report_now_description,
                icon = ImageVector.vectorResource(id = R.drawable.baseline_done_outline_24),
                modifier = Modifier.height(100.dp)
            )
            FormButton(
                onClick = {
                    appViewModel.setShouldShowWaitingDialog(false)
                    appViewModel.setIsWaitingDialogShowing(false)
                },
                // should probably be a "return to report" button and collapse the dialog
                labelResource = R.string.return_to_report
            )
        }
    }
}