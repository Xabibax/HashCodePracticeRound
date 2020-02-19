import java.util.HashMap;
import java.util.Map;

public class InputDataSet {
    public int participants;
    public int nbPizzaType;
    public Map<Integer, Pizza> mapPizza;

    public InputDataSet() {
        this.participants = 0;
        this.nbPizzaType = 0;
        this.mapPizza = new HashMap<>();
    }

    public InputDataSet(int participants) {
        this.participants = participants;
        this.nbPizzaType = 0;
        this.mapPizza = new HashMap<>();
    }

    public InputDataSet(int participants, int nbPizzaType, Map<Integer, Pizza> mapPizza) {
        this.participants = participants;
        this.nbPizzaType = nbPizzaType;
        this.mapPizza = mapPizza;
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

    public Map<Integer, Pizza> getMapPizza() {
        return mapPizza;
    }

    public void setMapPizza(Map<Integer, Pizza> mapPizza) {
        this.mapPizza = mapPizza;
    }

    public void addAPizza(Pizza pizza) {
        this.nbPizzaType++;
        this.mapPizza.put(pizza.type, pizza);
    }

    @Override
    public String toString() {
        return "InputDataSet{" +
                "participants=" + participants +
                ", nbPizzaType=" + nbPizzaType +
                ", mapPizza=" + mapPizza +
                '}';
    }

    public String getNbSlicesByType() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.getMapPizza().entrySet().toArray().length; i++) {
            if (i == this.getMapPizza().entrySet().toArray().length -1)
                result.append(this.getMapPizza().entrySet().toArray(
                        (Map.Entry<Integer, Pizza>[]) new Map.Entry[this.getMapPizza().entrySet().toArray().length])[i]
                        .getValue()
                        .getSlices()
                );
            else
                result.append(this.getMapPizza().entrySet().toArray(
                        (Map.Entry<Integer, Pizza>[]) new Map.Entry[this.getMapPizza().entrySet().toArray().length])[i]
                        .getValue()
                        .getSlices()
                ).append(" ");
        }
        return result.toString();
    }

    public String toDataSet() {
        return new String(participants + " " + this.getNbPizzaType() + System.lineSeparator() +
                this.getNbSlicesByType()
                );
    }
}
