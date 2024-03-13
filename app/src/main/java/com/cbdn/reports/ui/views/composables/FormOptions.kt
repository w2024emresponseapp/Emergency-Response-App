package com.cbdn.reports.ui.views.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cbdn.reports.R

data class EmergencyCategories(
    val id: String,
    val category: String,
)

data class EmergencyCodes(
    val code: String,
    val name: String
)

data class Trucks(
    val code: String
)

data class VictimCodes(
    val code: String,
    val name: String
)

object EmergencyCodeData {
    @Composable
    fun getCategories(): List<EmergencyCategories> {
        return listOf(
            EmergencyCategories(
                id = stringResource(R.string.emergency_category_1_code),
                category = stringResource(R.string.emergency_category_1_name)
            ),
            EmergencyCategories(
                id = stringResource(R.string.emergency_category_2_code),
                category = stringResource(R.string.emergency_category_2_name)
            ),
            EmergencyCategories(
                id = stringResource(R.string.emergency_category_3_code),
                category = stringResource(R.string.emergency_category_3_name)
            ),
            EmergencyCategories(
                id = stringResource(R.string.emergency_category_4_code),
                category = stringResource(R.string.emergency_category_4_name)
            ),
        )
    }

    @Composable
    fun getCode(
        emergencyCode: EmergencyCategories
    ): List<EmergencyCodes> {
        var returnList: List<EmergencyCodes> = emptyList()
        when (emergencyCode.id) {
            "F" -> returnList = listOf(
                EmergencyCodes(
                    code = stringResource(R.string.emergency_f_1_code),
                    name = stringResource(R.string.emergency_f_1_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_f_2_code),
                    name = stringResource(R.string.emergency_f_2_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_f_3_code),
                    name = stringResource(R.string.emergency_f_3_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_f_4_code),
                    name = stringResource(R.string.emergency_f_4_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_f_5_code),
                    name = stringResource(R.string.emergency_f_5_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_f_6_code),
                    name = stringResource(R.string.emergency_f_6_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_f_7_code),
                    name = stringResource(R.string.emergency_f_7_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_f_8_code),
                    name = stringResource(R.string.emergency_f_8_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_f_9_code),
                    name = stringResource(R.string.emergency_f_9_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_f_10_code),
                    name = stringResource(R.string.emergency_f_10_name)
                ),
            )
            "R" -> returnList = listOf(
                EmergencyCodes(
                    code = stringResource(R.string.emergency_r_1_code),
                    name = stringResource(R.string.emergency_r_1_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_r_2_code),
                    name = stringResource(R.string.emergency_r_2_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_r_3_code),
                    name = stringResource(R.string.emergency_r_3_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_r_4_code),
                    name = stringResource(R.string.emergency_r_4_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_r_5_code),
                    name = stringResource(R.string.emergency_r_5_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_r_6_code),
                    name = stringResource(R.string.emergency_r_6_name)
                ),
            )
            "I" -> returnList = listOf(
                EmergencyCodes(
                    code = stringResource(R.string.emergency_i_15_code),
                    name = stringResource(R.string.emergency_i_15_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_i_16_code),
                    name = stringResource(R.string.emergency_i_16_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_i_17_code),
                    name = stringResource(R.string.emergency_i_17_name)
                ),
            )
            "O" -> returnList = listOf(
                EmergencyCodes(
                    code = stringResource(R.string.emergency_e_1_code),
                    name = stringResource(R.string.emergency_e_1_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_d_2_code),
                    name = stringResource(R.string.emergency_d_2_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_i_1_code),
                    name = stringResource(R.string.emergency_i_1_name)
                ),
                EmergencyCodes(
                    code = stringResource(R.string.emergency_poda_code),
                    name = stringResource(R.string.emergency_poda_name)
                ),
            )
        }
        return returnList
    }
}

object TruckData {

    @Composable
    fun getTrucks(): List<Trucks> {
        return listOf(
            Trucks(code = stringResource(R.string.truck_r_30_code)),
            Trucks(code = stringResource(R.string.truck_r_32_code)),
            Trucks(code = stringResource(R.string.truck_r_36_code)),
            Trucks(code = stringResource(R.string.truck_b_2_code)),
            Trucks(code = stringResource(R.string.truck_b_3_code)),
            Trucks(code = stringResource(R.string.truck_b_4_code)),
            Trucks(code = stringResource(R.string.truck_b_5_code)),
            Trucks(code = stringResource(R.string.truck_b_6_code)),
            Trucks(code = stringResource(R.string.truck_b_7_code)),
            Trucks(code = stringResource(R.string.truck_b_9_code)),
            Trucks(code = stringResource(R.string.truck_b_10_code)),
            Trucks(code = stringResource(R.string.truck_b_11_code)),
            Trucks(code = stringResource(R.string.truck_b_12_code)),
            Trucks(code = stringResource(R.string.truck_b_16_code)),
            Trucks(code = stringResource(R.string.truck_b_15_code)),
            Trucks(code = stringResource(R.string.truck_b_18_code)),
            Trucks(code = stringResource(R.string.truck_b_19_code)),
            Trucks(code = stringResource(R.string.truck_b_20_code)),
            Trucks(code = stringResource(R.string.truck_b_21_code)),
            Trucks(code = stringResource(R.string.truck_b_22_code)),
            Trucks(code = stringResource(R.string.truck_b_31_code)),
            Trucks(code = stringResource(R.string.truck_c_9_code)),
            Trucks(code = stringResource(R.string.truck_c_10_code)),
            Trucks(code = stringResource(R.string.truck_c_12_code)),
            Trucks(code = stringResource(R.string.truck_c_21_code)),
            Trucks(code = stringResource(R.string.truck_c_22_code)),
            Trucks(code = stringResource(R.string.truck_c_23_code)),
            Trucks(code = stringResource(R.string.truck_c_24_code)),
            Trucks(code = stringResource(R.string.truck_e_26_code)),
            Trucks(code = stringResource(R.string.truck_e_27_code)),
            Trucks(code = stringResource(R.string.truck_e_28_code)),
        )
    }
}

object VictimCodeData {
    @Composable
    fun getCode(): List<VictimCodes> {
        return listOf(
            VictimCodes(
                code = stringResource(R.string.victim_i_26_code),
                name = stringResource(R.string.victim_i_26_name)
            ),
            VictimCodes(
                code = stringResource(R.string.victim_i_27_code),
                name = stringResource(R.string.victim_i_27_name)
            ),
            VictimCodes(
                code = stringResource(R.string.victim_i_28_code),
                name = stringResource(R.string.victim_i_28_name)
            ),
            VictimCodes(
                code = stringResource(R.string.victim_i_28_adv_code),
                name = stringResource(R.string.victim_i_28_adv_name)
            ),
            VictimCodes(
                code = stringResource(R.string.victim_i_29_code),
                name = stringResource(R.string.victim_i_29_name)
            ),
        )
    }
}