package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import daointerfaces01917.ReceptKompDAO;
import connector01917.Connector;
import daointerfaces01917.DALException;

import dto01917.RaavareDTO;
import dto01917.ReceptKompDTO;

public class MySQLReceptKompDAO implements ReceptKompDAO{

	@Override
	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent WHERE recept_id = " + receptId + " AND raavare_id = " + raavareId);
		try { 
			if(!rs.first()) throw new DALException("Raavaren med id " + raavareId + " findes ikke.");
			return new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getFloat("nom_netto"), rs.getFloat("tolerance"));
		} catch (SQLException e) { throw new DALException(e); }
	}
	

	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
