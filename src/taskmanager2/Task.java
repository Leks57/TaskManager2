package taskmanager2;

import java.text.ParseException;
import java.util.*;

public class Task {
    private String name;
    private String description;
    private Date date; // calendar или date
    
    private boolean completed = false;
    
    List<Contact> contacts = new ArrayList<Contact>();

    public void setName(String name) {
        this.name = name;
    }
    public void setTaskName() {
        try {
            System.out.println("Введите название задачи:");
            Scanner name = new Scanner(System.in);
            this.setName(name.nextLine());
        } catch(Exception e) {System.out.println("Ошибка ввода названия! Повторите ввод:"); setTaskName();}
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTaskDescription() {
        try {
            System.out.println("Введите описание задачи:");
            Scanner description = new Scanner(System.in);
            this.setDescription(description.nextLine());
        } catch(Exception e) {System.out.println("Ошибка ввода описания! Повторите ввод:"); setTaskDescription();}
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public void setTaskDate() {
        try {
            System.out.println("Введите дату в формате yyyy-MM-dd HH:mm:ss:");
            Scanner taskDate = new Scanner(System.in);
            this.setDate(TaskManager2.DATE_FORMAT.parse(taskDate.nextLine()));
        } catch (ParseException ex) {System.out.println("Введена неправильная дата! Повторите ввод!"); setTaskDate();
        }
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public boolean isCompleted() {
        return completed;
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }
    
    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }
    
    public void printContacts() {
        for(Contact t: contacts){
        t.printContact();
        }
    }
    
    public void printTask() {
        System.out.println("*****");
        System.out.println("Задача: " + this.name);
        System.out.println("Описание: " + this.description);
        System.out.println("Напоминание: " + this.date);
        this.printContacts();
    }
}