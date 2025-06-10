package com.example.mobilegame

import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testPushItemToInventory() {
        val gameEngine = GameEngine()
        val result = gameEngine.pushItemToInventory("Kılıç")
        assertEquals(true, result)
    }

    @Test
    fun testPopItemFromInventory() {
        val gameEngine = GameEngine()
        gameEngine.pushItemToInventory("Kılıç")
        val removedItem = gameEngine.popItemFromInventory()
        assertEquals("Kılıç", removedItem)
    }

    @Test
    fun testRescueVillage() {
        val gameEngine = GameEngine()
        val result = gameEngine.rescueVillage()
        assertEquals(true, result)
    }

    @Test
    fun testUseItem() {
        val gameEngine = GameEngine()
        gameEngine.pushItemToInventory("Kılıç")
        val result = gameEngine.useItem("Kılıç")
        assertEquals(true, result)
    }

    @Test
    fun testSearchItemInInventoryBST() {
        val gameEngine = GameEngine()
        gameEngine.pushItemToInventory("Kılıç")
        gameEngine.addItemToInventoryBST("Kılıç")
        val result = gameEngine.searchItemInInventoryBST("Kılıç")
        assertEquals(true, result)
    }
}