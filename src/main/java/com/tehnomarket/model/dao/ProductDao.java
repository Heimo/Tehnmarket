package com.tehnomarket.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tehnomarket.controller.DBManager;
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
			products.add(getProductById(rs.getInt("id")));
		}
		return products;
	}

}
