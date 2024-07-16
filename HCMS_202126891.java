package hcms;
import java.io.*;
import java.util.Scanner;

class Car{
    int no;
    int speed;
    double position;
    int baegi;
    int distance;
    int money;
    int timeminute;
    int onhighway;
    int highway;
    Time arrivaltime = new Time();
    Time departtime = new Time();
    String startpoint ="";
    String destination="";
}

class Time{
    int year;
    int month;
    int day;
    int hour;
    int minute;
}

public class HCMS_202126891{
    static int basemoney=0;
    static int howdistance=0;
    static int howcar=0;
    static Time time = new Time();

//-----------------------------------------------------------------------------------------------------------
    public static void carset(Car car,String[] str){//차 설정
        car.startpoint=str[2];
        car.destination=str[3];
        car.highway=1;
        car.onhighway=1;

        car.departtime.year=time.year;
        car.departtime.month=time.month;
        car.departtime.day=time.day;
        car.departtime.hour=time.hour;
        car.departtime.minute=time.minute;
        car.distance=getdistance(car.startpoint,car.destination);
        car.arrivaltime = caltime(car);
    }

//-----------------------------------------------------------------------------------------------------------
    public static int getdistance(String startpoint, String destination){//거리계산
        int distance=0;

        if(startpoint.equals("서울")||destination.equals("서울")){
            if(startpoint.equals("수원")||destination.equals("수원")){
                return 30;
            }else if(startpoint.equals("대전")||destination.equals("대전")){
                return 130;
            }else  if(startpoint.equals("대구")||destination.equals("대구")){
                return  290;
            }else if(startpoint.equals("부산")||destination.equals("부산")){
                return 400;
            }
        }else if(startpoint.equals("수원")||destination.equals("수원")){
            if(startpoint.equals("대전")||destination.equals("대전")){
                return  100;
            }else if(startpoint.equals("대구")||destination.equals("대구")){
                return 260;
            }else if(startpoint.equals("부산")||destination.equals("부산")){
                return 370;
            }
        }else if(startpoint.equals("대전")||destination.equals("대전")){
            if(startpoint.equals("대구")||destination.equals("대구")){
                return 160;
            }else if(startpoint.equals("부산")||destination.equals("부산")){
                return 270;
            }
        }else if(startpoint.equals("대구")||destination.equals("대구")){//출발지 입력 오류설정하면 지워
            if(startpoint.equals("부산")||destination.equals("부산")){
                return  110;
            }
        }
        return distance;
    }
//-----------------------------------------------------------년 월 같게 만들고 일,시 분으로 변환
    public static double calposition(Time departtime){
        double currentmin=0;
        double departmin=0;
        Time current=new Time();

        current.year=time.year;
        current.month=time.month;
        current.day=time.day;
        current.hour=time.hour;
        current.minute=time.minute;

        if(current.year>departtime.year){
            if(departtime.month==12){
                current.day+=31;   
            }
                     
        }else if(current.month>departtime.month){
            if(departtime.month==4||departtime.month==6||departtime.month==9||departtime.month==11){
                current.day+=30;
            }else if(departtime.month==2){
                if(departtime.year%4==0){
                    if(departtime.year%400!=0&&departtime.year%100==0){
                        current.day+=28;
                    }else{
                        current.day+=29;
                    }
                }else{
                    current.day+=28;
                }
            }else{
                current.day+=31;
            }
        }
        //변환...
        currentmin=current.day*1440+current.hour*60+current.minute;
        departmin=departtime.day*1440+departtime.hour*60+departtime.minute;
        return currentmin-departmin;
    }
   
