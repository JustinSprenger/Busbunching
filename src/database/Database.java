package database;
import java.sql.*;

import javax.print.DocFlavor.CHAR_ARRAY;
public class Database {
	private String user;
	private String pass;
	private String address;
	private int port;
	private String database;
	private int id;
	Connection conn;
	/**
	 * 
	 * @param add -url
	 * @param port -portnumber (3306 for mysql)
	 * @param db -database name
	 * @param user -admin user
	 * @param pass -password
	 */
	public Database(String add,int port,String db,String user,String pass){
		this.user = user;
		this.pass = pass;	
		this.address = add;
		this.port = port;
		this.database = db;
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public String[][] getDatabase(String table){
		String[][] result = new String[1][3];
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from " + table); 
			System.out.println("VER_No  NO     Start_OF_OPERATION");
			while(rs.next()){
				
				result[result.length-1][0] = rs.getString(1);
				result[result.length-1][1] = rs.getString(2);
				result[result.length-1][2] = rs.getString(3);
						
				System.out.println(rs.getString(1) + "    " + rs.getString(2) + "    " + rs.getString(3));
				id = rs.getRow();
				
				String[][] temp = new String[result.length+1][3];
				for(int i=0;i<result.length;i++){
					temp[i] = result[i];
				}
				result = temp;
			}
				conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @param ids -Busstationsnummer
	 * @param name -Name der Haltestelle
	 * @return
	 */
	public String[][] getVscsid(long ids,String name){
		String[][] result = new String[1][1];
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT VSCS_ID FROM NETWORK_POINTS WHERE ID like " + ids +" and Name like '"+ name +"'"); 
			while(rs.next()){
				result[result.length-1][0] = rs.getString(1);
				
				String[][] temp = new String[result.length+1][1];
				for(int i=0;i<result.length;i++){
					temp[i] = result[i];
				}
				result = temp;
			}
				conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @param ldi -Buslinie id
	 * @param routenum -Routennummer
	 * @return
	 */
	public String[][] getNpid(int ldi, int routenum){
		String[][] result = new String[1][1];
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT NP_ID FROM POINTS_ON_ROUTE WHERE LDI_ID like " + ldi + " and ROU_NO like " + routenum);
			while(rs.next()){
				result[result.length-1][0] = rs.getString(1);
				
				String[][] temp = new String[result.length+1][1];
				for(int i=0;i<result.length;i++){
					temp[i] = result[i];
				}
				result = temp;
			}
				conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @param vscs -Busnummer
	 * @return -TimeDelay(Zeitabweichung)
	 */
	public String[][] getTimeDelay(int vscs){
		String[][] result = new String[1][2];
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT TIMETABLE_DELAY, LAST_POR_ORDER FROM CM_VEHICLE_POSITIONS WHERE VSCS_ID Like " + vscs);
			while(rs.next()){
				result[result.length-1][0] = rs.getString(1);
				result[result.length-1][1] = rs.getString(2);
				
				String[][] temp = new String[result.length+1][2];
				for(int i=0;i<result.length;i++){
					temp[i] = result[i];
				}
				result = temp;
			}
				conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @param order -Position in der Reihenfolge des Busses
	 * @param routenum -Buslinie
	 * @return Np_Id
	 */
	public String[][] getNpidInOrder(int order,int routenum){
		String[][] result = new String[1][1];
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT NP_ID FROM POINTS_ON_ROUTE WHERE POR_ORDER like " + order + " and ROU_LIN_NO like " + routenum);
			while(rs.next()){
				
				result[result.length-1][0] = rs.getString(1);
				
				String[][] temp = new String[result.length+1][1];
				for(int i=0;i<result.length;i++){
					temp[i] = result[i];
				}
				
				result = temp;
			}
				conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	//new
	/**
	 * 
	 * @param no -Busliniennummer
	 * @return Ldi_Id -Buslinien-ID
	 */
	public String[][] getLdiId(int no){
		String[][] result = new String[1][1];
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT LDI_ID FROM `LINES` WHERE NO like " + no);
			while(rs.next()){
				
				result[result.length-1][0] = rs.getString(1);
				
				String[][] temp = new String[result.length+1][1];
				for(int i=0;i<result.length;i++){
					temp[i] = result[i];
				}
				
				result = temp;
			}
				conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @param ldi -Buslinien-ID
	 * @return Rou_No -Routennummer
	 */
	public String[][] getRouNo(int ldi){
		String[][] result = new String[1][1];
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT ROU_NO FROM POINTS_ON_ROUTE WHERE LDI_ID like " + ldi);
			while(rs.next()){
				
				result[result.length-1][0] = rs.getString(1);
				
				String[][] temp = new String[result.length+1][1];
				for(int i=0;i<result.length;i++){
					temp[i] = result[i];
				}
				
				result = temp;
			}
				conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @param direction -Fahrtrichtung
	 * @param no -Busliniennummer
	 * @return no
	 */
	public String[][] getNo(int direction, int no){
		String[][] result = new String[1][1];
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT NO FROM ROUTES WHERE DIRECTION like " + direction + " and NO like " + no);
			while(rs.next()){
				
				result[result.length-1][0] = rs.getString(1);
				
				String[][] temp = new String[result.length+1][1];
				for(int i=0;i<result.length;i++){
					temp[i] = result[i];
				}
				
				result = temp;
			}
				conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String[][] getNpIdTo(long npid){
		String[][] result = new String[1][1];
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT NP_ID_TO FROM LINKS WHERE NP_ID like " + npid);
			while(rs.next()){
				
				result[result.length-1][0] = rs.getString(1);
				
				String[][] temp = new String[result.length+1][1];
				for(int i=0;i<result.length;i++){
					temp[i] = result[i];
				}
				
				result = temp;
			}
				conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String[][] getRemark(int no){
		String[][] result = new String[1][1];
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+database+"?"+"user=" + user +"&"+"password=" + pass);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT REMARK FROM `LINES` WHERE NO like " + no);
			while(rs.next()){
				
				result[result.length-1][0] = rs.getString(1);
				
				String[][] temp = new String[result.length+1][1];
				for(int i=0;i<result.length;i++){
					temp[i] = result[i];
				}
				
				result = temp;
			}
				conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
