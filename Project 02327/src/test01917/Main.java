package test01917;

import daoimpl01917.*;
import daointerfaces01917.*;
import dto01917.*;

import java.sql.SQLException;

import connector01917.Connector;

public class Main {
	public static void main(String[] args) {
		testOperatoerDTO2();
		//testProduktBatchKompDTO();
		
	}
	
	public static void testOperatoerDTO2() 
	{
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		MySQLOperatoerDAO opr = new MySQLOperatoerDAO();
		
		//Indsæt ny operatoer
		System.out.println("Indsaettelse af ny operatoer");
		OperatoerDTO oprDTO = new OperatoerDTO(4,"Don Juan","DJ","000000-0000","iloveyou");
		try { opr.createOperatoer(oprDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		//Vis den nyligt indsatte operatoer
		System.out.println("Operatoer nummer 4:");
		try { System.out.println(opr.getOperatoer(4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		//Opdater den nye operatoer
		System.out.println("Opdatering af initialer for den nye operatoer");
		oprDTO.setIni("DoJu");
		try { opr.updateOperatoer(oprDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		//Vis alle operatoerer
		System.out.println("Alle operatoerer:");
		try { System.out.println(opr.getOperatoerList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		//Slet den nye operatoer
		System.out.println("Sletter den nye operatoer");
		try { opr.deleteOperatoer(oprDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		//Vis alle operatoerer, igen
		System.out.println("Alle operatoerer:");
		try { System.out.println(opr.getOperatoerList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
	}
	
	public static void testOperatoerDTO() 
	{
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		System.out.println("Operatoer nummer 3:");
		MySQLOperatoerDAO opr = new MySQLOperatoerDAO();
		try { System.out.println(opr.getOperatoer(3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny operatoer med opr_id =  4");
		OperatoerDTO oprDTO = new OperatoerDTO(4,"Don Juan","DJ","000000-0000","iloveyou");
		try { opr.createOperatoer(oprDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("Operatoer nummer 4:");
		try { System.out.println(opr.getOperatoer(4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af initialer for operatoer nummer 4");
		oprDTO.setIni("DoJu");
		try { opr.updateOperatoer(oprDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Operatoer nummer 4:");
		try { System.out.println(opr.getOperatoer(4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle operatoerer:");
		try { System.out.println(opr.getOperatoerList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Operatoer nummer 5:");
		try { System.out.println(opr.getOperatoer(5)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
			
	}


	public static void testProduktBatchKompDTO() 
	{
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		System.out.println("Produktbatchkomponent med ref_pb_id = 1 og ref_rb_id = 1");
		MySQLProduktBatchKompDAO pbk = new MySQLProduktBatchKompDAO();
		try
		{
			System.out.println(pbk.getProduktBatchKomp(1, 1));
		} catch(DALException e)
			{
			System.out.println(e.getMessage());
			}
		
		System.out.println("Indsættelse af ny Produktbatchkomponent");
		try
		{
			ProduktBatchKompDTO test = new ProduktBatchKompDTO(5, 2, 10.2, 3.2, 1);
			pbk.createProduktBatchKomp(test);
		} catch(DALException e)
			{
			System.out.println(e.getMessage());
			}
		
		System.out.println("Printer all produktbatchkomponenter med ref_pb_id = 1:");
		try
		{
			System.out.println(pbk.getProduktBatchKompList(1));
		} catch(DALException e)
			{
			System.out.println(e.getMessage());
			}
		
		System.out.println("Printer all produktbatchkomponenter:");
		try
		{
			System.out.println(pbk.getProduktBatchKompList());
		} catch(DALException e)
			{
			System.out.println(e.getMessage());
			}
		
	}

	
	
	
}