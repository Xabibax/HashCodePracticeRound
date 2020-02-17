public class Pizza {
    public int type;
    public int slices;

    public Pizza(int type, int slices) {
        this.type = type;
        this.slices = slices;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSlices() {
        return slices;
    }

    public void setSlices(int slices) {
        this.slices = slices;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "type=" + type +
                ", slices=" + slices +
                '}';
    }
}
