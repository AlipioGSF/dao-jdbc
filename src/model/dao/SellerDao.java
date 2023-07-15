package model.dao;

import java.util.List;

import model.entitites.Department;
import model.entitites.Seller;

public interface SellerDao {
	void insert(Seller obj);
	void update(Seller obj);
	void deleteByid(Integer id);
	void deleteByName(String name);
	Seller findById(Integer id);
	List<Seller> findAll();
	List<Seller>  findByDepartment(Department department);
	
}
