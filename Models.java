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

    abstract double calculateThrust();
}



abstract class Pod {
    String name;
    boolean crewed;
    int maxPassengers;
    int weight;
    int price;

    public Pod(String name, boolean crewed, int maxPassengers, int weight, int price) {
        this.name = name;
        this.crewed = crewed;
        this.maxPassengers = maxPassengers;
        this.weight = weight;
        this.price = price;
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