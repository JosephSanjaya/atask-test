package atask.sanjaya.ui.data.locale.model

import atask.sanjaya.ui.data.locale.model.LocaleData
import atask.sanjaya.ui.data.locale.model.LocalePageInfoData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * Designed and developed by Joseph Sanjaya, S.T., M.Kom. on 9/7/2022
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */
data class LocaleResponseData(
    @field:SerializedName("pageInfo")
    @Expose val pageInfo: LocalePageInfoData? = null,
    @field:SerializedName("list")
    @Expose val list: List<LocaleData?>? = null
)
