package application;

import java.util.Date;

import model.entitites.Department;
import model.entitites.Seller;

public class Program {
	public static void main(String[] args) {
		
		Department obj = new Department(1, "Books");
		
		
		Seller seller = new Seller(1,"Tiko","Tiko@email.com", new Date(),2000.00,obj);
		
		System.out.println(seller.toString());
		
	}

}
