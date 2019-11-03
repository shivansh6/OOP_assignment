import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.Console;
import java.util.regex.*;
import java.io.*;
class SignUp1
{
  String username;
  String email_id;
  String password;
  int ch;
  SignUp1(int ch)
  {
    this.username = new String();
    this.email_id = new String();
    this.ch = ch;
  }
  static boolean validName(String name)
  {
    for(int i=0;i<name.length();i++)
    {
      if((name.charAt(i)<'a' || name.charAt(i)>'z'))
        return false;
    }
    return true;
  }
  static boolean validMail_Student(String email)
  {
    String temp = email;
    int in = temp.indexOf('@');
    if(email.endsWith("@nirmauni.ac.in") && email.length()==23 && (email.charAt(0)=='1') && (email.charAt(1)=='6' || email.charAt(1)=='7' || email.charAt(1)=='8' || email.charAt(1)=='9'))
    {
      if((email.charAt(2)=='b' || email.charAt(2)=='m'))
      {
        if((email.charAt(3)=='c' && email.charAt(4)=='e')||(email.charAt(3)=='i'&&email.charAt(4)=='t')||(email.charAt(3)=='e'&&email.charAt(4)=='e')||(email.charAt(3)=='m'&&email.charAt(4)=='e')||(email.charAt(3)=='c'&&email.charAt(4)=='h')||(email.charAt(3)=='i'&&email.charAt(4)=='c')||(email.charAt(3)=='e' && email.charAt(4)=='c'))
          return true;
        else
          return false;
      }
      else
        return false;
    }
    else
      return false;
  }
  static boolean validMail_Recruiter(String email)
  {
    int in = email.indexOf('@');
    String temp = email.substring(in+1);
    if(!temp.contains("@"))
    {
      if(email.endsWith("@gmail.com") || email.endsWith("@yahoo.com") || email.endsWith("@hotmail.com"))
      {
        temp = email.substring(0,in);
        for(int i=0;i<temp.length();i++)
        {
          if(temp.charAt(i)=='.' || (temp.charAt(i)>='a' && temp.charAt(i)<='z') || (temp.charAt(i)>='0' && temp.charAt(i)<='9'))
          {}
          else
            return false;
        }
        return true;
      }
      else
        return false;
    }
    else
      return false;
  }
  static boolean validMail_Admin(String email)
  {
    String temp = email;
    int in = temp.indexOf('@');
    if(email.endsWith("@nirmauni.ac.in"))
    temp = email.substring(in);
    if(temp.contains("."))
    {
      if(email.endsWith("@nirmauni.ac.in"))
      {
          String t2 = new String();
          boolean ok;
          temp = email;
          in = email.indexOf('.');
          String t1 = email.substring(0,in);
          int in1 = email.indexOf('@');
          if(in<in1)
          t2 = email.substring(in+1,in1);
          else
            return false;
          ok = validName(t1);
          //System.out.println(t1+" "+t2);
          if(ok)
            ok = validName(t2);
          else
            return false;
          return ok;
      }
      else
        return false;
    }
    else
      return false;
  }
  static boolean is_Valid_Password(String password)
  {
      if (password.length() < 8) return false;
      int charCount = 0,numCount = 0,attherate = 0,uppercount = 0,lowercount = 0;
      for (int i = 0; i < password.length(); i++)
      {
          char ch = password.charAt(i);
          if (ch >= '0' && ch <= '9') numCount++;
          else if (ch >= 'a' && ch <= 'z') lowercount++;
          else if (ch >= 'A' && ch <= 'Z') uppercount++;
          else if (ch == '@' || ch == '$' || ch == '#') attherate++;
          else return false;
          charCount = lowercount + uppercount;
      }
      return (charCount >= 2 && numCount >= 2 && attherate == 1 && lowercount >= 1 && uppercount >= 1);
  }
  void SignUpme()
  {
      Scanner sc = new Scanner(System.in);
      System.out.println("____________________________________________________________________________________________________________________________________________________________________________");
      System.out.print("Name :- ");
      username = sc.nextLine();
      boolean ok = false;
      while(!ok)
      {
        System.out.print("Email Id :- ");
        email_id = sc.nextLine();
        switch(ch)
        {
          case 1 :
            ok = validMail_Student(email_id);
            break;
          case 2 :
            ok = validMail_Recruiter(email_id);
            break;
          case 3 :
            ok = validMail_Admin(email_id);
        }
        if(!ok)
        {
          switch(ch)
          {
            case 1 :
              System.out.println("Invalid email id. \ne.g 18bce159@nirmauni.ac.in");
              break;
            case 2 :
              System.out.println("Invalid email id. It should have digit or character or period");
              break;
            case 3 :
              System.out.println("Invalid email id. \ne.g shivansh.patel@nirmauni.ac.in");
              break;
          }
        }
      }
      //System.out.print("Password :- ");
      //password = sc.nextLine();
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      //System.out.println("Your name is: " + username);
      class EraserThread implements Runnable
      {
          private boolean stop;
          public void run()
          {
              stop = true;
              int i = 0;
              while (stop)
              {
                  if (i == 0)
                  {
                      System.out.print("\010 ");
                      i++;
                  }
                  else
                  {
                      System.out.print("\010*");
                      i++;
                  }
                  try
                  {
                      Thread.currentThread().sleep(1);
                  }
                  catch(InterruptedException ie)
                  {
                      ie.printStackTrace();
                  }
              }
          }
          public void stopMasking()
          {
              this.stop = false;
          }
        }
          class PasswordField
          {

              /**
               *@param prompt The prompt to display to the user
               *@return The password as entered by the user
               */
              String readPassword(String prompt)
              {
                  System.out.print(prompt);
                  EraserThread et = new EraserThread();
                  Thread mask = new Thread(et);
                  mask.start();
                  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                  String password = "";
                  try
                  {
                      password = in.readLine();
                  }
                  catch (IOException ioe)
                  {
                      ioe.printStackTrace();
                  }
                  et.stopMasking();
                  return password;
              }
          }
          PasswordField newpass = new PasswordField();
          String password = newpass.readPassword("Password :- ");
          boolean b = true;
          while(b)
          {
              if (is_Valid_Password(password))
                  b = false;
              else
              {
                  System.out.println("Password is not accepted. It's min length is 8 characters and it should have at least one digit and one character.");
                  password = newpass.readPassword("Password: ");
              }
          }
          try
          {
              FileWriter fout = new FileWriter("C:\\Users\\Brij\\Desktop\\Java_Project\\Text_Files\\Testout.txt", true);
              fout.write(username);
              fout.write("|||");
              fout.write(email_id);
              fout.write("||");
              fout.write(password);
              fout.write("\r\n");
              fout.close();
              System.out.println("Successfully account is created");
              int index = email_id.indexOf('@');
              System.out.println("Your username is "+email_id.substring(0,index));
          }
          catch (Exception e)
          {
              System.out.println(e);
          }
          System.out.println("____________________________________________________________________________________________________________________________________________________________________________");
    }



}
class SignUp
{
  public static void main(String args[])
  {
    Scanner sc = new Scanner(System.in);
    boolean ok = true;
    int ch=0;
    while(ok)
    {
      System.out.println("1. Student \n2. Recruiter \n3. Admin");
      System.out.print("Enter your choice : ");
      ch = sc.nextInt();
      if(ch>=1 || ch<=3)
        ok = false;
      else
        System.out.println("Entered choice is invalid");
    }
    SignUp1 ob = new SignUp1(ch);
    ob.SignUpme();
  }
}
