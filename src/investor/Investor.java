package investor;

import javafx.scene.control.TableColumn;
//class investor
public class Investor {
    int earning;
//earning
    public int getEarning() {
        return earning;
    }
//getter and setter
    public void setEarning(int earning) {
        this.earning = earning;
    }
    public Investor(int earnings) {
        this.earning = earnings;
    }
}
