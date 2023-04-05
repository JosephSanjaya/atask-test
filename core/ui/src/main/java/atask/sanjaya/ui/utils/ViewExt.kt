package atask.sanjaya.ui.utils

import android.widget.ViewFlipper
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

fun ViewFlipper.initAnim() {
    setInAnimation(context, android.R.anim.fade_in)
    setOutAnimation(context, android.R.anim.fade_out)
}

inline fun <reified T : BottomSheetDialogFragment> T.showing(
    fragmentManager: FragmentManager
): T {
    return (runCatching {
        fragmentManager.findFragmentByTag(T::class.simpleName) as T
    }.getOrNull() ?: this).also {
        if (!it.isVisible) it.show(fragmentManager, T::class.simpleName)
    }
}