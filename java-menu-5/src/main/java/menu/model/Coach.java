package menu.model;

import java.util.ArrayList;
import java.util.List;

public class Coach {

    private String name;
    private List<String> notEatableMenu;
    private List<String> selectMenu;

    public Coach(String name, List<String> notEatableMenu) {
        this.name = name;
        this.notEatableMenu = notEatableMenu;
        this.selectMenu = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getNotEatableMenu() {
        return notEatableMenu;
    }

    public List<String> getSelectMenu() {
        return selectMenu;
    }

    public void addMenu(String targetMenu) {
        selectMenu.add(targetMenu);
    }
}
