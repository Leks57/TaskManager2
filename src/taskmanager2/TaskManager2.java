package taskmanager2;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskManager2 {
    
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String pathFile = "C:\\test.xml";
    
    private static int operatedTask;
    public static void setOperatedTask(int operatedTask) {
        TaskManager2.operatedTask = operatedTask;
    }
    public static int getOperatedTask() {
        return operatedTask;
    }

    public static String getPathFile() {
        return pathFile;
    }
    
    static ScheduledTask newTask = new ScheduledTask();
    static Thread timerThread = new Thread(newTask);
        
    
    public static void main(String[] args) throws IOException {

        TaskList tasks = new TaskList();
        
        File file = new File("C:\\test.xml");
        if (file.exists()) {
            Xml.readXml();
        } else {
            System.out.println("Файл не существует. Будет создан новый файл xml");
            file.createNewFile();
        }
        
        TimerTask.updateTimerTask(); // Обновление даты первого срабатывания таймера и запуск таймера
        
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
                TimerTask.updateTimerTask();
            }
        });
        
        // Добавляем пункт меню editTask
        menu.getEntries().add(new MenuEntry("4 - EditTask") {
            @Override
            public void run() {
            tasks.editTask();
            TimerTask.updateTimerTask();
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
                
                System.out.println("tim.getState() = " + timerThread.getState());

            }
        });      
        
        menu.run();
    }
}
