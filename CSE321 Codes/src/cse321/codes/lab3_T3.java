package cse321.codes;
//start of code

class myClass2 extends Thread
{
    public myClass2(String s)
    {
        super(s);
    }

    @Override
    public void run()
    {
        long [] a = new long [50];
        long n1 = 0, n2 = 1, n3;
        int count = 50;
        a[0] = n1;
        a[1] = n2;
        //inserting fibonacci numbers to an array
        for (int i = 2; i < count; ++i)
        {
            n3 = n1 + n2;
            a[i]=n3;
            n1 = n2;
            n2 = n3;
        }
        int countHalf = count/2;
        long sumOdd1 = 0, sumEven1 = 0, sumOdd2 = 0 , sumEven2 = 0, sumT = 0; //to store sums
        long meanOfOdd1 = 0, meanOfEven1 = 0, meanOfOdd2 = 0 , meanOfEven2 = 0, avg = 0; // to store means
        int oddCount1 = 0, evenCount1 = 0, oddCount2 = 0, evenCount2 = 0; // to keep track of counts 
        
        for (int i = 0; i < countHalf; i++)
        {
            if (a[i] % 2 != 0)
            {
                sumOdd1 = sumOdd1 + a[i];
                oddCount1++;
            }
            else
            {
                sumEven1 = sumEven1 + a[i];
                evenCount1++;
            }
        }
        meanOfOdd1 = sumOdd1 / oddCount1;
        meanOfEven1 = sumEven1 / evenCount1;
        sumT =  meanOfOdd1 + meanOfEven1;
        for (int i = countHalf; i < count; i++)
        {
            if (a[i] % 2 != 0)
            {
                sumOdd2 = sumOdd2 + a[i];
                oddCount2++;
            }
            else
            {
                sumEven2 = sumEven2 + a[i];
                evenCount2++;
            }
        }
        meanOfOdd2 = sumOdd2 / oddCount2;
        meanOfEven2 = sumEven2 / evenCount2;
        sumT = sumT + meanOfOdd2 + meanOfEven2;
        if(getName() == "Average")
        {
            avg = sumT/4;
            System.out.println("PIN: "+ avg);
        }
    }
}
public class lab3_T3
{
    public static void main(String[] args)
    {
        
        myClass2 obj1 = new myClass2("First Half Odd");
        myClass2 obj2 = new myClass2("First Half Even");
        myClass2 obj3 = new myClass2("Second Half Odd");
        myClass2 obj4 = new myClass2("Second Half Even");
        myClass2 obj5 = new myClass2("Average");
        
        obj5.setPriority(Thread.MIN_PRIORITY);
        
        obj1.start();
        obj2.start();
        obj3.start();
        obj4.start();
        try
        {
            obj1.join();
            obj2.join();
            obj3.join();
            obj4.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        obj5.run();
    }
}

//end of code
