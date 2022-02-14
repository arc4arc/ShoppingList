package ru.rucobe.shoppinglist.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val enabled: Boolean,
    var id: Int = UNDEFINED_ID //значение по умолчанию
){ //жесткозакодированные числа выносятся в константы а не используются напрямую
    companion object{
        const val UNDEFINED_ID = -1
    }
}
