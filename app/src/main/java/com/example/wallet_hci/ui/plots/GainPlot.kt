package com.example.wallet_hci.ui.plots

import androidx.compose.foundation.layout.* 
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.text.font.FontWeight
import androidx.annotation.ColorInt
import com.example.wallet_hci.R
import com.example.wallet_hci.app.screens.home.ui.CardWrapper
import com.example.wallet_hci.ui.theme.WallethciTheme

// Graphs
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.DataSet
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.formatter.ValueFormatter


@Composable
fun GainPlot(
    modifier: Modifier = Modifier
) {
    CardWrapper(modifier = modifier) {
        Column (
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ){
            
            Text(stringResource(R.string.revenue), fontWeight = FontWeight.SemiBold) 
            ComposableLineChart()
        }
    }
}

@Composable
fun ComposableLineChart() {

    val entriesWithoutGain = ArrayList<Entry>();
    entriesWithoutGain.add(Entry(0f, 2232.22f))
    entriesWithoutGain.add(Entry(1f, 2532.13f)) 
    entriesWithoutGain.add(Entry(2f, 2532.13f))
    entriesWithoutGain.add(Entry(3f, 3532.13f))
    entriesWithoutGain.add(Entry(4f, 4532.13f))
    entriesWithoutGain.add(Entry(5f, 1532.13f))
    val dataSetWithoutGain = LineDataSet(entriesWithoutGain, "Sin ganancia")

    val entriesWithGain = ArrayList<Entry>();
    for (i in 0 until entriesWithoutGain.size) {
        entriesWithGain.add(Entry(i.toFloat(), entriesWithoutGain[i].y * 1.2f))
    }
    val dataSetWithGain = LineDataSet(entriesWithGain, "Con Ganancia")

    val weekDays = stringResource(id = R.string.weekDaysShort).split(",")

    val formatter = object : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return weekDays.getOrElse(value.toInt()) { "" }
        }
    }

    val colors = intArrayOf(R.color.primary_500, R.color.primary_300)

    val dataSets = listOf(dataSetWithGain, dataSetWithoutGain)
    val lineData = LineData(dataSets)

    AndroidView(factory = {
        context -> 
        val pieChart = LineChart(context)
        pieChart.apply { 
            description.isEnabled = false
            setExtraOffsets(5f, 5f, 5f, 5f)
            setBorderWidth(0f)
            setData(lineData)
            xAxis.setValueFormatter(formatter)
            xAxis.position = XAxis.XAxisPosition.BOTTOM

            dataSetWithGain.setColors(intArrayOf(R.color.primary_500), context)
            dataSetWithoutGain.setColors(intArrayOf(R.color.primary_200), context)
            legend.isEnabled = true
            legend.textSize = 14f
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.form = Legend.LegendForm.CIRCLE

            animateX(1000, Easing.EaseInOutQuart)
            animateY(1000, Easing.EaseInOutQuart)
        }
    },
        modifier = Modifier.fillMaxSize() 
    ) 
}


@Preview(showBackground = true)
@Composable
fun GainPlotPreview() {
    WallethciTheme {
        GainPlot()
    }
}