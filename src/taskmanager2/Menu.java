package taskmanager2;

import java.util.*;
import java.io.*;

public class Menu {
    private List<MenuEntry> entries = new ArrayList<MenuEntry>();
    private boolean isExit = false;

    public List<MenuEntry> getEntries() {
        return entries;
    }

    public Menu() {
        // Добавляем пункт меню Exit
        entries.add(new MenuEntry("1 - Exit") {
            @Override
            public void run() {
                isExit = true;
                Xml.saveXml();
                TimerTask.tim.stop();
            }
        });
    }

    public void run() {
        // Бесконечный цикл, пока не нажали кнопку выход
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!isExit) {
            System.out.println("Выберите действие:");
            printMenu();
            try {
                String line = reader.readLine();
                int choice = Integer.parseInt(line);
                // Выбираем нажатый пункт меню и выполняем его код
                MenuEntry entry = (MenuEntry) entries.get(choice - 1);
                entry.run();
                PrintLine.line();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void printMenu() {
        for(MenuEntry t: entries){
            System.out.println(t.getTitle());
        }
    }
}