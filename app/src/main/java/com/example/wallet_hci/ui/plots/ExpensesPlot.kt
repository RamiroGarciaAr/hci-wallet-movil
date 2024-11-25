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
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.animation.Easing
import com.google.firebase.encoders.EncodingException
import android.content.Context
import android.graphics.Color
import android.service.autofill.Dataset
import kotlin.collections.ArrayList


@Composable
fun ExpensesPlot(
    modifier: Modifier = Modifier
) {   
    CardWrapper(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Tus gastos", fontWeight = FontWeight.SemiBold)
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "Arrow",
                tint = colorResource(R.color.primary_500),
                modifier = Modifier.size(24.dp)
            )   
        }
        ComposablePieChart()
    }
}

@Composable
fun ComposablePieChart() {

    val entries = ArrayList<PieEntry>();
    entries.add(PieEntry(2213.22f, "Gastos"))
    entries.add(PieEntry(1521.13f, "Ingresos"))


    
    val dataSet = PieDataSet(entries, "")
    val colors = intArrayOf(R.color.primary_500, R.color.primary_300) 
    val pieData = PieData(dataSet)
    
    AndroidView(factory = {
        context -> 
        val pieChart = PieChart(context)
        pieChart.apply { 
            setUsePercentValues(false)
            description.isEnabled = false
            setExtraOffsets(5f, 5f, 5f, 5f)
            setDrawEntryLabels(false)
            setDrawHoleEnabled(true)
            
            dataSet.setColors(colors, context)
            data = pieData

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
fun ExpensesPlotPreview() {
    WallethciTheme {
        ExpensesPlot()
    }
}