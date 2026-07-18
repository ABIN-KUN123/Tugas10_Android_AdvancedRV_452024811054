package com.example.advancedrv.model

// Model data konten utama
data class MyDataModel(
    val itemId: Long,
    val title: String,
    val value: Int,
    val colorHex: String
)

// Sealed class untuk mengelola Multiple View Types (Header vs Konten)
sealed class DataItem {
    abstract val id: Long

    object Header : DataItem() {
        override val id: Long = Long.MIN_VALUE
    }

    data class ContentItem(val itemData: MyDataModel) : DataItem() {
        override val id: Long = itemData.itemId
    }
}