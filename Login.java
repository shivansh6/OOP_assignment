import java.util.Scanner;
import java.util.Arrays;
import java.io.FileOutputStream;
import java.io.Console;
import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.lang.*;
class Login1
{
  int ch;
  private String password;
  private String username;
  private String temp;
  private String ppi;
  private String cgpa;
  private String want_to_place;
  private String want_to_master;
  private int mincgpa;
  private  int intake;
  private String uni;
  private int mingpa;
  static HashMap<String,Integer> recru=new HashMap<>();
  static HashMap<String,Integer> admin = new HashMap<>();
  Login1(int ch)
  {
    this.ch = ch;
    password = new String();
    username = new String();
    HashMap<String,Integer> rec = new HashMap<>();
  }
  public void makeHashMap()
  {
    File file = new File("C:\\Users\\Brij\\Desktop\\Java_Project\\Text_Files\\recruiter_record.txt");
    try{
      RandomAccessFile raf = new RandomAccessFile(file,"r");
      while (raf.getFilePointer() < raf.length()) {
          String data = raf.readLine();
          int index_start = data.indexOf("|||");
          int index_end = data.indexOf('@');
          String rname;
          if(index_start!=-1)
          rname = data.substring(index_start+3,index_end);
          else
          break;
          int index = data.indexOf("|||*|||");
          data = data.substring(index+8);
          index = data.indexOf("||*||");
          data = data.substring(index+5);
          //System.out.println(rname+" "+data);
          recru.put(rname,Integer.parseInt(data));
        }
        //System.out.println(recru);
    }
    catch(Exception e)
    {
      System.out.println("");
    }
  }
  public void makeHashMap1()
  {
    File file = new File("C:\\Users\\Brij\\Desktop\\Java_Project\\Text_Files\\admin_record.txt");
    try{
      RandomAccessFile raf = new RandomAccessFile(file,"r");
      while (raf.getFilePointer() < raf.length()) {
          String data = raf.readLine();
          int index_start = data.indexOf("|||*|||");
          int index_end = data.indexOf("||*||",index_start+7);
          String rname;
          if(index_start!=-1)
          rname = data.substring(index_start+7,index_end);
          else
          break;
          int index = data.indexOf("|||*|||");
          data = data.substring(index+7);
          index = data.indexOf("||*||");
          data = data.substring(index+5);
          //System.out.println(rname+" "+data);
          admin.put(rname,Integer.parseInt(data));
        }
        //System.out.println(recru);
    }
    catch(Exception e)
    {
      System.out.println("");
    }
  }
  public static HashMap<String, Integer> sort(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
        // Sort the list
        Collections.sort(list,new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
		Collections.reverse(list);
          //Collections.sort(list,Collections.reverseOrder());
        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
  public boolean check_user() throws Exception
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
        int in4 = temp.indexOf("|||*|||");
        if(in4!=-1)
          temp = temp.substring(0,in4);
        System.out.println(temp);
        return false;
      }
    }
    return true;
  }
  public void Loginme()
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
    //System.out.println("We got success");
  }
  public void setdetails(int ch){
	  Scanner sc = new Scanner(System.in);
	  switch(ch){
		  case 1:
      boolean ok = true;
      while(ok)
      {
			     System.out.print("Enter your PPI(round of) :- ");
           try
           {
             ppi = sc.nextLine();
           }
           catch(Exception e)
           {
             System.out.println(e);
           }
           if(ppi.length()<2 || (ppi.length()==2 && ppi.equals("10")))
              ok = false;
      }
      ok = true;
      while(ok)
      {
			     System.out.print("Enter your CGPA(round of) :- ");
           try
           {
             cgpa = sc.nextLine();
           }
           catch(Exception e)
           {
             System.out.println(e);
           }
           if(cgpa.length()<2 || (cgpa.length()==2 && cgpa.equals("10")))
              ok = false;
      }
      Map<String,Integer> stu = sort(recru);
      System.out.println("All recruiter's list with minimum CGPA");
      System.out.println(recru);
      Set<String> keys = stu.keySet();
      System.out.println("\t\t\t\tThis are sorted companies");
      for(String i : keys)
			{
        if(recru.get(i)<=Integer.parseInt(cgpa))
          System.out.printf("%20s %40d\n",i,recru.get(i));
      }
			System.out.print("You want placement or not (yes/no) :- ");
			String s = sc.nextLine();
			if(s.equalsIgnoreCase("NO")){
				want_to_place = "NO";
			}else{
				want_to_place = "YES";
			}
      want_to_master = "NO";
      if(want_to_place.equalsIgnoreCase("NO"))
      {
        Map<String,Integer> stu1 = sort(admin);
        System.out.println("All MOU university list with minimum CGPA");
        System.out.println(admin);
        Set<String> keys1 = stu1.keySet();
        System.out.println("\t\t\t\tThis are sorted MOU university according your cgpa :");
        for(String i : keys1)
  			{
          if(admin.get(i)<=Integer.parseInt(cgpa))
            System.out.printf("%20s %40d\n",i,admin.get(i));
        }
        System.out.print("You want master or not (yes/no) :- ");
  			s = sc.nextLine();
  			if(s.equalsIgnoreCase("NO")){
  				want_to_master = "NO";
  			}else{
  				want_to_master = "YES";
  			}
      }
				File file2 = new File("C:\\Users\\Brij\\Desktop\\Java_Project\\Text_Files\\student_record.txt");
				File file3 = new File("C:\\Users\\Brij\\Desktop\\Java_Project\\for_copy_student.txt");
			try{

				RandomAccessFile raf = new RandomAccessFile(file2, "rw");
				RandomAccessFile tmpraf = new RandomAccessFile(file3, "rw");
				raf.seek(0);
				while (raf.getFilePointer() < raf.length()) {
  					String data = raf.readLine();
            int index_start = data.indexOf("|||");
  					int index_end = data.indexOf('@');
            String rname;
            if(index_start!=-1)
            rname = data.substring(index_start+3,index_end);
            else
            break;
  					if (rname.equals(username) && data.contains("|||*|||")==false) {
  					data = data.concat("|||*|||"+ ppi + "||*||" + cgpa + "|*|" +want_to_place.toUpperCase()+"P**"+want_to_master.toUpperCase()+"M");
            }
            else if(rname.equals(username) && data.contains("|||*|||")==true)
            {
              int index = data.indexOf("|||*|||");
              data = data.substring(0,index);
              data = data.concat("|||*|||"+ ppi + "||*||" + cgpa + "|*|" +want_to_place.toUpperCase()+"P**"+want_to_master.toUpperCase()+"M");
            }
  					tmpraf.writeBytes(data);
  					tmpraf.writeBytes(System.lineSeparator());
      }
				raf.seek(0);
				tmpraf.seek(0);
				while (tmpraf.getFilePointer() < tmpraf.length()) {
					raf.writeBytes(tmpraf.readLine());
					raf.writeBytes(System.lineSeparator());
					}
					//raf.setLength(tmpraf.length());
					tmpraf.close();
					raf.close();
					//file3.delete();

			}catch(IOException e){
				System.out.println(e);
			}
      HomePage.HOMEPAGE();
      break;
      case 2:
          ok=true;
          while(ok)
          {
            try{
            System.out.print("Enter total number of intakes :- ");
            intake = sc.nextInt();
            if(intake>0)
              ok=false;
            }
            catch(Exception e)
            {
              System.out.println(e);
            }
          }
          ok=true;
          while(ok)
          {
            try{
            System.out.print("Enter cutt off of cgpa : ");
            mincgpa = sc.nextInt();
            if(mincgpa>=0 & mincgpa<=10)
              ok=false;
            }
            catch(Exception e)
            {
              System.out.println(e);
            }
          }
          recru.put(username,mincgpa);
          file2 = new File("C:\\Users\\Brij\\Desktop\\Java_Project\\Text_Files\\recruiter_record.txt");
          file3 = new File("C:\\Users\\Brij\\Desktop\\Java_Project\\for_copy_recruiter.txt");
          try{

          RandomAccessFile raf = new RandomAccessFile(file2, "rw");
          RandomAccessFile tmpraf = new RandomAccessFile(file3, "rw");
          raf.seek(0);
          while (raf.getFilePointer() < raf.length()) {

          String data = raf.readLine();
          int index_start = data.indexOf("|||");
          int index_end = data.indexOf('@');
          if(index_start==-1)
            break;
          String rname = data.substring(index_start+3,index_end);
          if (rname.equals(username)) {
            if(!data.contains("|||*|||"))
          data = data.concat("|||*|||"+ intake + "||*||" + mincgpa);
          else
            {
              int indd = data.indexOf("|||*|||");
              data = data.substring(0,indd);
              data = data.concat("|||*|||"+ intake + "||*||" + mincgpa);
            }
                              }
          tmpraf.writeBytes(data);
          tmpraf.writeBytes(System.lineSeparator());
          }
          raf.seek(0);
          tmpraf.seek(0);
          while (tmpraf.getFilePointer() < tmpraf.length()) {
          raf.writeBytes(tmpraf.readLine());
          raf.writeBytes(System.lineSeparator());
          }
          raf.setLength(tmpraf.length());
          tmpraf.close();
          raf.close();
          file3.delete();

          }catch(IOException e){
          System.out.println(e);
          }
          HomePage.HOMEPAGE();
          break;
        case 3 :
          {
            ok=true;
            while(ok)
            {
              try{
              System.out.print("Enter name of University :- ");
              uni = sc.nextLine();
              ok=false;
              }
              catch(Exception e)
              {
                System.out.println(e);
              }
            }
            ok=true;
            while(ok)
            {
              try{
              System.out.print("Enter cutt off of cgpa : ");
              mingpa = sc.nextInt();
              if(mingpa>=0 & mingpa<=10)
                ok=false;
              }
              catch(Exception e)
              {
                System.out.println(e);
              }
            }
            admin.put(uni,mingpa);
            file2 = new File("C:\\Users\\Brij\\Desktop\\Java_Project\\Text_Files\\admin_record.txt");
            file3 = new File("C:\\Users\\Brij\\Desktop\\Java_Project\\for_copy_admin.txt");
            try{

            RandomAccessFile raf = new RandomAccessFile(file2, "rw");
            RandomAccessFile tmpraf = new RandomAccessFile(file3, "rw");
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {

            String data = raf.readLine();
            int index_start = data.indexOf("|||");
            int index_end = data.indexOf('@');
            if(index_start==-1)
              break;
            String rname = data.substring(index_start+3,index_end);
            if (rname.equals(username)) {
              if(!data.contains("|||*|||"))
            data = data.concat("|||*|||"+ uni + "||*||" + mingpa);
            else
              {
                int indd = data.indexOf("|||*|||");
                data = data.substring(0,indd);
                data = data.concat("|||*|||"+ uni + "||*||" + mingpa);
              }
            }
            tmpraf.writeBytes(data);
            tmpraf.writeBytes(System.lineSeparator());
            }
            raf.seek(0);
            tmpraf.seek(0);
            while (tmpraf.getFilePointer() < tmpraf.length()) {
            raf.writeBytes(tmpraf.readLine());
            raf.writeBytes(System.lineSeparator());
            }
            raf.setLength(tmpraf.length());
            tmpraf.close();
            raf.close();
            file3.delete();

            }catch(IOException e){
            System.out.println(e);
            }
            HomePage.HOMEPAGE();
            break;
          }
	  }
  }
}
class Login
{
  public void Final_login()
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
      else if(ch==-999)
        System.exit(0);
      else
        System.out.println("Entered choice is invalid");
    }
    Login1 ob = new Login1(ch);
    ob.makeHashMap();
    ob.makeHashMap1();
    ob.Loginme();
    ob.setdetails(ch);

  }
}
