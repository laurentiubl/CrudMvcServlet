package Test;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

//import static net.sourceforge.jwebunit.junit.JWebUnit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;

import Controller.Controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import Database.DaoProduct;
import Database.DbConnect;
import Model.Product;


public class TestServ {

	

	  @Mock
	  private Product product;
	
	  @Before
	    public void setUp() throws Exception {
	        MockitoAnnotations.initMocks(this);
	 
	      
	        
			product = new Product();
			product.setDescription("Laptop");
			product.setLocation("Trieste");
			product.setName("Dell");
			product.setQuantity(3);
			
			
	    }
	  
	  
	  
//	  @Before
//	    public void prepare() {
//	        setBaseUrl("http://localhost:9090/login");
//	    }
//
//	    @Test
//	    public void testLogin() {
//	        beginAt("/home");
//	        clickLink("login");
//	        assertTitleEquals("Login");
//	        setTextField("username", "admin");
//	        setTextField("password", "admin");
//	        submit();
//	        assertTitleEquals("Welcome, test!");
//	    }
//	  
//	
	@Test
	public void testParam() throws IOException, ServletException, SQLException, ParseException, JSONException {
		
		
//	 when(request.getParameter("op")).thenReturn("insert");
//	  new ControllerServ().doGet(request, response);
//		
		
//		assertThat(controller).isNotNull();
		
		 	HttpServletRequest mockRequest = mock(HttpServletRequest.class);
	        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
//	        ServletOutputStream mockOutput = mock(ServletOutputStream.class);
//	        when(mockResponse.getOutputStream()).thenReturn(mockOutput);
//	        mockRequest.setAttribute("op", "rosa");
	        
	        
	        
	        Controller cont = new Controller();
	      
	        when(mockRequest.getParameter("name")).thenReturn("admin");
	        when(mockRequest.getParameter("description")).thenReturn("test");
	        when(mockRequest.getParameter("quantity")).thenReturn("1");
	        when(mockRequest.getParameter("location")).thenReturn("edit");
	  
	        
	        cont.insertProduct(mockRequest, mockResponse);
	        
	        JSONObject json = new JSONObject();
	        
	       json =  cont.getJson("1");
	       System.out.println(json);
	       
	       JSONAssert.assertEquals("{name:\"Jason\"}", json, false);
	       
	       
	   
	
	}
	
	
	 @Test
	    public void testDbConnect() throws SQLException {
	       
		Connection con= null;
				
			con =	DbConnect.getInstance().getConnection();
			boolean var = false;
			
			if(con != null) {
				var = true;
				
			}
			assertEquals(var, true);
		
		
		String sql = "INSERT into products (name, description, quantity, location)VALUES(?,?,?,?)";
	
		boolean pass = false;
		
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, product.getName());
			stm.setString(2, product.getDescription());
			stm.setInt(3, product.getQuantity());
			stm.setString(4, product.getLocation());
			boolean rowInserted = false;
			
			rowInserted = stm.executeUpdate() > 0;
			
			
			if(rowInserted == true) {
				pass = true;
				assertEquals(pass, true);
			}
			
			assertEquals(rowInserted, true);
			
		
	    }    
	
	 
	 
	
	
	//Controller controller = new Controller();

	
	
	@Test
	public void testServlet() throws IOException, ServletException, SQLException{
		
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
	 
	
		 when(request.getParameter("op")).thenReturn("insert");
	
		 
		 DaoProduct daoProduct = DaoProduct.getInstance();
		
		 
//		new Controller().doPost(request, response);
		
		
		
		
//		controller.doGet(request, response);
		
//		Optional<Product> existingProduct = pro.find("4");
		 Optional<Product> existingProduct = daoProduct.find("3");
		
		 
		existingProduct.ifPresent(s -> request.setAttribute("product", s));
		
//		request.getAttribute("product");
		
		List<Product> product = (ArrayList<Product>) request.getAttribute("product");
		
		
//		for(Product p : product) {
//			System.out.println(p.getName());
//		}
		
//		controller.doGet(request, response);
		
//		assertThat(product).isSameAs(request.getAttribute("product"));
		
		
		
		
	}
	
	
	
	 @Test
	    public void createUserAndAssert() throws SQLException {
	       
		 
		 DaoProduct daoProduct = DaoProduct.getInstance();
		  
		 daoProduct.save(product);
		  
		 assertThat(daoProduct.save(product), is(notNullValue()));
		 
		
		 Optional<Product> pro = daoProduct.find("25");
		 
		 List<Product> list =new ArrayList<Product>();
		  
		 list.add(product);	
		 list.add(product);
		 assertThat(list, contains(product, product));
		 
		 assertEquals(product, product);
		 
		 assertEquals(1,1);
		
		 assertThat(product.getId(), equalTo(product.getId()));
		
//		 assertEquals(1,2);
		 
		
	  
//	  assertThat(product)
//	    .returns("Dell", from(Product::getName)),
//	   
//	    .returns("Laptop", from(Product::getDescription)),
//	    .returns("Dell", from(Product::getName));
//		  
//	    }
//	 
	 
//	 @Test(expected = RuntimeException.class)
//     public void testAddCustomer_throwsException() {
//		 
//         when(pro.save(any(Product.class))).thenThrow(RuntimeException.class);
//         
//         Product pro2 = new Product();
//         
//       
//     }
	 
	 }
	
	
}
