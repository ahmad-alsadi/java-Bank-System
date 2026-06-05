

package com.mycompany.banksystem;

import java.util.Scanner;

public class BankSystem {
    
   public static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args)
    {

        String[] name=new String[20];
        double[] Balance =new double[20];
        int[] id =new int[20];
        int[] Pin =new int[20];
       
        
        int count =CreateAcount(name,Balance,Pin,id);
        
        ATMmnue(name,Balance,id,count,Pin);
        
    }
    public static int CreateAcount(String[]name,double[]Balance,int[] pin,int[] id)
    {
        int count =0;
        
        while(count <20)
        {
            System.out.println("---------- Create Account ----------");
            count = AddingAcount(name,Balance,id,pin,count);
            System.out.print("Add another account? (Press (n/N) to stop: ");
            char choice =in.next().charAt(0);
            in.nextLine();
            if(choice =='n' ||choice =='N')
            {
                break;
            }
        }
        return count;
    }
    
    
    public static int AddingAcount(String[]name,double[]Balance,int[] id,int[] Pin,int count)
    {
        
        int StartId =1000;
        
        System.out.print("Enter Cleint Name : ");
        name[count]=in.nextLine();
        do
        {
        System.out.println("Enter Pin Code (it shoud be 4 Digits)");
        Pin[count]=in.nextInt();
        }while(Pin[count] < 1000 || Pin[count] > 9999);
        
        do
        {
            System.out.print("Enter Cleint Balance : ");
            Balance[count]=in.nextDouble();
                
        }while(Balance[count] < 0);
        
        id[count] =StartId + count;
        
        System.out.println("Acount Created The ID Is : " + id[count]);
        count++;
        return count;
    }
   
    public static void ShowAllAccounts(String[]name,double[]Balance,int[] id,int count)
    {
        System.out.println("---------- Acount List ----------");
        
        for(int i =0;i<count;i++)
        {
            System.out.println("Cleint Name : " + name[i] + " ||Id : " + id[i] + "|| Balance : " + Balance[i]);
        }
        
    }
    
    public static int FindAccountById(int[] id,int IdToSerch,int count)
    {
        
        for(int i =0;i<count;i++)
        {
            if(id[i] == IdToSerch)
            {
                return i;
            }
        }
        return -1;
    }
    
    public static void Doposit(double[] Balance,int count)
    {
        double amount;
        do
        {
        System.out.print("How Much You Want to Doposit : ");
         amount =in.nextDouble();
         
        }while(amount < 0);
        Balance[count] +=amount;
    }
    
      public static void Withdraw(double[] Balance,int count)
    {
        double amount;
        do
        {
        System.out.print("How Much You Want to Withdraw : ");
         amount =in.nextDouble();
         
        }while(amount < 0);
        if(amount <= Balance[count])
        {
        Balance[count] -=amount;
        }
        else
        {
            System.out.println("You Dont Have Enoght Money!");
        }
    }
      
    public static void TransFer(double[]Balance,int[] id,int FromIndex,int TotalCount)
    {
        System.out.println("Enter Acount Id : (To)");
        int TRansferId =in.nextInt();

       int ToIndex =FindAccountById(id,TRansferId,TotalCount);
        
        if(ToIndex == -1)
        {
            System.out.println("Account iD ["+ TRansferId +"] not found");
            return;
        }
        if(FromIndex == ToIndex)
        {
            System.out.println("Can not Transfer To The Same Acount!");
            return;
        }
        System.out.print("Transform Ammount : ");
        double amount =in.nextDouble();
        if(amount < 0)
        {
            System.out.println("Invalid Number To Transfer !");
        }
        else if(amount > Balance[FromIndex])
        {
            System.out.println("You Dont Have Enoght Mooney To Transfer It !");
        }
        else
        {
            Balance[FromIndex] -=amount;
            Balance[ToIndex] +=amount;
        }
        
        
        
    }
    public static boolean CheckPin(int[] Pin,int count)
    {
        int PinValidation;
        int tries = 3;
        
            while(tries >0)
            {
                System.out.print("Enter your Pin Code : ");
                PinValidation =in.nextInt();
                if(Pin[count]==PinValidation)
                {
                    return true;
                }
                
                tries--;
                System.out.println(tries + " Tries Lift");
            }
            return false;
        
    }
    
   
    public static void ATMmnue(String[]name,double[]Balance,int[] id,int count,int[] Pin)
    {
        int AdminPinCode;
        int IdToSerch;
        char CheckAdmin;
            System.out.println("Enter Admin Account ?(y/Y) if yes : ");
            CheckAdmin =in.next().charAt(0);
            if(CheckAdmin == 'y' || CheckAdmin == 'Y')
            {
                 System.out.println("Enter Code (For Admin)");
            AdminPinCode=in.nextInt();
            if(AdminPinCode == 2442007)
            {
                AdminMnue(name,Balance,id,count);
                return;
            }
            else
            {
                System.out.print("Wrong Pin Code");
            }
            }
            
           System.out.println("Transfer To User Check");
            System.out.print("Please Enter Your Account ID : ");
            IdToSerch =in.nextInt();
            
            int CountNum =FindAccountById(id,IdToSerch,count);
            if(CountNum == -1)
            {
                System.out.println("Account Not Found");
                return;
            }
            if(CheckPin(Pin,CountNum))
            {
                UserMnue(name,Balance,id,CountNum,count);
            }
            else
            {
                System.out.println("You Have No Tries Lift");
                System.out.println("Your Card Is Blocked ");
            }
            
    }

    
    public static void AdminMnue(String[]name,double[]Balance,int[] id,int count)
    {
        System.out.println("Welcome To Admin Mnue");
        System.out.println("1.Show All Accounts");
        System.out.println("2.Exit");
        System.out.println("Enter Your Choice");
        int choice =in.nextInt();
       switch (choice) 
       {
           case 1:
               ShowAllAccounts(name,Balance,id,count);
               break;
           case 2:
               System.out.println("Good Bye");
               break;
           default:
               System.out.println("Wrong Number But Good Bye Anyway :-)");
               break;
       }
    }
    
    public static void UserMnue(String[]name,double[]Balance,int[] id,int UserIndex,int TotalCount)
    {
        int choice;
                System.out.println("------- Cleint Mnue -------");
                System.out.println("      Welcome " + name[UserIndex]);


        do
        {
         System.out.println("\n-----------------");
        System.out.println("1.Show Balance");
        System.out.println("2.Doposit");
        System.out.println("3.Withdraw");
        System.out.println("4.Transfer");
        System.out.println("5.Logout");
        System.out.println("Enter Your Choice : ");
        choice=in.nextInt();
        switch(choice)
        {
             case 1:
                System.out.print("Your Balance Is : " +Balance[UserIndex] );
                break;
             case 2:
                Doposit(Balance,UserIndex);
                break;
             case 3:
                Withdraw(Balance,UserIndex);
                break;
             case 4:
                 TransFer(Balance,id,UserIndex,TotalCount);
                 break;
             case 5:
                 System.out.println("User Log Out");
                 break;
             default:
                 System.out.println("Invalid Number Try Again");
        }
        }while(choice !=5);
        
        
        
        }
    
}
