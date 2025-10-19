package com.ra2.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ra2.Model.Customer;

@Repository
public class CustomerRepository {
	
	@Autowired
	public JdbcTemplate jdbcTemp;
	
	private static final class CustomerRowMapper implements RowMapper<Customer>{
		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setId(rs.getLong("id"));
			customer.setFirstName(rs.getString("f_name"));
			customer.setLastName(rs.getString("l_name"));
			customer.setAge(rs.getInt("age"));
			customer.setCicle(rs.getString("cicle"));
			customer.setYear(rs.getInt("ano"));
			return customer;
		}
	}
	
	public void createTableCustomers() {
		jdbcTemp.execute("DROP TABLE customers if EXISTS");
		jdbcTemp.execute("CREATE TABLE customers (id SERIAL, f_name VARCHAR(255), l_name VARCHAR(255), age INT, cicle VARCHAR(255), ano INT)");
	}
	
	public void insertSampleData() {
		jdbcTemp.update("INSERT INTO customers (f_name, l_name, age, cicle, ano) VALUES (?, ?, ?, ?, ?)", "John", "Doe", 29, "DAM", 2025);
		jdbcTemp.update("INSERT INTO customers (f_name, l_name, age, cicle, ano) VALUES (?, ?, ?, ?, ?)", "Jane", "Smith", 20, "DAW", 2025);
		jdbcTemp.update("INSERT INTO customers (f_name, l_name, age, cicle, ano) VALUES (?, ?, ?, ?, ?)", "Bob", "Johnson", 25, "ASIX", 2025);
	}
	
	public List<Customer> findAll(){
		return jdbcTemp.query("SELECT id, f_name, l_name, age, cicle, ano FROM customers", new CustomerRowMapper());
	}
}
