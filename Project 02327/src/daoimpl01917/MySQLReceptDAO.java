package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;

import daointerfaces01917.DALException;
import daointerfaces01917.ReceptDAO;
import dto01917.ReceptDTO;

public class MySQLReceptDAO implements ReceptDAO{

	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
		ResultSet recept = Connector.doQuery("SELECT * FROM recept WHERE recept_id = " + receptId);
		try {
			if(!recept.first()) throw new DALException("recepten med id " + receptId + " findes ikke.");
			return new ReceptDTO(recept.getInt("recept_id"), recept.getString("recept_navn"));
		}catch (SQLException e) {throw new DALException(e);}
	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		ArrayList<ReceptDTO> recepter = new ArrayList<ReceptDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM recept");
		try {
			while(rs.next()){
				recepter.add(new ReceptDTO(rs.getInt("recept_id"), rs.getString("recept_navn")));
			} return recepter;
		} catch (SQLException e) { throw new DALException(e); }
	}

	@Override
	public void createRecept(ReceptDTO recept) throws DALException {
		String sql = "";
		sql = "INSERT INTO recept(recept_id, recept_navn) VALUES ";
		sql += "(";
		sql += "NULL"  + ", '";
		sql += recept.getReceptNavn() + "')";
		System.out.println(sql);
		Connector.doUpdate(sql);
	}

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		Connector.doUpdate("UPDATE recept SET recept_navn = '" + recept.getReceptNavn() 
		+ "' WHERE recept_id = " + recept.getReceptId());
	}
}
