package org.example;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Arsentiy Vavilov
 */
public class UniqueEmails {

    private final Set<String> emails = new HashSet<>();

    public void addEmail(String email) {
        emails.add(email);
    }

    // Поиск с использованием for-each
    public boolean searchEmail(String emailToFind) {
        for (String email : emails) {
            if (email.equals(emailToFind)) {
                return true;
            }
        }
        return false;
    }

    // Удаление — стандартно (без цикла, так как цикл вызовет ошибку)
    // Но по условию нужно использовать цикл в одном из методов — пусть это будет поиск
    public boolean removeEmail(String email) {
        return emails.remove(email); // это нормально, цикл не нужен
    }

    public Set<String> getEmails() {
        return new HashSet<>(emails);
    }

}
