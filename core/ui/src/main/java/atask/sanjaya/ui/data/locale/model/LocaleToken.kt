package atask.sanjaya.ui.data.locale.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LocaleToken(

	@field:SerializedName("token")
	@Expose
	val token: String? = null
)
