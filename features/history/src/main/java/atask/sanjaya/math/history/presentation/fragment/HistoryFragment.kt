package atask.sanjaya.math.history.presentation.fragment

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import atask.sanjaya.math.history.R
import atask.sanjaya.math.history.databinding.FragmentHistoriesBinding
import atask.sanjaya.math.history.presentation.adapter.CalcHistoryAdapter
import atask.sanjaya.ui.presentation.activity.CameraActivity
import atask.sanjaya.ui.utils.repeatOnStarted
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.reword.Reword
import splitties.intents.intent
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_histories),
    SwipeRefreshLayout.OnRefreshListener, PermissionUtils.SingleCallback, View.OnClickListener {
    private val binding by viewBinding(FragmentHistoriesBinding::bind)
    private val viewModel: HistoryViewModel by viewModels()
    private val adapter by lazy { CalcHistoryAdapter() }

    @Inject
    lateinit var pref: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            btnChangeLanguage.text = AppLocale.currentLocale.language
            btnChangeLanguage.setOnClickListener(this@HistoryFragment)
            btnAddInput.setOnClickListener(this@HistoryFragment)
            rvContent.adapter = adapter
            refresh.setOnRefreshListener(this@HistoryFragment)
        }
        repeatOnStarted {
            viewModel.historyState.collect {
                binding.refresh.isRefreshing = false
                adapter.setNewInstance(it.toMutableList())
            }
        }
        repeatOnStarted {
            viewModel.defaultLoading.collect {
                binding.refresh.isRefreshing = it
            }
        }
        repeatOnStarted {
            viewModel.defaultError.collect {
                if (!it.message.isNullOrBlank()) ToastUtils.showLong(it.message)
            }
        }
        requestPermissions()
    }

    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            callback(true, mutableListOf(), mutableListOf(), mutableListOf())
        } else {
            PermissionUtils.permissionGroup(PermissionConstants.STORAGE)
                .callback(this).request()
        }
    }

    override fun onRefresh() {
        adapter.setNewInstance(mutableListOf())
        viewModel.fetch()
    }

    override fun callback(
        isAllGranted: Boolean,
        granted: MutableList<String>,
        deniedForever: MutableList<String>,
        denied: MutableList<String>
    ) {
        if (isAllGranted) {
            onRefresh()
            return
        }
        if (deniedForever.isNotEmpty()) {
            ToastUtils.showLong("Please add permission for storage!")
            PermissionUtils.launchAppDetailsSettings()
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnChangeLanguage -> toggleLanguage()
            binding.btnAddInput -> CameraActivity.intent { _, extraSpec ->
                extraSpec.titleRes = R.string.title_camera_get_expression
                extraSpec.overlayRes = R.drawable.ic_overlay_camera
            }
        }
    }

    private fun toggleLanguage() {
        val current = AppLocale.currentLocale
        AppLocale.desiredLocale = if (current == Locale.ENGLISH) {
            Locale("id", "ID")
        } else {
            Locale.ENGLISH
        }
        pref.edit().putString("current-locale", AppLocale.currentLocale.toLanguageTag()).apply()
        binding.btnChangeLanguage.text = AppLocale.currentLocale.language
        Reword.reword(binding.root)
    }
}