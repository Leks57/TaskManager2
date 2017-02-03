package taskmanager2;

import java.util.*;

public class TaskList {
    
    private static List<Task> tasks = new ArrayList<Task>();

    public static List<Task> getTasks() {
        return tasks;
    }

    public TaskList() {}
    
    public static void addTask(Task task) {
       tasks.add(task);
    }
    
    public void printTasks() {
        if (tasks.isEmpty() == false) {
            for(Task t: this.tasks) {
                t.printTask();
            }
        }
        else {System.out.println("Список задач пуст!");}
    }
    
    public void createTask() {
        Task task = new Task();
        task.setTaskName();
        task.setTaskDescription();        
        task.setTaskDate();

        Contact contact = new Contact();
        contact.setContactName();
        contact.setContactSurname();
        contact.setContactPhone();
        
        task.addContact(contact);
        addTask(task);
    }
    
    public void editTask() {
        PrintLine.line();
        System.out.println("Текущие задачи:");
        for(Task t: this.tasks){
        System.out.println(tasks.indexOf(t) + 1 + " - " + t.getName());
        }
        
        Scanner scInt = new Scanner(System.in);
        Scanner scLine = new Scanner(System.in);
        
        try {
            System.out.println("Введите номер выбранной задачи: ");
            int taskNumber = scInt.nextInt() - 1;
            PrintLine.line();
            tasks.get(taskNumber).printTask();
        
        
            System.out.println("Введите номер действия: ");
            System.out.println("1 - Изменить название задачи");
            System.out.println("2 - Изменить описание задачи");
            System.out.println("3 - Изменить дату события");
            System.out.println("4 - Удалить задачу");
            System.out.println("0 - Выход");

            int userChoice = scInt.nextInt();
            PrintLine.line();
            switch(userChoice) {
                case 1: tasks.get(taskNumber).setTaskName(); break;
                case 2: System.out.println("Введите новое описание:"); tasks.get(taskNumber).setDescription(scLine.nextLine()); break;
                case 3: tasks.get(taskNumber).setTaskDate(); break;
                case 4: tasks.remove(taskNumber); System.out.println("Задача № " + (taskNumber + 1) + " успешно удалена"); break;
                case 0: break;
                default: System.out.println("Неверное действие!");
            }
        } catch (IndexOutOfBoundsException e) {System.out.println("Неправильный номер задачи!");}
    }
}