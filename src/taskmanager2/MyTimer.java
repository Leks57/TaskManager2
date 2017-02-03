package taskmanager2;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    final Timer time = new Timer();
        
public void activate() {
    time.schedule(new TimerTask() {
        int i = 0;
        @Override
        public void run() { //ПЕРЕЗАГРУЖАЕМ МЕТОД RUN
            if(i>=2){
                System.out.println("Таймер завершил свою работу");
                time.cancel();
                return;
            }
            System.out.println("Прошло 4 секунды");
            i = i + 1;
        }
    }, 4000, 4000); //(4000 - ПОДОЖДАТЬ ПЕРЕД НАЧАЛОМ В МИЛИСЕК, ПОВТОРЯТСЯ 4 СЕКУНДЫ (1 СЕК = 1000 МИЛИСЕК))

}
}