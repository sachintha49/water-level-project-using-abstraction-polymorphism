import java.util.ArrayList;
import java.util.Random;

abstract class WaterLevelObserver{
    abstract void update(int waterLevel);
}

class Alarm extends WaterLevelObserver{
    public void update(int waterLevel){
        System.out.println(waterLevel>50 ? "Alarm ON":"Alarm OFF");
    }
}

class Display extends WaterLevelObserver{
    public void update(int waterLevel){
        System.out.println("Water Level "+waterLevel);
    }
}

class SMSSender extends WaterLevelObserver{
    public void update(int waterLevel){
        System.out.println("Sending water level "+waterLevel);
    }
}

class Splitter extends WaterLevelObserver{
    public void update(int waterLevel){
        System.out.println(waterLevel >=75 ? "Splitter ON" : "Splitter OFF");
    }
}

class ControlRoom{
    private ArrayList<WaterLevelObserver> observerArray = new ArrayList<>();

    private int waterLevel;

    public void addWaterLevelObserver(WaterLevelObserver observer){
        observerArray.add(observer);
    }

    public void setWaterLevel(int waterLevel){
        if (this.waterLevel != waterLevel){
            this.waterLevel = waterLevel;
            notifyObjects();
        }
    }

    private void notifyObjects() {
        for (WaterLevelObserver ob : observerArray){
            ob.update(waterLevel);
        }
    }
}
public class Demo {
    public static void main(String[] args) {
        ControlRoom controlRoom = new ControlRoom();
        controlRoom.addWaterLevelObserver(new Alarm());
        controlRoom.addWaterLevelObserver(new Display());
        controlRoom.addWaterLevelObserver(new SMSSender());
        controlRoom.addWaterLevelObserver(new Splitter());

        Random random = new Random();
        while (true){
            int wtrLevel = random.nextInt(101);
            controlRoom.setWaterLevel(wtrLevel);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {

            }
        }
    }
}
