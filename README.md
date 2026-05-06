# Space Launch Simulator

git clone https://github.com/HaxTwoWater/Fusee.git

## Description

Space Launch Simulator is a Java console application that simulates rocket launches.

The user can:
- Configure a rocket (launcher, capsule, boosters)
- Choose a mission
- Launch a simulation
- Get a result (success or failure with reason)
- View launch history

---

## Architecture

Main.java
Models.java
Rocket.java
Launch.java
Simulator.java

---

## Main.java

Entry point of the program.

public class Main {
    public static void main(String[] args) {
        Simulator simulator = Simulator.getInstance();
        simulator.start();
    }
}

---

## Models.java

Contains:
- Launcher (abstract) + subclasses
- Capsule (abstract) + subclasses
- Mission (abstract) + subclasses
- Booster

---

## Rocket.java

Represents a rocket composed of:
- Launcher
- Capsule
- Boosters

Handles:
- Total mass calculation
- Total price calculation

---

## Launch.java

Represents a launch result:
- Date
- Rocket name
- Mission name
- Success / failure
- Reason
- Total cost

Used for history storage.

---

## Simulator.java

Handles:
- Menu
- User choices
- Simulation logic
- History management
- File saving/loading

---

## POO Concepts

Inheritance:
class SaturnV extends Launcher

Composition:
Rocket has Launcher, Capsule, Boosters

Polymorphism:
List<Launcher>

Encapsulation:
Getters used

Exception:
NotEnoughFuelException

---

## Simulation Logic

fuel = (mass × distance × coefficient) / 1000

Failure conditions:
- Not enough fuel
- Payload exceeded
- Too many boosters
- Incompatible capsule
- Random failure (5%)

---

## History

Saved in history.txt

Format:
date;rocket;mission;success;reason;cost

---

## Run

javac *.java
java Main

## Diagramme UML

```mermaid
classDiagram
    class Main {
        +main(String[] args) void
    }

    class Simulator {
        -static Simulator instance
        -List~Launcher~ launchers
        -List~Capsule~ capsules
        -List~Booster~ boosters
        -List~Mission~ missions
        -List~Launch~ history
        -Scanner scanner
        -Random random
        -Simulator()
        +getInstance() Simulator
        +start() void
        -loadCatalogs() void
        -startLaunchProcess() void
        -simulateLaunch(Rocket rocket, Mission mission) Launch
        -showHistory() void
        -saveHistory() void
        -loadHistory() void
    }

    class Rocket {
        -Launcher launcher
        -Capsule capsule
        -List~Booster~ boosters
        +Rocket(Launcher launcher, Capsule capsule)
        +addBooster(Booster booster) void
        +addBooster(Booster booster, int quantity) void
        +calculateTotalMass() double
        +calculateTotalPrice() double
        +getBoosterCount() int
        +getLauncher() Launcher
        +getCapsule() Capsule
        +getName() String
    }

    class Launch {
        -LocalDateTime date
        -String rocketName
        -String missionName
        -boolean success
        -String reason
        -double totalCost
        +Launch(LocalDateTime date, Rocket rocket, Mission mission, boolean success, String reason, double totalCost)
        +toFileLine() String
        +fromFileLine(String line) Launch
        +toString() String
    }

    class Booster {
        -String name
        -double thrust
        -double mass
        -double price
        +Booster(String name, double thrust, double mass, double price)
        +getMass() double
        +getPrice() double
        +toString() String
    }

    class Launcher {
        <<abstract>>
        #String name
        #boolean crewed
        #int maxBoosters
        #double maxFuel
        #double maxPayload
        #double price
        +getName() String
        +getMaxBoosters() int
        +getMaxFuel() double
        +getMaxPayload() double
        +getPrice() double
        +calculateThrust() double
        +toString() String
    }

    class SaturnV
    class Ariane5
    class Falcon9
    class SLS

    class Capsule {
        <<abstract>>
        #String name
        #boolean crewed
        #int maxOccupants
        #double mass
        #double price
        +getName() String
        +isCrewed() boolean
        +getMass() double
        +getPrice() double
        +toString() String
    }

    class Orion
    class CrewDragon
    class Apollo
    class CargoDragon

    class Mission {
        <<abstract>>
        #String name
        #boolean crewedRequired
        #double distance
        #double fuelCoefficient
        +getName() String
        +isCrewedRequired() boolean
        +calculateRequiredFuel(Rocket rocket) double
        +toString() String
    }

    class EarthOrbit
    class ISS
    class Moon
    class Mars
    class Sun

    class NotEnoughFuelException {
        +NotEnoughFuelException(String message)
    }

    Main --> Simulator
    Simulator --> Rocket
    Simulator --> Launch
    Simulator --> Launcher
    Simulator --> Capsule
    Simulator --> Booster
    Simulator --> Mission
    Simulator --> NotEnoughFuelException

    Rocket --> Launcher
    Rocket --> Capsule
    Rocket --> Booster

    Launch --> Rocket
    Launch --> Mission

    Launcher <|-- SaturnV
    Launcher <|-- Ariane5
    Launcher <|-- Falcon9
    Launcher <|-- SLS

    Capsule <|-- Orion
    Capsule <|-- CrewDragon
    Capsule <|-- Apollo
    Capsule <|-- CargoDragon

    Mission <|-- EarthOrbit
    Mission <|-- ISS
    Mission <|-- Moon
    Mission <|-- Mars
    Mission <|-- Sun

    NotEnoughFuelException --|> Exception
```

## AI declaration

Mostly used for the readme :p