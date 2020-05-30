package `in`.surelocal.listviewmultichartactivitynew

import android.content.Context
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.data.ChartData
import com.github.mikephil.charting.formatter.ValueFormatter

class DayAxisValueFormatter : ValueFormatter {

    private val mMonths = arrayOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )

    private var chart: BarLineChartBase<*>? = null

    constructor(chart: BarLineChartBase<*>) {
        this.chart = chart
    }

//    fun DayAxisValueFormatter(chart: BarLineChartBase<*>) {
//        this.chart = chart
//    }
}