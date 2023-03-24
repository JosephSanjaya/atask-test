package atask.sanjaya.math.calculate.data.calculation.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CalculationData(

	@field:SerializedName("result")
	@Expose
	val result: List<String?>? = null,

	@field:SerializedName("error")
	@Expose
	val error: String? = null
)
