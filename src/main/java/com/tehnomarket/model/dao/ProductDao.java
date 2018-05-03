package com.tehnomarket.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tehnomarket.controller.DBManager;
import com.tehnomarket.model.Characteristics;
import com.tehnomarket.model.Order;
import com.tehnomarket.model.Product;
import com.tehnomarket.model.User;


@Component
public class ProductDao {

	private static ProductDao instance;
	@Autowired
	private static DBManager manager;
	
	public static ProductDao getInstance() {
		if(instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}
	
	
	// search works with name
	public static Collection<Product> search(String search) throws SQLException {
		Collection<Product> products = new ArrayList<Product>();
		String sql = "SELECT id, name, brand, price, discount, discount_end, product_image, categories_id FROM products WHERE LOCATE(?,name)";

		Connection connection = DBManager.getInstance().getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, search);
		ResultSet result = ps.executeQuery();
		while(result.next()) {
			Product p = new Product(result.getInt("id"),
					result.getString("name"),
					result.getString("brand"),
					result.getFloat("price"),
					null,
					result.getInt("discount"),
					result.getDate("discount_end"),
					result.getString("product_image"),
					result.getInt("categories_id"));
			products.add(p);
		}
		
		return products;
	}
	
	// returns product by id
	public static Product getProductById(int id) throws SQLException {
		String sql = "SELECT id, name, brand, price, discount, discount_end, product_image, categories_id FROM products WHERE id = ?";
		
		Connection connection = DBManager.getInstance().getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, id);
		ResultSet result = ps.executeQuery();
		 
