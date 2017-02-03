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
    

    // Добавляем такс
    @Override
    public void run() {
        Date now = new Date();
        System.out.println("Время начала работы таймера: " + now);
        try {
            while(true) {
                now = new Date();
                Thread.sleep(1000);
                if (now.compareTo(alarm) >= 0) {
                    System.out.println("Напоминание сработало: " + now);
                    System.out.println("Now " + now);
                    System.out.println("Alarm " + alarm);
                    break;
                }
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    public static void start() {
        Timer time = new Timer();
        ScheduledTask currentTask = new ScheduledTask();
        time.schedule(currentTask, currentTask.getAlarm()); // void schedule (TimerTask task, Date when)
        }

}
