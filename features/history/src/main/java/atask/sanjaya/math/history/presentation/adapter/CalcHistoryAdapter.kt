package atask.sanjaya.math.history.presentation.adapter

import atask.sanjaya.math.history.R
import atask.sanjaya.math.history.data.models.HistoryData
import atask.sanjaya.math.history.databinding.LayoutRowHistoriesBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

class CalcHistoryAdapter :
    BaseQuickAdapter<HistoryData, BaseDataBindingHolder<LayoutRowHistoriesBinding>>(
        R.layout.layout_row_histories, mutableListOf()
    ) {
    init {
        animationEnable = true
        setAnimationWithDefault(AnimationType.SlideInBottom)
    }

    override fun convert(
        holder: BaseDataBindingHolder<LayoutRowHistoriesBinding>,
        item: HistoryData
    ) {
        holder.dataBinding?.apply {
            tvInput.text = item.expression
            tvResult.text = item.result
        }
    }
}