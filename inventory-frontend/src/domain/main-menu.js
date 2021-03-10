class MainMenuItem {
    constructor(titleI18nKey, routeName, requireRoles) {
        this.titleI18nKey = titleI18nKey;
        this.routeName = routeName;
        this.requireRoles = requireRoles ?? [];
    }

    allowNavigate(availableRoles) {
        return this.requireRoles.length == 0 || this.requireRoles.filter(r => availableRoles != null && availableRoles.includes(r)).length > 0;
    }
}

const mainMenu = {
    items: [
        new MainMenuItem('dashboard', 'Dashboard'),
        new MainMenuItem('createInventoryForm', 'CreateInventoryForm'),
        new MainMenuItem('inventoryFormManagement', 'InventoryFormManagement'),
        new MainMenuItem('takeInventory', 'TakeInventory'),
    ]
}

export default mainMenu;