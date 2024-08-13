class parent
{
    private int num1;
    private int num2;

    public int getNum1()
    {
        return num1;
    }
    public int getNum2()
    {
        return num2;
    }

    public void setNum1(int no)
    {
        this.num1 = no;
    }
    public void setNum2(int no)
    {
        this.num2 = no;
    }


    public parent()
    {
        System.out.println("==============This is Parent Constructor==================");
    }
}

class child extends parent
{
    private int num3;

    public int getNum3()
    {
        return num3;
    }

    public void setNum3(int no)
    {
        this.num3 = no;
    }

    public child()
    {
        super();
        System.out.println("==============This is Child Constructor==================");
    }
}

// Main class to test the inheritance
public class lab2 {
    public static void main(String[] args) {
        parent p1 = new parent();
        p1.setNum1(11);
        p1.setNum2(22);

        System.out.println("==============This is Parent Class==================");
        System.out.println("Number 1= " + p1.getNum1());
        System.out.println("Number 2= " + p1.getNum2());

        child c1 = new child();

        c1.setNum3(33);
        c1.setNum1(44);
        c1.setNum2(55);

        System.out.println("==============This is child Class==================");
        System.out.println("Number 1= " + c1.getNum1());
        System.out.println("Number 2= " + c1.getNum2());
        System.out.println("Number 3= " + c1.getNum3());


    }
}
