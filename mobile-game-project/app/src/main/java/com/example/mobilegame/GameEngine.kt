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

    // BST ile Çanta Yönetimi
    fun addItemToInventoryBST(item: String) {
        inventoryBST.insert(item)
        println("$item çantaya eklendi.")
    }

    fun searchItemInInventoryBST(item: String): Boolean {
        val found = inventoryBST.search(item)
        println("$item çantada ${if (found) "bulundu" else "bulunamadı"}.")
        return found
    }

    fun deleteItemFromInventoryBST(item: String) {
        inventoryBST.delete(item)
        println("$item çantadan silindi.")
    }

    fun displaySortedInventory() {
        val sortedItems = inventoryBST.inorderTraversal()
        println("Çantadaki öğeler (sıralı): ${sortedItems.joinToString(", ")}")
    }

    // BST ile Köy Öğeleri Yönetimi
    fun addItemToVillageBST(item: String) {
        villageBST.insert(item)
        println("$item köy öğelerine eklendi.")
    }

    fun searchItemInVillageBST(item: String): Boolean {
        val found = villageBST.search(item)
        println("$item köyde ${if (found) "bulundu" else "bulunamadı"}.")
        return found
    }

    fun deleteItemFromVillageBST(item: String) {
        villageBST.delete(item)
        println("$item köy öğelerinden silindi.")
    }

    fun displaySortedVillageItems() {
        val sortedItems = villageBST.inorderTraversal()
        println("Köy öğeleri (sıralı): ${sortedItems.joinToString(", ")}")
    }

    // Yığına öğe ekleme (push)
    fun pushItemToInventory(item: String): Boolean {
        if (inventory.size < inventoryCapacity) {
            inventory.push(item) // Öğeyi yığının en üstüne ekle
            println("Öğe eklendi: $item")
            return true
        } else {
            println("Çanta dolu! Yeni öğe eklemek için aşağıdaki seçeneklerden birini seçin:")
            println("1. Mevcut öğelerden birini çıkar.")
            println("2. Mevcut öğeleri sıralayıp görüntüle.")
            println("Seçiminizi yapın (1 veya 2):")

            val choice = readLine() // Kullanıcıdan seçim yapmasını iste
            when (choice) {
                "1" -> {
                    println("Mevcut öğeler: ${inventory.joinToString(", ")}")
                    println("Çıkarmak istediğiniz öğeyi seçin (isim girin):")

                    val selectedItem = readLine() // Kullanıcıdan öğe seçmesini iste
                    if (selectedItem != null && inventory.contains(selectedItem)) {
                        inventory.remove(selectedItem) // Seçilen öğeyi çantadan çıkar
                        println("$selectedItem çantadan çıkarıldı.")
                        inventory.push(item) // Yeni öğeyi ekle
                        println("Öğe eklendi: $item")
                        return true
                    } else {
                        println("Geçersiz seçim! $item eklenemedi.")
                        return false
                    }
                }
                "2" -> {
                    println("Mevcut öğeler (sıralı): ${inventory.sorted().joinToString(", ")}")
                    println("Çıkarmak istediğiniz öğeyi seçin (isim girin):")

                    val selectedItem = readLine() // Kullanıcıdan öğe seçmesini iste
                    if (selectedItem != null && inventory.contains(selectedItem)) {
                        inventory.remove(selectedItem) // Seçilen öğeyi çantadan çıkar
                        println("$selectedItem çantadan çıkarıldı.")
                        inventory.push(item) // Yeni öğeyi ekle
                        println("Öğe eklendi: $item")
                        return true
                    } else {
                        println("Geçersiz seçim! $item eklenemedi.")
                        return false
                    }
                }
                else -> {
                    println("Geçersiz seçim! $item eklenemedi.")
                    return false
                }
            }
        }
    }

    // Yığından öğe çıkarma (pop)
    fun popItemFromInventory(): String? {
        return if (inventory.isNotEmpty()) {
            val removedItem = inventory.pop() // Yığının en üstündeki öğeyi çıkar
            println("Öğe çıkarıldı: $removedItem")
            removedItem
        } else {
            println("Çanta boş! Çıkarılacak öğe yok.")
            null
        }
    }

    // Köy kurtarma
    fun rescueVillage(): Boolean {
        val currentVillage = villageQueue.poll() // Kuyruktan sıradaki köyü çıkar
        return if (currentVillage != null) {
            println("${currentVillage.name} kurtarıldı!")
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

    fun startGame() {
        isRunning = true
        gameState = GameState.RUNNING
        println("Oyun başladı!")
    }

    fun pauseGame() {
        isRunning = false
        gameState = GameState.PAUSED
        println("Oyun duraklatıldı!")
    }

    fun resumeGame() {
        isRunning = true
        gameState = GameState.RUNNING
        println("Oyun devam ediyor!")
    }

    fun stopGame() {
        isRunning = false
        gameState = GameState.STOPPED
        println("Oyun durduruldu!")
    }

    fun updateGame(deltaTime: Float) {
        if (isRunning) {
            println("Oyun güncelleniyor... DeltaTime: $deltaTime")
        }
    }

    fun render() {
        if (isRunning) {
            println("Oyun grafikleri çiziliyor...")
        }
    }

    enum class GameState {
        INITIAL,
        RUNNING,
        PAUSED,
        STOPPED
    }
}