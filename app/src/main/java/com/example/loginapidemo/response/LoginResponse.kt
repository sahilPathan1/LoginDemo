package com.example.loginapidemo.response

import com.example.loginapidemo.baseresposne.BaseResponse
import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("booking_info")
    val bookingInfo: BookingInfo? = null,

    @field:SerializedName("cancel_reason")
    val cancelReason: List<CancelReasonItem?>? = null,

    @field:SerializedName("driver_subscription")
    val driverSubscription: DriverSubscription? = null,

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("invite_message")
    val inviteMessage: String? = null,

    @field:SerializedName("sos_number")
    val sosNumber: String? = null,

    @field:SerializedName("otp")
    val otp: Int? = null,

    @field:SerializedName("meter_check")
    val meterCheck: MeterCheck? = null,


    ): BaseResponse(){


    data class Data(

        @field:SerializedName("corporate_driver_type")
        val corporateDriverType: String? = null,

        @field:SerializedName("bank_branch")
        val bankBranch: String? = null,

        @field:SerializedName("country")
        val country: String? = null,

        @field:SerializedName("is_adam_adjustment")
        val isAdamAdjustment: String? = null,

        @field:SerializedName("account_holder_name")
        val accountHolderName: String? = null,

        @field:SerializedName("is_saving_wallet_show")
        val isSavingWalletShow: Boolean? = null,

        @field:SerializedName("saving_wallet_percentage")
        val savingWalletPercentage: String? = null,

        @field:SerializedName("rating")
        val rating: String? = null,

        @field:SerializedName("device_type")
        val deviceType: String? = null,

        @field:SerializedName("car_type")
        val carType: String? = null,

        @field:SerializedName("profile_image")
        val profileImage: String? = null,

        @field:SerializedName("transaction_password")
        val transactionPassword: String? = null,

        @field:SerializedName("verify_auto_debit_subscription_response")
        val verifyAutoDebitSubscriptionResponse: Any? = null,

        @field:SerializedName("driver_role")
        val driverRole: String? = null,

        @field:SerializedName("is_corporate_driver")
        val isCorporateDriver: String? = null,

        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("state")
        val state: String? = null,

        @field:SerializedName("remember_token")
        val rememberToken: String? = null,

        @field:SerializedName("portfolioName")
        val portfolioName: Any? = null,

        @field:SerializedName("payment_method")
        val paymentMethod: Any? = null,

        @field:SerializedName("lat")
        val lat: String? = null,

        @field:SerializedName("vehicle_info")
        val vehicleInfo: List<VehicleInfoItem?>? = null,

        @field:SerializedName("owner_mobile_no")
        val ownerMobileNo: String? = null,

        @field:SerializedName("initiate_auto_debit_subscription_reference_no")
        val initiateAutoDebitSubscriptionReferenceNo: String? = null,

        @field:SerializedName("owner_name")
        val ownerName: String? = null,

        @field:SerializedName("lng")
        val lng: String? = null,

        @field:SerializedName("trash")
        val trash: String? = null,

        @field:SerializedName("old_device_type")
        val oldDeviceType: String? = null,

        @field:SerializedName("portfolioId")
        val portfolioId: Any? = null,

        @field:SerializedName("dob")
        val dob: String? = null,

        @field:SerializedName("x-api-key")
        val xApiKey: String? = null,

        @field:SerializedName("invite_code")
        val inviteCode: String? = null,

        @field:SerializedName("status")
        val status: String? = null,

        @field:SerializedName("account_number")
        val accountNumber: String? = null,

        @field:SerializedName("driver_type")
        val driverType: String? = null,

        @field:SerializedName("gender")
        val gender: String? = null,

        @field:SerializedName("setup_wallet_response")
        val setupWalletResponse: String? = null,

        @field:SerializedName("city")
        val city: String? = null,

        @field:SerializedName("accountName")
        val accountName: Any? = null,

        @field:SerializedName("last_wallet_update_time")
        val lastWalletUpdateTime: Any? = null,

        @field:SerializedName("other_company_id")
        val otherCompanyId: String? = null,

        @field:SerializedName("mobile_no")
        val mobileNo: String? = null,

        @field:SerializedName("last_wallet_response")
        val lastWalletResponse: Any? = null,

        @field:SerializedName("created_at")
        val createdAt: String? = null,

        @field:SerializedName("approval_awaiting")
        val approvalAwaiting: String? = null,

        @field:SerializedName("old_device_token")
        val oldDeviceToken: String? = null,

        @field:SerializedName("busy")
        val busy: String? = null,

        @field:SerializedName("bank_name")
        val bankName: String? = null,

        @field:SerializedName("qr_code")
        val qrCode: String? = null,

        @field:SerializedName("verify")
        val verify: String? = null,

        @field:SerializedName("driver_docs")
        val driverDocs: DriverDocs? = null,

        @field:SerializedName("first_name")
        val firstName: String? = null,

        @field:SerializedName("email")
        val email: String? = null,

        @field:SerializedName("owner_email")
        val ownerEmail: String? = null,

        @field:SerializedName("jambopay_profile_id")
        val jambopayProfileId: Any? = null,

        @field:SerializedName("tariff_plan_id")
        val tariffPlanId: String? = null,

        @field:SerializedName("address")
        val address: String? = null,

        @field:SerializedName("company_id")
        val companyId: String? = null,

        @field:SerializedName("vehicle_type")
        val vehicleType: String? = null,

        @field:SerializedName("last_name")
        val lastName: String? = null,

        @field:SerializedName("wallet_balance")
        val walletBalance: String? = null,

        @field:SerializedName("accountNumber")
        val account_Number: Any? = null,

        @field:SerializedName("work_with_other_company")
        val workWithOtherCompany: String? = null,

        @field:SerializedName("other_company_name")
        val otherCompanyName: String? = null,

        @field:SerializedName("device_token")
        val deviceToken: String? = null,

        @field:SerializedName("referral_code")
        val referralCode: String? = null,

        @field:SerializedName("duty")
        val duty: String? = null,

        @field:SerializedName("saving_wallet_balance")
        val savingWalletBalance: String? = null,

        @field:SerializedName("saving_wallet_balance_date")
        val savingWalletBalanceDate: Any? = null,

        @field:SerializedName("is_jambopay_wallet_setup")
        val isJambopayWalletSetup: String? = null,

        @field:SerializedName("memberid")
        val memberid: Any? = null
    )

    data class CancelReasonItem(

        @field:SerializedName("reason")
        val reason: String? = null,

        @field:SerializedName("for_whom")
        val forWhom: String? = null,

        @field:SerializedName("id")
        val id: String? = null
    )

    data class DriverSubscription(
        val any: Any? = null
    )

    data class MeterCheck(
        val any: Any? = null
    )

    data class DriverDocs(

        @field:SerializedName("vehicle_insurance_certi")
        val vehicleInsuranceCerti: String? = null,

        @field:SerializedName("psv_badge_image")
        val psvBadgeImage: String? = null,

        @field:SerializedName("driver_id")
        val driverId: String? = null,

        @field:SerializedName("driver_psv_license_exp_date")
        val driverPsvLicenseExpDate: String? = null,

        @field:SerializedName("psv_comprehensive_insurance_exp_date")
        val psvComprehensiveInsuranceExpDate: String? = null,

        @field:SerializedName("vehicle_insurance_exp_date")
        val vehicleInsuranceExpDate: String? = null,

        @field:SerializedName("driver_psv_license")
        val driverPsvLicense: String? = null,

        @field:SerializedName("vehicle_psv_license")
        val vehiclePsvLicense: String? = null,

        @field:SerializedName("ntsa_exp_date")
        val ntsaExpDate: String? = null,

        @field:SerializedName("national_id_image")
        val nationalIdImage: String? = null,

        @field:SerializedName("national_id_number")
        val nationalIdNumber: String? = null,

        @field:SerializedName("is_verify_ntsa")
        val isVerifyNtsa: String? = null,

        @field:SerializedName("vehicle_register_doc")
        val vehicleRegisterDoc: String? = null,

        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("is_verify_vehicle_insurance")
        val isVerifyVehicleInsurance: String? = null,

        @field:SerializedName("driver_licence_exp_date")
        val driverLicenceExpDate: String? = null,

        @field:SerializedName("psv_comprehensive_insurance")
        val psvComprehensiveInsurance: String? = null,

        @field:SerializedName("ntsa_inspection_image")
        val ntsaInspectionImage: String? = null,

        @field:SerializedName("driver_licence_image")
        val driverLicenceImage: String? = null,

        @field:SerializedName("is_verify_psv_badge")
        val isVerifyPsvBadge: String? = null,

        @field:SerializedName("psv_badge_exp_date")
        val psvBadgeExpDate: String? = null,

        @field:SerializedName("vehicle_log_book_image")
        val vehicleLogBookImage: String? = null,

        @field:SerializedName("is_verify_police_clearance")
        val isVerifyPoliceClearance: String? = null,

        @field:SerializedName("police_clearance_certi")
        val policeClearanceCerti: String? = null,

        @field:SerializedName("is_verify_driver_licence")
        val isVerifyDriverLicence: String? = null,

        @field:SerializedName("vehicle_reg_expired_date")
        val vehicleRegExpiredDate: Any? = null,

        @field:SerializedName("police_clearance_exp_date")
        val policeClearanceExpDate: String? = null,

        @field:SerializedName("vehicle_psv_license_exp_date")
        val vehiclePsvLicenseExpDate: String? = null
    )

    data class BookingInfo(

        @field:SerializedName("total_driver_earning")
        val totalDriverEarning: String? = null,

        @field:SerializedName("total_trips")
        val totalTrips: String? = null,

        @field:SerializedName("driver_duty")
        val driverDuty: String? = null
    )

    data class VehicleInfoItem(

        @field:SerializedName("car_front")
        val carFront: String? = null,

        @field:SerializedName("driver_id")
        val driverId: String? = null,

        @field:SerializedName("vehicle_color")
        val vehicleColor: String? = null,

        @field:SerializedName("vehicle_type_name")
        val vehicleTypeName: String? = null,

        @field:SerializedName("company_id")
        val companyId: String? = null,

        @field:SerializedName("driver_type")
        val driverType: String? = null,

        @field:SerializedName("vehicle_type_model_name")
        val vehicleTypeModelName: String? = null,

        @field:SerializedName("car_interior")
        val carInterior: String? = null,

        @field:SerializedName("vehicle_type")
        val vehicleType: String? = null,

        @field:SerializedName("year_of_manufacture")
        val yearOfManufacture: String? = null,

        @field:SerializedName("vehicle_type_manufacturer_id")
        val vehicleTypeManufacturerId: String? = null,

        @field:SerializedName("type")
        val type: String? = null,

        @field:SerializedName("car_back")
        val carBack: String? = null,

        @field:SerializedName("car_right")
        val carRight: String? = null,

        @field:SerializedName("car_left")
        val carLeft: String? = null,

        @field:SerializedName("no_of_passenger")
        val noOfPassenger: String? = null,

        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("plate_number")
        val plateNumber: String? = null,

        @field:SerializedName("vehicle_type_model_id")
        val vehicleTypeModelId: String? = null,

        @field:SerializedName("vehicle_type_manufacturer_name")
        val vehicleTypeManufacturerName: String? = null
    )
}
