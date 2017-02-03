package taskmanager2;

import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        else {System.out.println("Список задач пуст!");};
    }
    
    public void createTask() {
        Scanner sc = new Scanner(System.in);
        Task task = new Task();
        System.out.println("Введите название задачи: ");
        task.setName(sc.nextLine());
        System.out.println("Введите описание задачи: ");
        task.setDescription(sc.nextLine());
        System.out.println("Введите дату задачи в формате yyyy-MM-dd HH:mm:ss");
        try {
            task.setDate(TaskManager2.DATE_FORMAT.parse(sc.nextLine()));
        } catch (ParseException ex) {System.out.println("Введена неправильная дата!");
        //    Logger.getLogger(TaskList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Contact contact = new Contact();
        System.out.println("Введите имя контакта: ");
        contact.setName(sc.nextLine());
        System.out.println("Введите фамилию контакта: ");
        contact.setSurname(sc.nextLine());
        try {
        System.out.println("Введите телефон контакта: ");
        contact.setPhone(sc.nextInt());
        } catch (Exception ex) {System.out.println("Введен неправильный номер!");}
        
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
          case 1: System.out.println("Введите новое название:"); tasks.get(taskNumber).setName(scLine.nextLine()); break;
          case 2: System.out.println("Введите новое описание:"); tasks.get(taskNumber).setDescription(scLine.nextLine()); break;
          case 3: System.out.println("Введите новую дату:"); {
            try {
                tasks.get(taskNumber).setDate(TaskManager2.DATE_FORMAT.parse(scLine.nextLine()));
            } catch (ParseException ex) {
                Logger.getLogger(TaskList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        break;
          case 4: tasks.remove(taskNumber); System.out.println("Задача № " + taskNumber + "успешно удалена"); break;
          case 0: break;
          default: System.out.println("Неверное действие!");
        } // switch
        } catch (IndexOutOfBoundsException e) {System.out.println("Неправильный номер задачи!");}
    }
}