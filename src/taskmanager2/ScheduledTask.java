package taskmanager2;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class ScheduledTask extends TimerTask {
    private Date alarm;
    
    private static Date firstAlarmDate = null;
    private static Date currentAlarmDate = null;
    private static boolean isExit = false;

    public void setAlarm(Date alarm) {
        this.alarm = alarm;
    }
    public Date getAlarm() {
        return alarm;
    }
    
    static Timer time = new Timer();
   // static ScheduledTask currentTask = new ScheduledTask();

    // Добавляем таск
    @Override
    public void run() {
        Date now = new Date();
        System.out.println("Время начала работы таймера: " + now);
        try {
            while(true) {
                now = new Date();
                Thread.sleep(1000);
                if (now.compareTo(alarm) >= 0) {
                    System.out.println("НАПОМИНАНИЕ!");
                    
                    TaskList.getTasks().get(TaskManager2.getOperatedTask()).printTask();    // вывод задачи на экран
                    TaskList.getTasks().get(TaskManager2.getOperatedTask()).setCompleted(true);
                    
                    
                    synchronized(TaskManager2.mainThread) {
                        boolean isExit = false;
                        while(!isExit) {
                        TaskManager2.mainThread.wait();

                        System.out.println("");
                        System.out.println("Отложить напоминание на 10 секунд? 1/0");
                        System.out.println("Текущий поток: " + Thread.currentThread().getName());

                    //    TaskManager2.mainThread.yield();

                        Scanner choice = new Scanner(System.in);

                if (choice.nextInt() == 1) {
                    System.out.println("Введено 1");
                            Date prevDate = new Date();
                            prevDate.setTime(prevDate.getTime() + 10000); // откладываем задачу на 10 с

                            //Добавить сравнение по времени с другими текущими задачами
                            TaskManager2.newTask.setAlarm(prevDate);
                         //   TaskManager2.newTask.run();
                            System.out.println("Задача отложена!");

                            TaskList.getTasks().get(TaskManager2.getOperatedTask()).setDate(prevDate);
                            TaskList.getTasks().get(TaskManager2.getOperatedTask()).setCompleted(false);
                            isExit = true;
                        } else {System.out.println("Введено 0"); System.out.println("Задача завершена!"); TaskManager2.newTask.setAlarm(null); isExit = true; //TaskManager2.timerThread.interrupt();
                            }
                    
                        }
                    TaskManager2.mainThread.notify();
                    }
                 //   break;
                }
            }
            
          //  taskmanager2.TimerTask.updateTimerTask();
        } catch(Exception e) {
            //e.printStackTrace();
            time.cancel();
        }
    }
 
    public static void start() {
        time.schedule(TaskManager2.newTask, TaskManager2.newTask.getAlarm()); // void schedule (TimerTask task, Date when)
        }

    
    public static void updateTimerTask() {
        
        for(Task z: TaskList.getTasks()) {
            
            Date now = new Date();
            if (now.compareTo(z.getDate()) <= 0) {      // срабатывает при now <= z.getDate()
                currentAlarmDate = z.getDate();
                System.out.println("currentAlarmDate = " + currentAlarmDate);
                    
                try {
                    if (firstAlarmDate.compareTo(currentAlarmDate) >= 0) {      // срабатывает при firstAlarmDate >= currentAlarmDate
                        firstAlarmDate = currentAlarmDate;
                        TaskManager2.setOperatedTask(TaskList.getTasks().indexOf(z));
                    }
                } catch (NullPointerException ex) {
                    firstAlarmDate = currentAlarmDate;
                    TaskManager2.setOperatedTask(TaskList.getTasks().indexOf(z));
                    }
                      
            }

            if (currentAlarmDate != null) {
                TaskManager2.newTask.setAlarm(firstAlarmDate);
                System.out.println("Задача установлена на: " + firstAlarmDate);
                if (TaskManager2.timerThread.getState() != Thread.State.RUNNABLE) {TaskManager2.timerThread.start();}
                currentAlarmDate = null;
            }

        }

    }
    
    
    public static void printMissedTasks() {
        for(Task z: TaskList.getTasks()) {
            Date now = new Date();
            if (z.isCompleted() == false && now.compareTo(z.getDate()) >= 0) {   // Вывод на экран пропущенных задач
                System.out.println("");
                System.out.println("НАПОМИНАНИЕ!");
                z.printTask();  //вывести напоминание
                z.setCompleted(true);
            }
        }
        
        if (TaskManager2.timerThread.getState() == Thread.State.NEW) {System.out.println("Текущие задачи отсутствуют");}
        firstAlarmDate = null;
        
    }
    
}