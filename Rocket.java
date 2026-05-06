import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class Rocket {
    private Launcher launcher;
    private Pod pod;
    private List<Booster> boosters = new ArrayList<>();


    //Rocket constructor
    public Rocket(Launcher launcher, Pod pod) {
        this.launcher = launcher;
        this.pod = pod;
    }

    //GET
    public Launcher getLauncher() {
        return launcher;
    }

    public Pod getPod() {
        return pod;
    }

    public int getBoosterCount() {
        return boosters.size();
    }
    
    public String getName() {
        return launcher.getName() + " + " + pod.getName();
    }

    // METHOD

    //method used to add a booster to the boosters list which is used in the future for the history and the total price/mass
    public void addBooster(Booster booster) {
        boosters.add(booster);
    }

    //Method adding the weight of the pod
    public double calculateTotalMass() {
        double total = pod.getMass();
        //then foreach booster in the boosters list adding to the total
        for (Booster booster : boosters) {
            total += booster.getMass();
        }

        return total;
    }
    //Method adding the price of the pod and the launcher
    public double calculateTotalPrice() {
        double total = launcher.getPrice() + pod.getPrice();
        //then foreach booster in the boosters list adding to the total
        for (Booster booster : boosters) {
            total += booster.getPrice();
        }

        return total;
    }

}