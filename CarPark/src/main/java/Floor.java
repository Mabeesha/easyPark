

public class Floor {

    int floor, free, total;

    public Floor(){

    }

    public Floor(int floor, int free, int total){
        this.floor = floor;
        this.free = free;
        this.total = total;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
