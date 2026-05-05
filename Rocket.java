import java.util.ArrayList;
import java.util.List;

class Rocket {
    private Launcher launcher;
    private Pod pod;
    private List<Booster> boosters = new ArrayList<>();

    public Rocket(Launcher launcher, Pod pod) {
        this.launcher = launcher;
        this.pod = pod;
    }

    public Launcher getLauncher() {
        return launcher;
    }

    public Pod getPod() {
        return pod;
    }

    public void addBooster(Booster booster) {
        boosters.add(booster);
    }

    public int getBoosterCount() {
        return boosters.size();
    }

    public double calculateTotalMass() {
        double total = pod.getMass();

        for (Booster booster : boosters) {
            total += booster.getMass();
        }

        return total;
    }

    public double calculateTotalPrice() {
        double total = launcher.getPrice() + pod.getPrice();

        for (Booster booster : boosters) {
            total += booster.getPrice();
        }

        return total;
    }

    public String getName() {
        return launcher.getName() + " + " + pod.getName();
    }
}