package ru.rucobe.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.rucobe.shoppinglist.data.ShopListRepositoryImpl
import ru.rucobe.shoppinglist.domain.DeleteShopItemUseCase
import ru.rucobe.shoppinglist.domain.EditShopItemUseCase
import ru.rucobe.shoppinglist.domain.GetShopListUseCase
import ru.rucobe.shoppinglist.domain.ShopItem

class MainViewModel: ViewModel(){ //для передачи контекста наследуемся от AndroidViewModel(), здесь не нужно

    //неправильно (без DI) задаем репозиторий напрямую
    private val repository = ShopListRepositoryImpl

    //создаем экземпляры классов требуемых UseCase
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    //самостоятельно создаем LiveData для shopList
    //невозможно создать LiveData, требуется наследник MutableLiveData
    val shopList = getShopListUseCase.getShopList()

    //взаимодействие ViewModel c View только через LiveData - ненапрямую!
    //т е запрещено:
    //fun getShopList():List<ShopItem>{
    //    return getShopListUseCase.getShopList()
    //}

    fun delateShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)

    }
    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}
