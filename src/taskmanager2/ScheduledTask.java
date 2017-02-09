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
                    
                    System.out.println("");
                    System.out.println("Отложить напоминание на 10 секунд? 1/0");
                    Scanner choice = new Scanner(System.in);
                    
                    if (choice.nextInt() == 1) {
                        Date prevDate = new Date();
                        prevDate.setTime(prevDate.getTime() + 10000); // откладываем задачу на 10 с
                        
                        TaskManager2.newTask.setAlarm(prevDate);
                        TaskManager2.newTask.run();
                        
                        TaskList.getTasks().get(TaskManager2.getOperatedTask()).setDate(prevDate);
                        TaskList.getTasks().get(TaskManager2.getOperatedTask()).setCompleted(false);
                    }
                    
                    
                    break;
                }
            }
          //  taskmanager2.TimerTask.updateTimerTask();
        } catch(InterruptedException e) {
            //e.printStackTrace();
            time.cancel();
        }
    }
 
    public static void start() {
        time.schedule(TaskManager2.newTask, TaskManager2.newTask.getAlarm()); // void schedule (TimerTask task, Date when)
        }

}
