# Space Launch Simulator

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

[![](https://mermaid.ink/img/pako:eNq9V1tP4zgU_itWeCkiRS30QiuENFNAOxIdEF3tw273wSRuam1id2xnBoahv30dx05tx2Wuu32pc77jc46_c4nzHCU0RdE0WpIkh5xfYpgxWCwJkD8lAXOICXiuJdXvqJCCzkIwTLK__gaQZfwQfKQ4rVVelsTevcBFmUNBmW2iywUUOLFATKSIJMjSucFcbG9gSZI1YluQ6xX3VWZww8scbUFSL1oKbynlojLxUC9aCnPMOaZkC4p6wcNRbMFaPlD2ZMGLBBKCGOD1v4XcQ5LSAjD1Z-8wZ-4cWpxmSLzTFHQOd7xYGhJkomMzrczlFKYzKGBOM95G1aY6-jtGE8RDOrUzVKt17mnyDxKAqb8YaG4MNYegVrP3r-mn32piAsbhR7QXrGIPgV4J6Yjs-jFl0VSFhel6MOXwHdVwVHvqtKzHwDNqpw6mqbbZ0f_GtH_gV1Rj2QECfCghEVg8tTYmME9Ukn6nMtVzqPKY0vLBPp-ndcewKqaWmqw17X1GS1LVlPTt4oaBjsm3zXCloPmQ-MynuYLfw0LVsRoSwaTWZt2kUnmAyyp8XCCQyoXdNsqULsvKfBvTJeqBkuAcQQJ4mVT1HzCJIKfEkteEAVGROJM8WUfTPdKONAav900MvDhi4LiPge_VLjFBr3GObjDxSVXoitGiwbXVXK7bvSoN1fjruTG1-dwmi7jkmqDXrLR5MvICOoRr6aYqTCso0xKWix0dynLzWBlsHpQdb4juaw0J7e2HbyOlGQoWK-fn8IELBhNxcbGTHgS5OjAFkDD0CVnNfVB1fgEf37bm0UFz6sfrEuVB4A4-VWO0jfksh7tyx1vjPzQP5nUEe5idN2F8L_W7iaXy_OPZWUBRMvKHLXrDMCRoaIuupTtKJs7Gm4VryUz6_yrNt0lSbuSYD-aZ859KJOYz5VViOoz_qztuGaYujTKQSwYzV_pmQ_OcunSzjBpF59qpB-ivycM9-lBihgJ9kmL_5mmQlaz4GUWrFU4w8jviaykwDoOpaMreaKnmct4gP5yJK3njW9-yByxs6bvFwiGXunmZQzN3dFeUXjbeU3FFaJmtq0ivHhO0EV5yjsIqZqoX8p0HMzOum5jVx0W3e-FfeXdfBhVYMxNC7DdcCEFBe86dxYX0FAxBuiJDUPjs5pA6se2oLMAJyZI38dSIvjb5pFjiJkobka-t8y_drjsmXcgZly7kjE3P4G58msGp5NY8cOT-WHBAezq4u9pDwowHhfs174BN6TvSXQe44qYRHLHVD3s6odv9cgGaxyiOMobTaCpYieKoQEx-NMvHSDXMMhJrJIdVNJXLFK1gmYul_AJ_kdvkq-FPSguzk1WeoukK5lw-lZvqrqm_0RsVRFJ9lY-mw4kyEU2fo8doenI2PD49HQ36g-H4pN8fjuLoKZoO-se9yah32u_3BoNxrz8avMTRZ-W0dzzpnZ2OJuPxZHg2Gk9OXv4FuBQJHw?type=png)](https://mermaid.live/edit#pako:eNq9V1tP4zgU_itWeCkiRS30QiuENFNAOxIdEF3tw273wSRuam1id2xnBoahv30dx05tx2Wuu32pc77jc46_c4nzHCU0RdE0WpIkh5xfYpgxWCwJkD8lAXOICXiuJdXvqJCCzkIwTLK__gaQZfwQfKQ4rVVelsTevcBFmUNBmW2iywUUOLFATKSIJMjSucFcbG9gSZI1YluQ6xX3VWZww8scbUFSL1oKbynlojLxUC9aCnPMOaZkC4p6wcNRbMFaPlD2ZMGLBBKCGOD1v4XcQ5LSAjD1Z-8wZ-4cWpxmSLzTFHQOd7xYGhJkomMzrczlFKYzKGBOM95G1aY6-jtGE8RDOrUzVKt17mnyDxKAqb8YaG4MNYegVrP3r-mn32piAsbhR7QXrGIPgV4J6Yjs-jFl0VSFhel6MOXwHdVwVHvqtKzHwDNqpw6mqbbZ0f_GtH_gV1Rj2QECfCghEVg8tTYmME9Ukn6nMtVzqPKY0vLBPp-ndcewKqaWmqw17X1GS1LVlPTt4oaBjsm3zXCloPmQ-MynuYLfw0LVsRoSwaTWZt2kUnmAyyp8XCCQyoXdNsqULsvKfBvTJeqBkuAcQQJ4mVT1HzCJIKfEkteEAVGROJM8WUfTPdKONAav900MvDhi4LiPge_VLjFBr3GObjDxSVXoitGiwbXVXK7bvSoN1fjruTG1-dwmi7jkmqDXrLR5MvICOoRr6aYqTCso0xKWix0dynLzWBlsHpQdb4juaw0J7e2HbyOlGQoWK-fn8IELBhNxcbGTHgS5OjAFkDD0CVnNfVB1fgEf37bm0UFz6sfrEuVB4A4-VWO0jfksh7tyx1vjPzQP5nUEe5idN2F8L_W7iaXy_OPZWUBRMvKHLXrDMCRoaIuupTtKJs7Gm4VryUz6_yrNt0lSbuSYD-aZ859KJOYz5VViOoz_qztuGaYujTKQSwYzV_pmQ_OcunSzjBpF59qpB-ivycM9-lBihgJ9kmL_5mmQlaz4GUWrFU4w8jviaykwDoOpaMreaKnmct4gP5yJK3njW9-yByxs6bvFwiGXunmZQzN3dFeUXjbeU3FFaJmtq0ivHhO0EV5yjsIqZqoX8p0HMzOum5jVx0W3e-FfeXdfBhVYMxNC7DdcCEFBe86dxYX0FAxBuiJDUPjs5pA6se2oLMAJyZI38dSIvjb5pFjiJkobka-t8y_drjsmXcgZly7kjE3P4G58msGp5NY8cOT-WHBAezq4u9pDwowHhfs174BN6TvSXQe44qYRHLHVD3s6odv9cgGaxyiOMobTaCpYieKoQEx-NMvHSDXMMhJrJIdVNJXLFK1gmYul_AJ_kdvkq-FPSguzk1WeoukK5lw-lZvqrqm_0RsVRFJ9lY-mw4kyEU2fo8doenI2PD49HQ36g-H4pN8fjuLoKZoO-se9yah32u_3BoNxrz8avMTRZ-W0dzzpnZ2OJuPxZHg2Gk9OXv4FuBQJHw)
