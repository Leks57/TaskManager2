package taskmanager2;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskManager2 {
    
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String pathFile = "C:\\test.xml";

    public static String getPathFile() {
        return pathFile;
    }
        
    public static void main(String[] args) throws IOException {

        TaskList tasks = new TaskList();
        File file = new File("C:\\test.xml");
        if (file.exists()) {
            Xml.readXml();
        } else {
            System.out.println("Файл не существует. Будет создан новый файл xml");
            file.createNewFile();
        }
        
        
   
        Menu menu = new Menu();
        
        // Добавляем пункт меню PrintTasks
        menu.getEntries().add(new MenuEntry("2 - PrintTasks") {
            @Override
            public void run() {
                tasks.printTasks();
            }
        });
        
        // Добавляем пункт меню CreateTask
        menu.getEntries().add(new MenuEntry("3 - CreateTask") {
            @Override
            public void run() {
                tasks.createTask();
                Xml.saveXml();
            }
        });
        
        // Добавляем пункт меню editTask
        menu.getEntries().add(new MenuEntry("4 - EditTask") {
            @Override
            public void run() {
            tasks.editTask();
            }
        });
        
        // Добавляем пункт меню saveTaskList
        menu.getEntries().add(new MenuEntry("5 - SaveTaskList") {
            @Override
            public void run() {
                Xml.saveXml();
            }
        });
        
        // Добавляем пункт меню Timer
        menu.getEntries().add(new MenuEntry("6 - Timer") {
            @Override
            public void run() {
                for(Task z: TaskList.getTasks()){
                    Date now = new Date();
                    if (now.compareTo(z.getDate()) <= 0) {
                        ScheduledTask newTask = new ScheduledTask();
                        System.out.println("Установлено напоминание на: " + z.getDate());
                        newTask.setAlarm(z.getDate());
                        Thread t = new Thread(newTask);
                        t.start();
                    }
                }
                
                
            }
        });        
        
        menu.run();
        

    }
    
}
