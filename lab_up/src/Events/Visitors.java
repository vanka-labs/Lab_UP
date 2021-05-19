package Events;

public class Visitors {
    public String day;
    public String age1;

    public void setDay(String day) {
        this.day = day;
    }
    public String getAge1() {
        return age1;
    }
    public void setAge1(String age1) {
        this.age1 = age1;
    }
    public String getAge2() {
        return age2;
    }

    public void setAge2(String age2) {
        this.age2 = age2;
    }

    public String getAge3() {
        return age3;
    }
    public void setAge3(String age3) {
        this.age3 = age3;
    }
    public void setCount1(String count1) {
        int i=Integer.parseInt (count1);
        this.count1 = i;
    }
    public void setCount2(String count2) {
        this.count2 = Integer.parseInt (count2);
    }
    public void setCount3(String count3) {
        this.count3 = Integer.parseInt (count3);
    }

    public String age2;
    public String age3;
    public int count1;
    public int count2;
    public int count3;

    public Visitors(String day, String age1,String age2,String age3, int count1,int count2,int count3) {
        this.day = day;
        this.age1 = age1;
        this.age2 = age2;
        this.age3 = age3;
        this.count1 = count1;
        this.count2 = count2;
        this.count3 = count3;
    }
    public Visitors() {
        this.day="Monday";
        this.count1=1111;
        this.count2=2222;
        this.count3=3333;
        this.age1="children";
        this.age2="young";
        this.age3="old";
    }

    public int[] getCount() {

        int[] coun=new int[3];
        coun[0]=count1;
        coun[1]=count2;
        coun[2]=count3;
        return coun;
    }
    public String getDay() {
        return this.day;
    }
    public String[] getAge() {
        String[] ag=new String[3];
        ag[0]=age1;
        ag[1]=age2;
        ag[2]=age3;
        return ag;
    }




}
