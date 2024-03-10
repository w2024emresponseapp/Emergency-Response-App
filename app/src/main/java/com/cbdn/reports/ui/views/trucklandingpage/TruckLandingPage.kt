package com.cbdn.reports.ui.views.trucklandingpage

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.cbdn.reports.R
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.FormButton
import com.cbdn.reports.ui.views.composables.MenuButton

@Composable
fun TruckLandingPage(
    appViewModel: AppViewModel,
    navController: NavController
){
    val uiState by appViewModel.uiState.collectAsStateWithLifecycle()
    val reportState by appViewModel.reportState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxSize()
                .weight(3f)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_bomberos_oficial_tools_webp),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillWidth,
                )

            }
        }
        MenuButton(
            // this should open up the menu to choose which truck you're on
            // and then once that's submitted, move to the landing page

//            onClick = { navController.navigate(Destinations.AppMenu.name) },
            onClick = { navController.navigate(Destinations.InputEmergency.name)},
            labelResource = R.string.salida,
            subLabelResource = R.string.salida_description,
            icon =  ImageVector.vectorResource(R.drawable.salida),
            modifier = Modifier.weight(2f),
            isRound = true
        )
        Spacer(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondary)
        )
        MenuButton(
            onClick = { navController.navigate(Destinations.FinishReport.name) },
            labelResource = R.string.finish_report,
            subLabelResource = R.string.submit_finish_description,
            icon = Icons.Rounded.CheckCircle,
            modifier = Modifier.weight(1.1f)
        )
        MenuButton(
            onClick = { navController.navigate(Destinations.SearchReports.name) },
            labelResource = R.string.search_reports,
            subLabelResource = R.string.view_filter_description,
            icon = ImageVector.vectorResource(id = R.drawable.baseline_search_24),
            modifier = Modifier.weight(1.1f)
        )
        FormButton(
            onClick = {
                appViewModel.setRespondingTruck("")
                navController.popBackStack()
            },
            labelResource = R.string.back_to_main
        )
    }
}