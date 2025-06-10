package com.example.mobilegame

fun main() {
    val gameEngine = GameEngine()

    while (true) {
        println("\n--- Oyun Menüsü ---")
        println("1. Köyleri sırayla kurtar")
        println("2. Çantayı görüntüle")
        println("3. Öğeleri kullan/çıkar")
        println("4. Ağaçta arama yap")
        println("5. Oyun içi ilerleme kontrolü")
        println("6. Çıkış")
        print("Seçiminizi yapın: ")

        when (readLine()) {
            "1" -> {
                while (gameEngine.rescueVillage()) {
                    gameEngine.displayInventory()
                }
                gameEngine.checkGameEnd()
            }
            "2" -> gameEngine.displayInventory()
            "3" -> {
                print("Kullanmak istediğiniz öğeyi girin: ")
                val item = readLine()
                if (item != null) gameEngine.useItem(item)
            }
            "4" -> {
                print("Aramak istediğiniz öğeyi girin: ")
                val item = readLine()
                if (item != null) gameEngine.searchItemInInventoryBST(item)
            }
            "5" -> {
                println("\n--- Oyun İçi İlerleme Kontrolü ---")
                gameEngine.displayRemainingVillages()
                gameEngine.displayCurrentVillage()
                print("Hangi köydeki öğeleri görmek istersiniz? ")
                val villageName = readLine()
                if (villageName != null) gameEngine.displayVillageItems(villageName)
            }
            "6" -> {
                println("Oyun sona erdi. Çıkış yapılıyor...")
                break
            }
            else -> println("Geçersiz seçim! Lütfen tekrar deneyin.")
        }
    }
}