    public static void onthehighway(Car[] car,int howcar){
        int num=1;
        for(int i=0;i<howcar;i++){
            if((comptimeon(car[i].arrivaltime,time)==0)){
                if(num==1){
                    System.out.printf("현재시간: %d/%02d/%02d-%02d:%02d\n",time.year,time.month,time.day,time.hour,time.minute);
                }
                System.out.printf("%d. %d %dcc %dkm %s->%s %d/%02d/%02d-%02d:%02d 위치:%.0fkm\n",num,car[i].no,car[i].baegi,car[i].speed,car[i].startpoint,car[i].destination,car[i].departtime.year,car[i].departtime.month,car[i].departtime.day,car[i].departtime.hour,car[i].departtime.minute,car[i].position);
                num++;
            }

        }
        if(num==1){
            System.out.printf("%d/%02d/%02d-%02d:%02d에 통행 차량이 없습니다!\n",time.year,time.month,time.day,time.hour,time.minute);
        }
    }
//-----------------------------------------------------------------------------------------------------------  
    public static void exithighway(Car[]car,int howcar,Time pasttime){
        int num=1;
        for(int i=0;i<howcar;i++){
            if((comptime(car[i].arrivaltime,pasttime)==0)&&(comptime(time,car[i].arrivaltime)==0)){
                if(num==1){
                    System.out.printf("현재시간: %d/%02d/%02d-%02d:%02d\n",time.year,time.month,time.day,time.hour,time.minute);
                }
                System.out.printf("%d. %d %dcc %dkm %s->%s %d/%02d/%02d-%02d:%02d %d/%02d/%02d-%02d:%02d %d원\n",num,car[i].no,car[i].baegi,car[i].speed,car[i].startpoint,car[i].destination,car[i].departtime.year,car[i].departtime.month,car[i].departtime.day,car[i].departtime.hour,car[i].departtime.minute,car[i].arrivaltime.year,car[i].arrivaltime.month,car[i].arrivaltime.day,car[i].arrivaltime.hour,car[i].arrivaltime.minute,car[i].money);
                num++;
            }
        }
        if(num==1){
            System.out.println("진출한 차량이 없습니다!");
        }
    }

//시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간시간
    public static void timeinit(){
        time.year=2024; time.month=3;time.day=20;time.hour=21;time.minute=0;
    }

//-----------------------------------------------------------------------------------------------------------
    public static Time caltime(Car car){//도착시간계산완료 윤년, 매달마다 30일 31일 그거 해 완료
        int hour=0; int minute=0;
        
        car.timeminute=car.distance*60/car.speed;
        hour=car.timeminute/60;
        minute=car.timeminute%60;

        car.arrivaltime.year=car.departtime.year;
        car.arrivaltime.month=car.departtime.month;
        car.arrivaltime.day=car.departtime.day;
        car.arrivaltime.hour=car.departtime.hour;
        car.arrivaltime.minute=car.departtime.minute;

        car.arrivaltime.hour=car.departtime.hour+hour;
        car.arrivaltime.minute=car.departtime.minute+minute;

        if(car.arrivaltime.minute>59){
            car.arrivaltime.minute-=60;
            car.arrivaltime.hour++;
        }
        if(car.arrivaltime.hour>23){
            car.arrivaltime.hour-=24;
            car.arrivaltime.day++;
        }
        if(car.arrivaltime.month==2){
            if(car.arrivaltime.year%4==0){//윤년

                if(car.arrivaltime.year%400!=0&&car.arrivaltime.year%100==0){
                    if(car.arrivaltime.day>28){
                        System.out.println("dd");
                        car.arrivaltime.day-=28;
                        car.arrivaltime.month++;
                    }else{
                        System.out.println("ddd");
                        if(car.arrivaltime.day>29){
                            car.arrivaltime.day-=29;
                            car.arrivaltime.month++;
                        }
                    }
                }
            }else{
                if(car.arrivaltime.day>28){
                    car.arrivaltime.day-=28;
                    car.arrivaltime.month++;
                }
            }
        }else if(car.arrivaltime.month==4||car.arrivaltime.month==6||car.arrivaltime.month==9||car.arrivaltime.month==11){
            //30일
            if(car.arrivaltime.day>30){
                car.arrivaltime.day=1;
                car.arrivaltime.month++;
            }
        }else{
            //31일
            if(car.arrivaltime.day>31){
                car.arrivaltime.day=1;
                car.arrivaltime.month++;
            }
        }
        if(car.arrivaltime.month>12){
            car.arrivaltime.year++;
            car.arrivaltime.month=1;
        }

        return car.arrivaltime;
    }
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
        if(Integer.parseInt(str[1])>time.year){//년 과거/음수 검사->미래년에서 다른거 음수인지검사
            if(Integer.parseInt(str[2])>0){//month
                if(Integer.parseInt(str[3])>0){//day
                    if(Integer.parseInt(str[4])>=0){//hour
                        if(Integer.parseInt(str[5])>=0){//minute
                            return 0;
                        }
                    }
                }
            }
        }else if(Integer.parseInt(str[1])==time.year){
            if(Integer.parseInt(str[2])>time.month){
                if(Integer.parseInt(str[3])>0){//day
                    if(Integer.parseInt(str[4])>=0){//hour
                        if(Integer.parseInt(str[5])>=0){//minute
                            return 0;
                        }
                    }
                }
            }else if(Integer.parseInt(str[2])==time.month){
                if(Integer.parseInt(str[3])>time.day){
                    if(Integer.parseInt(str[4])>=0){//hour
                        if(Integer.parseInt(str[5])>=0){//minute
                            return 0;
                        }
                    }
                }else if(Integer.parseInt(str[3])==time.day){
                    if(Integer.parseInt(str[4])>time.hour){//hour
                        if(Integer.parseInt(str[5])>=0){//minute
                            return 0;
                        }
                    }else if(Integer.parseInt(str[4])==time.hour){
                        if(Integer.parseInt(str[5])>time.minute){//minute
                            return 0;
                        }
                    }
                }
            }
        }
        return 1;
    }
 //-----------------------------------------------------------------------------------------------------------
    public static int timeupcheck(String[] str){
        if(Integer.parseInt(str[2])<13){
            if(Integer.parseInt(str[2])==2){//2월 0t 1년 2월 3일 4시간 5분
                if(Integer.parseInt(str[1])%4==0){//윤년 검사
                    if(Integer.parseInt(str[1])%400!=0&&Integer.parseInt(str[1])%100==0){
                        if(Integer.parseInt(str[3])>28){
                            return 1;
                        }
                    }else{
                        if(Integer.parseInt(str[3])>29){
                            return 1;
                        }
                    }
                }else{
                    if(Integer.parseInt(str[3])>28){
                        return 1;
                    }
                }
            }else if((Integer.parseInt(str[2])==1)||(Integer.parseInt(str[2])==3)||(Integer.parseInt(str[2])==5)||(Integer.parseInt(str[2])==7)||(Integer.parseInt(str[2])==8)||(Integer.parseInt(str[2])==10)||(Integer.parseInt(str[2])==12)){
                //31일
                if(Integer.parseInt(str[3])>31){
                    return 1;
                }

            }else{//30일
                if(Integer.parseInt(str[3])>30){
                    return 1;
                }
            }
            if(Integer.parseInt(str[4])>23){
                return 1;
            }
            if(Integer.parseInt(str[5])>59){
                return 1;
            }
        }else{
            return 1;
        }
        return 0;
    }
    public static int comptime(Time time1, Time time2){
        if(time1.year>time2.year){
            return 0;
        }else if(time1.year==time2.year){
            if(time1.month>time2.month){
                return 0;
            }else if(time1.month==time2.month){
                if(time1.day>time2.day){
                    return 0;
                }else if(time1.day==time2.day){
                    if(time1.hour>time2.hour){
                        return 0;
                    }else if(time1.hour==time2.hour){
                        if(time1.minute>=time2.minute){
                            return 0;
                        }
                    }
                }
            }
        }
        return 1;
    }
    public static int comptimeon(Time time1, Time time2){
        if(time1.year>time2.year){
            return 0;
        }else if(time1.year==time2.year){
            if(time1.month>time2.month){
                return 0;
            }else if(time1.month==time2.month){
                if(time1.day>time2.day){
                    return 0;
                }else if(time1.day==time2.day){
                    if(time1.hour>time2.hour){
                        return 0;
                    }else if(time1.hour==time2.hour){
                        if(time1.minute>time2.minute){
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

        Car[] car = new Car[howcar];
        for(int i=0;i<howcar;i++){
            car[i]=new Car();
            car[i].arrivaltime.year=0;
            car[i].money=0;
        }
        for(int i=0;i<howcar;i++){
            car[i].no = in.nextInt();
            car[i].baegi = in.nextInt();
            car[i].speed = in.nextInt();
        }
      
        timeinit();
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
                    System.out.println("시간 입력이 잘못 되었습니다");
                    continue;
                }

                pasttime.year=time.year;
                pasttime.month=time.month;
                pasttime.day=time.day;
                pasttime.hour=time.hour;
                pasttime.minute=time.minute;

                time.year=Integer.parseInt(str[1]);
                time.month=Integer.parseInt(str[2]);
                time.day=Integer.parseInt(str[3]);
                time.hour=Integer.parseInt(str[4]);
                time.minute=Integer.parseInt(str[5]);

                for(int i=0;i<howcar;i++){
                    if(car[i].onhighway==1&&comptime(time,car[i].arrivaltime)==0){//도착한 차량
                        car[i].onhighway=0;
                        car[i].money=(basemoney+car[i].distance*howdistance)*car[i].speed/100*car[i].baegi/2000;
                        car[i].money=car[i].money/10;
                        car[i].money=car[i].money*10;
                    }else if(car[i].onhighway==1){
                        if(car[i].startpoint.equals("부산")){
                            car[i].position=400-car[i].speed*calposition(car[i].departtime)/60;
                        }else if(car[i].startpoint.equals("서울")){
                            car[i].position=car[i].speed*calposition(car[i].departtime)/60;
                        }else if(car[i].startpoint.equals("수원")&&car[i].destination.equals("대전")){
                            car[i].position=30+car[i].speed*calposition(car[i].departtime)/60;
                        }else if(car[i].startpoint.equals("수원")&&car[i].destination.equals("대구")){
                            car[i].position=30+car[i].speed*calposition(car[i].departtime)/60;
                        }else if(car[i].startpoint.equals("수원")&&car[i].destination.equals("부산")){
                            car[i].position=30+car[i].speed*calposition(car[i].departtime)/60;
                        }else if(car[i].startpoint.equals("대전")&&car[i].destination.equals("대구")){
                            car[i].position=130+car[i].speed*calposition(car[i].departtime)/60;
                        }else if(car[i].startpoint.equals("대전")&&car[i].destination.equals("부산")){
                            car[i].position=130+car[i].speed*calposition(car[i].departtime)/60;
                        }else if(car[i].startpoint.equals("대구")&&car[i].destination.equals("부산")){
                            car[i].position=290+car[i].speed*calposition(car[i].departtime)/60;
                        }else if(car[i].startpoint.equals("대구")&&car[i].destination.equals("대전")){
                            car[i].position=290-car[i].speed*calposition(car[i].departtime)/60;
                        }else if(car[i].startpoint.equals("대구")&&car[i].destination.equals("수원")){
                            car[i].position=290-car[i].speed*calposition(car[i].departtime)/60;
                        }else if(car[i].startpoint.equals("대구")&&car[i].destination.equals("서울")){
                            car[i].position=290-car[i].speed*calposition(car[i].departtime)/60;
                        }else if(car[i].startpoint.equals("대전")&&car[i].destination.equals("수원")){
                            car[i].position=130-car[i].speed*calposition(car[i].departtime)/60;
                        }else if(car[i].startpoint.equals("대전")&&car[i].destination.equals("서울")){
                            car[i].position=130-car[i].speed*calposition(car[i].departtime)/60;
                        }else if(car[i].startpoint.equals("수원")&&car[i].destination.equals("서울")){
                            car[i].position=30-car[i].speed*calposition(car[i].departtime)/60;
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
                    if(car[i].no==Integer.parseInt(str[1])){
                        if(car[i].onhighway==0){//이미 있는차인지 검사
                            carset(car[i],str);
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
