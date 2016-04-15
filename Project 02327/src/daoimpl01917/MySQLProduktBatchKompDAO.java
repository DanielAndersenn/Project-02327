package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import connector01917.Connector;

import daointerfaces01917.DALException;
import daointerfaces01917.ProduktBatchKompDAO;
import dto01917.ProduktBatchKompDTO;

public class MySQLProduktBatchKompDAO implements ProduktBatchKompDAO{

	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId + " AND rb_id = " + rbId);
		try {
			if(!rs.first()) throw new DALException("Produktbatchkomponenten med pb_id = " + pbId + " og rb_id = " + rbId + " kunne ikke findes.");
			return new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id"));
			
		} catch(SQLException e) {
			throw new DALException(e);
		}
	}
	
	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {
		List<ProduktBatchKompDTO> toReturn = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId);
		try{
			while(rs.next())
			{
				toReturn.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")));
			}
		} catch(SQLException e)
		{
			throw new DALException(e);
		}
		return toReturn;
	}

	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
		List<ProduktBatchKompDTO> toReturn = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent");
		try{
			while(rs.next())
			{
				toReturn.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")));
			}
		} catch(SQLException e)
		{
			throw new DALException(e);
		}
		return toReturn;
	}

	public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		String sql = "";
		sql = "INSERT INTO produktbatchkomponent(pb_id, rb_id, tara, netto, opr_id) VALUES ";
		sql += "(";
		sql += produktbatchkomponent.getPbId()  + ", ";
		sql += produktbatchkomponent.getRbId()  + ", ";
		sql += produktbatchkomponent.getTara()  + ", ";
		sql += produktbatchkomponent.getNetto() + ", ";
		sql += produktbatchkomponent.getOprId() + ")";
				
		System.out.println(sql);
		
 		Connector.doUpdate(sql);
		
	}

	public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		String sql = "";
		sql = "UPDATE produktbatchkomponent SET tara = ";
		sql += produktbatchkomponent.getTara() + ", netto = ";
		sql += produktbatchkomponent.getNetto();
		sql += "WHERE pb_id = ";
		sql += produktbatchkomponent.getPbId();
		sql += " AND rb_id = ";
		sql += produktbatchkomponent.getRbId();
	
		System.out.println(sql);
		
		Connector.doUpdate(sql);
		
	}

}
