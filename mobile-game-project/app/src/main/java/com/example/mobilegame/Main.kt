package com.example.mobilegame

fun main() {
    val gameEngine = GameEngine()

    // Köy kuyruğunu görüntüleme
    gameEngine.displayVillageQueue()

    // Köy kurtarma
    while (gameEngine.rescueVillage()) {
        gameEngine.displayInventory()
    }

    // Oyun sonu kontrolü
    gameEngine.checkGameEnd()

    // Çantayı sıralı şekilde görüntüleme
    gameEngine.displaySortedInventory()

    // Köy öğelerini sıralı şekilde görüntüleme
    gameEngine.displaySortedVillageItems()
}