package hcms;
import java.io.*;
import java.util.Scanner;

//입력 받을 때 차 구분하는거완, 차 등록시 차 구분완, 출력 부분 수정, 돈계산 하는거 다형성

abstract class Car{
    Car(int no, int speed){
        this.no=no;
        this.speed=speed;
    }
    private int no;
    private int speed;

    private int money;
    private int onhighway;

    private int timeminute;
    private Time arrivaltime=new Time();
    private Time departtime=new Time();

    private String startpoint ="";
    private String destination="";
    protected int distance;
    private double position;

    Time getarrivaltTime(){
        return arrivaltime;
    }
    Time getdeparttime(){
        return departtime;
    }
    public void settime(final Time time,final Car car,int distance){
        this.departtime.setyear(time.getyear());
        this.departtime.setmonth(time.getmonth());
        this.departtime.setday(time.getday());
        this.departtime.sethour(time.gethour());
        this.departtime.setminute(time.getminute());
        calarrivaltime(car,distance);
    }
    public void calarrivaltime(final Car car,int distance){//도착시간계산완료 윤년, 매달마다 30일 31일 그거 해 완료
        int hour=0; int minute=0;
        timeminute=distance*60/car.getspeed();
        hour=timeminute/60;
        minute=timeminute%60;

        arrivaltime.setyear(departtime.getyear());
        arrivaltime.setmonth(departtime.getmonth());
        arrivaltime.setday(departtime.getday());
        arrivaltime.sethour(departtime.gethour());
        arrivaltime.setminute(departtime.getminute());
        arrivaltime.sethour(departtime.gethour()+hour);
        arrivaltime.setminute(departtime.getminute()+minute);

        if(arrivaltime.getminute()>59){
            arrivaltime.setminute(arrivaltime.getminute()-60);
            arrivaltime.sethour(arrivaltime.gethour()+1);
        }
        if(arrivaltime.gethour()>23){
            arrivaltime.sethour(arrivaltime.gethour()-24);
            arrivaltime.setday(arrivaltime.getday()+1);
        }
        if(arrivaltime.getmonth()==2){
            if(arrivaltime.getyear()%4==0){//윤년
                if(arrivaltime.getday()>29){
                    arrivaltime.setday(arrivaltime.getday()-29);
                    arrivaltime.setmonth(arrivaltime.getmonth()+1);
                }
            }else{
                if(arrivaltime.getday()>28){
                    arrivaltime.setday(arrivaltime.getday()-28);
                    arrivaltime.setmonth(arrivaltime.getmonth()+1);
                }
            }
        }else if(arrivaltime.getmonth()==4||arrivaltime.getmonth()==6||arrivaltime.getmonth()==9||arrivaltime.getmonth()==11){
            //30일
            if(arrivaltime.getday()>30){
                arrivaltime.setday(1);
                arrivaltime.setmonth(arrivaltime.getmonth()+1);
            }
        }else{
            //31일
            if(arrivaltime.getday()>31){
                arrivaltime.setday(1);
                arrivaltime.setmonth(arrivaltime.getmonth()+1);
            }
        }
        if(arrivaltime.getmonth()>12){
            arrivaltime.setyear(arrivaltime.getyear()+1);
            arrivaltime.setmonth(1);
        }
    }
    public int getno(){
        return no;
    }
    public void setno(int no){
        this.no = no;
    }
    public int getspeed(){
        return speed;
    }
    public void setspeed(int speed){
        this.speed = speed;
    }
    public int getmoney(){
        return money;
    }
    public void setmoney(int money){
        this.money = money;
    }
    public int getonhighway(){
        return onhighway;
    }
    public void setonhighway(int no){
        this.onhighway = no;
    }

