abstract class Launcher {
    String name;
    boolean crewed;
    int maxBoosters;
    double maxFuel;
    double maxPayload;
    double price;

    public Launcher(String name, boolean crewed, int maxBoosters, double maxFuel, double maxPayload, double price) {
        this.name = name;
        this.crewed = crewed;
        this.maxBoosters = maxBoosters;
        this.maxFuel = maxFuel;
        this.maxPayload = maxPayload;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getMaxBoosters() {
        return maxBoosters;
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    public double getMaxPayload() {
        return maxPayload;
    }

    public double getPrice() {
        return price;
    }

    abstract double calculateThrust();
}

class SaturneV extends Launcher {
    
    public SaturneV() {
        super("Saturne V", true, 0 ,2700, 140, 1500);
    }

    @Override
    double calculateThrust() {
        return 3500;
    }
}

class Ariane5 extends Launcher {

    public Ariane5() {
        super("Ariane 5", false, 2, 700, 20, 180);
    }

    @Override
    double calculateThrust() {
        return 2500;
    }
}

class Falcon9 extends Launcher {
    
    public Falcon9() {
        super("Falcon 9", true, 0, 500, 22, 60);
    }

    @Override
    double calculateThrust() {
        return 1500;
    }
}

class Sls extends Launcher {

    public Sls() {
        super("SLS", true, 2, 2600, 130, 2000);
    }

    @Override
    double calculateThrust() {
        return 2500;
    }
}

abstract class Pod {
    String name;
    boolean crewed;
    int maxPassengers;
    double weight;
    double price;

    public Pod(String name, boolean crewed, int maxPassengers, double weight, double price) {
        this.name = name;
        this.crewed = crewed;
        this.maxPassengers = maxPassengers;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public boolean isCrewed() {
        return crewed;
    }

    public double getMass() {
        return weight;
    }

    public double getPrice() {
        return price;
    }
}

class Orion extends Pod {
    public Orion() {
        super("Orion", true, 4, 10.4, 300);
    }
}

class CrewDragon extends Pod {
    public CrewDragon() {
        super("Crew Dragon", true, 7, 12.0, 150);
    }
}

class Apollo extends Pod {
    public Apollo() {
        super("Apollo", true, 3, 5.6, 200);
    }
}

class CargoDragon extends Pod {
    public CargoDragon() {
        super("Cargo Dragon", false, 0, 9.5, 100);
    }
}

abstract class Booster {
    String name;
    int maxThrust;
    double fuelCapacity;
    double price;

    public Booster(String name, int maxThrust, double fuelCapacity, double price) {
        this.name = name;
        this.maxThrust = maxThrust;
        this.fuelCapacity = fuelCapacity;
        this.price = price;
    }

    public double getMass() {
        return fuelCapacity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " | thrust: " + maxThrust + " kN | mass: " + fuelCapacity + " t | price: " + price + " M€";
    }
}

class EapAriane extends Booster {
    public EapAriane() {
        super("EAP (Ariane)", 6470, 270, 30);
    } 
}

class SrbShuttle extends Booster {
    public SrbShuttle() {
        super("SRB (Shuttle)", 12500, 590, 55);
    }
}

class Be3 extends Booster {
    public Be3() {
        super("BE-3", 490, 25, 12);
    }
}


abstract class Mission {
    String name;
    boolean crewed;
    double distance;
    double coefficient;

    public Mission(String name, boolean crewed, double distance, double coefficient) {
        this.name = name;
        this.crewed = crewed;
        this.distance = distance;
        this.coefficient = coefficient;
    }

    abstract double calculateFuel(double masse);
}

class EarthOrbit extends Mission {
    public EarthOrbit() {
        super("Earth Orbit", false, 400, 1.0);
    }

    @Override
    double calculateFuel(double weight) {
        return (weight * distance * coefficient) / 1000;
    }
}

class Iss extends Mission {
    public Iss() {
        super("ISS", true, 400, 1.2);
    }

    @Override
    double calculateFuel(double weight) {
        return (weight * distance * coefficient) / 1000;
    }
}

class Moon extends Mission {
    public Moon() {
        super("Moon", true, 400000, 0.005);
    }

    @Override
    double calculateFuel(double weight) {
        return (weight * distance * coefficient) / 1000;
    }
}

class Mars extends Mission {

    public Mars() {
        super("Mars", true, 225000000, 0.000015);
    }

    @Override
    double calculateFuel(double weight) {
        return (weight * distance * coefficient) / 1000;
    }
}