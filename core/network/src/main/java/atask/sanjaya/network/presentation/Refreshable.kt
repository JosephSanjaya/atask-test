package atask.sanjaya.network.presentation

interface Refreshable {
    fun refresh(onRefreshDone: () -> Unit = {}) {
        onRefreshDone()
    }
}