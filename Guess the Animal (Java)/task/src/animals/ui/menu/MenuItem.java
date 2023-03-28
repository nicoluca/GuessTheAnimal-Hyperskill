package animals.ui.menu;

public class MenuItem {
    private final String description;
    private final Runnable action;

    public MenuItem(String description, Runnable action) {
        this.description = description;
        this.action = action;
    }

    // TODO Check if this is the best way to do this
    public void launch() throws InterruptedException {
        Thread thread = new Thread(action);
        thread.start();
        thread.join();
    }

    public String getDescription() {
        return description;
    }
}
