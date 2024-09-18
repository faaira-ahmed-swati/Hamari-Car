package BookCar;
// a car class,
public class Car {
    String sector;
    String type;
    int model;
    int number;
    String color;
    int available;
//attributed
    public Car(String sector, String type, int model, int number, String color, int available) {
        this.sector = sector;
        this.type = type;
        this.model = model;
        this.number = number;
        this.color = color;
        this.available = available;
    }
// constructor
//    getters and setters
    public int isAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
