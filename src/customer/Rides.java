package customer;
//class rides
public class Rides {
//    attributes
    int carNo,user_id,hour,min,sec,day,month,year;
//getter for car number
    public int getCarNo() {
        return carNo;
    }
//constructor
    public Rides(int carNo,int user_id,int hour,int min,int sec,int day,int month,int year) {
        this.carNo = carNo;
        this.user_id=user_id;
        this.hour=hour;
        this.min=min;
        this.sec=sec;
        this.day=day;
        this.month=month;
        this.year=year;
    }
//getters and setters
    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getSec() {
        return sec;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth(){
        return this.month;
    }
    public void setCarNo(int carNo) {
        this.carNo = carNo;
    }
}
