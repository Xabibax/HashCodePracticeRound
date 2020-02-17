import java.util.HashMap;
import java.util.Map;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

public class OutputDataSet {

    public int participants;
    public int nbPizzaType;
    public Map<Integer, Pizza> unsettledPizzas;
    public Map<Integer, Pizza> settledPizzas;

    public OutputDataSet(int participants, Map<Integer, Pizza> unsettledPizzas) {
        this.participants = participants;
        this.unsettledPizzas = unsettledPizzas;
        this.settledPizzas = new HashMap<>();
        this.nbPizzaType = 0;
    }

    public int getScore() {
        final int[] result = {0};
        settledPizzas.forEach((index, pizza) -> {
            result[0] += pizza.getSlices();
        });

        return result[0];
    }

    public boolean addAPizza(Pizza pizza) {
        if (this.getScore() + pizza.getSlices() <= this.getParticipants()){
            this.settledPizzas.put(pizza.type, pizza);
            this.setNbPizzaType(this.getNbPizzaType() + 1);
            return true;
        } else
            return false;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public int getNbPizzaType() {
        return nbPizzaType;
    }

    public void setNbPizzaType(int nbPizzaType) {
        this.nbPizzaType = nbPizzaType;
    }

    public Map<Integer, Pizza> getUnsettledPizzas() {
        return unsettledPizzas;
    }

    public void setUnsettledPizzas(Map<Integer, Pizza> unsettledPizzas) {
        this.unsettledPizzas = unsettledPizzas;
    }

    public Map<Integer, Pizza> getSettledPizzas() {
        return settledPizzas;
    }

    public void setSettledPizzas(Map<Integer, Pizza> settledPizzas) {
        this.settledPizzas = settledPizzas;
        this.nbPizzaType = this.getSettledPizzas().size();
    }

    public Map<Integer, Pizza> getSortedPizzas() {
        return this.unsettledPizzas.entrySet()
                .stream()
                .sorted(comparingByKey())
                .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2));
    }

    public void removeLittleUntilScore() {
        this.setSettledPizzas(this.getUnsettledPizzas());
        this.setUnsettledPizzas(new HashMap<>());
        int i = 0;
        while (this.getScore() > this.getParticipants()) {
            this.unsettledPizzas.put(settledPizzas.get(i).type, settledPizzas.get(i));
            this.settledPizzas.remove(i++);
            this.setNbPizzaType(this.getNbPizzaType() - 1);
        }
    }

    @Override
    public String toString() {
        return "OutputDataSet{" +
                "participants=" + participants +
                ", nbPizzaType=" + nbPizzaType +
                ", unsettledPizzas=" + unsettledPizzas +
                ", settledPizzas=" + settledPizzas +
                '}';
    }

    public String toDataSet() {
        StringBuilder result = new StringBuilder(settledPizzas.size() + System.lineSeparator());
        settledPizzas.entrySet().toArray((Map.Entry<Integer, Pizza>[]) new Map.Entry[10]);
        for (int i = 0; i < settledPizzas.entrySet().toArray().length -1 ; i++) {
            result.append(settledPizzas.entrySet().toArray(
                    (Map.Entry<Integer, Pizza>[]) new Map.Entry[settledPizzas.entrySet().toArray().length])[i]
                    .getValue()
                    .getType()
            ).append(" ");
        }
        result.append(settledPizzas.entrySet().toArray(
                (Map.Entry<Integer, Pizza>[]) new Map.Entry[settledPizzas.entrySet().toArray().length])[settledPizzas.entrySet().toArray().length -1]
                .getValue()
                .getType()
        );

        return result.toString();
    }
}
