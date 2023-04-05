package atask.sanjaya.ui.data.locale.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LocaleTokenBody(

	@field:SerializedName("email")
	@Expose
	val email: String? = null,

	@field:SerializedName("password")
	@Expose
	val password: String? = null,
)
