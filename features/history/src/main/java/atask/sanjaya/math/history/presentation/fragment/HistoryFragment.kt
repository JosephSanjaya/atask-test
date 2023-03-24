package atask.sanjaya.math.history.presentation.fragment

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import atask.sanjaya.math.history.R
import atask.sanjaya.math.history.databinding.FragmentHistoriesBinding
import atask.sanjaya.math.history.presentation.adapter.CalcHistoryAdapter
import atask.sanjaya.ui.utils.repeatOnStarted
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_histories),
    SwipeRefreshLayout.OnRefreshListener, PermissionUtils.SingleCallback {
    private val binding by viewBinding(FragmentHistoriesBinding::bind)
    private val viewModel: HistoryViewModel by viewModels()
    private val adapter by lazy { CalcHistoryAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
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
}