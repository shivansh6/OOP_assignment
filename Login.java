import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.Console;
import java.util.regex.*;
import java.io.*;
class Login1
{
  int ch;
  String password;
  String username;
  String temp;
  Login1(int ch)
  {
    this.ch = ch;
    password = new String();
    username = new String();
  }
  boolean check_user() throws Exception
  {
    File file = new File("C:\\Users\\Brij\\Desktop\\Java_Project\\Text_Files\\Testout.txt");
    switch(ch)
    {
      case 1 :
        file = new File("C:\\Users\\Brij\\Desktop\\Java_Project\\Text_Files\\student_record.txt");
        break;
      case 2 :
        file = new File("C:\\Users\\Brij\\Desktop\\Java_Project\\Text_Files\\recruiter_record.txt");
        break;
      case 3 :
        file = new File("C:\\Users\\Brij\\Desktop\\Java_Project\\Text_Files\\admin_record.txt");
    }
    BufferedReader br = new BufferedReader(new FileReader(file));
    String s;
    while((s=br.readLine()) != null)
    {
      int in1 = s.indexOf("|||");
      int in2 = s.indexOf("@");
      temp = s.substring(in1+3,in2);
      if(temp.equals(username))
      {
        temp = s.substring(in2);
        int in3 = temp.indexOf("||");
        temp = temp.substring(in3+2);
        System.out.println(temp);
        return false;
      }
    }
    return true;
  }
  void Loginme()
  {
    Scanner sc = new Scanner(System.in);
    System.out.print("Username : ");
    boolean ok=true;
    while(ok)
    {
      username = sc.next();
      try
      {
        ok = check_user();
      }
      catch(Exception e)
      {
        System.out.println(e);
      }
      if(ok)
        System.out.println("Invalid username");
    }
    ok = true;
    while(ok)
    {
      System.out.print("Password : ");
      password = sc.next();
      if(temp.equals(password))
        ok = false;
    }
    System.out.println("We got success");
  }
}
class Login
{
  public static void main(String args[])
  {
    Scanner sc = new Scanner(System.in);
    boolean ok = true;
    int ch=0;
    System.out.println("____________________________________________________________________________________________________________________________________________________________________________");
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
    Login1 ob = new Login1(ch);
    ob.Loginme();
  }
}
