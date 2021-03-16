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
        new MainMenuItem('dashboard', 'Dashboard', ['DEPT_USER', 'DEPT_ADMIN']),
        new MainMenuItem('createInventoryForm', 'CreateInventoryForm', ['DEPT_ADMIN']),
        new MainMenuItem('inventoryFormManagement', 'InventoryFormManagement', ['DEPT_ADMIN']),
        new MainMenuItem('takeInventory', 'TakeInventory', ['DEPT_USER', 'DEPT_ADMIN']),
        new MainMenuItem('logs', 'Logs', ['SYS_ADMIN']),
    ]
}

export default mainMenu;