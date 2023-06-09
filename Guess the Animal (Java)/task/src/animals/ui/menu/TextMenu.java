package animals.ui.menu;

import animals.Main;
import animals.util.CLIUtil;
import animals.util.LocalizationUtil;

import java.util.HashMap;

public class TextMenu {
    private static TextMenu instance;
    private final String title = MenuConfig.getMenuTitle();
    private final HashMap<Integer, MenuItem> menuItems;
    private boolean isRunning;


    private TextMenu() {
        this.menuItems = new HashMap<>();
        MenuConfig.getMenuItems().forEach(this::addMenuItem);
        this.isRunning = true;
    }

    public static TextMenu getInstance() {
        if (instance == null)
            instance = new TextMenu();
        return instance;
    }

    private void addMenuItem(MenuItem menuItem) {
        menuItems.put(menuItems.size(), menuItem);
    }

    private void print() {
        System.out.println(title);
        for (int i = 1; i < menuItems.size(); i++)
            System.out.println(i + ". " + menuItems.get(i).getDescription());
        System.out.println(0 + ". " + menuItems.get(0).getDescription());
    }

    private void launchMenuItem(int index) {
        try {
            menuItems.get(index).launch();
        } catch (InterruptedException e) {
            Main.LOGGER.severe("Error running menu item: " + e.getMessage());
        }
    }

    public void launch() {
        while (isRunning) {
            print();
            int choice = getValidChoice();
            launchMenuItem(choice);
        }
    }

    private int getValidChoice() {
        int choice = CLIUtil.getInt();
        if (choice < 0 || choice > menuItems.size() - 1) {
            System.out.println(LocalizationUtil.getMessage("textmenu.invalid"));
            return getValidChoice();
        }
        return choice;
    }

    synchronized public void stop() {
        isRunning = false;
    }
}
