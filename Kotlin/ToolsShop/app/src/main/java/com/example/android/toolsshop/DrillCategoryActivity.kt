package com.example.android.toolsshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.android.toolsshop.databinding.ActivityCategoryDrillBinding

class DrillCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryDrillBinding

    private lateinit var drills: MutableList<Drill>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_drill)

        binding = ActivityCategoryDrillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.hide()

        drills = arrayListOf()
        drills.add(
            Drill(
                getString(R.string.drill_interskol_title),
                getString(R.string.drill_interskol_info),
                R.drawable.interskol
            )
        )
        drills.add(
            Drill(
                getString(R.string.drill_makita_title),
                getString(R.string.drill_makita_info),
                R.drawable.makita
            )
        )
        drills.add(
            Drill(
                getString(R.string.drill_dewalt_title),
                getString(R.string.drill_dewalt_info),
                R.drawable.dewalt
            )
        )
        /**
         * ArrayAdapter<> - * Возвращает представление для каждого объекта в коллекции объектов данных
         *ArrayAdapter(контекст, макет (внешний вид),используемый массив)
         * далее передаем адаптер в ListView
         */
        val adapter: ArrayAdapter<Drill> =
            ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1,drills)
        binding.listViewDrill.adapter = adapter

        binding.listViewDrill.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val drill = drills[position]
            intent = Intent(applicationContext,DrillDetailActivity::class.java)
            intent.putExtra("title",drill.title)
            intent.putExtra("info",drill.info)
            intent.putExtra("imageResourceId",drill.imageResourceId)
            startActivity(intent)
        }



    }
}