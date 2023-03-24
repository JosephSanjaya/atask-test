package atask.sanjaya.math.registry

import androidx.fragment.app.Fragment
import atask.sanjaya.math.dashboard.domain.content.DashboardContentGetter
import atask.sanjaya.math.location.presentation.DashboardCurrentSetFragment
import javax.inject.Inject

class DashboardContentGetterImpl @Inject constructor() : DashboardContentGetter {
    override fun getCurrentLocation(): Fragment {
        return DashboardCurrentSetFragment()
    }
}