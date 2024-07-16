package hcms;



class Car{
    Car(int no, int baegi, int speed){
        this.no=no;
        this.speed=speed;
        this.baegi=baegi;
        this.timeminute=0;
        this.distance=0;
    }
    int no;
    int speed;
    int baegi;
    int distance;
    int timeminute;
    String startpoint ="";
    String destination="";
}
//list만들어서 해결하며 ㄴ될듯?어라이벌시간 체크해서 리스트 옮겨다니게
class ExitCar{
    int money;
    Car car;
    Time arrivaltime = new Time();
    Time departtime = new Time();
}
class MoveCar{
    Car car;
    int position;
    Time departtime = new Time();
}

//mcar하고 ecar다 배열 만들어서 하나씩 car하고 번호 같게 할당?하는게
public class test {

    public static void main(String[] args){
        Car[] car = new Car[100];
        MoveCar[] mcar=new MoveCar[100];
        ExitCar[] ecar=new ExitCar[100];
        
        System.out.println("hello world");
        int num;
        num=0;
        if(num==0){
            System.out.println("print");
        }
    }    
}
