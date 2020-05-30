@file:Suppress("UseExpressionBody")

package `in`.surelocal.listviewmultichartactivitynew

import android.content.Context
import android.graphics.Color
import android.graphics.Path
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

//    private lateinit var chart1 : BarChart
//    private lateinit var chart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linechart()
        barchartActivity()
        piechartActivity()
//           setData(10, 50f)
    }


    private fun piechartActivity() {
        //chart2=findViewById(R.id.chart)
        PieChart.setUsePercentValues(true)
        PieChart.description.isEnabled = false

        PieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        PieChart.dragDecelerationFrictionCoef = 0.95f

        PieChart.setCenterTextTypeface(tfLightpie)
        PieChart.centerText = generateCenterSpannableText()

        PieChart.isDrawHoleEnabled = true
        PieChart.setHoleColor(Color.WHITE)

        PieChart.setTransparentCircleColor(Color.WHITE)
        PieChart.setTransparentCircleAlpha(110)

        PieChart.holeRadius = 58f
        PieChart.transparentCircleRadius = 61f
        PieChart.setDrawCenterText(true)

        PieChart.rotationAngle = 0f
        PieChart.isRotationEnabled = true
        PieChart.isHighlightPerTapEnabled = true

        //chart.setOnChartValueSelectedListener(this)
        PieChart.animateY(1400, Easing.EaseInOutQuad)
        // chart.spin(2000, 0, 360);
        val l = PieChart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yEntrySpace = 0f
        l.yOffset = 0f


        // entry label styling
        PieChart.setEntryLabelColor(Color.WHITE)
        PieChart.setEntryLabelTypeface(tfRegularpie)
        PieChart.setEntryLabelTextSize(12f)


        val entries = ArrayList<PieEntry>()

        for (i in 0 until 8) {
            entries.add(
                PieEntry(
                    (Math.random() * 50f + 50f / 5f).toFloat(),
                    parties[i % parties.size],
                    resources.getDrawable(R.drawable.star)
                )
            )
        }


        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0F, 40F)
        dataSet.selectionShift = 5f


        val colors = ArrayList<Int>()
        for (c: Int in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c: Int in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c: Int in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c: Int in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c: Int in ColorTemplate.PASTEL_COLORS) colors.add(c)
        colors.add(ColorTemplate.getHoloBlue())
        dataSet.setColors(colors)

        // val data=PieData(dataSet)
        //dataSet.setSelectionShift(0f);
        var data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter(PieChart))
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        data.setValueTypeface(tfLight)
        PieChart.data = data
        PieChart.highlightValues(null)
        PieChart.invalidate()
    }

    private fun generateCenterSpannableText(): CharSequence? {
        val s = SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda")
        s.setSpan(RelativeSizeSpan(1.7f), 0, 14, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), 14, s.length - 15, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 14, s.length - 15, 0)
        s.setSpan(RelativeSizeSpan(.8f), 14, s.length - 15, 0)
        s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 14, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 14, s.length, 0)
        return s
    }

    fun onValueSelected(
        e: Entry?,
        h: Highlight
    ) {
        if (e == null) return
        Log.i(
            "VAL SELECTED",
            "Value: " + e.y + ", index: " + h.x
                    + ", DataSet index: " + h.dataSetIndex
        )
    }

    fun onNothingSelected() {
        Log.i("PieChart", "nothing selected")
    }

    private fun linechart() {
        val yValue = ArrayList<Entry>()
        yValue.add(Entry(0f, 60f))
        yValue.add(Entry(1f, 50f))
        yValue.add(Entry(2f, 60f))
        yValue.add(Entry(3f, 300f))
        yValue.add(Entry(4f, 700f))
        yValue.add(Entry(5f, 800f))
        val set = LineDataSet(yValue, "DATA SET 1")

        set.fillColor = Color.WHITE
        set.fillAlpha = 225
        set.lineWidth = 3f
//        set.valueTextSize=10f
//        set.valueTextColor=Color.GREEN
        set.valueTextSize = 10f
        set.valueTextColor = Color.GREEN

        val yValues1 = java.util.ArrayList<Entry>()
        yValues1.add(Entry(0f, 160f))
        yValues1.add(Entry(1f, 140f))
        yValues1.add(Entry(2f, 160f))
        yValues1.add(Entry(3f, 200f))
        yValues1.add(Entry(4f, 510f))
        yValues1.add(Entry(5f, 690f))
        yValues1.add(Entry(6f, 780f))
        val set2 = LineDataSet(yValues1, "DATE SET 1")

        set2.fillAlpha = 110
        set2.color = Color.BLUE
        set2.lineWidth = 3f
        set2.valueTextSize = 10f
        set2.valueTextColor = Color.GREEN

        val dataSets = java.util.ArrayList<ILineDataSet>()
        dataSets.add(set)
        dataSets.add(set2)
        val data = LineData(dataSets)
        linechart.setBackgroundColor(Color.WHITE)
        linechart.data = data
    }

    //  LINE CHAT END................
    private fun barchartActivity() {

        //  chart=findViewById(R.id.chart1)
        //  chart.setOnChartValueSelectedListener(this)
        barChart.setDrawBarShadow(false)
        barChart.description.isEnabled = false
        barChart.setMaxVisibleValueCount(60)
        barChart.setPinchZoom(false)
        barChart.setDrawGridBackground(false)

        val xAxisFormatter = DayAxisValueFormatter(barChart)
        val xAxis = barChart.xAxis
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
        xAxis.typeface = tfLight
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.labelCount = 7
        xAxis.setValueFormatter(xAxisFormatter)

        val custom: ValueFormatter = MyValueFormatter("$")

        val leftAxis = barChart.axisLeft
        leftAxis.typeface = tfLight
        leftAxis.setLabelCount(8, false)
        leftAxis.valueFormatter = custom
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.spaceTop = 15f
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        val rightAxis = barChart.axisRight

        rightAxis.setDrawGridLines(false)
        rightAxis.typeface = tfLight
        rightAxis.setLabelCount(8, false)
        rightAxis.valueFormatter = custom
        rightAxis.spaceTop = 15f
        rightAxis.axisMinimum = 0f

        val l = barChart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.form = Legend.LegendForm.SQUARE
        l.formSize = 9f
        l.textSize = 11f
        l.xEntrySpace = 4f
        setData(12, 50f)
    }

    private fun setData(count: Int, range: Float) {
        val start = 1f
        val values = java.util.ArrayList<BarEntry>()
        var i = start.toInt()
        while (i < start + count) {
            val `val` = (Math.random() * (range + 1)).toFloat()
            if (Math.random() * 100 < 25) {
                values.add(BarEntry(i.toFloat(), `val`, resources.getDrawable(R.drawable.star)))
            } else {
                values.add(BarEntry(i.toFloat(), `val`))
            }
            i++
        }
        val set1: BarDataSet
        if (barChart.data != null &&
            barChart.data.dataSetCount > 0
        ) {
            set1 = barChart.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values
            barChart.data.notifyDataChanged()
            barChart.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values, "The year 2017")
            set1.setDrawIcons(false)

            val dataSets = java.util.ArrayList<IBarDataSet>()
            dataSets.add(set1)
            val data = BarData(dataSets)
            data.setValueTextSize(10f)
            data.setValueTypeface(tfLight)
            data.barWidth = 0.9f
            barChart.data = data
        }

    }
}




