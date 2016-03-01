package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.RaavareDAO;
import daointerfaces01917.ReceptDAO;
import dto01917.RaavareDTO;

public class MySQLRaavareDAO implements RaavareDAO{

	@Override
	public RaavareDTO getRaavare(int raavareId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM raavare where raavare_id = "+raavareId);
		try { 
			if(!rs.first()) throw new DALException("Raavaren med id " + raavareId + " findes ikke.");
			return new RaavareDTO(rs.getInt("raavare_id"), rs.getString("raavare_navn"), rs.getString("leverandoer"));
		} catch (SQLException e) { throw new DALException(e); }
	}

	@Override
	public List<RaavareDTO> getRaavareList() throws DALException {
		ArrayList<RaavareDTO> list = new ArrayList<RaavareDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM raavare");
		try {
			while(rs.next()){
				list.add(new RaavareDTO(rs.getInt("raavare_id"), rs.getString("raavare_navn"), rs.getString("leverandoer")));
			}
		} catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRaavare(RaavareDTO raavare) throws DALException {
		Connector.doUpdate("INSERT INTO raavare(raavare_id, raavare_navn, leverandoer) VALUES (" 
			+ raavare.getRaavareId() + ", '" + raavare.getRaavareNavn() + "', '" + raavare.getLeverandoer() + "')");
	}

	@Override
	public void updateRaavare(RaavareDTO raavare) throws DALException {
		Connector.doUpdate("UPDATE raavare SET raavare_navn = '" + raavare.getRaavareNavn() 
		+ "', '" + raavare.getLeverandoer() + "')");
	}
}
