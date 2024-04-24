package com.jsp.data;
import java.sql.*;
import java.util.*;
public class jspdatabase {
		public static void registration()
		{
			String url ="jdbc:mysql://localhost:3306?user=root&password=12345";
			String query ="insert advancejavamorning.jspidersdata values(?,?,?,?,?,?)";
			try {
				Connection connect =DriverManager.getConnection(url);
				PreparedStatement prepare = connect.prepareStatement(query);
				Scanner sc1 =new Scanner(System.in);
				System.out.println("enter the first name");
				prepare.setString(1, sc1.next());
				System.out.println("enter the last name");
				prepare.setString(2,sc1.next());
				System.out.println("enter the email id");
				prepare.setString(3, sc1.next());
				System.out.println("enter the mobile");
				prepare.setString(4, sc1.next());
				System.out.println("enter the password");
				String p1 = sc1.next();
				prepare.setString(5, p1);
				System.out.println("confirm the password");
				String p2 =sc1.next();
				for(int i=0; i<p1.length(); i++)
				{
				if(p1.charAt(i)==p2.charAt(i))
				{
					System.out.println("password matched");
						break;
				}
				else {
					System.err.println("incorect verification of password");
				}
				}
				System.out.println("add date of birth");
				prepare.setInt(6, sc1.nextInt());
				int result =prepare.executeUpdate();
				if(result>0)
				{
					System.out.println("registration is successfull");
					System.out.println("please login through your account");
				}
				else
				{
					System.err.println("please fill correctly");
				}
				connect.close();	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
  public static void  login()
  {
	  String url1 ="jdbc:mysql://localhost:3306?user=root&password=12345";
	  String query12 ="{call advancejavamorning.retrievemailp(?,?)}";
	  System.out.println("enter 1 for login with mail and password");
	   System.out.println("enter 2 for login with mobile and password");
	   System.out.println("enter 3 if you forgot login details, signin with mobile ");
	   Scanner sc2 =new Scanner(System.in);
	   int choose =sc2.nextInt();
	   if(choose==1)
	   {
		   try {
			Connection connect1 =DriverManager.getConnection(url1);
			CallableStatement call1 =connect1.prepareCall(query12);
			System.out.println("entr the email");
			call1.setString(1,sc2.next() );
			System.out.println("enter the password");
			call1.setString(2, sc2.next());
			ResultSet result2 =call1.executeQuery();
			if(result2.next())
			{
				System.out.println("log in succeful");
				System.out.println("firstname"+" "+result2.getString("firstname"));
				System.out.println("lastname"+ " "+result2.getString("lastname"));
				System.out.println("mobile"+" "+result2.getString("mobile"));
				System.out.println("date of birth"+" "+result2.getInt("DOB"));
			}
			else {
				System.err.println("invalid login details");
			}
			connect1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	   }
	   else if(choose==2)
	   {
		   String query21 ="{call advancejavamorning.retrievemp(?,?)}";
		   try {
				Connection connect1 =DriverManager.getConnection(url1);
				CallableStatement call1 =connect1.prepareCall(query21);
				System.out.println("entr the mobile");
				call1.setString(1,sc2.next() );
				System.out.println("enter the password");
				call1.setString(2, sc2.next());
				ResultSet result2 =call1.executeQuery();
				if(result2.next())
				{
					System.out.println("log in succeful");
					System.out.println("firstname"+" "+result2.getString("firstname"));
					System.out.println("lastname"+ " "+result2.getString("lastname"));
					System.out.println("mail"+" "+result2.getString("email"));
					System.out.println("date of birth"+" "+result2.getInt("DOB"));
				}
				else {
					System.err.println("invalid login details");
				}
				connect1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	   }
	   else if(choose==3)
	   {
		   String query31 ="{call advancejavamorning.retrievemobile(?)}";
		   
		   try {
				Connection connect3 =DriverManager.getConnection(url1);
				CallableStatement call3 =connect3.prepareCall(query31);
				Scanner sc3 =new Scanner(System.in);
				System.out.println("entr the mobile");
				call3.setString(1,sc3.next());
				
				ResultSet result3 =call3.executeQuery();
				if(result3.next())
				{
					Random random =new Random();
					int otp =random.nextInt(10000);
					if(otp<1000) {
						otp+=1000;
					}System.out.println("otp is "+" "+otp);
					System.out.println("enter the generated otp");
					int uotp =sc3.nextInt();
					if(otp ==uotp)
					{
						System.out.println("login successfull");
					
					
					System.out.println("fname"+" "+result3.getString("firstname"));
					System.out.println("lname"+ " "+result3.getString("lastname"));
					System.out.println("mobile"+" "+result3.getString("mobile"));
					System.out.println("date of birth"+" "+result3.getInt("DOB"));
					}
					else
					{
						System.err.println("invaid otp");
					}
				}
				else {
					System.err.println("invalid input ");
				}
				connect3.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	   }
	   
  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m=1;
		for(int i=0; i<m; i++) 
		{
		System.out.println("Jspider institute");
		Scanner sc = new Scanner(System.in);
		System.out.println("enter 1 for registration");
		System.out.println("enter 2 for login");
		switch(sc.nextInt())
		{
		case 1:jspdatabase.registration();break;
		case 2:jspdatabase.login();break;
		default:System.err.println("invalid key"); break;
		}
		System.out.println("to continue pls press(y/n)");
		switch(sc.next())
		{
		case"y":m=m+1; break;
		case "n":m=-1;System.out.println("your succcessfully exists");break;
		}
	}

}
}
