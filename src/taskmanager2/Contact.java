package taskmanager2;

import java.util.Scanner;

public class Contact {
    String name;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getPhone() {
        return phone;
    }
    String surname;
    int phone;
    
    public void setName(String name) {
        this.name = name;
    }
    public void setContactName() {
        try {
        System.out.println("Введите имя контакта:");
        Scanner contactName = new Scanner(System.in);
        this.setName(contactName.nextLine());
        } catch(Exception e) {System.out.println("Ошибка ввода имени контакта! Повторите ввод:"); setContactName();}
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setContactSurname() {
        try {
        System.out.println("Введите фамилию контакта:");
        Scanner contactSurname = new Scanner(System.in);
        this.setSurname(contactSurname.nextLine());
        } catch(Exception e) {System.out.println("Ошибка ввода фамилии контакта! Повторите ввод:"); setContactSurname();}
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    public void setContactPhone() {
        try {
        System.out.println("Введите номер контакта:");
        Scanner contactPhone = new Scanner(System.in);
        this.setPhone(contactPhone.nextInt());
        } catch(Exception e) {System.out.println("Ошибка ввода номера контакта! Повторите ввод:"); setContactPhone();}
    }
    
    
    public void printContact() {
        System.out.println("Контакт: " + this.name + " " + this.surname + ", телефон: " + this.phone);
    }
}