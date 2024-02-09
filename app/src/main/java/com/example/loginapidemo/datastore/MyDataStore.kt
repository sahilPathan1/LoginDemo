package com.example.loginapidemo.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MyDataStore constructor(private val context: Context) {

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        private val IS_LOGIN = booleanPreferencesKey("isLogin")
        private val TOKEN = stringPreferencesKey("token")
        private val ID = stringPreferencesKey("id")
        private val PRIVACY_URL = stringPreferencesKey("privacy")
        private val TERMOFSERVICE_URL = stringPreferencesKey("term")

        private val OTP = stringPreferencesKey("otp")
        private val API_KEY = stringPreferencesKey("x_api_key")
        private val LOGIN_MODEL = stringPreferencesKey("login_model")
        private val HAS_API_KEY = stringPreferencesKey("hasApiKey")
        private const val LANGUAGE_PREFERENCES_NAME = "language_preferences"
        private val LANGUAGE_KEY = stringPreferencesKey("language_key")
        private val ENGLISH = stringPreferencesKey("english")
        private val ARABIC = stringPreferencesKey("arabic")
        private val Context.languagePreferences by preferencesDataStore(name = LANGUAGE_PREFERENCES_NAME)

        private val FIRST_NAME = stringPreferencesKey("first_name")
        private val LAST_NAME = stringPreferencesKey("last_name")
        private val CUSTOMER_FIRST_NAME = stringPreferencesKey("first_name")
        private val VEHICLE_YEAR = stringPreferencesKey("year")
        private val VEHICLE_MODEL = stringPreferencesKey("model")
        private val DRIVER_LICENCE_EXP = stringPreferencesKey("driverLicenceExp")
        private val NTSA_BUDGE_EXP = stringPreferencesKey("NTSABudgeExp")
        private val NTSA_INSPECTION_EXP = stringPreferencesKey("ntsaInspectionExp")
        private val PSV_COMPREHENSIVE_EXP = stringPreferencesKey("PsvComprehensiveExpiry")

        private val CUSTOMER_LAST_NAME = stringPreferencesKey("last_name")
        private val CAR_TYPE = stringPreferencesKey("car_type")
        private val GENDER = stringPreferencesKey("gender")
        private val ADDRESS = stringPreferencesKey("address")
        private val DOB = stringPreferencesKey("dob")
        private val PROFILE_IMAGE = stringPreferencesKey("profile_image")
        private val RATING_IMAGE = stringPreferencesKey("rating_image")
        private val MOBILE_NO = stringPreferencesKey("mobile_no")
        private val EMAIL = stringPreferencesKey("email")
        private val OWNER_NAME = stringPreferencesKey("owner_name")
        private val OWNER_EMAIL = stringPreferencesKey("owner_email")
        private val OWNER_MOBILENO = stringPreferencesKey("owner_mobileno")
        private val NATIONAL_ID = stringPreferencesKey("national_Id_no")
        private val PLATE_NO = stringPreferencesKey("plate_number")
        private val COLOR = stringPreferencesKey("color")
        private val MANUFACTURE = stringPreferencesKey("manufacture")
        private val MODELID = stringPreferencesKey("model_id")
        private val MODELNAME = stringPreferencesKey("model_name")
        private val VEHICLETYPE = stringPreferencesKey("vehicle_type")
        private val INSIDE_IMAGE = stringPreferencesKey("inside")
        private val BACK_IMAGE = stringPreferencesKey("back")
        private val FRONT_IMAGE = stringPreferencesKey("front")
        private val RIGHT_IMAGE = stringPreferencesKey("right")
        private val LEFT_IMAGE = stringPreferencesKey("left")
        private val LNG = stringPreferencesKey("lng")
        private val LAT = stringPreferencesKey("lat")
        private val MANUFACTUREID = stringPreferencesKey("manufactureId")
        private val NATIONAL_ID_IMAGE = stringPreferencesKey("nationalIdImage")
        private val DRIVER_LICENCE_IMAGE = stringPreferencesKey("driverLicenceImage")
        private val NTSA_BADGE_IMAGE = stringPreferencesKey("ntsaBadgeImage")
        private val GOOD_CONDUCT_IMAGE = stringPreferencesKey("policeCertificate")
        private val VEHICLE_LOG_BOOK_IMAGE = stringPreferencesKey("vehicleLogBook")
        private val NTSA_INSPECTION_IMAGE = stringPreferencesKey("ntsaInspectionImage")
        private val PSV_COMPREHENSIVE_INSURANCE_IMAGE = stringPreferencesKey("psvComprehensiveInsuarence")
        private val BOOKING_ID = stringPreferencesKey("bookingId")
        private val BOOKING_TYPE = stringPreferencesKey("bookingType")
    }

    suspend fun setUserId(id: String) {
        context.dataStore.edit { preferences ->
            preferences[ID] = id
        }
    }

    val getUserId: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[ID] ?: ""
        }

    suspend fun setBookingId(id: String) {
        context.dataStore.edit { preferences ->
            preferences[BOOKING_ID] = id
        }
    }

    val getBookingId: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[BOOKING_ID] ?: ""
        }

    suspend fun setBookingType(id: String) {
        context.dataStore.edit { preferences ->
            preferences[BOOKING_TYPE] = id
        }
    }

    val getBookingType: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[BOOKING_TYPE] ?: ""
        }

    val getProfileImage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PROFILE_IMAGE] ?: ""
        }

    suspend fun setProfileImage(image: String) {
        context.dataStore.edit { preferences ->
            preferences[PROFILE_IMAGE] = image
        }
    }


    val getRatingImage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[RATING_IMAGE] ?: ""
        }

    suspend fun setRatingImage(image: String) {
        context.dataStore.edit { preferences ->
            preferences[RATING_IMAGE] = image
        }
    }


    suspend fun setOwnerMobileNo(ownEmail: String) {
        context.dataStore.edit { preferences ->
            preferences[OWNER_MOBILENO] = ownEmail
        }
    }

    val getOwnerMobileNo: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[OWNER_MOBILENO] ?: ""
        }

    suspend fun setOwnerEmail(ownEmail: String) {
        context.dataStore.edit { preferences ->
            preferences[OWNER_EMAIL] = ownEmail
        }
    }

    val getOwnerEmail: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[OWNER_EMAIL] ?: ""
        }


    suspend fun setOwnerName(ownerName: String) {
        context.dataStore.edit { preferences ->
            preferences[OWNER_NAME] = ownerName
        }
    }

    val getOwnerName: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[OWNER_NAME] ?: ""
        }


    suspend fun setEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL] = email
        }
    }

    val getEmail: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[EMAIL] ?: ""
        }


    suspend fun setNationalIdNo(nationalId: String) {
        context.dataStore.edit { preferences ->
            preferences[NATIONAL_ID] = nationalId
        }
    }

    val getNationalIdNo: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[NATIONAL_ID] ?: ""
        }

    suspend fun setMobileNo(mobileNo: String) {
        context.dataStore.edit { preferences ->
            preferences[MOBILE_NO] = mobileNo
        }
    }

    val getMobileNo: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[MOBILE_NO] ?: ""
        }

    suspend fun setDob(dob: String) {
        context.dataStore.edit { preferences ->
            preferences[DOB] = dob
        }
    }

    val getDob: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[DOB] ?: ""
        }


    suspend fun setFirstName(firstName: String) {
        context.dataStore.edit { preferences ->
            preferences[FIRST_NAME] = firstName
        }
    }

    val getFirstName: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[FIRST_NAME] ?: ""
        }

    suspend fun setLastName(lastName: String) {
        context.dataStore.edit { preferences ->
            preferences[LAST_NAME] = lastName
        }
    }

    val getLastName: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[LAST_NAME] ?: ""
        }

    suspend fun setCustomerFirstName(firstName: String) {
        context.dataStore.edit { preferences ->
            preferences[CUSTOMER_FIRST_NAME] = firstName
        }
    }

    val getCustomerFirstName: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[CUSTOMER_FIRST_NAME] ?: ""
        }

    suspend fun setCustomerLastName(lastName: String) {
        context.dataStore.edit { preferences ->
            preferences[CUSTOMER_LAST_NAME] = lastName
        }
    }

    val getCustomerLastName: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[CUSTOMER_LAST_NAME] ?: ""
        }

    suspend fun setCarType(carType: String) {
        context.dataStore.edit { preferences ->
            preferences[CAR_TYPE] = carType
        }
    }

    val getCarType: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[CAR_TYPE] ?: ""
        }

    suspend fun setGender(gender: String) {
        context.dataStore.edit { preferences ->
            preferences[GENDER] = gender
        }
    }

    val getGender: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[GENDER] ?: ""
        }

    suspend fun setAdress(address: String) {
        context.dataStore.edit { preferences ->
            preferences[ADDRESS] = address
        }
    }

    val getAddress: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[ADDRESS] ?: ""
        }


    suspend fun setPlateNumber(plateNo: String) {
        context.dataStore.edit { preferences ->
            preferences[PLATE_NO] = plateNo
        }
    }

    val getPlateNumber: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PLATE_NO] ?: ""
        }


    suspend fun setColor(plateNo: String) {
        context.dataStore.edit { preferences ->
            preferences[COLOR] = plateNo
        }
    }

    val getColor: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[COLOR] ?: ""
        }


    suspend fun setManufactureName(manufacture: String) {
        context.dataStore.edit { preferences ->
            preferences[MANUFACTURE] = manufacture
        }
    }

    val getManufactureName: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[MANUFACTURE] ?: ""
        }

    suspend fun setManufactureId(manufacture: String) {
        context.dataStore.edit { preferences ->
            preferences[MANUFACTUREID] = manufacture
        }
    }

    val getManufactureId: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[MANUFACTUREID] ?: ""
        }


    suspend fun setVehicleModelId(manufacture: String) {
        context.dataStore.edit { preferences ->
            preferences[MODELID] = manufacture
        }
    }

    val getVehicleModelId: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[MODELID] ?: ""
        }

    suspend fun setVehicleModelName(manufacture: String) {
        context.dataStore.edit { preferences ->
            preferences[MODELNAME] = manufacture
        }
    }

    val getVehicleModelName: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[MODELNAME] ?: ""
        }


    suspend fun setVehicleType(manufacture: String) {
        context.dataStore.edit { preferences ->
            preferences[VEHICLETYPE] = manufacture
        }
    }

    val getVehicleType: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[VEHICLETYPE] ?: ""
        }


    suspend fun setTermUrl(termUrl: String) {
        context.dataStore.edit { preferences ->
            preferences[TERMOFSERVICE_URL] = termUrl
        }
    }

    val getTermUrl: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[TERMOFSERVICE_URL] ?: ""
        }


    suspend fun setApiKey(apikey: String) {
        context.dataStore.edit { preferences ->
            preferences[API_KEY] = apikey
        }
    }
    val hasApiKey: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[API_KEY] ?: "false"
        }


    suspend fun setDataStore(loginModel: String) {
        context.dataStore.edit { preferences ->
            preferences[LOGIN_MODEL] = loginModel
        }
    }
    val getDataStore: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[LOGIN_MODEL] ?: ""
        }


    suspend fun setHasApiKey(hasApiKey: String) {
        context.dataStore.edit { preferences ->
            preferences[HAS_API_KEY] = hasApiKey
        }
    }
    val getApiKey: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[API_KEY] ?: ""
        }


    suspend fun setPrivacyUrl(privacyUrl: String) {
        context.dataStore.edit { preferences ->
            preferences[PRIVACY_URL] = privacyUrl
        }
    }

    val getPrivacyUrl: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PRIVACY_URL] ?: ""
        }


    //User login or not save
    suspend fun isUserLogin(isLogin: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGIN] = isLogin
        }
    }

    //Check user login or not
    val isUserLogin: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[IS_LOGIN] ?: false
        }

    suspend fun setFcmToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    val getFcmToken: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[TOKEN] ?: ""
        }


    suspend fun setOtp(token: String) {
        context.dataStore.edit { preferences ->
            preferences[OTP] = token
        }
    }

    val getOtp: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[OTP] ?: ""
        }



    suspend fun logoutId() {
        context.dataStore.edit {
            it.clear()
        }
    }

    suspend fun deleteNamePreferences() {
        context.dataStore.edit { preferences ->
            preferences.remove(API_KEY)
        }
    }

    suspend fun deleteAllPreferences() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }


    suspend fun setLanguage(language: String) {
        context.dataStore.edit { preferences ->

            Log.d("language---------------------------------", language)
            preferences[ENGLISH] = language

            preferences[ARABIC] = language

        }
    }

    suspend fun driverLogoutId() {
        context.dataStore.edit {
            it.clear()
        }
    }

    val getLanguage: Flow<String> = context.dataStore.data.map { preferences ->

        preferences[ENGLISH] ?: ""

        preferences[ARABIC] ?: ""

    }


    val getLeftImage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[LEFT_IMAGE] ?: ""
        }

    suspend fun setLeftImage(image: String) {
        context.dataStore.edit { preferences ->
            preferences[LEFT_IMAGE] = image
        }
    }


    val getLng: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[LNG] ?: ""
        }

    suspend fun setLng(lng: String) {
        context.dataStore.edit { preferences ->
            preferences[LNG] = lng
        }
    }

    val getLat: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[LAT] ?: ""
        }

    suspend fun setLat(lat: String) {
        context.dataStore.edit { preferences ->
            preferences[LAT] = lat
        }
    }


    suspend fun setRightImage(image: String) {
        context.dataStore.edit { preferences ->
            preferences[RIGHT_IMAGE] = image
        }
    }
    val getRightImage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[RIGHT_IMAGE] ?: ""
        }

    suspend fun setFrontImage(image: String) {
        context.dataStore.edit { preferences ->
            preferences[FRONT_IMAGE] = image
        }
    }
    val getFrontImage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[FRONT_IMAGE] ?: ""
        }

    suspend fun setBackImage(image: String) {
        context.dataStore.edit { preferences ->
            preferences[BACK_IMAGE] = image
        }
    }
    val getBackImage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[BACK_IMAGE] ?: ""
        }

    suspend fun setInsideImage(image: String) {
        context.dataStore.edit { preferences ->
            preferences[INSIDE_IMAGE] = image
        }
    }

    val getInsideImage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[INSIDE_IMAGE] ?: ""
        }


    val getVehicleYear: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[VEHICLE_YEAR] ?: ""
        }

    suspend fun setVehicleYear(year: String) {
        context.dataStore.edit { preferences ->
            preferences[VEHICLE_YEAR] = year
        }
    }


    val getDriverLicenceExpiry: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[DRIVER_LICENCE_EXP] ?: ""
        }

    suspend fun setDriverLicenceExpiry(licenceExp: String) {
        context.dataStore.edit { preferences ->
            preferences[DRIVER_LICENCE_EXP] = licenceExp
        }
    }
    val getNTSABudgeExpiry: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[NTSA_BUDGE_EXP] ?: ""
        }

    suspend fun setNTSABudgeExpiry(ntsaBudge: String) {
        context.dataStore.edit { preferences ->
            preferences[NTSA_BUDGE_EXP] = ntsaBudge
        }
    }
    val getNTSAInspectionExpiry: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[NTSA_INSPECTION_EXP] ?: ""
        }

    suspend fun setNTSAInspectionExpiry(ntsaInspection: String) {
        context.dataStore.edit { preferences ->
            preferences[NTSA_INSPECTION_EXP] = ntsaInspection
        }
    }
    val getPsvComprehensiveExpiry: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PSV_COMPREHENSIVE_EXP] ?: ""
        }

    suspend fun setPsvComprehensiveExpiry(psvComprehensive: String) {
        context.dataStore.edit { preferences ->
            preferences[PSV_COMPREHENSIVE_EXP] = psvComprehensive
        }
    }
    val getNationalIdImage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[NATIONAL_ID_IMAGE] ?: ""
        }

    suspend fun setNationalIdImage(nationalIdImage: String) {
        context.dataStore.edit { preferences ->
            preferences[NATIONAL_ID_IMAGE] = nationalIdImage
        }
    }
    val getDriverLicenceImage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[DRIVER_LICENCE_IMAGE] ?: ""
        }

    suspend fun setDriverLicenceImage(driverLicenceImage: String) {
        context.dataStore.edit { preferences ->
            preferences[DRIVER_LICENCE_IMAGE] = driverLicenceImage
        }
    }
    val getNtsaBadgeImage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[NTSA_BADGE_IMAGE] ?: ""
        }

    suspend fun setNtsaBadgeImage(ntsaBadgeImage: String) {
        context.dataStore.edit { preferences ->
            preferences[NTSA_BADGE_IMAGE] = ntsaBadgeImage
        }
    }
    val getGoodConductImage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[GOOD_CONDUCT_IMAGE] ?: ""
        }

    suspend fun setGoodConductImage(goodConductImage: String) {
        context.dataStore.edit { preferences ->
            preferences[GOOD_CONDUCT_IMAGE] = goodConductImage
        }
    }
    val getVehicleLogBookImage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[VEHICLE_LOG_BOOK_IMAGE] ?: ""
        }

    suspend fun setVehicleLogBookImage(logBookImage: String) {
        context.dataStore.edit { preferences ->
            preferences[VEHICLE_LOG_BOOK_IMAGE] = logBookImage
        }
    }

    val getNtsaInspectionImage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[NTSA_INSPECTION_IMAGE] ?: ""
        }

    suspend fun setNtsaInspectionImage(ntsaInspectionImage: String) {
        context.dataStore.edit { preferences ->
            preferences[NTSA_INSPECTION_IMAGE] = ntsaInspectionImage
        }
    }

    val getComprehensiveImage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PSV_COMPREHENSIVE_INSURANCE_IMAGE] ?: ""
        }

    suspend fun setComprehensiveImage(psvComprehensiveImage: String) {
        context.dataStore.edit { preferences ->
            preferences[PSV_COMPREHENSIVE_INSURANCE_IMAGE] = psvComprehensiveImage
        }
    }
}





