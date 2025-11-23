package org.example;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Arsentiy Vavilov
 */
public class MessageQueue {

    private final Queue<String> messages = new LinkedList<>();

    public void enqueue(String message) {
        messages.offer(message);
    }

    // Поиск с использованием while
    public boolean searchMessage(String messageToFind) {
        Iterator<String> it = messages.iterator();
        while (it.hasNext()) {
            if (it.next().equals(messageToFind)) {
                return true;
            }
        }
        return false;
    }

    // Удаление по значению с использованием while
    public boolean removeMessage(String messageToRemove) {
        // Создаём временную очередь
        Queue<String> temp = new LinkedList<>();
        boolean found = false;

        while (!messages.isEmpty()) {
            String msg = messages.poll();
            if (!found && msg.equals(messageToRemove)) {
                found = true; // пропускаем первый найденный
            } else {
                temp.offer(msg);
            }
        }

        // Восстанавливаем очередь
        messages.addAll(temp);
        return found;
    }

    public String dequeue() {
        return messages.poll();
    }

    public String peek() {
        return messages.peek();

    }

    public boolean isEmpty() {
        return messages.isEmpty();
    }

}