//    }//MAIN CLASS END
//private operator fun <float> float.plus(fl: Float) {
//
//}
//
//private fun Int.toFloat(star: Int) {
//
//}

//
//private fun BarDataSet.setFills(gradientFills: java.util.ArrayList<Path.FillType>) {
//
//}

//private fun barchartActivity() {
//
//         //chart.setOnChartValueSelectedListener(this)
//        barChart.setDrawBarShadow(false)
//        barChart.description.isEnabled=false
//        barChart.setMaxVisibleValueCount(60)
//        barChart.setPinchZoom(false)
//        barChart.setDrawGridBackground(false)
//        val xAxisFormatter=DayAxisValueFormatter(barChart)
//        val xAxis = barChart.xAxis
//        xAxis.position=XAxis.XAxisPosition.BOTTOM
//        xAxis.typeface= tfLight
//        xAxis.setDrawGridLines(false)
//        xAxis.granularity=1f
//        xAxis.labelCount=7
//        xAxis.valueFormatter=xAxisFormatter
//
//        val custom: ValueFormatter = MyValueFormatter("$")
//
//        val leftAxis :YAxis = barChart.axisLeft
//
//        leftAxis.typeface = tfLight
//        leftAxis.setLabelCount(8, false)
//        leftAxis.valueFormatter = custom
//        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
//        leftAxis.spaceTop = 15f
//        leftAxis.axisMinimum = 0f
//
//        val rightAxis : YAxis=barChart.axisRight
//
//        rightAxis.setDrawGridLines(false)
//        rightAxis.typeface = tfLight
//        rightAxis.setLabelCount(8, false)
//        rightAxis.valueFormatter = custom
//        rightAxis.spaceTop = 15f
//        rightAxis.axisMinimum = 0f
//
//        val l:Legend=barChart.legend
//        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
//        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
//        l.orientation = Legend.LegendOrientation.HORIZONTAL
//        l.setDrawInside(false)
//        l.form = Legend.LegendForm.SQUARE
//        l.formSize = 9f
//        l.textSize = 11f
//        l.xEntrySpace = 4f
//        setData(12, 50f)









//   BAR CHART ACTIVITY STARTY.......
//    private fun setData(count: Int, range: Float) {
//        val start = 1f
//        val values = java.util.ArrayList<BarEntry>()
//        var i = start.toInt()
//        while (i < start + count) {
//            val `val` = (Math.random() * (range + 1)).toFloat()
//            if (Math.random() * 100 < 25) {
//                values.add(BarEntry(i.toFloat(), `val`, resources.getDrawable(R.drawable.star)))
//            } else {
//                values.add(BarEntry(i.toFloat(), `val`))
//            }
//            i++
//        }
//        val set1: BarDataSet
//        if (barChart.data != null &&
//            barChart.data.dataSetCount > 0
//        ) {
//            set1 = barChart.data.getDataSetByIndex(0) as BarDataSet
//            set1.values = values
//            barChart.data.notifyDataChanged()
//            barChart.notifyDataSetChanged()
//        } else {
//            set1 = BarDataSet(values, "The year 2017")
//            set1.setDrawIcons(false)
////
//            val dataSets = java.util.ArrayList<IBarDataSet>()
//            dataSets.add(set1)
//            val data = BarData(dataSets)
//            data.setValueTextSize(10f)
//            data.setValueTypeface(tfLight)
//            data.barWidth = 0.9f
//            //chart.data = data
////        }
//    }
//