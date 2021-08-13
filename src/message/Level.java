package message;

/**
 * Level of difficulties
 */
public class Level extends Message {
    private String lHigh = "HIGH";
    private String lLow = "LOW";
    private String lMedium = "MEDIUM";
    private String lHell = "HELL";
    private int signal;

    /**
     * Will check which level the user chose
     * @param signal number representation of level
     */
    public Level(int signal){

        this.signal = signal;
    }

    /**
     * set number to each signal
     * @return number representation of level
     */
    public int getCurrentLevel(){
        if(signal == 1)
            return 10;
        if(signal == 2)
            return 30;
        if(signal == 3)
            return 60;
        if(signal == 4)
            return 70;
        return -1;
    }
}
