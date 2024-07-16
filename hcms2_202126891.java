package hcms;
import java.io.*;
import java.util.Scanner;

class Car{
    Car(int no, int baegi, int speed){
        this.no=no;
        this.speed=speed;
        this.baegi=baegi;
    }
    private int no;
    private int speed;
    private int baegi;
    private int money;
    private int onhighway;
    int getno(){
        return no;
    }
    void setno(int no){
        this.no = no;
    }
    int getspeed(){
        return speed;
    }
    void setspeed(int speed){
        this.speed = speed;
    }
    int getbaegi(){
        return baegi;
    }
    void setbaegi(int baegi){
        this.baegi = baegi;
    }
    int getmoney(){
        return money;
    }
    void setmoney(int money){
        this.money = money;
    }
    int getonhighway(){
        return onhighway;
    }
    void setonhighway(int no){
        this.onhighway = no;
    }
}

class CarTime{
    private int timeminute;
    private Time arrivaltime=new Time();
    private Time departtime=new Time();
    Time getarrivaltTime(){
        return arrivaltime;
    }
    Time getdeparttime(){
        return departtime;
    }
    void settime(final Time time,final Car car,int distance){
        this.departtime.setyear(time.getyear());
        this.departtime.setmonth(time.getmonth());
        this.departtime.setday(time.getday());
        this.departtime.sethour(time.gethour());
        this.departtime.setminute(time.getminute());
        calarrivaltime(car,distance);
    }
    void calarrivaltime(final Car car,int distance){//도착시간계산완료 윤년, 매달마다 30일 31일 그거 해 완료
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
}

class CarLoc{
    private String startpoint ="";
    private String destination="";
    private int distance;
    private double position;
    double getposition(){
        return position;
    }
    int getdistance(){
        return distance;
    }
    void setpoint(String startpoint,String destination){
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
    void setposition(Car car,final CarTime cartime,final Time time){
        if(this.startpoint.equals("부산")){
            this.position=400-car.getspeed()*calposition(cartime.getdeparttime(),time)/60;
        }else if(this.startpoint.equals("서울")){
            this.position=car.getspeed()*calposition(cartime.getdeparttime(),time)/60;
        }else if(this.startpoint.equals("수원")&&this.destination.equals("대전")){
            this.position=30+car.getspeed()*calposition(cartime.getdeparttime(),time)/60;
        }else if(this.startpoint.equals("수원")&&this.destination.equals("대구")){
            this.position=30+car.getspeed()*calposition(cartime.getdeparttime(),time)/60;
        }else if(this.startpoint.equals("수원")&&this.destination.equals("부산")){
            this.position=30+car.getspeed()*calposition(cartime.getdeparttime(),time)/60;
        }else if(this.startpoint.equals("대전")&&this.destination.equals("대구")){
            this.position=130+car.getspeed()*calposition(cartime.getdeparttime(),time)/60;
        }else if(this.startpoint.equals("대전")&&this.destination.equals("부산")){
            this.position=130+car.getspeed()*calposition(cartime.getdeparttime(),time)/60;
        }else if(this.startpoint.equals("대구")&&this.destination.equals("부산")){
            this.position=290+car.getspeed()*calposition(cartime.getdeparttime(),time)/60;
        }else if(this.startpoint.equals("대구")&&this.destination.equals("대전")){
            this.position=290-car.getspeed()*calposition(cartime.getdeparttime(),time)/60;
        }else if(this.startpoint.equals("대구")&&this.destination.equals("수원")){
            this.position=290-car.getspeed()*calposition(cartime.getdeparttime(),time)/60;
        }else if(this.startpoint.equals("대구")&&this.destination.equals("서울")){
            this.position=290-car.getspeed()*calposition(cartime.getdeparttime(),time)/60;
        }else if(this.startpoint.equals("대전")&&this.destination.equals("수원")){
            this.position=130-car.getspeed()*calposition(cartime.getdeparttime(),time)/60;
        }else if(this.startpoint.equals("대전")&&this.destination.equals("서울")){
            this.position=130-car.getspeed()*calposition(cartime.getdeparttime(),time)/60;
        }else if(this.startpoint.equals("수원")&&this.destination.equals("서울")){
            this.position=30-car.getspeed()*calposition(cartime.getdeparttime(),time)/60;
        }
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

public class hcms2_202126891{
    static int basemoney=0;
    static int howdistance=0;
    static int howcar=0;
    static Time time = new Time(2024,3,20,21,0);
//-----------------------------------------------------------------------------------------------------------  
    public static void onthehighway(Car[] car,CarLoc[] carloc, CarTime[] cartime,int howcar){
        int num=1;
        for(int i=0;i<howcar;i++){
            if((comptimeon(cartime[i].getarrivaltTime(),time)==0)){
                /*if(num==1){
                    System.out.printf("현재시간: %d/%02d/%02d-%02d:%02d\n",time.getyear(),time.getmonth(),time.getday(),time.gethour(),time.getminute());
                }*/
                System.out.printf("%d. %d %.0fkm\n",num,car[i].getno(),carloc[i].getposition());
                num++;
            }

        }
        if(num==1){
            System.out.printf("%d/%02d/%02d-%02d:%02d에 통행 차량이 없습니다!\n",time.getyear(),time.getmonth(),time.getday(),time.gethour(),time.getminute());
        }
    }
//-----------------------------------------------------------------------------------------------------------  
    public static void exithighway(Car[] car,CarLoc[] carloc, CarTime[] cartime,int howcar,Time pasttime){
        int num=1;
        for(int i=0;i<howcar;i++){
            if((comptime(cartime[i].getarrivaltTime(),pasttime)==0)&&(comptime(time,cartime[i].getarrivaltTime())==0)){
                /*if(num==1){
                    System.out.printf("현재시간: %d/%02d/%02d-%02d:%02d\n",time.getyear(),time.getmonth(),time.getday(),time.gethour(),time.getminute());
                }*/
                System.out.printf("%d. %d %d/%02d/%02d-%02d:%02d %d원\n",num,car[i].getno(),cartime[i].getarrivaltTime().getyear(),cartime[i].getarrivaltTime().getmonth(),cartime[i].getarrivaltTime().getday(),cartime[i].getarrivaltTime().gethour(),cartime[i].getarrivaltTime().getminute(),car[i].getmoney());

                num++;
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
        Scanner in = new Scanner(new File("hcms1.txt"),"UTF-8");
        basemoney = in.nextInt();
        howdistance = in.nextInt();
        howcar=in.nextInt();

        Car[] car = new Car[10000];
        CarTime[] cartime = new CarTime[10000];
        CarLoc[] carloc=new CarLoc[10000];
        for(int i=0;i<howcar;i++){
            car[i]=new Car(in.nextInt(),in.nextInt(),in.nextInt());
            cartime[i]=new CarTime();
            carloc[i]=new CarLoc();
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

                for(int i=0;i<howcar;i++){
                    if(car[i].getonhighway()==1 && comptime(time,cartime[i].getarrivaltTime())==0){//도착한 차량
                        car[i].setonhighway(0);
                        car[i].setmoney((basemoney+carloc[i].getdistance()*howdistance)*car[i].getspeed()/100*car[i].getbaegi()/2000);
                        car[i].setmoney(car[i].getmoney()/10);
                        car[i].setmoney(car[i].getmoney()*10);
                        System.out.println(basemoney);
                        System.out.println(carloc[i].getdistance());
                        System.out.println(howdistance);
                        System.out.println(car[i].getspeed());
                        System.out.println(car[i].getbaegi());

                    }else if(car[i].getonhighway()==1){
                        carloc[i].setposition(car[i],cartime[i],time);
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
                    if(car[i].getno()==Integer.parseInt(str[1])){///////////////////////////////////////////////////////
                        if(car[i].getonhighway()==0){//이미 있는차인지 검사
                            carloc[i].setpoint(str[2],str[3]);
                            car[i].setonhighway(1);
                            cartime[i].settime(time,car[i],carloc[i].getdistance());
                            carloc[i].setposition(car[i],cartime[i],time);
                            a=1;
                            break;
                        }else{
                            a=2;
                            break;
                        }
                    }
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
                if(str.length!=4){
                    System.out.println("입력이 잘못 되었습니다");
                    continue;
                }
                for(int i=0;i<howcar;i++){
                    if(car[i].getno()==(Integer.parseInt(str[1]))){
                        System.out.println("이미 등록된 차량입니다");
                    }
                }
                car[howcar]=new Car(Integer.parseInt(str[1]),Integer.parseInt(str[2]),Integer.parseInt(str[3]));
                carloc[howcar]=new CarLoc();
                cartime[howcar]=new CarTime();
                howcar++;
            }
            else if(str[0].charAt(0)=='o'){//고속도로에 있는 차 보기
                if(str.length!=1){
                    System.out.println("입력이 잘못 되었습니다");
                    continue;
                }
                onthehighway(car,carloc,cartime,howcar);
            }
            else if(str[0].charAt(0)=='x'){//친출한 차량보기
                if(str.length!=1){
                    System.out.println("입력이 잘못 되었습니다");
                    continue;
                }
                exithighway(car,carloc,cartime,howcar,pasttime);
            }
            else{
                System.out.println("입력이 잘못 되었습니다");
                continue;
            }
        }
    }
}