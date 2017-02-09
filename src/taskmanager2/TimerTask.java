package taskmanager2;

import java.util.Date;

public class TimerTask {
    private static Date firstAlarmDate = null;
    private static Date currentAlarmDate = null;
    private static boolean isExit = false;
    
    
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