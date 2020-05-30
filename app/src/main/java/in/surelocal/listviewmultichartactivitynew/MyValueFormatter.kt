package `in`.surelocal.listviewmultichartactivitynew

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat

class MyValueFormatter (val suffix: String) : ValueFormatter(){

    private var mFormat: DecimalFormat = DecimalFormat("###,###,###,##0.0")
//    private var suffix: String? = null
//    constructor()
//    constructor(suffix: String) {
//        mFormat = DecimalFormat("###,###,###,##0.0")
//        this.suffix = suffix
//    }

    override fun getFormattedValue(value: Float): String {
        return mFormat.format(value) + suffix
    }

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        if (axis is XAxis) {
            return mFormat.format(value)
        } else if (value > 0) {
            return mFormat.format(value) + suffix
        } else {
            return mFormat.format(value)
        }
    }

}