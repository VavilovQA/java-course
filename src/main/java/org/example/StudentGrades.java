package org.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Arsentiy Vavilov
 */
public class StudentGrades {

    private final Map<String, Integer> grades = new HashMap<>();

    public void addGrade(String student, int grade) {
        grades.put(student, grade);
    }

    // Поиск с do-while
    public boolean searchStudent(String nameToFind) {
        if (grades.isEmpty()) {
            return false;
        }

        Iterator<String> it = grades.keySet().iterator();
        boolean found = false;
        do {
            String name = it.next();
            if (name.equals(nameToFind)) {
                found = true;
                break;
            }
        } while (it.hasNext());

        return found;
    }

    // Удаление с do-while (аналогично)
    public boolean removeStudent(String nameToRemove) {
        if (grades.isEmpty()) {
            return false;
        }

        Iterator<String> it = grades.keySet().iterator();
        boolean found = false;
        do {
            String name = it.next();
            if (name.equals(nameToRemove)) {
                it.remove(); // безопасное удаление через итератор
                found = true;
                break;
            }
        } while (it.hasNext());

        return found;
    }

    public Map<String, Integer> getGrades() {
        return new HashMap<>(grades);
    }

}
