package src;


public class Sample {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private double tempertaure;
    private int humidity;



    public Sample(int year, int month, int day, int hour, int min, double tempertaure, int humidity) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = min;
        this.tempertaure = tempertaure;
        this.humidity = humidity;
    }
    
    
    
    public double getTempertaure() {
        return tempertaure;
    }
    public int getHumidity() {
        return humidity;
    }
    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }
    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }
    
}
