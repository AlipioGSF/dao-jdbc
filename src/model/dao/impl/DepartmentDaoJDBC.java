package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entitites.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"insert into department "
					+ "(Name) values (?)"
					,Statement.RETURN_GENERATED_KEYS
			);
			
			st.setString(1, obj.getName());
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					obj.setId(rs.getInt(1));					
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Unexpected error!");
			}

			
					
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"update department "
							+ "set Name = ? "
							+ "where Id = ?"
					, Statement.RETURN_GENERATED_KEYS
					);
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		
	}

	@Override
	public void deleteByid(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"delete from department "
					+ "where Id = ?"
			);
			
			st.setInt(1, id);
			
			st.executeUpdate();
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
				"select * from department "
				+ "where Id = ?", Statement.RETURN_GENERATED_KEYS
			);
			
			st.setInt(1, id);

			rs = st.executeQuery();
			
			if(rs.next()) {
				Department dep = new Department(rs.getInt(1), rs.getString(2));
				return dep;
			}
					
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return null;
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"select * from department"
					,Statement.RETURN_GENERATED_KEYS
			);
			
			rs = st.executeQuery();
			List<Department> list = new ArrayList<>();
			
			while(rs.next()) {
				list.add(new Department(rs.getInt(1), rs.getNString(2)));
			}
			
			return list;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

}
