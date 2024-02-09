package com.example.loginapidemo.ui

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.lifecycleScope
import com.example.loginapidemo.R
import com.example.loginapidemo.base.BaseActivity
import com.example.loginapidemo.databinding.ActivityMainBinding
import com.example.loginapidemo.di.Resource
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, LoginViewModel>() {
    override val layoutId: Int
        get() = R.layout.activity_main
    override val bindingVariable: Int
        get() = BR.viewModel
    private lateinit var cameraResultLauncher: ActivityResultLauncher<Intent>
    private var imageUriCamera: Uri? = null
    private val REQUEST_IMAGE_CAPTURE = 1
    override fun setUpObserver() {
        mViewModel.getLoginObserver().observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, "${it.data?.data?.firstName} success", Toast.LENGTH_SHORT)
                        .show()
                }

                Resource.Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }

                Resource.Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, "${it.data?.message} success", Toast.LENGTH_SHORT).show()
                }

                Resource.Status.NO_INTERNET_CONNECTION,
                -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, "no Internet connection", Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }



        /**edit profile multipart demo*/


        mViewModel.getProfileInfoObservable().observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    myDialog.hide()
                    if (it.data?.status == true) {
                        /* ApiConstant.initBookingInfo = gson.toJson(it.data)*/
                        lifecycleScope.launch {
                            dataStore.setDataStore(gson.toJson(it.data))
                        }
                        Toast.makeText(this@MainActivity, "image upload successful", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "${it.message!!}", Toast.LENGTH_SHORT).show()
                    }
                }

                Resource.Status.ERROR -> {
                    myDialog.hide()
                    Toast.makeText(this@MainActivity, "${it.message!!}", Toast.LENGTH_SHORT).show()
                    Log.e(ContentValues.TAG, "on Profile Info error=>${it.message}")
                }

                Resource.Status.LOADING -> {
                    myDialog.show()
                }

                Resource.Status.NO_INTERNET_CONNECTION -> {
                    myDialog.hide()
                    Toast.makeText(this@MainActivity, "${it.message!!}", Toast.LENGTH_SHORT).show()
                }

                Resource.Status.UNKNOWN -> {
                    myDialog.hide()
                    window.clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    )
                }

                else -> {}
            }
        }
    }

    private val requestCameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (permissions[android.Manifest.permission.CAMERA] == true
            ) {
                openCamera()
            } else {
                Toast.makeText(this, ".please_grant_camera_and_storage_permissions_in_the_app_settings", Toast.LENGTH_SHORT).show()
               /* CommonFun.showCameraSetting(
                    this,
                    getString(R.string.please_grant_camera_and_storage_permissions_in_the_app_settings)
                )*/
            }
        } else {
            if (permissions[android.Manifest.permission.CAMERA] == true &&
                permissions[android.Manifest.permission.WRITE_EXTERNAL_STORAGE] == true &&
                permissions[android.Manifest.permission.READ_EXTERNAL_STORAGE] == true
            ) {
                openCamera()
            } else {
             /*   CommonFun.showCameraSetting(
                    this,
                    getString(R.string.please_grant_camera_and_storage_permissions_in_the_app_settings)
                )*/
                Toast.makeText(this, ".please_grant_camera_and_storage_permissions_in_the_app_settings", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (permissions[android.Manifest.permission.CAMERA] == true &&
                permissions[android.Manifest.permission.READ_MEDIA_IMAGES] == true
            ) {
                openCamera()
            } else {
                showPermissionDeniedDialog()
            }
        } else {
            if (permissions[android.Manifest.permission.CAMERA] == true &&
                permissions[android.Manifest.permission.WRITE_EXTERNAL_STORAGE] == true &&
                permissions[android.Manifest.permission.READ_EXTERNAL_STORAGE] == true
            ) {
                openCamera()
            } else {
                showPermissionDeniedDialog()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.submit.setOnClickListener {
            var number = binding.etNumber.text?.trim().toString()
            var pass = binding.etPass.text?.trim().toString()
            checkValidation(number, pass)
        }
        binding.img.setOnClickListener {
            checkPermissions()
        }
    }

    private fun checkPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestCameraPermissionLauncher.launch(
                arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_MEDIA_IMAGES
                )
            )
        } else {
            /*below android 13 device */
            requestCameraPermissionLauncher.launch(
                arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            )
        }
    }

    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(
                arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_MEDIA_IMAGES
                )
            )
        } else {
            /*below android 13 device */
            requestPermissionLauncher.launch(
                arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            )
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val pathCol = arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor? = imageUriCamera.let {
                activity.contentResolver.query(
                    it!!,
                    pathCol,
                    null,
                    null,
                    null
                )
            }
            cursor?.moveToFirst()
            val colIdx: Int = cursor?.getColumnIndex(pathCol[0])!!
            val img: String = cursor.getString(colIdx)
            Log.e("imgTest", "" + img)
            cursor.close()
            // binding.img.setImageURI(Uri.fromFile(File(img)))
            binding.img.setImageURI(imageUriCamera)
            lifecycleScope.launch {
                dataStore.setProfileImage(imageUriCamera.toString())
                Log.e("imgTest", "" + img)
            }
        }
    }

    private fun showPermissionDeniedDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Permission Denied")
            .setMessage("Please grant camera and storage permissions in the app settings.")
            .setPositiveButton("Settings") { _, _ ->
                openAppSettings()
            }
            .setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(this, "Permissions denied. Cannot open camera.", Toast.LENGTH_SHORT)
                    .show()
            }
            .show()
    }

    private fun openAppSettings() {
        val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }

    private fun openCamera() {

        val values = ContentValues()
        imageUriCamera =
            contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        /*  intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)*/
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriCamera)
        /* if (intent.resolveActivity(packageManager) != null) {*/
        /* startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)*/
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    private fun checkValidation(number: String, pass: String) {
        if (number.isEmpty()) {
            Toast.makeText(this, "please enter number", Toast.LENGTH_SHORT).show()
        } else if (pass.isEmpty()) {
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show()
        } else {
            mViewModel.loginApi(number, pass)
        }
    }

    fun Register(view: View) {
        mViewModel.profileInfo()
    }
}