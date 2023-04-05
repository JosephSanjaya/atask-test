package atask.sanjaya.ui.presentation.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.ViewPumpAppCompatDelegate
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import atask.sanjaya.ui.R
import atask.sanjaya.ui.databinding.ActivityCameraBinding
import atask.sanjaya.ui.presentation.sheet.CameraDisabledSheet
import coil.load
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.hoc081098.viewbindingdelegate.viewBinding
import dev.b3nedikt.restring.Restring
import splitties.bundle.withExtras
import splitties.intents.ActivityIntentSpec
import splitties.intents.activitySpec
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity(R.layout.activity_camera), View.OnClickListener, PermissionUtils.FullCallback {

    companion object :
        ActivityIntentSpec<CameraActivity, CameraActivitySpec> by activitySpec(
            CameraActivitySpec
        )


    private val appCompatDelegate: AppCompatDelegate by lazy {
        ViewPumpAppCompatDelegate(
            baseDelegate = super.getDelegate(),
            baseContext = this,
            wrapContext = Restring::wrapContext
        )
    }

    override fun getDelegate(): AppCompatDelegate {
        return appCompatDelegate
    }

    private val binding by viewBinding(ActivityCameraBinding::bind)
    private var imageCapture: ImageCapture? = null
    private var camera: Camera? = null
    private var cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
    private var lensFacing = CameraSelector.LENS_FACING_BACK
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.clicklistener = this@CameraActivity
        initWithExtras()
        requestCameraPermission()
    }

    override fun onGranted(granted: MutableList<String>) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.cameraView.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(lensFacing)
                .build()

            try {
                cameraProvider.unbindAll()

                camera = cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )
            } catch (exc: Exception) {
                ToastUtils.showLong("Unable to connect to camera")
            }

        }, ContextCompat.getMainExecutor(this))
    }

    override fun onDenied(deniedForever: MutableList<String>, denied: MutableList<String>) {
        if (deniedForever.isNotEmpty()) {
            CameraDisabledSheet.show(supportFragmentManager).apply {
                setOnDismissCallback {
                    finish()
                }
            }
            return
        }
        ToastUtils.showLong(getString(R.string.label_camera_permission_permanently_disabled_msg))
        requestCameraPermission()
    }

    private fun requestCameraPermission() {
        PermissionUtils.permission(PermissionConstants.CAMERA).callback(this)
            .request()
    }

    private fun initWithExtras() = withExtras(CameraActivitySpec) {
        binding.ivOverlay.isGone = overlayRes == null
        overlayRes?.let {
            binding.ivOverlay.load(overlayRes)
        }
        titleRes?.let {
            binding.tvTitle.text = getString(it)
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnBack -> onBackPressedDispatcher.onBackPressed()
            binding.btnFlip -> onFlipCamera()
            binding.btnTakePhoto -> onTakePhoto()
        }
    }

    private fun onTakePhoto() {
        val imageCapture = imageCapture ?: return

        val photoFile = File.createTempFile(
            "image_",
            "${System.currentTimeMillis()}.jpg"
        )

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions, cameraExecutor, object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    ToastUtils.showLong("Error capturing image")
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    val resultIntent = Intent().apply {
                        data = savedUri
                    }
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }
            })
    }

    private fun onFlipCamera() {
        lensFacing = if (lensFacing == CameraSelector.LENS_FACING_BACK) {
            CameraSelector.LENS_FACING_FRONT
        } else {
            CameraSelector.LENS_FACING_BACK
        }
        onGranted(mutableListOf())
    }
}