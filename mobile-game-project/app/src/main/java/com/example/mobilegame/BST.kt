package com.example.mobilegame

class BSTNode<T : Comparable<T>>(val value: T) {
    var left: BSTNode<T>? = null
    var right: BSTNode<T>? = null
}

class BST<T : Comparable<T>> {
    private var root: BSTNode<T>? = null

    // Öğeyi BST'ye ekleme
    fun insert(value: T) {
        root = insertRec(root, value)
    }

    private fun insertRec(node: BSTNode<T>?, value: T): BSTNode<T> {
        if (node == null) {
            return BSTNode(value)
        }
        if (value < node.value) {
            node.left = insertRec(node.left, value)
        } else if (value > node.value) {
            node.right = insertRec(node.right, value)
        }
        return node
    }

    // BST'de öğe arama
    fun search(value: T): Boolean {
        return searchRec(root, value)
    }

    private fun searchRec(node: BSTNode<T>?, value: T): Boolean {
        if (node == null) {
            return false
        }
        return when {
            value == node.value -> true
            value < node.value -> searchRec(node.left, value)
            else -> searchRec(node.right, value)
        }
    }

    // BST'den öğe silme
    fun delete(value: T) {
        root = deleteRec(root, value)
    }

    private fun deleteRec(node: BSTNode<T>?, value: T): BSTNode<T>? {
        if (node == null) {
            return null
        }
        when {
            value < node.value -> node.left = deleteRec(node.left, value)
            value > node.value -> node.right = deleteRec(node.right, value)
            else -> {
                // Tek çocuk veya hiç çocuk yok
                if (node.left == null) return node.right
                if (node.right == null) return node.left

                // İki çocuk varsa, en küçük değeri bul
                node.value = minValue(node.right!!)
                node.right = deleteRec(node.right, node.value)
            }
        }
        return node
    }

    private fun minValue(node: BSTNode<T>): T {
        var current = node
        while (current.left != null) {
            current = current.left!!
        }
        return current.value
    }

    // BST'yi sıralı şekilde yazdırma
    fun inorderTraversal(): List<T> {
        val result = mutableListOf<T>()
        inorderRec(root, result)
        return result
    }

    private fun inorderRec(node: BSTNode<T>?, result: MutableList<T>) {
        if (node != null) {
            inorderRec(node.left, result)
            result.add(node.value)
            inorderRec(node.right, result)
        }
    }
}