    public double getposition(){
        return position;
    }
    public int getdistance(){
        return distance;
    }
    public void setpoint(String startpoint,String destination){
        this.startpoint=startpoint;
        this.destination=destination;
        if(startpoint.equals("서울")||destination.equals("서울")){
            if(startpoint.equals("수원")||destination.equals("수원")){
                this.distance = 30;
            }else if(startpoint.equals("대전")||destination.equals("대전")){
                this.distance = 130;
            }else  if(startpoint.equals("대구")||destination.equals("대구")){
                this.distance = 290;
            }else if(startpoint.equals("부산")||destination.equals("부산")){
                this.distance = 400;
            }
        }else if(startpoint.equals("수원")||destination.equals("수원")){
            if(startpoint.equals("대전")||destination.equals("대전")){
                this.distance = 100;
            }else if(startpoint.equals("대구")||destination.equals("대구")){
                this.distance = 260;
            }else if(startpoint.equals("부산")||destination.equals("부산")){
                this.distance = 370;
            }
        }else if(startpoint.equals("대전")||destination.equals("대전")){
            if(startpoint.equals("대구")||destination.equals("대구")){
                this.distance = 160;
            }else if(startpoint.equals("부산")||destination.equals("부산")){
                this.distance = 270;
            }
        }else if(startpoint.equals("대구")||destination.equals("대구")){//출발지 입력 오류설정하면 지워
            if(startpoint.equals("부산")||destination.equals("부산")){
                this.distance = 110;
            }
        }
    }
    public static double calposition(final Time departtime,final Time time){
        double currentmin=0;
        double departmin=0;
        Time current=new Time();
        current.setyear(time.getyear());
        current.setmonth(time.getmonth());
        current.setday(time.getday());
        current.sethour(time.gethour());
        current.setminute(time.getminute());
        if(current.getyear()>departtime.getyear()){
            if(departtime.getmonth()==12){
                current.setday(current.getday()+31);   
            }          
        }else if(current.getmonth()>departtime.getmonth()){
            if(departtime.getmonth()==4||departtime.getmonth()==6||departtime.getmonth()==9||departtime.getmonth()==11){
                current.setday(current.getday()+30); 
            }else if(departtime.getmonth()==2){
                if(departtime.getyear()%4==0){
                    current.setday(current.getday()+29);
                }else{
                    current.setday(current.getday()+28); 
                }
            }else{
                current.setday(current.getday()+31);   
            }
        }
        currentmin=current.getday()*1440+current.gethour()*60+current.getminute();
        departmin=departtime.getday()*1440+departtime.gethour()*60+departtime.getminute();
        return currentmin-departmin;
    }
    public void setposition(Car car,final Time time){
        if(this.startpoint.equals("부산")){
            this.position=400-car.getspeed()*calposition(this.departtime,time)/60;
        }else if(this.startpoint.equals("서울")){
            this.position=car.getspeed()*calposition(this.departtime,time)/60;
        }else if(this.startpoint.equals("수원")&&this.destination.equals("대전")){
            this.position=30+car.getspeed()*calposition(this.departtime,time)/60;
        }else if(this.startpoint.equals("수원")&&this.destination.equals("대구")){
            this.position=30+car.getspeed()*calposition(this.departtime,time)/60;
        }else if(this.startpoint.equals("수원")&&this.destination.equals("부산")){
            this.position=30+car.getspeed()*calposition(this.departtime,time)/60;
        }else if(this.startpoint.equals("대전")&&this.destination.equals("대구")){
            this.position=130+car.getspeed()*calposition(this.departtime,time)/60;
        }else if(this.startpoint.equals("대전")&&this.destination.equals("부산")){
            this.position=130+car.getspeed()*calposition(this.departtime,time)/60;
        }else if(this.startpoint.equals("대구")&&this.destination.equals("부산")){
            this.position=290+car.getspeed()*calposition(this.departtime,time)/60;
        }else if(this.startpoint.equals("대구")&&this.destination.equals("대전")){
            this.position=290-car.getspeed()*calposition(this.departtime,time)/60;
        }else if(this.startpoint.equals("대구")&&this.destination.equals("수원")){
            this.position=290-car.getspeed()*calposition(this.departtime,time)/60;
        }else if(this.startpoint.equals("대구")&&this.destination.equals("서울")){
            this.position=290-car.getspeed()*calposition(this.departtime,time)/60;
        }else if(this.startpoint.equals("대전")&&this.destination.equals("수원")){
            this.position=130-car.getspeed()*calposition(this.departtime,time)/60;
        }else if(this.startpoint.equals("대전")&&this.destination.equals("서울")){
            this.position=130-car.getspeed()*calposition(this.departtime,time)/60;
        }else if(this.startpoint.equals("수원")&&this.destination.equals("서울")){
            this.position=30-car.getspeed()*calposition(this.departtime,time)/60;
        }
    }  
    abstract public void calmoney(int basemoney,int howdistance);
}



