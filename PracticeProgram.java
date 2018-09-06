package ApiTesting;

import 		java.sql.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class PracticeProgram {

	public static void main(String[] args) throws Exception{
		
		//DbconnectionDemo();
		
		System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		
		driver.navigate().to("http://www.seleniumeasy.com/test/basic-first-form-demo.html");
		
		JavascriptExecutor js=((JavascriptExecutor)driver);
		
		try {
			WebElement searchbox =null;
		searchbox =(WebElement)js.executeAsyncScript("return document.getElementById('sum2')");
		driver.quit();
		}
		catch(Exception ex)
		{
			
			System.out.println(ex.getMessage());
			driver.quit();
		}
	}
	
	
	static void DbconnectionDemo()
	{
		Connection con=null;
		try 
		{
		
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "admin");
			
			PreparedStatement preStat=con.prepareStatement("update test.softcrylic set dep=? where ID=?");			
			preStat.setString(1, "hr");
			preStat.setInt(2, 1);
			
			int count=preStat.executeUpdate();
					
			PreparedStatement pst=con.prepareStatement("insert into test.softcrylic values(?,?,?,?)");
			pst.setInt(1, 6);
			pst.setString(2, "Siva");
			pst.setString(3, "Kumar");
			pst.setString(4, "Dev");
			
			int value=pst.executeUpdate();
			
			if(!(value==0))
			{
				System.out.println("new Record have Inserted");
			}
			value=0;
			PreparedStatement pr=con.prepareStatement("delete  from test.softcrylic where ID=6");
			value=pr.executeUpdate();
			if(!(value==0))
			{
				System.out.println("Record Have deleted sucessfully");
			}
		
			List<ListValue> A1=new LinkedList<>();
			
			Statement str=con.createStatement();
			
			ResultSet rs=str.executeQuery("select * from test.softcrylic");
			
			while(rs.next())
			{
			
				int ID=rs.getInt("ID");
				String Firstname=rs.getString("FirstName");
				String LastName=rs.getString("LastName");
				String Dep=rs.getString("Dep");
				ListValue L=new ListValue(ID, Firstname, LastName, Dep);
				A1.add(L);
			}
			System.out.println(A1.size());
			for(ListValue Li:A1)
			{
				System.out.println(Li.id+"\n"+Li.FirstName+"\n"+Li.LastName+"\n"+Li.Dep);
			}
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		
		}
		finally {
			
			
		}
	}
	static void StringMethod()
	{
		//String Inset types
		String value="NewWorld";
		
		String test="";
		//chatAt method() it will return the value of specific value
	
		char CharValue=value.charAt(5);
		System.out.println(CharValue);

	//CompareTo method it will return compare value
		
		System.out.println(" Name as :- " +value.compareTo("NewWorld"));
		
	//Concat() method it will combine two value
		test=" with";
		
		value=value.concat(test);
		System.out.println(value.concat(" with new Environment"));
		
	//contains() method it will check the value is present in string or not
		
		if(value.contains("New")) 
		{
			System.out.println("expected value is present in the String");
		}
				
	//	endswith() return boolean value it will compare case sensitive also
		test="LD";
		if(value.endsWith(test))
		{
			System.out.println(value+" is ends with:- "+test);
			
		}
		else
		{
			System.out.println(value+" is not ends with:- "+test);
		}
		
		//equal is used to compare the both string and is case sensitive 
		test="Newworld with";
		if(value.equals(test))
		{
			System.out.println(value+" both are equal "+test);
		}
		else
		{
			System.out.println(value+" both are not equal "+test);
		}
		
		//equalsIgnoreCase() is used to compare the string value and it is not casesensitive comparison 
		
		test="Newworld with";
		
		if(value.equalsIgnoreCase(test))
		{
			System.out.println(value+" both are equal "+test);
		}
		else
		{
			System.out.println(value+" both are not equal "+test);
		}
		
		//formate() 
		String Sf1=value.format("name as %s", test);
		
		String Sf2=value.format("Number as float %f", 32.45);
		
		String sf3=value.format("Number as float %32.12f",32.34 );
		
		System.out.println(Sf1+"  "+Sf2+"  "+sf3);
		
		//byte
		
		byte[] bytevalue=value.getBytes();
		for(int i=0;i<bytevalue.length;i++)
		{
			System.out.println(bytevalue[i]);
		}
	 
		//intern() method which is used to take the  string for pool memory.
		
		String s1=new String("hello");  
		String s2="hello";  
		String s3=s1.intern();
		
		System.out.println("Creted object Vs nromal creation of string "+s1==s2);;
		System.out.println("Creted object Vs nromal creation of string"+s2==s3);
		System.out.println(s1.equals(s2));
		
		
		//isEmpty() if it is an null it will through an exception as null point exception 
			 
		s1="";
		s2=null;
		
		System.out.println("to verify is its empty or not "+s1.isEmpty());
		try {
		System.out.println("to verify is its empty or not "+s2.isEmpty());
		}catch(Exception ex)
		{
		}
		System.out.println("to verify is its empty or not"+value.isEmpty());
		
		//join()
		
		String joinstring=value.join("-", "New","India","clean");
		
		System.out.println(joinstring);
		
		//lastIndexOf()
		
		System.out.println(value.lastIndexOf('w'));
		
		//length() it will return length value of string
		
		System.out.println(value.length());
		
		//replace will replace the char vice but replace all String  
		value=value.replace('i', 'H');
		
		System.out.println("value:-"+value);
		
		value=value.replaceAll("wHth", "with");
		
		System.out.println("Repalce value of wHth as:- "+value); 
		
		//Split() is used to split the value and return the char[]
		String[] sp=value.split(" ");
		for(int i=0;i<sp.length;i++)
		{
			System.out.println(sp[i]);
		}
		
		//start-with() is used to retrun the start value
		
		//tocharArray it will return the char[]
			
		char[] Ch=value.toCharArray();
		
		for(int i=0;i<=Ch.length-1;i++)
		{
			System.out.print(Ch[i]+" ");
		}
		System.out.println();
		
		//it is used to concate the value of String it may type
		int i=10;
		
		String Valueof=String.valueOf(i);
		
		System.out.println(Valueof+10);
		
		boolean b=false;
		
		Valueof=String.valueOf(true);
		System.out.println("Value of function as:-"+Valueof+b);
		
	}
	static void print379Numbers()
	{
		for(int i=1;i<=100;i++)
			
		{
			 if((i%3==0)||(i%9==0)||((i%7==0)))
			{
				if((i%3==0)&&(i%9==0)&&((i%7==0)))
				{
					System.out.print(" Seven and Three and Nine");
				}
				else if((i%3==0)&&(i%9==0))
				{
					System.out.print(" Three and Nine");
				}
				else if((i%3==0)&&(i%7==0))
				{
					System.out.print(" Three and Seven");
				}
				else if((i%7==0))
				{
					System.out.print(" Seven");
					//System.out.print(" "+"Seven and Three and Nine");
				}
				else if((i%3==0))
				{
					System.out.print(" Three");
					//System.out.print(" "+"Seven and Three and Nine");
				}
				else
				{
					System.out.print(" "+i);
				}
			}
				
				else
			System.out.print(" "+i);
			
		}
	}
	
	static void Sorting() 
	{
		int[] a= {5,2,8,7,6,3,0,5,4,7};
		
		Set<Integer> oddvalue=new HashSet<>();
		Set<Integer> evenvalue=new HashSet<>();
		
	
		for(int i=0;i<=a.length-1;i++)
		{
			if(a[i]%2==0)
			{
				evenvalue.add(a[i]);
			}
			else
			{
				oddvalue.add(a[i]);
			}
			
		}
		
		for(int i:oddvalue) {
			System.out.print(i+",");
		}
		for(int i:evenvalue) {
			System.out.print(i+",");
		}
	}
	private static void JDBCconnection() throws SQLException{
		Connection con=null;
		try 
		{
		Class.forName("com.mysql.jdbc.Driver");  
		
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","admin");

		upadteData(con);
		if(!con.isClosed())
		System.out.println("Connected");
		upadteData(con);
		//selectRecordsFromDbUserTable(con);
		//insertData(con);
		}
		
		
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally 
		{
			con.close();
		}
	
	}
	
	 private static void insertData(Connection con)
	 {
		 try {
		 String insertTableSQL = "INSERT test.softcrylic"
					+ "(ID, FirstName, LastName) VALUES"
					+ "(?,?,?)";
			PreparedStatement prepStatement = con.prepareStatement(insertTableSQL);
			prepStatement.setInt(1, 8);
			prepStatement.setString(2, "Shibi");
			prepStatement.setString(3, "palanisamy");
			
			int resultReturn=prepStatement .executeUpdate();
		
			if(!(resultReturn==0))
			{
				System.out.println("new Row have inserted");
			}
			
		 }
		 catch(Exception ex)
		 {
			 System.out.println(ex.getMessage());
		 }
	 }
	 private static void upadteData(Connection con)
	 {
		 try {
		 String updateTableSQL = "update test.softcrylic set Dep=? "
		 		+ "where ID=?";
			PreparedStatement prepStatement = con.prepareStatement(updateTableSQL);
			
					prepStatement.setString(1,"Testing");
					prepStatement.setInt(2, 1);
			
		 
		 				prepStatement .executeUpdate();
				int resultReturn=prepStatement .executeUpdate();
				
				if(!(resultReturn==0))
				{
					System.out.println("Row have Updated Sucessfully");
				}
		
		 }
		 catch(Exception ex)
		 {
			 System.out.println(ex.getMessage());
		 }
	 }
	 
	 private static void selectRecordsFromDbUserTable(Connection con) throws SQLException  {

			Connection dbConnection = null;
			Statement statement = null;

			String selectTableSQL = "select * from test.softcrylic";

			try {
				
				statement = con.createStatement();

				System.out.println(selectTableSQL);

				ResultSet rs = statement.executeQuery(selectTableSQL);

				while (rs.next()) {

					String ID = rs.getString("ID");
					String FirstName = rs.getString("FirstName");
					String LastName = rs.getString("LastName");

										
					System.out.println("ID : " + ID);
					System.out.println("FirstName : " + FirstName);
					System.out.println("LastName : " + LastName);

				}

			} 
			catch (SQLException e) 
			{
				new ExceptionHandler(e.getMessage(), e);

				
				System.out.println(e.getMessage());

			} 
			finally 
			{

				if (statement != null) 
				{
					statement.close();
				}
			}

		}

}

class ExceptionHandler
{
	public ExceptionHandler(String message,Exception ex) {
		// TODO Auto-generated constructor stub
	}
}

class ListValue
{
	int id;
	String FirstName;
	String LastName;
	String Dep;
	
	public ListValue(int id,String FirstName,String LastName,String Dep) 
	{
		this.id=id;
		this.FirstName=FirstName;
		this.LastName=LastName;
		this.Dep=Dep;
	}
}
