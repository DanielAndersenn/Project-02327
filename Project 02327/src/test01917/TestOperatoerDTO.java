package test01917;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import daoimpl01917.*;
import daointerfaces01917.*;
import dto01917.*;

import java.sql.SQLException;
import java.util.ArrayList;

import connector01917.Connector;

public class TestOperatoerDTO {

	MySQLOperatoerDAO opr = new MySQLOperatoerDAO();

	@Before 
	public void setUp(){
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }	
	}
	@Test
	public void testGetOperatoerList(){
		ArrayList<OperatoerDTO> temp = null;
		try { temp = opr.getOperatoerList(); }
		catch(DALException e) { System.out.println(e.getMessage()); }
		assertTrue(temp.size() == 3);
	}
	@Test
	public void testGetOperatoer() {
		OperatoerDTO oprExpected = new OperatoerDTO("Angelo A", "AA", "070770-7007", "lKje4fa");
		oprExpected.setOprId(1);
		OperatoerDTO oprActual = null;
		try { 
			oprActual = opr.getOperatoer(1); 
		}
		catch (DALException e) { System.out.println(e.getMessage()); }

		assertNotNull(oprExpected);
		assertNotNull(oprActual);

		assertEquals(oprExpected.getOprId(), oprActual.getOprId());
		assertEquals(oprExpected.getOprNavn(), oprActual.getOprNavn());
		assertEquals(oprExpected.getIni(), oprActual.getIni());
		assertEquals(oprExpected.getCpr(), oprActual.getCpr());
		assertEquals(oprExpected.getPassword(), oprActual.getPassword());
	}
	@Test
	public void testCreateOperatoer() {
		ArrayList<OperatoerDTO> temp = null;
		OperatoerDTO oprExpected = new OperatoerDTO("Kanye West", "KW", "111111-1111", "yeezy");
		OperatoerDTO oprActual = null;
		try {
			opr.createOperatoer(oprExpected);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try{
			temp = opr.getOperatoerList();
			for(int i = 0; i < temp.size(); i++){
				if(temp.get(i).getOprNavn().equals("Kanye West")){
					oprActual = temp.get(i);
					break;
				}
			}
			assertEquals(oprExpected.getOprNavn(), oprActual.getOprNavn());
			assertEquals(oprExpected.getIni(), oprActual.getIni());
			assertEquals(oprExpected.getCpr(), oprActual.getCpr());
			assertEquals(oprExpected.getPassword(), oprActual.getPassword());
		} catch (DALException e) { System.out.println(e); }
	}
	@Test
	public void testUpdateOperatoer(){
		OperatoerDTO oprExpected = new OperatoerDTO("Vic Mensa", "VM", "200000-9001", "wimmenah");
		oprExpected.setOprId(3);
		OperatoerDTO oprActual = null;
		try {
			opr.updateOperatoer(oprExpected);
			oprActual = opr.getOperatoer(3);
		} catch (DALException e) {
			e.printStackTrace();
		}
		assertEquals(oprExpected.getOprNavn(), oprActual.getOprNavn());
		assertEquals(oprExpected.getIni(), oprActual.getIni());
		assertEquals(oprExpected.getCpr(), oprActual.getCpr());
		assertEquals(oprExpected.getPassword(), oprActual.getPassword());
	}
	@Test
	public void testDeleteOperatoer(){
		try {
			opr.createOperatoer(new OperatoerDTO("Kendrick Lamar", "KL", "121212-1313", "alright"));
			opr.deleteOperatoer(opr.getOperatoer(4));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
}
