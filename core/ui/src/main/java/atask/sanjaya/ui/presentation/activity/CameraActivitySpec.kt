package atask.sanjaya.ui.presentation.activity

import splitties.bundle.BundleSpec
import splitties.bundle.bundleOrNull

object CameraActivitySpec: BundleSpec() {
    var overlayRes: Int? by bundleOrNull()
    var titleRes: Int? by bundleOrNull()
}