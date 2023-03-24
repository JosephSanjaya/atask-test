package atask.sanjaya.math.history.presentation.fragment

import atask.sanjaya.math.history.data.models.HistoryData
import atask.sanjaya.math.history.domain.HistoryUseCase
import atask.sanjaya.network.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val useCase: HistoryUseCase
) : BaseViewModel() {

    val historyState by state<List<HistoryData>>("history-fetch", listOf())

    fun fetch() = executeRoutine("history-fetch") {
        useCase.getHistoriesAsync().await()
    }
}