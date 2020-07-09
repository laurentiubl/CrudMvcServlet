package Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Model.Product;
public class DaoProduct implements ProductDao{

	private DaoProduct() {}
	
	private static class SingletonHelper{
		private static final DaoProduct INSTANCE = new DaoProduct();
	}
	
	public static DaoProduct getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	
	@Override
	public Optional<Product> find(String id) throws SQLException {
		
		String sql = "SELECT id,name,description,quantity,location FROM products where id = ?";
		int id_product = 0, quantity = 0;
		String name = "", description = "", location = "";
		Connection conn = DbConnect.getInstance().getConnection();
		
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, id);
		ResultSet resultSet = stm.executeQuery();
		
		if(resultSet.next()) {
			id_product = resultSet.getInt("id");
			name = resultSet.getString("name");
			description = resultSet.getString("description");
			quantity = resultSet.getInt("quantity");
			location = resultSet.getString("location");
		}
		return Optional.of(new Product(id_product, name, description, quantity, location));
	}

	@Override
	public List<Product> findAll() throws SQLException {
		
		List<Product> listProducts = new ArrayList<>();
		String sql = "SELECT id,name,description,quantity,location FROM products";
		
		Connection conn = DbConnect.getInstance().getConnection();
		Statement stm = conn.createStatement();
		ResultSet resultSet;
		resultSet = stm.executeQuery(sql);
		
		while(resultSet.next()) {
			
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			int quantity = resultSet.getInt("quantity");
			String location = resultSet.getString("location");
			
			Product product = new Product(id, name, description, quantity, location);
			listProducts.add(product);
		}
		
		
		return listProducts;
	}

	@Override
	public boolean save(Product product) throws SQLException {
		
		String sql = "INSERT into products (name, description, quantity, location)VALUES(?,?,?,?)";
		boolean rowInserted = false;
		Connection conn = DbConnect.getInstance().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, product.getName());
			stm.setString(2, product.getDescription());
			stm.setInt(3, product.getQuantity());
			stm.setString(4, product.getLocation());
			rowInserted = stm.executeUpdate() > 0;
			
	
		return rowInserted;
	}

	@Override
	public boolean update(Product product) throws SQLException {
		
		String sql = "UPDATE products SET name = ?, description = ?, quantity = ?, location = ?";
		
		sql += " WHERE id= ?";
		boolean rowUpdated = false;
		Connection conn = DbConnect.getInstance().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, product.getName());
			stm.setString(2, product.getDescription());
			stm.setInt(3, product.getQuantity());
			stm.setString(4, product.getLocation());
			stm.setInt(5, product.getId());
			rowUpdated = stm.executeUpdate() > 0;
			
		return rowUpdated;
	}

	@Override
	public boolean delete(Product product) throws SQLException {


		String sql = "DELETE FROM products WHERE id = ?";
		boolean rowDeleted = false;
		
		Connection conn = DbConnect.getInstance().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, product.getId());
			rowDeleted = stm.executeUpdate() > 0;
			
		return rowDeleted;
	}

}