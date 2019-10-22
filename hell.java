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
		// try{  
  //            FileOutputStream fout=new FileOutputStream("D:\\testout.txt"); 
  //            byte b[] = n.getBytes();   
  //            fout.write(b);    
  //            fout.close();    
  //            System.out.println("success...");    
  //           }catch(Exception e){System.out.println(e);}      
		
class EraserThread implements Runnable {
   private boolean stop;
 
   /**
    *@param The prompt displayed to the user
    */
   public EraserThread(String prompt) {
       System.out.print(prompt);
   }

   /**
    * Begin masking...display asterisks (*)
    */
   public void run () {
      stop = true;
      while (stop) {
         System.out.print("\010*");
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
      EraserThread et = new EraserThread(prompt);
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
      // return the password entered by the user
      return password;
   }
}


	
		PasswordField newpass = new PasswordField();
     String password = newpass.readPassword("Enter password: ");
   System.out.println("The password entered is: "+password);
   try{  
             FileWriter fout=new FileWriter("D:\\testout.txt"); 
             // byte c[] = password.getBytes(); 
             // byte b[] = n.getBytes();
             // byte a[] = "||".getBytes();
             fout.write(n); 
             fout.write("||"); 
             fout.write(password+"\n"); 
             //fout.write("\n");
             fout.write("hello");   
             fout.close();    
             System.out.println("success...");    
            }catch(Exception e){System.out.println(e);}
 	 	
 	//  	char []pass	= in.readPassword("Enter password");
		// System.out.println(pass);
	}
}
