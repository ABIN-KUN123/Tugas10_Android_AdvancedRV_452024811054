package com.example.advancedrv.utils

import android.graphics.Color
import android.widget.TextView
import android.view.View
import androidx.databinding.BindingAdapter
import com.example.advancedrv.model.MyDataModel

@BindingAdapter("itemTitleFormatted")
fun TextView.setItemTitleFormatted(item: MyDataModel?) {
    item?.let {
        text = "ID: ${item.itemId} - ${item.title}"
    }
}

@BindingAdapter("dynamicBackgroundColor")
fun View.setDynamicBackgroundColor(colorHex: String?) {
    colorHex?.let {
        try {
            setBackgroundColor(Color.parseColor(it))
        } catch (e: IllegalArgumentException) {
            setBackgroundColor(Color.GRAY) // Fallback jika format salah
        }
    }
}