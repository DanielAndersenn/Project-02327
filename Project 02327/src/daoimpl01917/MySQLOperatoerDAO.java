package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.SQLException;

import connector01917.Connector;

import daointerfaces01917.DALException;
import daointerfaces01917.OperatoerDAO;
import dto01917.OperatoerDTO;

public class MySQLOperatoerDAO implements OperatoerDAO {
	
	int returnMsg = 0;
	String SQLMsg = "";
	int SQLErr = 0;
	
	
	
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM operatoer WHERE opr_id = " + oprId);
	    try {
	    	if (!rs.first()) throw new DALException("Operatoeren " + oprId + " findes ikke");
	    	return new OperatoerDTO (rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
		
	}
	
	public void createOperatoer(OperatoerDTO opr) throws DALException {		
			
			CallableStatement statement = null;
			String cmd = "CALL InsertOperatoer(?, ?, ?, ?, ?, ?, ?)";
			
			try {
				statement = Connector.conn.prepareCall(cmd);
				
				statement.setString(1, opr.getOprNavn());
				statement.setString(2, opr.getIni());
				statement.setString(3, opr.getCpr());
				statement.setString(4, opr.getPassword());
				statement.registerOutParameter(5, java.sql.Types.INTEGER);
				statement.registerOutParameter(6, java.sql.Types.VARCHAR);
				statement.registerOutParameter(7, java.sql.Types.SMALLINT);
				
				statement.executeUpdate();
				
				returnMsg = statement.getInt(5);
				SQLMsg = statement.getString(6);
				SQLErr = statement.getInt(7);
				
				System.out.println("var returnMsg: " + returnMsg + " var SQLMsg: " + SQLMsg + " var SQLErr: " + SQLErr);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		CallableStatement statement = null;
		String cmd = "CALL UpdateOperatoer(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			statement = Connector.conn.prepareCall(cmd);
			
			statement.setInt(1, opr.getOprId());
			statement.setString(2, opr.getOprNavn());
			statement.setString(3, opr.getIni());
			statement.setString(4, opr.getCpr());
			statement.setString(5, opr.getPassword());
			statement.registerOutParameter(6, java.sql.Types.INTEGER);
			statement.registerOutParameter(7, java.sql.Types.VARCHAR);
			statement.registerOutParameter(8, java.sql.Types.SMALLINT);
			
			statement.executeUpdate();
			
			returnMsg = statement.getInt(6);
			SQLMsg = statement.getString(7);
			SQLErr = statement.getInt(8);
			
			System.out.println("var returnMsg: " + returnMsg + " var SQLMsg: " + SQLMsg + " var SQLErr: " + SQLErr);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteOperatoer(OperatoerDTO opr) throws DALException
	{
		CallableStatement statement = null;
		String cmd = "CALL DeleteOperatoer(?, ?, ?, ?)";
		
		try {
			statement = Connector.conn.prepareCall(cmd);
			
			statement.setInt(1, opr.getOprId());
			System.out.println("Prøver at slette operatoer med ID = " + opr.getOprId());
			statement.registerOutParameter(2, java.sql.Types.INTEGER);
			statement.registerOutParameter(3, java.sql.Types.VARCHAR);
			statement.registerOutParameter(4, java.sql.Types.SMALLINT);
			
			statement.executeUpdate();
			
			returnMsg = statement.getInt(2);
			SQLMsg = statement.getString(3);
			SQLErr = statement.getInt(4);
			
			System.out.println("var returnMsg: " + returnMsg + " var SQLMsg: " + SQLMsg + " var SQLErr: " + SQLErr);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM operatoer");
		try
		{
			while (rs.next()) 
			{
				list.add(new OperatoerDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
		
		
}
	
