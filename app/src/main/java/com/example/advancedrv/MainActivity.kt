package com.example.advancedrv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.advancedrv.adapter.MyAdvancedAdapter
import com.example.advancedrv.adapter.ITEM_VIEW_TYPE_HEADER
import com.example.advancedrv.databinding.ActivityMainBinding
import com.example.advancedrv.model.MyDataModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. Menggunakan DataBindingUtil untuk memuat layout XML activity_main
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = MyAdvancedAdapter()

        // 2. Konfigurasi Grid dengan 3 Kolom sesuai perintah tugas
        val manager = GridLayoutManager(this, 3)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    ITEM_VIEW_TYPE_HEADER -> 3 // Header mengambil penuh 3 kolom (1 baris penuh)
                    else -> 1                  // Item biasa mengambil 1 kolom
                }
            }
        }

        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter

        // 3. Data Dummy awal untuk pengujian
        val dummyData = listOf(
            MyDataModel(1, "Merah", 10, "#FF0000"),
            MyDataModel(2, "Hijau", 25, "#00FF00"),
            MyDataModel(3, "Biru", 45, "#0000FF"),
            MyDataModel(4, "Kuning", 12, "#FFFF00"),
            MyDataModel(5, "Ungu", 88, "#800080"),
            MyDataModel(6, "Pink", 30, "#FFC0CB")
        )

        // 4. Kirim data ke ListAdapter
        adapter.addHeaderAndSubmitList(dummyData)
    }
}