package taskmanager2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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
    
    static Thread mainThread = Thread.currentThread();    
    
    
    public static void main(String[] args) throws IOException {
        
        System.out.println("Current thread: " + Thread.currentThread());
        mainThread = Thread.currentThread(); // убрать
        
        TaskList tasks = new TaskList();
        
        Xml.defineXml(); // создание файла xml или определение текущего
        
        ScheduledTask.printMissedTasks();
        
        ScheduledTask.updateTimerTask(); // Обновление даты первого срабатывания таймера и запуск таймера
        
  
        
        //Основное МЕНЮ
        int action = 0;        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        while (action!=1) {
            PrintLine.line();
            System.out.println("Выберите действие:");
            System.out.println("1 - Exit");
            System.out.println("2 - PrintTasks");
            System.out.println("3 - CreateTask");
            System.out.println("4 - EditTask");
            System.out.println("5 - SaveTaskList");
            System.out.println("6 - Timer");
            
            String line = reader.readLine();
            action = Integer.parseInt(line);
            switch(action) {
                case 1: Xml.saveXml(); ScheduledTask.time.cancel(); TaskManager2.timerThread.interrupt(); break; //Выход
                case 2: tasks.printTasks(); break; //PrintTasks
                case 3: tasks.createTask(); Xml.saveXml(); ScheduledTask.updateTimerTask(); break; //CreateTask
                case 4: tasks.editTask(); ScheduledTask.updateTimerTask(); break; //editTask
                case 5: Xml.saveXml(); break; //saveTaskList
                case 6: System.out.println("tim.getState() = " + timerThread.getState()); break; //Timer
                default: System.out.println("Выбрано неверное действие! Повторите ввод!"); break;
            }
        }
        
        
    }
}
