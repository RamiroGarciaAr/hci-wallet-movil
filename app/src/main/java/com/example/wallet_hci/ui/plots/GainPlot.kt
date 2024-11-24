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
            
            Text("Rendimiento", fontWeight = FontWeight.SemiBold) 
            ComposableLineChart()
        }
    }
}

@Composable
fun ComposableLineChart() {

    val entries = ArrayList<Entry>();
    entries.add(Entry(0f, 2213.22f))
    entries.add(Entry(1f, 1521.13f))
    entries.add(Entry(2f, 1521.13f))
    entries.add(Entry(3f, 3521.13f))
    entries.add(Entry(4f, 4521.13f))
    entries.add(Entry(5f, 1521.13f))
    entries.add(Entry(6f, 5521.13f))

    val dataSet = LineDataSet(entries, "")
    val weekDays = stringResource(id = R.string.weekDaysShort).split(",")

    val formatter = object : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return weekDays.getOrElse(value.toInt()) { "" }
        }
    }

    val colors = intArrayOf(R.color.primary_500, R.color.primary_300)

    val dataSets = listOf(dataSet)
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

            dataSet.setColors(colors, context)
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