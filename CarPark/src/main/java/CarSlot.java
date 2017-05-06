
public class CarSlot {

    int slotNumber;
    int isFreeSlot;

    public CarSlot(){

    }

    public CarSlot(int slot, int isFree){

        this.slotNumber = slot;
        this.isFreeSlot = isFree;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getIsFreeSlot() {
        return isFreeSlot;
    }

    public void setIsFreeSlot(int isFreeSlot) {
        this.isFreeSlot = isFreeSlot;
    }

}
