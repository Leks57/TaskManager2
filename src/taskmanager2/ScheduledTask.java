package taskmanager2;

import java.text.ParseException;
import java.util.Date;
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
                    
                    taskmanager2.TimerTask.updateTimerTask();
                    
                    break;
                }
            }
        } catch(InterruptedException e) {
            //e.printStackTrace();
            time.cancel();
        }
    }
 
    public static void start() {
        time.schedule(TaskManager2.newTask, TaskManager2.newTask.getAlarm()); // void schedule (TimerTask task, Date when)
        }

}
