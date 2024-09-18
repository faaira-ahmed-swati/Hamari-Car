package customer;
//booking history class
public class BookingHist {
    int carNo,user_id,hour,min,sec,day,month,year,amount;
//attributes
//    getter for car no
    public int getCarNo() {
        return carNo;
    }
//constructor
    public BookingHist(int carNo,int user_id,int hour,int min,int sec,int day,int month,int year,int amount) {
        this.carNo = carNo;
        this.user_id=user_id;
        this.hour=hour;
        this.min=min;
        this.sec=sec;
        this.day=day;
        this.month=month;
        this.year=year;
        this.amount=amount;
    }
//setter and getters
    public void setCarNo(int carNo) {
        this.carNo = carNo;
    }
    public int getAmount(){
        return this.amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

}
