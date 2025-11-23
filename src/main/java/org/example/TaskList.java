package org.example;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Arsentiy Vavilov
 */
public class TaskList {

    private final List<String> tasks = new ArrayList<>();

    public void addTask(String task) {
        tasks.add(task);
    }

    // Удаление по значению с использованием обычного for
    public boolean removeTaskByValue(String taskToRemove) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).equals(taskToRemove)) {
                tasks.remove(i);
                return true;
            }
        }
        return false;
    }

    // Поиск с использованием обычного for
    public boolean searchTask(String taskToFind) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).equals(taskToFind)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getTasks() {
        return new ArrayList<>(tasks);
    }

}
