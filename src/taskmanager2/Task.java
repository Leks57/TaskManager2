package taskmanager2;

import java.util.*;

public class Task {
    private String name;
    private String description;
    private Date date; // calendar или date
    List<Contact> contacts = new ArrayList<Contact>();

    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setDate(Date date) {
        this.date = date;
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
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }
    
}
