package atask.sanjaya.math.dashboard.domain.content

import androidx.fragment.app.Fragment

interface DashboardContentGetter {
    fun getCurrentLocation(): Fragment
}