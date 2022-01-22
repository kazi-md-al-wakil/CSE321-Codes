package cse321.codes;

import static java.lang.Thread.State.TERMINATED;

class myClass1 extends Thread
{

    public myClass1(String s)
    {
        super(s);
    }

    @Override
    public void run()
    {
        
        try
        {
            if (getName() == "House Stark" || getName() == "House Targaryen")
            {
                System.out.println("The house is: "+ getName());
                sleep(1000);
            }
            else if(getName() == "House Lannister" || getName() == "House Bolton")
            {
                System.out.println("The house is: "+ getName());
                sleep(3000);
            }
            else
            {
                System.out.println("The house is: "+ getName());
                sleep(5000);
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

public class lab3_T2
{

    public static void main(String[] args)
    {
        myClass1 obj1 = new myClass1("House Stark");
        myClass1 obj2 = new myClass1("House Targaryen");
        myClass1 obj3 = new myClass1("House Lannister");
        myClass1 obj4 = new myClass1("House Bolton");
        myClass1 obj5 = new myClass1("House Tyrell");
        
        obj1.setPriority(Thread.MAX_PRIORITY);
        obj4.setPriority(Thread.MIN_PRIORITY);
                
        //Single Threading
        obj1.run();
        obj2.run();
        obj3.run();
        obj4.run();
        
        try
        {
            obj1.join();
            obj3.join();
            obj4.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        if (obj1.isAlive())
        {
            System.out.println("Not Today!");
        }
        if (!obj4.isAlive())
        {
            System.out.println("You know nothing!");
        }
        
        
        // Multithreading
        obj1.start();
        obj3.start();
        obj4.start();
        obj5.start();
        
        try
        {
            obj1.join();
            obj3.join();
            obj4.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        if (obj1.isAlive())
        {
            System.out.println("Not Today!");
        }
        if (!obj4.isAlive())
        {
            System.out.println("You know nothing!");
        }
    }
    
}