class GasCar extends Car{
    protected int baegi;
    public GasCar(int a, int b, int c){
        super(a,b);
        this.baegi=c;
    }
    public void calmoney(int basemoney,int howdistance){
        double tempbaegi=1;
        int temp=1;
        if(baegi<1000) tempbaegi=0.8;
        else if(baegi<2000) tempbaegi=1.0;
        else if(baegi<3000) tempbaegi=1.2;
        else  tempbaegi=1.5;

        temp=(int)((basemoney+super.getdistance()*howdistance)*super.getspeed()/100*tempbaegi);
        temp=temp/10;
        temp=temp*10;
/*
        System.out.println((super.getdistance()));
        System.out.println((howdistance));
        System.out.println((super.getspeed()));
        System.out.println((tempbaegi));
        System.out.println((baegi));*/
        super.setmoney(temp);
    }
}
class HybridCar extends Car{
    protected int baegi;
    public HybridCar(int a, int b, int c){
        super(a,b);
        this.baegi=c;
    }
    public void calmoney(int basemoney,int howdistance){
        double tempbaegi=1;
        int temp=1;
        if(baegi<1000) tempbaegi=0.8;
        else if(baegi<2000) tempbaegi=1.0;
        else if(baegi<3000) tempbaegi=1.2;
        else  tempbaegi=1.5;

        temp=(int)((basemoney+super.getdistance()*howdistance)*super.getspeed()/100*tempbaegi/2);
        temp=temp/10;
        temp=temp*10;



        super.setmoney(temp);
    }
}

class Bus extends Car{
    private int passenger;
    public Bus(int a, int b, int c){
        super(a,b);
        this.passenger=c;
    }
    public void calmoney(int basemoney,int howdistance){
        double temppassenger=1;
        int temp=1;
        if(passenger<12) temppassenger=0.8;
        else if(passenger<20) temppassenger=1.0;
        else if(passenger<30) temppassenger=1.2;
        else  temppassenger=1.5;

        temp=(int)((basemoney+super.getdistance()*howdistance)*super.getspeed()/100*temppassenger);
        temp=temp/10;
        temp=temp*10;

        super.setmoney(temp);
    }
}

class Truck extends Car{
    private double weight;
    public Truck(int a, int b, double c){
        super(a,b);
        this.weight=c;
    }
    public void calmoney(int basemoney,int howdistance){
        double tempweight=1;
        int temp=1;
        if(weight<1) tempweight=0.8;
        else if(weight<2) tempweight=1.0;
        else if(weight<4) tempweight=1.2;
        else  tempweight=1.5;

        temp=(int)((basemoney+super.getdistance()*howdistance)*super.getspeed()/100*tempweight);
        
        temp=temp/10;
        temp=temp*10;
        

        super.setmoney(temp);
    }
}

class Time{
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    Time(int year, int month, int day, int hour,int minute){
        this.year=year; this.month=month;this.day=day;this.hour=hour;this.minute=minute;
    }
    Time(){
        this.year=0;this.month=0;this.day=0;this.hour=0;this.minute=0;
    }
    public void setyear(int year) {
        this.year = year;
    }
    public void setday(int day) {
        this.day = day;
    }
    public void sethour(int hour) {
        this.hour = hour;
    }
    public void setminute(int minute) {
        this.minute = minute;
    }
    public void setmonth(int month) {
        this.month = month;
    }
    public int getday() {
        return day;
    }
    public int gethour() {
        return hour;
    }
    public int getminute() {
        return minute;
    }
    public int getmonth() {
        return month;
    }
    public int getyear() {
        return year;
    }
    
}

