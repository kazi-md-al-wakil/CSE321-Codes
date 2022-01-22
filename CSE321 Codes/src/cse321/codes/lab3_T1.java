package cse321.codes;

import java.util.Scanner;
class myClass extends Thread
{
    public myClass(String s)
    {
        super(s);
    }
    @Override
    public void run()
    {
        Scanner sc = new Scanner (System.in);
        System.out.println("Give two input 1: ");
        int a = sc.nextInt();
        System.out.println("Give two input 2: ");
        int b = sc.nextInt();
        
        String name = getName();
        
        if(name == "add")
        {
            System.out.println("Add: "+ (a + b));
        }
        else if(name == "sub")
        {
            System.out.println("Sub: "+ (a - b));
        }
        else if(name == "mul")
        {
            System.out.println("Mul: "+ (a * b));
        }
        else if(name == "div")
        {
            System.out.println("Div: " + (a / b));
        }
        else if(name == "oth")
        {
            System.out.println("No valid operation");
        }
    }
}
public class lab3_T1
{

    public static void main(String[] args)
    {
        myClass obj1 = new myClass("add");
        myClass obj2 = new myClass("sub");
        myClass obj3 = new myClass("mul");
        myClass obj4 = new myClass("div");
        myClass obj5 = new myClass("oth");
        
        obj1.run();
        obj2.run();
        obj3.run();
        obj4.run();
        obj5.run();
    }
    
}
