package org.example;

/**
 * Демонстрация работы 4 классов с разными коллекциями и циклами
 *
 * @author Arsentiy Vavilov
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Класс, использующий ArrayList ===");
        TaskList taskList = new TaskList();
        taskList.addTask("Сделай домашку");
        taskList.addTask("Помой посуду");

        System.out.println("Нашёл 'Сделай домашку'? " + taskList.searchTask("Сделай домашку"));
        System.out.println("Удалил 'Помой посуду'? " + taskList.removeTaskByValue("Помой посуду"));
        System.out.println("Оставшиеся задачи: " + taskList.getTasks());

        System.out.println("\n=== Класс, использующий HashSet ===");
        UniqueEmails uniqueEmails = new UniqueEmails();
        uniqueEmails.addEmail("ars111@mail.ru");
        uniqueEmails.addEmail("123@ya.ru");

        System.out.println("Нашёл '123@ya.ru'? " + uniqueEmails.searchEmail("123@ya.ru"));
        System.out.println("Удалил 'ars111@mail.ru'? " + uniqueEmails.removeEmail("ars111@mail.ru"));
        System.out.println("Оставшиеся email'ы: " + uniqueEmails.getEmails());

        System.out.println("\n=== Класс, использующий очередь Queue ===");
        MessageQueue messageQueue = new MessageQueue();
        messageQueue.enqueue("Привет");
        messageQueue.enqueue("Как сам?");

        System.out.println("Нашёл 'Привет'? " + messageQueue.searchMessage("Привет"));
        System.out.println("Удалил 'Как сам?'? " + messageQueue.removeMessage("Как сам?"));
        System.out.println("Очередь после удаления: " +
                (messageQueue.isEmpty() ? "пусто" : "(см. через peek: " + messageQueue.peek() + ")"));

        System.out.println("\n=== Класс, использующий HashMap ===");
        StudentGrades studentGrades = new StudentGrades();
        studentGrades.addGrade("Oleg", 1);
        studentGrades.addGrade("Andrey", 4);

        System.out.println("Нашёл 'Oleg'? " + studentGrades.searchStudent("Oleg"));
        System.out.println("Удалил 'Andrey'? " + studentGrades.removeStudent("Andrey"));
        System.out.println("Оставшиеся оценки: " + studentGrades.getGrades());
    }
}
