package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entitites.Department;

public class Program2 {

	public static void main(String[] args) {

		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== TEST 1 : Department findAll === ");
		List<Department> deps = depDao.findAll();
		deps.forEach(System.out::println);
		
		System.out.println("=== TEST 2 : Department findById === ");
		Department dep = depDao.findById(7);
		System.out.println(dep);
		
		System.out.println("=== TEST 3 : Department insert === ");
		Department newDep = new Department(null, "Pop");
		depDao.insert(newDep);
		System.out.println("Added a new department");
		
		System.out.println("=== TEST 4 : Department update === ");
		dep.setName("NovoNome");
		depDao.update(dep);
		

		System.out.println("=== TEST 5 : Department delete === ");
		depDao.deleteByid(7);
		System.out.println("Deleted");
		
	}

}