public class hcms3_202126891{
    static int basemoney=0;
    static int howdistance=0;
    static int howcar=0;
    static Time time = new Time(2024,3,20,21,0);
//-----------------------------------------------------------------------------------------------------------  
    public static void onthehighway(Car[] car,int howcar){
        int num=1;
        for(int i=0;i<howcar;i++){
            if(car[i]!=null){
                if((comptimeon(car[i].getarrivaltTime(),time)==0)){
                    /*if(num==1){
                        System.out.printf("현재시간: %d/%02d/%02d-%02d:%02d\n",time.getyear(),time.getmonth(),time.getday(),time.gethour(),time.getminute());
                    }*/
                    if(car[i] instanceof GasCar){
                        System.out.printf("%d. 가솔린차 %d %.0fkm\n",num,car[i].getno(),car[i].getposition());
                    }
                    if(car[i] instanceof Bus){
                        System.out.printf("%d. 버스 %d %.0fkm\n",num,car[i].getno(),car[i].getposition());
                    }
                    if(car[i] instanceof Truck){
                        System.out.printf("%d. 트럭 %d %.0fkm\n",num,car[i].getno(),car[i].getposition());
                    }
                    if(car[i] instanceof HybridCar){
                        System.out.printf("%d. 하이브리드차 %d %.0fkm\n",num,car[i].getno(),car[i].getposition());
                    }
                    num++;
                }
            }
          

        }
        if(num==1){
            System.out.println("통행 차량이 없습니다!");
        }
    }
//-----------------------------------------------------------------------------------------------------------  
    public static void exithighway(Car[] car,int howcar,Time pasttime){
        int num=1;
        for(int i=0;i<howcar;i++){
            if(car[i]!=null){
                if((comptime(car[i].getarrivaltTime(),pasttime)==0)&&(comptime(time,car[i].getarrivaltTime())==0)){
                    /*if(num==1){
                        System.out.printf("현재시간: %d/%02d/%02d-%02d:%02d\n",time.getyear(),time.getmonth(),time.getday(),time.gethour(),time.getminute());
                    }*/
                    //System.out.printf("%d. %d %d/%02d/%02d-%02d:%02d %d원\n",num,car[i].getno(),car[i].getarrivaltTime().getyear(),car[i].getarrivaltTime().getmonth(),car[i].getarrivaltTime().getday(),car[i].getarrivaltTime().gethour(),car[i].getarrivaltTime().getminute(),car[i].getmoney());
                    if(car[i] instanceof GasCar){
                        System.out.printf("%d. 가솔린차 %d %d/%02d/%02d-%02d:%02d %d원\n",num,car[i].getno(),car[i].getarrivaltTime().getyear(),car[i].getarrivaltTime().getmonth(),car[i].getarrivaltTime().getday(),car[i].getarrivaltTime().gethour(),car[i].getarrivaltTime().getminute(),car[i].getmoney());                }
                    if(car[i] instanceof Bus){
                        System.out.printf("%d. 버스 %d %d/%02d/%02d-%02d:%02d %d원\n",num,car[i].getno(),car[i].getarrivaltTime().getyear(),car[i].getarrivaltTime().getmonth(),car[i].getarrivaltTime().getday(),car[i].getarrivaltTime().gethour(),car[i].getarrivaltTime().getminute(),car[i].getmoney());                }
                    if(car[i] instanceof Truck){
                        System.out.printf("%d. 트럭 %d %d/%02d/%02d-%02d:%02d %d원\n",num,car[i].getno(),car[i].getarrivaltTime().getyear(),car[i].getarrivaltTime().getmonth(),car[i].getarrivaltTime().getday(),car[i].getarrivaltTime().gethour(),car[i].getarrivaltTime().getminute(),car[i].getmoney());                }
                    if(car[i] instanceof HybridCar){
                        System.out.printf("%d. 하이브리드차 %d %d/%02d/%02d-%02d:%02d %d원\n",num,car[i].getno(),car[i].getarrivaltTime().getyear(),car[i].getarrivaltTime().getmonth(),car[i].getarrivaltTime().getday(),car[i].getarrivaltTime().gethour(),car[i].getarrivaltTime().getminute(),car[i].getmoney());                }
                    num++;
                }
            }
            
        }
        if(num==1){
            System.out.println("진출한 차량이 없습니다!");
        }
    }

//시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간

//-----------------------------------------------------------------------------------------------------------
    public static int timeerrorcheck(String[] str){//시간 오류 검사
        if(timepastcheck(str)==1){//시간 과거, 음수 확인
            return 1;//실패
        }
        if(timeupcheck(str)==1){//월 12이상, 날짜가 30,31 이런거 보다 크기, 시간 더큰거 검사
            return 1;//실패
        }
        return 0;
    }
//----------------------------------------------------------------------------------------------------------- 
    public static int timepastcheck(String[] str){
        if(Integer.parseInt(str[1])<time.getyear()){//년 과거/음수 검사->미래년에서 다른거 음수인지검사
            if(Integer.parseInt(str[1])<0){
                System.out.println("년이 잘못 입력되었습니다");
            }else{
                System.out.println("과거시간(년)이 입력되었습니다");
            }
            return 1;
        }else if(Integer.parseInt(str[1])==time.getyear()){
            if(Integer.parseInt(str[2])<time.getmonth()){
                if(Integer.parseInt(str[2])<0){
                    System.out.println("월이 잘못 입력되었습니다");
                }else{
                    System.out.println("과거시간(월)이 입력되었습니다");
                }
                return 1;
            }else if(Integer.parseInt(str[2])==time.getmonth()){
                if(Integer.parseInt(str[3])<time.getday()){
                    if(Integer.parseInt(str[3])<0){
                        System.out.println("일이 잘못 입력되었습니다");
                    }else{
                        System.out.println("과거시간(일)이 입력되었습니다");
                    }
                    return 1;
                }else if(Integer.parseInt(str[3])==time.getday()){
                    if(Integer.parseInt(str[4])<time.gethour()){
                        if(Integer.parseInt(str[4])<0){
                            System.out.println("시가 잘못 입력되었습니다");
                        }else{
                            System.out.println("과거시간(시)이 입력되었습니다");
                        }
                        return 1;
                    }else if(Integer.parseInt(str[4])==time.gethour()){
                        if(Integer.parseInt(str[5])<time.getminute()){
                            if(Integer.parseInt(str[5])<0){
                                System.out.println("분이 잘못 입력되었습니다");
                            }else{
                                System.out.println("과거시간(분)이 입력되었습니다");
                            }
                        }else if(Integer.parseInt(str[5])==time.getminute()){
                            System.out.println("현재시간이 입력되었습니다");
                            return 1;
                        }
                    }
                }
            }
        }
        return 0;
    }
 //-----------------------------------------------------------------------------------------------------------
    public static int timeupcheck(String[] str){
        if(Integer.parseInt(str[2])<13){
            if(Integer.parseInt(str[2])==2){//2월 0t 1년 2월 3일 4시간 5분
                if(Integer.parseInt(str[1])%4==0){//윤년 검사
                    if(Integer.parseInt(str[3])>29){
                        System.out.println("날짜가 잘못 입력되었습니다");
                        return 1;
                    }
                }else{
                    if(Integer.parseInt(str[3])>28){
                        System.out.println("날짜가 잘못 입력되었습니다");
                        return 1;
                    }
                }
            }else if((Integer.parseInt(str[2])==1)||(Integer.parseInt(str[2])==3)||(Integer.parseInt(str[2])==5)||(Integer.parseInt(str[2])==7)||(Integer.parseInt(str[2])==8)||(Integer.parseInt(str[2])==10)||(Integer.parseInt(str[2])==12)){
                //31일
                if(Integer.parseInt(str[3])>31){
                    System.out.println("날짜가 잘못 입력되었습니다");
                    return 1;
                }

            }else{//30일
                if(Integer.parseInt(str[3])>30){
                    System.out.println("날짜가 잘못 입력되었습니다");
                    return 1;
                }
            }
            if(Integer.parseInt(str[4])>23){
                System.out.println("시가 잘못 입력되었습니다");
                return 1;
            }
            if(Integer.parseInt(str[5])>59){
                System.out.println("분이 잘못 입력되었습니다");
                return 1;
            }
        }else{
            System.out.println("월이 잘못 입력되었습니다");
            return 1;
        }
        return 0;
    }
    public static int comptime(final Time time1, final Time time2){
        if(time1.getyear()>time2.getyear()){
            return 0;
        }else if(time1.getyear()==time2.getyear()){
            if(time1.getmonth()>time2.getmonth()){
                return 0;
            }else if(time1.getmonth()==time2.getmonth()){
                if(time1.getday()>time2.getday()){
                    return 0;
                }else if(time1.getday()==time2.getday()){
                    if(time1.gethour()>time2.gethour()){
                        return 0;
                    }else if(time1.gethour()==time2.gethour()){
                        if(time1.getminute()>=time2.getminute()){
                            return 0;
                        }
                    }
                }
            }
        }
        return 1;
    }
    public static int comptimeon(final Time time1, final Time time2){
        if(time1.getyear()>time2.getyear()){
            return 0;
        }else if(time1.getyear()==time2.getyear()){
            if(time1.getmonth()>time2.getmonth()){
                return 0;
            }else if(time1.getmonth()==time2.getmonth()){
                if(time1.getday()>time2.getday()){
                    return 0;
                }else if(time1.getday()==time2.getday()){
                    if(time1.gethour()>time2.gethour()){
                        return 0;
                    }else if(time1.gethour()==time2.gethour()){
                        if(time1.getminute()>time2.getminute()){
                            return 0;
                        }
                    }
                }
            }
        }
        return 1;
    }
//------------------------------------------------메인--------------------------------------------------
    public static Scanner sc=new Scanner(System.in);
    public static void main(String[] args)throws IOException{
        Scanner in = new Scanner(new File("hcms.txt"),"UTF-8");
        basemoney = in.nextInt();
        howdistance = in.nextInt();
        howcar=in.nextInt();
        in.nextLine();

        Car[] car = new Car[20];

        for(int i=0;i<howcar;i++){//여기 나눠야함
            String[] arr;
            arr=in.nextLine().split(" ");
            if(arr[0].charAt(0)=='g'){
                car[i]=new GasCar(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]));
            }
            if(arr[0].charAt(0)=='b'){
                car[i]=new Bus(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]));
            }
            if(arr[0].charAt(0)=='t'){
                car[i]=new Truck(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]));
            }
            if(arr[0].charAt(0)=='h'){
                car[i]=new HybridCar(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]));
            }
        }

        Time pasttime=new Time();
        in.close();
        
        while(true){
            String[] str;
            str= sc.nextLine().split(" ");
            if(str[0].charAt(0)=='q'){//정지
                if(str.length!=1){
                    System.out.println("입력이 잘못 되었습니다");
                    continue;

                }
                sc.close();
                System.exit(0);
            }
            else if(str[0].charAt(0)=='t'){//시간 설정
                if(str.length!=6){
                    System.out.println("시간 입력이 잘못 되었습니다");
                    continue;
                }

                if(timeerrorcheck(str)==1){
                    continue;
                }

                pasttime.setyear(time.getyear());
                pasttime.setmonth(time.getmonth());
                pasttime.setday(time.getday());
                pasttime.sethour(time.gethour());
                pasttime.setminute(time.getminute());

                time.setyear(Integer.parseInt(str[1]));
                time.setmonth(Integer.parseInt(str[2]));
                time.setday(Integer.parseInt(str[3]));
                time.sethour(Integer.parseInt(str[4]));
                time.setminute(Integer.parseInt(str[5]));
//여ㅛ기 수정 돈계산 하는데 ------------------------------------------------------------------------------------------------------
                /*for(int i=0;i<howcar;i++){
                    if(car[i].getonhighway()==1 && comptime(time,car[i].getarrivaltTime())==0){//도착한 차량
                        car[i].setonhighway(0);
                        car[i].calmoney(basemoney,howdistance);
                    }else if(car[i].getonhighway()==1){
                        car[i].setposition(car[i],time);
                    }
                }*/
                for (int i = 0; i < howcar; i++) {
                    if (car[i] != null) {
                        if (car[i].getonhighway() == 1 && comptime(time, car[i].getarrivaltTime()) == 0) { // 도착한 차량
                            car[i].setonhighway(0);
                            car[i].calmoney(basemoney, howdistance);
                        } else if (car[i].getonhighway() == 1) {
                            car[i].setposition(car[i], time);
                        }
                    }
                }
                continue;
            }
            else if(str[0].charAt(0)=='n'){//차 설정
                int a=0;
                if(str.length!=4){
                    System.out.println("차 입력이 잘못 되었습니다");
                    continue;
                }
                if(str[2].equals(str[3])){
                    System.out.println("출발지와 목적지가 같습니다");
                    continue;
                }
                if(str[2].equals("서울")||str[2].equals("수원")||str[2].equals("대전")||str[2].equals("대구")||str[2].equals("부산")){
                    if(str[3].equals("서울")||str[3].equals("수원")||str[3].equals("대전")||str[3].equals("대구")||str[3].equals("부산")){
                    }else{
                        System.out.println("목적지 입력이 잘못 되었습니다");
                        continue;
                    }
                }else{
                    System.out.println("출발지 입력이 잘못 되었습니다");
                    continue;
                }
                
                for(int i=0;i<howcar;i++){
                    if (car[i] != null) {
                        if(car[i].getno()==Integer.parseInt(str[1])){///////////////////////////////////////////////////////
                            if(car[i].getonhighway()==0){//이미 있는차인지 검사
                                car[i].setpoint(str[2],str[3]);
                                car[i].setonhighway(1);
                                car[i].settime(time,car[i],car[i].getdistance());
                                car[i].setposition(car[i],time);
                                a=1;
                                break;
                            }else{
                                a=2;
                                break;
                            }
                        }
                    }/*
                    if(car[i].getno()==Integer.parseInt(str[1])){///////////////////////////////////////////////////////
                        if(car[i].getonhighway()==0){//이미 있는차인지 검사
                            car[i].setpoint(str[2],str[3]);
                            car[i].setonhighway(1);
                            car[i].settime(time,car[i],car[i].getdistance());
                            car[i].setposition(car[i],time);
                            a=1;
                            break;
                        }else{
                            a=2;
                            break;
                        }
                    }*/
                }
                if(a==1){
                    continue;
                }else if(a==2){
                    System.out.println("이미 고속도로에 있는 차입니다.");
                    continue;
                }
                System.out.println("잘못된 차 번호입니다");
                continue;
            }
            else if(str[0].charAt(0)=='r'){//새차 등록
                if(str.length!=5){
                    System.out.println("입력이 잘못 되었습니다");
                    continue;
                }
                for(int i=0;i<howcar;i++){
                    if(car[i].getno()==(Integer.parseInt(str[2]))){
                        System.out.println("이미 등록된 차량입니다");
                    }
                }
                if(str[1].charAt(0)=='g'){
                    car[howcar]=new GasCar(Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4]));
                }
                if(str[1].charAt(0)=='b'){
                    car[howcar]=new Bus(Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4]));
                }
                if(str[1].charAt(0)=='t'){
                    car[howcar]=new Truck(Integer.parseInt(str[2]),Integer.parseInt(str[3]),Double.parseDouble(str[4]));
                }
                if(str[1].charAt(0)=='h'){
                    car[howcar]=new HybridCar(Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4]));
                }
                howcar++;
            }
            else if(str[0].charAt(0)=='o'){//고속도로에 있는 차 보기
                if(str.length!=1){
                    System.out.println("입력이 잘못 되었습니다");
                    continue;
                }
                onthehighway(car,howcar);
            }
            else if(str[0].charAt(0)=='x'){//친출한 차량보기
                if(str.length!=1){
                    System.out.println("입력이 잘못 되었습니다");
                    continue;
                }
                exithighway(car,howcar,pasttime);
            }
            else{
                System.out.println("입력이 잘못 되었습니다");
                continue;
            }
        }
    }
}