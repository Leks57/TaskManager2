package taskmanager2;

import java.util.Date;

public class TimerTask {
    private static Date firstAlarmDate = new Date();
    private static Date currentAlarmDate = null;
        
    private static ScheduledTask newTask = new ScheduledTask();
    static Thread tim = new Thread(newTask);
    
    public static void updateTimerTask() {
        tim.start();
        
        for(Task z: TaskList.getTasks()) {
            Date now = new Date();
                if (now.compareTo(z.getDate()) <= 0) {
                    currentAlarmDate = z.getDate();
                    System.out.println("currentAlarmDate = " + currentAlarmDate);
                    if (firstAlarmDate.compareTo(currentAlarmDate) <= 0) {
                        firstAlarmDate = currentAlarmDate;
                    }
                }
            }
        if (currentAlarmDate != null) {
            newTask.setAlarm(firstAlarmDate);
            System.out.println("Задача установлена на: " + firstAlarmDate);
        }
        
 
                    
       /*        for(Task z: TaskList.getTasks()){
                    Date now = new Date();
                    if (now.compareTo(z.getDate()) <= 0) {
                        ScheduledTask newTask = new ScheduledTask();
                        System.out.println("Установлено напоминание на: " + z.getDate());
                        newTask.setAlarm(z.getDate());
                        Thread t = new Thread(newTask);
                        t.start();
                    }
                }
*/
    }
}