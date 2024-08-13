class Complex
{
    Complex(int num1, int num2)
    {
        this.real = num1;
        this.img = num2;
    }
    private int real;
    private int img;

    public void setreal(int no)
    {
        this.real = no;
    }
    public void setimg(int no)
    {
        this.img = no;
    }

    public int getreal(int no)
    {
        return this.real;
    }
    public int getimg(int no)
    {
        return this.img;
    }

    static Complex  addComplexNumber(Complex c1, Complex c2)
    {
        Complex temp = new Complex(0,0);
        temp.real = c1.real + c2.real;
        temp.img = c1.img + c2.img;

        return temp;
    }
    static Complex mulComplexNumber(Complex c1, Complex c2)
    {
        Complex temp = new Complex(0,0);
        temp.real = c1.real * c2.real;
        temp.img = c1.img * c2.img;


        return temp;
    }

    public void printNumbers()
    {
        if(this.img > 0)
        {
            System.out.println(this.real+"+"+this.img+"i");
        }
        else
        {
            System.out.println(this.real+""+this.img+"i");
        }
        
        //System.out.print("helllllllllllll");
        
    }
    
}



// Main class to test the inheritance
public class lab3 {
    public static void main(String[] args) {
        
        Complex c1 = new Complex(5 , -6);
        //c1.printNumbers();
        Complex c2 = new Complex(55 , 66);
        Complex result = Complex.addComplexNumber(c1, c2);

        result.printNumbers();

    }
}