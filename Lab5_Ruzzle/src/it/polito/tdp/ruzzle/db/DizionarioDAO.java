package it.polito.tdp.ruzzle.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DizionarioDAO {

	public boolean check(String parola){
		Connection conn = DBConnection.getConnection();
		String sql = "select nome from parola where nome=?";
		PreparedStatement st;
		try{	
			st = conn.prepareStatement(sql);
			st.setString(1, parola);
			ResultSet res = st.executeQuery();
			if(res.next()){
				conn.close();
				return true;
			}
		} catch(SQLException e){
			System.out.println("Errore nella ricerca parola "+parola);
		}
		return false;		
	}
	
}
