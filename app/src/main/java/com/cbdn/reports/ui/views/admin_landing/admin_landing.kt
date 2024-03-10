package com.cbdn.reports.ui.views.admin_landing

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Button

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cbdn.reports.R
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.MenuButton
import com.cbdn.reports.ui.views.composables.SelectTruckDialog

@Composable
fun AdminLanding(
    appViewModel: AppViewModel,
    navController: NavController
){
    val uiState by appViewModel.uiState.collectAsStateWithLifecycle()
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
            Image(
                painter = painterResource(id = R.drawable.logo_bomberos_oficial_tools_webp),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillWidth,
            )
        }
        Spacer(
            modifier = Modifier
                .height(12.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondary)
        )
        // View Current Emergency Button
        Button(
            onClick = { /* Define navigation to View Current Emergency screen */ },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text("View Current Emergency")
        }

        // View All Emergencies Button
        Button(
            onClick = { /* Define navigation to View All Emergencies screen */ },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text("View All Emergencies")
        }
        // View Truck Status Button
        Button(
            onClick = {appViewModel.setIsTruckSelectShowing(true)},
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text("Choose Your Truck")
        }

        Button(
            onClick = { navController.navigate(Destinations.AppMenu.name) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Create/View Report")
        }


    }
    if (uiState.isTruckSelectShowing) {
        SelectTruckDialog(
            appViewModel = appViewModel,
            navController = navController,
            modifier = Modifier
        )
    }
}

