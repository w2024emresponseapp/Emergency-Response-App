package com.cbdn.reports.ui.views.dispatchmappage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cbdn.reports.BuildConfig
import com.cbdn.reports.R
import com.cbdn.reports.ui.viewmodel.AppViewModel
import com.cbdn.reports.ui.views.composables.FormDivider
import com.cbdn.reports.ui.views.composables.FormHeader
import com.cbdn.reports.ui.views.composables.FormMultiLineTextField
import com.cbdn.reports.ui.views.composables.FormSubHeader
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.google.android.gms.location.FusedLocationProviderClient
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cbdn.reports.ui.views.composables.MenuButton
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.AdvancedMarkerOptions
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.PinConfig
import com.google.maps.android.compose.CameraPositionState


@Composable
fun DispatchMap(
    appViewModel: AppViewModel,
    navController: NavController
) {

    val reportState by appViewModel.reportState.collectAsStateWithLifecycle()
    val uiState by appViewModel.uiState.collectAsStateWithLifecycle()
    val categoryIndex = uiState.categoryIndex

    // Location Info
    var mapCoordinates = LatLng(30.29, -81.59)
    val currentLocation = reportState.location
    val context = LocalContext.current as Activity
    val geocoder = Geocoder(context)
    val geoListener = Geocoder.GeocodeListener { addressList ->
        mapCoordinates = LatLng(addressList[0].latitude, addressList[0].longitude)
    }
    if (currentLocation != null) {
        geocoder.getFromLocationName(currentLocation, 1, geoListener)
    }

    // Get the icon
    var bmpIcon = AppCompatResources.getDrawable(
        context,
        R.drawable.emergency_fill0_wght400_grad0_opsz24
    )?.toBitmap()

    if (categoryIndex == 0) {
        bmpIcon = AppCompatResources.getDrawable(
            context,
            R.drawable.local_fire_department_fill0_wght400_grad0_opsz24
        )?.toBitmap()
    } else if (categoryIndex == 1) {
        bmpIcon = AppCompatResources.getDrawable(
            context,
            R.drawable.support_fill0_wght400_grad0_opsz24
        )?.toBitmap()
    } else if (categoryIndex == 2) {
        bmpIcon = AppCompatResources.getDrawable(
            context,
            R.drawable.car_crash_fill0_wght400_grad0_opsz24
        )?.toBitmap()
    }

    // Create a new intent and set the package
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("google.navigation:q=${mapCoordinates.latitude},${mapCoordinates.longitude}&model=d")
    )

    intent.setPackage("com.google.android.apps.maps")

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
        GoogleMap(
            cameraPositionState = CameraPositionState(CameraPosition.fromLatLngZoom(mapCoordinates, 10f)),
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            if (bmpIcon != null) {
                Marker(
                    state = MarkerState(position = mapCoordinates),
                    icon = BitmapDescriptorFactory.fromBitmap(bmpIcon)
                )
            } else {
                Marker(
                    state = MarkerState(position = mapCoordinates)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(12.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondary)
        )
        MenuButton(
            onClick = { /*TODO*/ },
            labelResource = R.string.cont,
            subLabelResource = R.string.cont_desc,
            icon = ImageVector.vectorResource(R.drawable.baseline_arrow_forward_ios_24),
            modifier = Modifier.weight(2f)
        )
        Spacer(
            modifier = Modifier
                .height(12.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondary)
        )
        MenuButton(
            onClick = {
                context.startActivity(intent)
            },
            labelResource = R.string.navigate,
            subLabelResource = R.string.nav_desc,
            icon = ImageVector.vectorResource(R.drawable.baseline_navigation_24),
            modifier = Modifier.weight(2f)
        )
        Spacer(
            modifier = Modifier
                .height(12.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondary)
        )
        MenuButton(
            onClick = { /*TODO*/ },
            labelResource = R.string.canc,
            subLabelResource = R.string.canc_desc,
            icon = ImageVector.vectorResource(R.drawable.baseline_arrow_back_ios_24),
            modifier = Modifier.weight(2f)
        )
    }


}