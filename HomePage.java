import java.util.Scanner;
public class HomePage
{
  public static void HOMEPAGE()
  {
    System.out.println("____________________________________________________________________________________________________________________________________________________________________________");
    for(int i=0;i<75;i++)
      System.out.print(" ");
    System.out.println("Nirma university");
    for(int i=0;i<70;i++)
      System.out.print(" ");
    System.out.println("Welcome to Pacement Cell");
    System.out.println("Choices : ");
    System.out.println("1 --> Sign Up");
    System.out.println("2 --> Sign In");
    System.out.println("\n*Enter -999 to close the application at any stage");
    System.out.print("Enter your choice :- ");
    Scanner sc = new Scanner(System.in);
    boolean ok = true;
     while(ok)
     {
       int ch=98;
       boolean flag1 = false;
       do{
         try{
         ch = sc.nextInt();
         flag1 =true;
         }
         catch(Exception e)
         {
           System.out.println("Enter valid choice");
         }
      }while(!flag1);
      switch(ch)
      {
        case 1 :
          SignUp ob = new SignUp();
          ob.SIGNUP();
          ok = false;
          break;
        case 2 :
          Login ob1 = new Login();
          ob1.Final_login();
          ok = false;
          break;
        case -999 :
          System.exit(0);
        default :
          System.out.println("Enter valid choice");
      }
    }
    System.out.println("____________________________________________________________________________________________________________________________________________________________________________");
  }
  public static void main(String args[])
  {
    //Login1 ob = new Login1(2);
    //ob.makeHashMap();
    HOMEPAGE();
  }
}
