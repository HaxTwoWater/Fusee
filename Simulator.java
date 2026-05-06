import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

class Simulator {
    //setting static value given in the doc
    private static final double RANDOM_FAILURE_RATE = 0.05;
    private static final double FUEL_PRICE_PER_TON = 1200;

    private static Simulator instance;

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    //creating each array needed for display and calcul purposes
    private final List<Launcher> launchers = new ArrayList<>();
    private final List<Pod> pods = new ArrayList<>();
    private final List<Booster> boosters = new ArrayList<>();
    private final List<Mission> missions = new ArrayList<>();
    private final List<Launch> history = new ArrayList<>();

    //when a new instance is created the simulator is then execute this have 2 purposes
    private Simulator() {
        //executing the loadcatalogs method (which is explain at the start at the specific method)
        loadCatalogs();
        //and same for this one
        loadHistory();
    }

    // getinstance method which is used to start the simulator
    public static Simulator getInstance() {
        if (instance == null) {
            instance = new Simulator();
        }
        return instance;
    }

    //this is the method that ist executed just after creating a new instance and excuting the simulator
    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Space Launch Simulator ===");
            System.out.println("1. Start a launch");
            System.out.println("2. Show history");
            System.out.println("0. Quit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> startLaunchProcess();
                case "2" -> showHistory();
                case "0" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    //loadCatalogs method is used to load all launchers/pods/boosters/missions
    private void loadCatalogs() {
        launchers.add(new SaturneV());
        launchers.add(new Ariane5());
        launchers.add(new Falcon9());
        launchers.add(new Sls());

        pods.add(new Orion());
        pods.add(new CrewDragon());
        pods.add(new Apollo());
        pods.add(new CargoDragon());

        boosters.add(new EapAriane());
        boosters.add(new SrbShuttle());
        boosters.add(new Be3());

        missions.add(new EarthOrbit());
        missions.add(new Iss());
        missions.add(new Moon());
        missions.add(new Mars());
        missions.add(new Sun());
    }

    //After the "player/user" chosed in the menu the launch this program is then started
    private void startLaunchProcess() {
        // getting the player/user to chosed from the list previously load for a launcher
        Launcher launcher = chooseFromList(launchers, "Choose a launcher");
        // getting the player/user to chosed from the list previously load for a pod
        Pod pod = chooseFromList(pods, "Choose a pod");
        
        //then rocket class is used and launcher and pod are "send" to build the rocket
        //the booster aren't send yet because theres the possibility that the user don't chose any booster at all*
        Rocket rocket = new Rocket(launcher, pod);

        //*(that's why there is the question about booster)
        System.out.print("How many boosters do you want to add? ");
        int boosterCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < boosterCount; i++) {

            Booster booster = chooseFromList(boosters, "Choose booster " + (i + 1));
            //*(and why the addbooster method exist)
            rocket.addBooster(booster);
        }
        //the user is then ask for a mission
        Mission mission = chooseFromList(missions, "Choose a mission");

        //Launch method is called after all the required question are answered and all data needed is collected
        Launch launch = simulateLaunch(rocket, mission);

        //after the simulation the launch data are add to history and saved
        history.add(launch);
        saveHistory();

        //then printed out
        System.out.println("\n=== Launch result ===");
        System.out.println(launch);
    }

    //simulateLuanch method the used is in the name
    private Launch simulateLaunch(Rocket rocket, Mission mission) {
        boolean success = true;
        String reason = "Launch successful";

        //the required fuel is the acquired the mission method calculatinf fuel requirement based on the rocket chosed
        double requiredFuel = mission.calculateRequiredFuel(rocket);
        // and then a sum for the real total price adding the fuel
        double totalCost = rocket.calculateTotalPrice() + (requiredFuel * FUEL_PRICE_PER_TON / 1_000_000);

        //try for all requirement 
        try {

            //this is my own exception  (for the fuel)
            if (requiredFuel > rocket.getLauncher().getMaxFuel()) {
                throw new NotEnoughFuelException("Not enough fuel capacity");
            }

            //Paylod Exception
            if (rocket.calculateTotalMass() > rocket.getLauncher().getMaxPayload()) {
                success = false;
                reason = "Payload limit exceeded";
            //booster limit exception
            } else if (rocket.getBoosterCount() > rocket.getLauncher().getMaxBoosters()) {
                success = false;
                reason = "Too many boosters";
            //Compatibility between the pod and required crewed for the mission exception
            } else if (mission.isCrewedRequired() && !rocket.getPod().isCrewed()) {
                success = false;
                reason = "Pod incompatible with crewed mission";
            //then the random failure is checked
            } else if (random.nextDouble() < RANDOM_FAILURE_RATE) {
                success = false;
                reason = "Unexpected technical anomaly";
            }

        } catch (NotEnoughFuelException e) {
            success = false;
            reason = e.getMessage();
        }
        //if all the requirement are matched and checked
        //then we return the launch result
        return new Launch(
                LocalDateTime.now(),
                rocket,
                mission,
                success,
                reason,
                totalCost
        );
    }

    //Own method to simplify all the choice needed
    private <T> T chooseFromList(List<T> list, String title) {
        //printing each specific title
        System.out.println("\n" + title);

        //Getting everything on the given list
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }

        int choice = -1;

        //waiting for the choice and catching exception
        while (choice < 1 || choice > list.size()) {
            System.out.print("Choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
        //return choice -1 because of the index number
        return list.get(choice - 1);
    }

    //Showing history
    private void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No launch history yet.");
            return;
        }
        //Printing each launch in history
        for (Launch launch : history) {
            System.out.println(launch);
        }
    }

    //saving the history
    private void saveHistory() {
        //a try to create a new file .txt and writing in it each launch in the history
        try (PrintWriter writer = new PrintWriter(new FileWriter("history.txt"))) {
            for (Launch launch : history) {
                writer.println(launch.toFileLine());
            }
        //exception in case something bad happen
        } catch (IOException e) {
            System.out.println("Error while saving history.");
        }
    }

    //Loading history
    private void loadHistory() {
        //loading the file
        File file = new File("history.txt");

        //if the file doesn't exist return nothing
        if (!file.exists()) {
            return;
        }

        //This part is there to read the file
        //then finding the next empty line 
        //then adding the launch in to the history
        //the history while be then displayed
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                history.add(Launch.fromFileLine(line));
            }

        } catch (IOException e) {
            System.out.println("Error while loading history.");
        }
    }
}

//The class for my own exception
class NotEnoughFuelException extends Exception {
    public NotEnoughFuelException(String message) {
        super(message);
    }
}