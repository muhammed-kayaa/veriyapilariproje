package com.example.mobilegame

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    // Köyler ve envanter
    private val villages = mutableListOf(
        Village("Köy 1", mutableListOf("Kılıç", "Altın", "Harita"), isUnlocked = true),
        Village("Köy 2", mutableListOf("Kalkan", "İksir", "Yiyecek")),
        Village("Köy 3", mutableListOf("Ok", "Yay", "Balta")),
        Village("Köy 4", mutableListOf("Zırh", "Mızrak", "Bıçak")),
        Village("Köy 5", mutableListOf("Taş", "Odun", "Elmas")),
        Village("Köy 6", mutableListOf("Kristal", "Büyü Kitabı", "Asa")),
        Village("Köy 7", mutableListOf("İksir", "Harita", "Altın"))
    )

    private val inventory = mutableListOf<String>() // Oyuncunun envanteri
    private val removedItems = mutableListOf<String>() // Çıkarılan öğeler
    private val inventoryCapacity = 10 // Çanta kapasitesi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        val currentVillageTextView = findViewById<TextView>(R.id.tvCurrentVillage)
        val inventoryListView = findViewById<ListView>(R.id.lvInventory)
        val removedItemsTextView = findViewById<TextView>(R.id.tvRemovedItems)

        // Envanter Listesi için Adapter
        val inventoryAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, inventory)
        inventoryListView.adapter = inventoryAdapter

        // Envanter öğesine tıklama
        inventoryListView.setOnItemClickListener { _, _, position, _ ->
            val item = inventory[position]
            inventory.remove(item)
            removedItems.add(item)
            inventoryAdapter.notifyDataSetChanged()
            removedItemsTextView.text = "Çıkarılan Öğeler: ${removedItems.joinToString(", ")}"
            Toast.makeText(this, "$item çıkarıldı.", Toast.LENGTH_SHORT).show()
        }

        updateCurrentVillageText(currentVillageTextView, 0) // Başlangıçta Köy 1

        // Envanter TextView
        updateInventoryText(removedItemsTextView)

        // Köy 1 Butonu
        val btnVillage1 = findViewById<Button>(R.id.btnVillage1)
        btnVillage1.setOnClickListener {
            handleVillageClick(0, removedItemsTextView)
            updateCurrentVillageText(currentVillageTextView, 0)
        }

        // Köy 2 Butonu
        val btnVillage2 = findViewById<Button>(R.id.btnVillage2)
        btnVillage2.setOnClickListener {
            handleVillageClick(1, removedItemsTextView)
            updateCurrentVillageText(currentVillageTextView, 1)
        }

        // Diğer köy butonları için benzer işlemler yapılabilir
    }

    private fun handleVillageClick(index: Int, inventoryTextView: TextView) {
        val village = villages[index]
        if (village.isUnlocked) {
            Toast.makeText(this, "${village.name}'e girdiniz! 4 işlem sorusu çözerek öğeleri kazanın.", Toast.LENGTH_SHORT).show()

            // Öğe kazanma mantığı (örnek olarak sadece bir öğe ekleniyor)
            val earnedItem = village.items.firstOrNull()
            if (earnedItem != null) {
                Toast.makeText(this, "$earnedItem kazandınız! Envantere eklemek için butona basın.", Toast.LENGTH_SHORT).show()
                village.items.remove(earnedItem)

                // Envantere ekleme kontrolü
                if (inventory.size < inventoryCapacity) {
                    inventory.add(earnedItem)
                    updateInventoryText(inventoryTextView)
                } else {
                    Toast.makeText(this, "Çanta dolu! Öğe eklemek için mevcut öğelerden çıkarın.", Toast.LENGTH_SHORT).show()
                }
            }

            // Bir sonraki köyün açılması için kontrol
            if (index < villages.size - 1) {
                val requiredItems = villages[index].items
                if (inventory.containsAll(requiredItems)) {
                    unlockNextVillage(index + 1) // Bir sonraki köyü aç
                } else {
                    Toast.makeText(this, "${villages[index + 1].name}'in açılması için tüm öğeleri toplamalısınız!", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "${village.name} kilitli!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun unlockNextVillage(index: Int) {
        if (index < villages.size) {
            villages[index].isUnlocked = true
            Toast.makeText(this, "${villages[index].name} açıldı!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateInventoryText(inventoryTextView: TextView) {
        inventoryTextView.text = "Envanter: ${inventory.joinToString(", ")}"
    }

    private fun updateCurrentVillageText(currentVillageTextView: TextView, index: Int) {
        currentVillageTextView.text = "Şu an: ${villages[index].name}"
    }
}