		if(result.next()) {
			Product p = new Product(result.getInt("id"),
					result.getString("name"),
					result.getString("brand"),
					result.getFloat("price"),
					null,
					result.getInt("discount"),
					result.getDate("discount_end"),
					result.getString("product_image"),
					result.getInt("categories_id"));
			
			return p;
		}
		return null;
	}
	
	
	// Function for returning product pages by categories
	public static List<Product> getProductByCat(int catId) throws SQLException{
		List<Product> products = new ArrayList<Product>();
		String sql = "SELECT id, name, brand, price,info, discount, discount_end, product_image, categories_id FROM products WHERE categories_id=?";

		Connection connection = DBManager.getInstance().getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, catId);
		ResultSet result = ps.executeQuery();
		while(result.next()) {
			
			Product p = new Product(
					result.getInt("id"),
					result.getString("name"),
					result.getString("brand"),
					result.getFloat("price"),
					result.getString("info"),
					result.getInt("discount"),
					result.getDate("discount_end"),
					result.getString("product_image"),
					result.getInt("categories_id"));
			products.add(p);
		}
		if(products.isEmpty()) {
			
			return null;
		}
		else {
			
			return products;
		}
		
	
		
	}


	public static ArrayList<Integer> getProductQuantities(ArrayList<Integer> itemIds) throws SQLException {
		ArrayList<Integer> quantities = new ArrayList<Integer>();
		
		System.out.println("GOT TO THE DAO");
		
		for(int i=0;i<itemIds.size();i++) {
			String sql = "SELECT quantity FROM products_has_store WHERE products_id=?";
			
			Connection connection = DBManager.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, itemIds.get(i));
			ResultSet result = ps.executeQuery();
			
			System.out.println("INSIDE FOR LOOP"+i);
			while(result.next()) {
				System.out.println(result.getInt("quantity"));
				quantities.add(result.getInt("quantity"));
			}
			
			
		}
		
		return quantities;
	}
		
	public static void saveProduct(Product p) throws SQLException {
		String sql = "INSERT INTO products(name, brand, price, discount, discount_end, product_image,categories_id) VALUES (?, ?, ?, ?,?,?,?)";
		Connection connection = DBManager.getInstance().getConnection();
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, p.getName());
		ps.setString(2, p.getBrand());
		ps.setFloat(3, p.getPrice());
		ps.setInt(4, p.getDiscount());
		ps.setDate(5, p.getDiscountEnd());
		ps.setString(6, p.getImage());
		ps.setLong(7, p.getCategoryId());
		ps.executeUpdate();
		
	}
	
	public static void deleteProductById(int id) throws SQLException {
		String sql = "DELETE FROM products WHERE id= ?";
		Connection connection = DBManager.getInstance().getConnection();
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		
	}
	
	public static Collection<Product> getAllProducts() throws SQLException {
		Collection<Product> products = new ArrayList<Product>();
		String sql = "SELECT id, name, brand, price, discount, discount_end, product_image, categories_id FROM products";

		Connection connection = DBManager.getInstance().getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet result = ps.executeQuery();
		while(result.next()) {
			Product p = new Product(result.getInt("id"),
					result.getString("name"),
					result.getString("brand"),
					result.getFloat("price"),
					null,
					result.getInt("discount"),
					result.getDate("discount_end"),
					result.getString("product_image"),
					result.getInt("categories_id"));
			products.add(p);
		}
		
		return products;
	}

	// adds fav product 
	public static void addToFavourites(long userId, int idItem) throws SQLException {
		
		String sql = "INSERT INTO favourite_products (users_id,products_id) VALUES (?,?)";
		Connection connection = DBManager.getInstance().getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		int userID = (int) userId;
		ps.setInt(1, userID);
		ps.setInt(2, idItem);
		
		ps.executeUpdate();
		
	}
	
	// removes fav product 
	public static void removeFromFavourites(long userId,int idItem) throws SQLException {
		
		String sql = "DELETE FROM favourite_products WHERE users_id=? AND products_id=?";
		Connection connection = DBManager.getInstance().getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		int userID = (int) userId;
		ps.setInt(1, userID);
		ps.setInt(2, idItem);
		
		ps.executeUpdate();
		
	}
	
	//gets fav products by user
	public static Collection<Product> getFavouritesByUserId(long userId) throws SQLException {
		ArrayList<Product> products = new ArrayList();
		String sql = "SELECT products_id FROM favourite_products WHERE users_id = ?";
		Connection connection = DBManager.getInstance().getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		int userID = (int) userId;
		ps.setLong(1, userID);
		ResultSet rs = 	ps.executeQuery();
		while(rs.next()) {
			products.add(getProductById(rs.getInt("products_id")));
		}
		
		return products;
	}

	/*
	 * 1. Entrance in order
	 * 2. Multiple entrances in product_order
	 * 3. Update product quantity table
	 */
	public static void makeOrder(Order o) throws SQLException {
		
		PreparedStatement orderInsert = null;
		PreparedStatement orderId = null;
		PreparedStatement productOrders = null;
		PreparedStatement quantityUpdate = null; 
		
		String sql1 = "INSERT INTO orders (the_date,total_cost,the_status,users_id,city,street,entrance,phone_number) VALUES (?,?,?,?,?,?,?,?)";
		String sql2 = "SELECT id FROM orders WHERE the_date=? AND users_id=?";
		String sql3 = "INSERT INTO product_orders (order_id,products_id,quantity) VALUES (?,?,?)";
		String sql4 = "UPDATE products_has_store SET quantity=quantity-? WHERE products_id=?";
		
		Connection connection = DBManager.getInstance().getConnection();
		
		try {
			
			connection.setAutoCommit(false);
			
			// first input 
			orderInsert = connection.prepareStatement(sql1);
			
			Date sqlDate = new Date(o.getDateOfOrder().getTime());
			orderInsert.setDate(1, sqlDate);
			
			orderInsert.setDouble(2,o.getTotalCost());
			orderInsert.setInt(3, o.getStatus());
			orderInsert.setInt(4, (int) o.getUserId());
			orderInsert.setString(5, o.getCity());
			orderInsert.setString(6, o.getStreet());
			orderInsert.setString(7, o.getEntrance());
			orderInsert.setInt(8,o.getPhoneNumber());
			
			orderInsert.executeUpdate();
			
			// second query 
			orderId = connection.prepareStatement(sql2);
			
			Date sqlDate2 = new Date(o.getDateOfOrder().getTime());
			orderId.setDate(1, sqlDate2);
			orderId.setInt(2, (int) o.getUserId());
			
			ResultSet rs = orderId.executeQuery();
			
			Integer intOrderId=null;
			while(rs.next()) {
				
				intOrderId = rs.getInt("id");
				System.out.println(intOrderId);
			}
			if(intOrderId==null) {
				System.out.println("DIDNT GET ORDER ID");
				throw new IllegalArgumentException();
			}
			
			// third input 
			productOrders = connection.prepareStatement(sql3);
			HashMap<Product,Integer> theCart = o.getOrder();
			for(Product key : theCart.keySet()) {
				Product p = key;
				int quantity = theCart.get(key);
				int productId = p.getId();
				productOrders.setInt(1,intOrderId);
				productOrders.setInt(2,productId);
				productOrders.setInt(3,quantity);
				productOrders.executeUpdate();
			}
			
			//fourth input 
			quantityUpdate = connection.prepareStatement(sql4);
			for(Product key : theCart.keySet()) {
				Product p = key;
				int quantity = theCart.get(key);
				int productId = p.getId();
				quantityUpdate.setInt(1, quantity);
				quantityUpdate.setInt(2,productId);
				quantityUpdate.executeUpdate();
			}
			
			connection.commit();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			try {
				connection.rollback();
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		/*
		finally {
			connection.setAutoCommit(true);
			if(orderInsert != null) {
				orderInsert.close();
			}
			if(orderId != null) {
				orderId.close();
			}
			if(productOrders != null) {
				orderInsert.close();
			}
			if(quantityUpdate != null) {
				quantityUpdate.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		*/
	}
	
	// edit product 
		public static void editProduct(Product p) throws SQLException {
			
			
			String sql = "UPDATE products SET name = ?, brand = ?, price = ?, info = ?,"
					+ " discount = ?, discount_end = ?, product_image = ?, categories_id = ? "
					+ "WHERE id = ?";
			Connection connection = DBManager.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getBrand());
			ps.setDouble(3, p.getPrice());
			ps.setString(4, p.getInfo());
			ps.setInt(5, p.getDiscount());
			ps.setDate(6, p.getDiscountEnd());
			ps.setString(7, p.getImage());
			ps.setLong(8, p.getCategoryId());
			ps.setInt(9, p.getId());
			
			ps.executeUpdate();
			
		}


		public void replaceProductCharacteristics(ArrayList<Characteristics> characts) throws SQLException {
			
			
			Connection connection = DBManager.getInstance().getConnection();
			try {
			
				connection = DBManager.getInstance().getConnection();
				connection.setAutoCommit(false);
				
				String sql = "DELETE FROM product_characteristics WHERE products_id=?";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, characts.get(0).getProductsId());
				ps.executeUpdate();
				
				for(Characteristics c : characts) {
					sql = "INSERT INTO product_characteristics(products_id,characteristics_id,input) VALUES (?,?,?)";
					
					ps = connection.prepareStatement(sql);
					ps.setInt(1, c.getProductsId());
					ps.setInt(2, c.getCharacteristicsId());
					ps.setString(3, c.getInput());
					ps.executeUpdate();
				}
				
				connection.commit();
				connection.setAutoCommit(true);
			}finally {
				
				connection.setAutoCommit(true);
			}
			
			
		}


		public static void addPictureToProductById(String filePath,int id) throws SQLException {
			String sql = "UPDATE products SET product_image = ? WHERE id = ?";
			Connection connection = DBManager.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, filePath);
			ps.setInt(2, id);
			ps.executeUpdate();
			
		}

}
