import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.Console;
import java.util.regex.*;
import java.io.*;
class hell
{
  public static void main(String[] args) {
    
    Console in = System.console();
    System.out.println("Enter your name:");
    String n = in.readLine();
    System.out.println("Your name is: " +n);
class EraserThread implements Runnable {
   private boolean stop;
  /**
    * Begin masking...display asterisks (*)
    */
   public void run () {
      stop = true;
      int i=0;
      while (stop) {
        if(i==0)
        { System.out.println("\010 ");
          i++;}
        else{
          System.out.print("\010*");
          i++;
        }
   try {
      Thread.currentThread().sleep(1);
         } catch(InterruptedException ie) {
             ie.printStackTrace();
         }
      }
   }

   /**
    * Instruct the thread to stop masking
    */
   public void stopMasking() {
      this.stop = false;
   }
}


class PasswordField {

   /**
    *@param prompt The prompt to display to the user
    *@return The password as entered by the user
    */
    String readPassword (String prompt) {
      EraserThread et = new EraserThread();
      Thread mask = new Thread(et);
      mask.start();

      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      String password = "";

      try {
         password = in.readLine();
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
      // stop masking
      et.stopMasking();
      return password;
   }
}


  
    PasswordField newpass = new PasswordField();
     String password = newpass.readPassword("Enter password: ");
   System.out.println("The password entered is: "+password);
   try{  
             FileWriter fout=new FileWriter("D:\\testout.txt",true); 
             fout.write(n); 
             fout.write("||"); 
             fout.write(password+"\n");
             fout.write("\r\n");  
             fout.close();    
             System.out.println("success...");    
            }catch(Exception e){System.out.println(e);}
  }
}
