package com.cbdn.reports.data.datamodel
import com.google.android.gms.maps.model.LatLng

data class Report(
    // dispatch
    var respondingTruck: String? = null,
    var commandingOfficer: String? = null,
    var datetimeDispatch: Long? = null,
    var emergencyCode: String? = null,
    // location as an address string
    var location: String? = null,
    var coordinates: LatLng = LatLng(18.46, -69.94),    // Santo Domingo
    // on scene
    var datetimeArrival: Long? = null,
    var policePresent: String? = null,
    var ambulancePresent: String? = null,
    var electricCompanyPresent: String? = null,
    var transitPolicePresent: String? = null,
    var victimInfo: List<VictimInfo> = emptyList(),
    var notes: String? = null,
    // submittal
    var datetimeReturn: Long? = null,
    var reportWriter: String? = null,
    var finalized: Boolean = false,
    var next: String? = null,
    var previous: String? = null
){
    constructor(): this(
        respondingTruck = null,
        commandingOfficer = null,
        datetimeDispatch = null,
        emergencyCode = null,
        location = null,
        coordinates = LatLng(18.46, -69.94),
        datetimeArrival = null,
        policePresent = null,
        ambulancePresent = null,
        electricCompanyPresent = null,
        transitPolicePresent = null,
        victimInfo = emptyList(),
        notes = null,
        datetimeReturn = null,
        reportWriter = null,
        finalized = false,
        next = null ,
        previous = null
    )
}