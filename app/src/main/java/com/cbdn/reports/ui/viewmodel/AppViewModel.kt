package com.cbdn.reports.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cbdn.reports.data.datamodel.FireStoreUtility
import com.cbdn.reports.data.datamodel.Report
import com.cbdn.reports.data.datamodel.VictimInfo
import com.cbdn.reports.ui.views.newreport.DetailSections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AppUiState(
    var prevDestination: String? = null,
    // Get Reports UI
    var reportID: String = "",
    var pulledReports: List<Pair<String, Report>>? = null,
    var reportItemIndex: Int? = null,

    // Report Form UI
    var currentScreen: String = DetailSections.DispatchDetails.name,
    var dispatchDetailsComplete: Boolean = false,
    var locationDetailsComplete: Boolean = false,
    var siteDetailsComplete: Boolean = false,
    var submittalDetailsComplete: Boolean = false,
    var reportComplete: Boolean = false,
    // dispatch
    var categoryIndex: Int = 0,
    // location
    // on scene
    var policeCheck: Boolean = false,
    var ambulanceCheck: Boolean = false,
    var electricCompanyCheck: Boolean = false,
    var transitPoliceCheck: Boolean = false,
    var addVictimDialog: Boolean = false,
    var victimEditIndex: Int? = null,
    var victimStatusCode: String = "",
    var victimName: String = "",
    var victimAge: String = "",
    var victimIdentification: String = "",
    // submittal
    var submitSuccessful: Boolean = false,
)
class AppViewModel(
    private val _db: FireStoreUtility = FireStoreUtility()
) : ViewModel() {
    private var _reportState = MutableStateFlow(Report())
    private var _uiState = MutableStateFlow(AppUiState())
    var reportState: StateFlow<Report> = _reportState.asStateFlow()
    var uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    init {
        Log.d("DEV", "AppViewModel init.")
    }
    fun resetUI() {
        _uiState.update { AppUiState() }
        _reportState.update { Report() }
    }
    fun clearPulledReports() {
        _uiState.update {
            it.copy(pulledReports = null)
        }
    }
    fun setReportItemIndex(input: Int) {
        _uiState.update {
            it.copy(reportItemIndex = input)
        }
    }
    fun clearReportItemIndex() {
        _uiState.update {
            it.copy(reportItemIndex = null)
        }
    }
    fun setCurrentScreen(input: String) {
        _uiState.update {
            it.copy(currentScreen = input)
        }
    }
    fun setPrevDestination(input: String) {
        _uiState.update {
            it.copy(prevDestination = input)
        }
    }
    private fun isReportComplete() {
        if (
            uiState.value.dispatchDetailsComplete &&
            uiState.value.locationDetailsComplete &&
            uiState.value.siteDetailsComplete &&
            uiState.value.submittalDetailsComplete
        ) {
            _uiState.update {
                it.copy(reportComplete = true)
            }
            _reportState.update {
                it.copy(finalized = true)
            }
        } else {
            _uiState.update {
                it.copy(reportComplete = false)
            }
        }
    }
    private fun isDispatchComplete() {
        if (
            reportState.value.respondingTruck != null &&
            reportState.value.commandingOfficer != null &&
            reportState.value.datetimeDispatch != null &&
            reportState.value.emergencyCode != null
        ) {
            _uiState.update {
                it.copy(dispatchDetailsComplete = true)
            }
        } else {
            _uiState.update {
                it.copy(dispatchDetailsComplete = false)
            }
        }
        isReportComplete()
    }
    private fun isLocationComplete() {
        if (
            reportState.value.location != null
        ) {
            _uiState.update {
                it.copy(locationDetailsComplete = true)
            }
        } else {
            _uiState.update {
                it.copy(locationDetailsComplete = false)
            }
        }
        isReportComplete()
    }
    private fun isOnSceneComplete() {
        if (
            reportState.value.datetimeArrival != null &&
            reportState.value.policePresent != null &&
            reportState.value.ambulancePresent != null &&
            reportState.value.electricCompanyPresent != null &&
            reportState.value.transitPolicePresent != null &&
            reportState.value.notes != null
        ) {
            _uiState.update {
                it.copy(siteDetailsComplete = true)
            }
        } else {
            _uiState.update {
                it.copy(siteDetailsComplete = false)
            }
        }
        isReportComplete()
    }
    private fun isSubmitComplete() {
        if (
            reportState.value.datetimeReturn != null &&
            reportState.value.reportWriter != null
        ) {
            _uiState.update {
                it.copy(submittalDetailsComplete = true)
            }
            isReportComplete()
        }
    }

    fun setRespondingTruck(input: String) {
        _reportState.update {
            it.copy(respondingTruck = input.ifEmpty { null })
        }
        isDispatchComplete()
    }

    fun setCommandingOfficer(input: String) {
        _reportState.update {
            it.copy(commandingOfficer = input.ifEmpty { null })
        }
        isDispatchComplete()
    }

    fun setDatetimeDispatch(input: Long) {
        _reportState.update {
            it.copy(datetimeDispatch = input)
        }
        isDispatchComplete()
    }
    fun setCategoryIndex(input: Int) {
        _uiState.update {
            it.copy(categoryIndex = input)
        }
    }
    fun setEmergencyCode(input: String) {
        _reportState.update {
            it.copy(emergencyCode = input.ifEmpty { null })
        }
        isDispatchComplete()
    }
    fun setLocation(input: String) {
        _reportState.update {
            it.copy(location = input.ifEmpty { null })
        }
        isLocationComplete()
    }
    fun setDatetimeArrival(input: Long) {
        _reportState.update {
            it.copy(datetimeArrival = input)
        }
        isOnSceneComplete()
    }
    fun setPoliceCheck(input: Boolean) {
        _uiState.update {
            it.copy(policeCheck = input)
        }
        if (input) setPolicePresent(null)
        else setPolicePresent("")
    }
    fun setPolicePresent(input: String?) {
        _reportState.update {
            it.copy(policePresent = input)
        }
        isOnSceneComplete()
    }
    fun setAmbulanceCheck(input: Boolean) {
        _uiState.update {
            it.copy(ambulanceCheck = input)
        }
        if (input) setAmbulancePresent(null)
        else setAmbulancePresent("")
    }
    fun setAmbulancePresent(input: String?) {
        _reportState.update {
            it.copy(ambulancePresent = input)
        }
        isOnSceneComplete()
    }
    fun setElectricCompanyCheck(input: Boolean) {
        _uiState.update {
            it.copy(electricCompanyCheck = input)
        }
        if (input) setElectricCompanyPresent(null)
        else setElectricCompanyPresent("")
    }
    fun setElectricCompanyPresent(input: String?) {
        _reportState.update {
            it.copy(electricCompanyPresent = input)
        }
        isOnSceneComplete()
    }
    fun setTransitPoliceCheck(input: Boolean) {
        _uiState.update {
            it.copy(transitPoliceCheck = input)
        }
        if (input) setTransitPolicePresent(null)
        else setTransitPolicePresent("")
    }
    fun setTransitPolicePresent(input: String?) {
        _reportState.update {
            it.copy(transitPolicePresent = input)
        }
        isOnSceneComplete()
    }
    fun setNotes(input: String) {
        _reportState.update {
            it.copy(notes = input.ifEmpty { null })
        }
        isOnSceneComplete()
    }
    fun toggleVictimDialog() {
        _uiState.update {
            it.copy(addVictimDialog = !uiState.value.addVictimDialog)
        }
    }
    private fun toggleVictimEdit(input: Int? = null) {
        _uiState.update {
            it.copy(victimEditIndex = input)
        }
    }
    fun setVictimStatusCode(input: String) {
        _uiState.update {
            it.copy(victimStatusCode = input)
        }
    }
    fun setVictimName(input: String) {
        _uiState.update {
            it.copy(victimName = input)
        }
    }
    fun setVictimAge(input: String) {
        _uiState.update {
            it.copy(victimAge = input)
        }
    }
    fun setVictimIdentification(input: String) {
        _uiState.update {
            it.copy(victimIdentification = input)
        }
    }
    private fun setVictimFields(
        statusCode: String = "",
        name: String = "",
        age: String = "",
        identification: String = ""
    ) {
        _uiState.update {
            it.copy(
                victimStatusCode = statusCode,
                victimName = name,
                victimAge = age,
                victimIdentification = identification
            )
        }
    }
    fun addVictim() {
        _reportState.update {
            it.copy(victimInfo = it.victimInfo + VictimInfo(
                statusCode = uiState.value.victimStatusCode,
                name = uiState.value.victimName,
                age = uiState.value.victimAge,
                identification = uiState.value.victimIdentification
            )
            )
        }
        setVictimFields()
        toggleVictimDialog()
    }
    fun cancelVictimDialog() {
        setVictimFields()
        toggleVictimDialog()
        if (uiState.value.victimEditIndex != null) {_uiState.value.victimEditIndex = null}
    }
    fun removeVictim(index: Int) {
        _reportState.update {
            it.copy(victimInfo = it.victimInfo - it.victimInfo[index])
        }
    }
    fun initiateVictimEdit(index: Int) {
        setVictimFields(
            statusCode = reportState.value.victimInfo[index].statusCode,
            name = reportState.value.victimInfo[index].name,
            age = reportState.value.victimInfo[index].age,
            identification = reportState.value.victimInfo[index].identification
        )
        toggleVictimEdit(index)
        toggleVictimDialog()
    }
    fun updateVictim() {
        val firstVictimsList: List<VictimInfo>? =
            uiState.value.victimEditIndex?.let {
                reportState.value.victimInfo.subList(0, it)
            }
        val secondVictimsList: List<VictimInfo>? =
            uiState.value.victimEditIndex?.let {
                reportState.value.victimInfo.subList(it + 1, reportState.value.victimInfo.size)
            }
        val newList: MutableList<VictimInfo> = mutableListOf()
        if (firstVictimsList != null) newList += firstVictimsList
        val updatedVictim = VictimInfo(
            statusCode = uiState.value.victimStatusCode,
            name = uiState.value.victimName,
            age = uiState.value.victimAge,
            identification = uiState.value.victimIdentification
        )
        newList += updatedVictim
        if (secondVictimsList != null) newList += secondVictimsList
        _reportState.update {
            it.copy(victimInfo = newList)
        }
        toggleVictimEdit()
        setVictimFields()
        toggleVictimDialog()
    }
    fun setDatetimeReturn(input: Long) {
        _reportState.update {
            it.copy(datetimeReturn = input)
        }
        isSubmitComplete()
    }
    fun setReportWriter(input: String) {
        _reportState.update {
            it.copy(reportWriter = input.ifEmpty { null })
        }
        isSubmitComplete()
    }

    // DATABASE CALLS
    fun submitReport() {
        if (uiState.value.reportID.isNotEmpty()) {
            _db.updateReport(reportState.value, uiState.value.reportID)
        } else {
            _db.submitReport(reportState.value)
        }
        _uiState.update {
            it.copy(submitSuccessful = true)
        }
    }
    suspend fun getUnfinishedReports() {
        val reports: List<Pair<String, Report>> = _db.getReport(finalized = false)
        _uiState.update {
            it.copy(pulledReports = reports)
        }
    }
    suspend fun getFinishedReports() {
        val reports: List<Pair<String, Report>> = _db.getReport(finalized = true)
        _uiState.update {
            it.copy(pulledReports = reports)
        }
    }

}