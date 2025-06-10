package com.example.mobilegame

import java.util.LinkedList

class GameEngine {
    private var isRunning: Boolean = false
    private var gameState: GameState = GameState.INITIAL

    // Çanta (Yığın)
    private val inventory = LinkedList<String>() // Çanta (Yığın)
    private val inventoryCapacity = 10 // Maksimum kapasite
    private val villageQueue = LinkedList<Village>() // Köy kuyruğu

    // BST Kullanımı
    private val inventoryBST = BST<String>() // Çanta için BST
    private val villageBST = BST<String>() // Köy öğeleri için BST

    init {
        // Köyleri kuyruğa ekleme
        villageQueue.offer(Village("Köy 1", listOf("Kılıç", "Altın", "Harita")))
        villageQueue.offer(Village("Köy 2", listOf("Kalkan", "İksir", "Yiyecek")))
        villageQueue.offer(Village("Köy 3", listOf("Ok", "Yay", "Balta")))
        villageQueue.offer(Village("Köy 4", listOf("Zırh", "Mızrak", "Bıçak")))
        villageQueue.offer(Village("Köy 5", listOf("Taş", "Odun", "Elmas")))
        villageQueue.offer(Village("Köy 6", listOf("Kristal", "Büyü Kitabı", "Asa")))
        villageQueue.offer(Village("Köy 7", listOf("İksir", "Harita", "Altın")))
    }

    // Çantadan öğe kullanma
    fun useItem(itemName: String): Boolean {
        return if (inventory.contains(itemName)) {
            inventory.remove(itemName) // Öğeyi çantadan çıkar
            println("$itemName kullanıldı ve çantadan çıkarıldı.")
            true
        } else {
            println("$itemName çantada bulunamadı!")
            false
        }
    }

    // Köy kurtarma
    fun rescueVillage(): Boolean {
        val currentVillage = villageQueue.poll() // Kuyruktan sıradaki köyü çıkar
        return if (currentVillage != null) {
            println("${currentVillage.name} kurtarıldı!")

            // Son 3 köy için özel şartlar
            when (currentVillage.name) {
                "Köy 5" -> {
                    if (useItem("Balta") && useItem("İksir")) {
                        println("Köy 5 kurtarıldı! Gerekli öğeler kullanıldı.")
                    } else {
                        println("Köy 5 kurtarılamadı! Çantada gerekli öğeler yok.")
                        return false
                    }
                }
                "Köy 6" -> {
                    if (useItem("Kristal") && useItem("Büyü Kitabı")) {
                        println("Köy 6 kurtarıldı! Gerekli öğeler kullanıldı.")
                    } else {
                        println("Köy 6 kurtarılamadı! Çantada gerekli öğeler yok.")
                        return false
                    }
                }
                "Köy 7" -> {
                    if (useItem("Harita") && useItem("Altın")) {
                        println("Köy 7 kurtarıldı! Gerekli öğeler kullanıldı.")
                    } else {
                        println("Köy 7 kurtarılamadı! Çantada gerekli öğeler yok.")
                        return false
                    }
                }
            }

            // Diğer köyler için öğeleri çantaya ekleme
            currentVillage.items.forEach { item ->
                if (!pushItemToInventory(item)) {
                    println("Çanta dolu! $item eklenemedi.")
                }
            }
            true
        } else {
            println("Kurtarılacak köy kalmadı!")
            false
        }
    }

    // Çantayı görüntüleme
    fun displayInventory() {
        println("Çanta: ${inventory.joinToString(", ")}")
    }

    // Kuyruğu görüntüleme
    fun displayVillageQueue() {
        println("Kurtarılacak Köyler: ${villageQueue.joinToString { it.name }}")
    }

    fun displayRemainingVillages() {
        println("Kurtarılması gereken köyler: ${villageQueue.joinToString { it.name }}")
    }

    fun displayCurrentVillage() {
        println("Şu anki köy: ${villageQueue.peek()?.name ?: "Tüm köyler kurtarıldı!"}")
    }

    fun displayVillageItems(villageName: String) {
        val village = villageQueue.find { it.name == villageName }
        if (village != null) {
            println("Köydeki öğeler: ${village.items.joinToString(", ")}")
        } else {
            println("$villageName köyü bulunamadı!")
        }
    }

    fun checkGameEnd() {
        if (villageQueue.isEmpty()) {
            println("Tüm köyler kurtarıldı! Oyun sona erdi.")
        }
    }

    fun pushItemToInventory(item: String): Boolean {
        if (inventory.size < inventoryCapacity) {
            inventory.push(item)
            println("$item çantaya eklendi.")
            return true
        } else {
            println("Çanta dolu! Yeni öğe eklemek için mevcut öğelerden birini çıkarın.")
            return false
        }
    }

    fun popItemFromInventory(): String? {
        return if (inventory.isNotEmpty()) {
            val removedItem = inventory.pop()
            println("Öğe çıkarıldı: $removedItem")
            removedItem
        } else {
            println("Çanta boş! Çıkarılacak öğe yok.")
            null
        }
    }

    enum class GameState {
        INITIAL,
        RUNNING,
        PAUSED,
        STOPPED
    }
}