package atask.sanjaya.ui.presentation.sheet

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import atask.sanjaya.ui.databinding.LayoutCameraDisabledSheetBinding
import atask.sanjaya.ui.utils.showing
import com.blankj.utilcode.util.PermissionUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CameraDisabledSheet : BottomSheetDialogFragment(), View.OnClickListener {
    private lateinit var binding: LayoutCameraDisabledSheetBinding
    private var onCloseUnit: (CameraDisabledSheet.() -> Unit)? = null
    private var onDismissUnit: (CameraDisabledSheet.() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutCameraDisabledSheetBinding.inflate(layoutInflater, container, false)
        initUi()
        return binding.root
    }

    private fun initUi() = binding.apply {
        clicklistener = this@CameraDisabledSheet
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnClose -> {
                onCloseUnit?.invoke(this)
                dismissAllowingStateLoss()
            }
            binding.btnOpenSettings -> {
                PermissionUtils.launchAppDetailsSettings()
                dismissAllowingStateLoss()
            }
        }
    }

    fun setOnDismissCallback(action: CameraDisabledSheet.() -> Unit) {
        this.onDismissUnit = action
    }

    fun setOnCloseAction(action: CameraDisabledSheet.() -> Unit) {
        this.onCloseUnit = action
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissUnit?.invoke(this)
    }

    companion object {
        fun show(
            fragmentManager: FragmentManager
        ) = CameraDisabledSheet().showing(fragmentManager)
    }
}