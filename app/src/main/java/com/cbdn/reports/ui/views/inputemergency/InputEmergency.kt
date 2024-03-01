package com.cbdn.reports.ui.views.inputemergency

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.cbdn.reports.R
import com.cbdn.reports.data.EmergencyCodeData
import com.cbdn.reports.ui.navigation.Destinations
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.FormButton
import com.cbdn.reports.ui.views.composables.FormDivider
import com.cbdn.reports.ui.views.composables.FormDropDownTextField
import com.cbdn.reports.ui.views.composables.FormHeader
import com.cbdn.reports.ui.views.composables.FormMultiLineTextField
import com.cbdn.reports.ui.views.composables.FormOneLineTextField
import com.cbdn.reports.ui.views.composables.FormSubHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputEmergency(
    appViewModel: AppViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val reportState by appViewModel.reportState.collectAsStateWithLifecycle()
    val uiState by appViewModel.uiState.collectAsStateWithLifecycle()
    val codeCategories = EmergencyCodeData.getCategories()
    val optionsEmergencyCodes = EmergencyCodeData.getCode(codeCategories[uiState.categoryIndex])
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(12.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Column(
            modifier = modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center
//                .background(color = MaterialTheme.colorScheme.background)
        ){
            Text(
//                stringResource(id = currentScreen.title),
                text = stringResource(id = R.string.emergency_address),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            FormMultiLineTextField(
                value = reportState.location,
                updateValue = { appViewModel.setLocation(it) },
                labelResource = R.string.enter_location,
            )
        }

//        FormDivider()
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier
                .height(4.dp)
                .width(dimensionResource(id = R.dimen.full_field_width))
                .padding(vertical = dimensionResource(id = R.dimen.thin_spacing))
        )
        Column (
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center,

        ){
            Text(
//                stringResource(id = currentScreen.title),
                text = stringResource(id = R.string.emergency_category),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.full_field_width))
                    .padding(bottom = dimensionResource(id = R.dimen.moderate_spacing)),
            ) {
                codeCategories.forEachIndexed { index, label ->
                    SegmentedButton(
                        selected = index == uiState.categoryIndex,
                        onClick = { appViewModel.setCategoryIndex(index) },
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = codeCategories.size),
                        colors = SegmentedButtonDefaults.colors(
                            activeContainerColor = MaterialTheme.colorScheme.onPrimary,
                            activeContentColor = MaterialTheme.colorScheme.primary,
                            inactiveContainerColor = MaterialTheme.colorScheme.primary,
                            inactiveContentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(text = label.category)
                    }
                }
            }
        }
//        FormDivider()

        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier
                .height(4.dp)
                .width(dimensionResource(id = R.dimen.full_field_width))
                .padding(vertical = dimensionResource(id = R.dimen.thin_spacing))
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
//                stringResource(id = currentScreen.title),
                text = stringResource(id = R.string.emergency_code_select),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            FormDropDownTextField(
                displayValue = reportState.emergencyCode,
                updateDataValue = { appViewModel.setEmergencyCode(it) },
                optionsEmergencyCodes = optionsEmergencyCodes,
                labelResource = R.string.select_emergency_code_id
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            FormButton(
                onClick = {
                          navController.popBackStack()
                },
                labelResource = R.string.cancel,
                modifier = Modifier.height(80.dp)
            )
            FormButton(
                onClick = {
                    if (reportState.location != null && reportState.emergencyCode != null){
                        navController.navigate(Destinations.AppMenu.name)
                    }
                },
                labelResource = R.string.submit,
                modifier = Modifier.height(80.dp)
            )
        }

    }
}