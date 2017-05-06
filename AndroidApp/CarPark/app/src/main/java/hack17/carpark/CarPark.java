package hack17.carpark;


public class CarPark {

    String name;
    int totalSlots, freeSlots;

    public CarPark(){

    }

    public CarPark(String name, int freeSlots, int totalSlots){
        this.name = name;
        this.freeSlots = freeSlots;
        this.totalSlots = totalSlots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getFreeSlots() {
        return freeSlots;
    }

    public void setFreeSlots(int freeSlots) {
        this.freeSlots = freeSlots;
    }
}
