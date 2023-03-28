package animals.ui.menu;

public class MenuItem {
    private final String description;
    private final Runnable action;

    public MenuItem(String description, Runnable action) {
        this.description = description;
        this.action = action;
    }

    public void launch() throws InterruptedException {
        Thread thread = new Thread(action);
        thread.start();
        thread.join();
    }

    public String getDescription() {
        return description;
    }
}
