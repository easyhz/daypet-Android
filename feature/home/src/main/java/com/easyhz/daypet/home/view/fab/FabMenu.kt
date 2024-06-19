package com.easyhz.daypet.home.view.fab

import com.easyhz.daypet.design_system.util.fab.FabItemType

object MainMenu: FabItemType

sealed class SubMenu: FabItemType {
    data object Memory: SubMenu()
    data object Todo: SubMenu()
}

