package jp.sb.naoto.ichihashi.piesample

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //グラフのタイトルとして日付を表示
        val date = Date()
        textView.text = DateFormat.format("yyyy/MM/dd",date)

        setupPieChartView()

    }

    fun setupPieChartView() {
        var mPie:PieChart
        mPie = findViewById(R.id.pie)

        //入力データ<PieEntryクラス>の準備
        //.add(PieEntry(value,label)のvalue,labelにテーブルの値を代入して追加
        val entry = ArrayList<PieEntry>()
        val value = arrayListOf(20f, 20f, 30f, 40f)
        val label = arrayListOf("A", "B", "C", "D")
        for (i in value.indices) {
            entry.add(PieEntry(value[i], label[i]))
        }

        //DataSetの凡例の名前は空文字にして非表示
        val set = PieDataSet(entry, "")
        //DataSetの色を設定
        set.colors = ColorTemplate.MATERIAL_COLORS.toList()

        val data = PieData(set)

        //データの書式
        data.setValueTextSize(25f)
        data.setValueTextColor(Color.WHITE)

        //Viewにdataを設定
        mPie.data = data

        //ViewのUI設定
        mPie.setUsePercentValues(true)  //データを百分率で表示
        mPie.setEntryLabelTextSize(25f) //グラフ中の凡例文字の大きさ
        mPie.centerText = "PieChart\n(%)"
        mPie.setCenterTextSize(25f)
        mPie.holeRadius = 40f   //中央円部の直径の比率
        mPie.setHoleColor(Color.WHITE)
        mPie.isRotationEnabled = false  //グラフの回転操作を禁止
        mPie.legend.isEnabled = false   //凡例を非表示
        mPie.description.isEnabled = false //グラフの説明を非表示

        button.setOnClickListener {
            when(mPie.isUsePercentValuesEnabled){
                true -> {
                    //DataSetの百分率換算前の数字/単位で表示
                    mPie.setUsePercentValues(false)
                    mPie.centerText = "PieChart\n(min)"
                    mPie.notifyDataSetChanged()
                    mPie.invalidate()
                }
                else ->{
                    mPie.setUsePercentValues(true)
                    mPie.centerText = "PieChart\n(%)"
                    mPie.notifyDataSetChanged()
                    mPie.invalidate()
                }
            }
        }

        //update pie chart
        mPie.notifyDataSetChanged()
        mPie.invalidate()

    }
}