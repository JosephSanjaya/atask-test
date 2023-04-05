package atask.sanjaya.ui.data.locale.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * Designed and developed by Joseph Sanjaya, S.T., M.Kom. on 9/7/2022
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */
data class LocalePageInfoData(
    @field:SerializedName("isLastPage")
    @Expose
    val isLastPage: Boolean? = null,
    @field:SerializedName("pageSize")
    @Expose val pageSize: Int? = null,
    @field:SerializedName("totalRows")
    @Expose val totalRows: Int? = null,
    @field:SerializedName("page")
    @Expose val page: Int? = null,
    @field:SerializedName("isFirstPage")
    @Expose val isFirstPage: Boolean? = null
)
