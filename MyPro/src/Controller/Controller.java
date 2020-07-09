package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import Database.DaoProduct;

import Database.checkUserPass;
import Model.Product;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Controller() {
        super();
      
    }

    
    private DaoProduct daoProduct = DaoProduct.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
    
	
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String user = request.getParameter("account");
	    String pass = request.getParameter("password");
		    
//	 String action = request.getServletPath();
	    String op = request.getParameter("op");
	   
	    
	try {
		switch(op) {
	
		case"login":
			if(checkUserPass.login(user, pass) == true) {
	    		System.out.println("it s ok..:D");
	    			session.setAttribute("user", user);
	    			session.setMaxInactiveInterval(300);
	    				forward(request,response,"/home.jsp");
	    	} 
	    	else {
	    		System.out.println("non entra");
	    			forward(request,response,"/login.jsp");
	    	}
			break;
		
		case"logout":
			session.invalidate();
	    	forward(request,response,"/login.jsp");
			break;
			
		case"edit":
			showEditForm(request, response);
			break;
		case "neww":
			listProducts(request, response);
		//	showNewForm(request, response);
			break;
		case "insert":
			insertProduct(request, response);
			break;
		case "delete":
			deleteProduct(request, response);
			break;
//		default:
//			listProducts(request, response);
//			break;
		}
//		/getServletPath()
	}
	


catch(SQLException e){		
	LOGGER.log(Level.SEVERE, "SQL Error");
}

	}
	
	

public void insertProduct(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException{
	
	String name = request.getParameter("name");
	String description = request.getParameter("description");
	int quantity = Integer.parseInt(request.getParameter("quantity"));
	String location = request.getParameter("location");
	
	Product newProduct = new Product(name, description, quantity, location);
	daoProduct.save(newProduct);
	response.sendRedirect("home.jsp");
//	forward(request,response,"/home.jsp");
	

}


public JSONObject getJson(String id) throws SQLException, ParseException {
	

	List<Product> list = new ArrayList();
	list = daoProduct.findAll();
	
	String jsonn = new Gson().toJson(list);
	try{
		 JSONArray array = new JSONArray(jsonn);
		 for(int i = 0; i < array.length();i++) {	 
			 JSONObject obj = array.optJSONObject(i);
			 if(obj.has("id")) {
				  int idInt = obj.getInt("id");
				  String str2 = Integer.toString(idInt);
				  if(str2.equalsIgnoreCase(id)){
					  return obj;
				  }	
			 }
		 }
	}catch(JSONException e){
		e.printStackTrace();
		}
	return null;
	}

//JSONParser parser = new JSONParser();
//JSONObject jsonOb = new JSONObject();
//jsonOb = (JSONObject) parser.parse(jsonn);
//json = new JSONObject(jsonn); 
//			if(array.has("Id") ) {
//				int idInt = json.getInt("Id");
//				String str2 = Integer.toString(idInt);
//			if(str2.contains(id)) {
//				JSONObject j = new JSONObject();
//				
//				 String pageName = json.getString("Name");
//				 
//				 System.out.println(pageName);
//			j =	json.get("Id");
				
				
//				
//				return j;
//    	}
//    	return json;
//    }else {
//    	System.out.println("The json don t have Id field...");
//    }
//    
//      
//    }catch(JSONException e){
//        e.printStackTrace();
//    }
//	
//	return  json;
//	
//}


 private void forward(HttpServletRequest request, HttpServletResponse response, String page) 
	       throws ServletException, IOException
	    {
	        ServletContext sc = getServletContext();
	        RequestDispatcher rd = sc.getRequestDispatcher(page);
	        rd.forward(request,response);
	  }


//private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//		throws SQLException, IOException, ServletException{
//	
//	RequestDispatcher disp = request.getRequestDispatcher("/ProductForm.jsp");
//	disp.forward(request, response);
//	
//}

private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException{
	
	int id = Integer.parseInt(request.getParameter("opp"));
	
	Product product = new Product(id);
	daoProduct.delete(product);
	forward(request,response,"/home.jsp");
	

}

//mi piace this

private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
		throws SQLException, IOException, ServletException{
	String id = request.getParameter("opp");
	Optional<Product> existingProduct = daoProduct.find(id);
	RequestDispatcher disp = request.getRequestDispatcher("/ProductForm.jsp");
	existingProduct.ifPresent(s -> request.setAttribute("product", s));
	disp.forward(request, response);
}


private void listProducts(HttpServletRequest request, HttpServletResponse response) 
throws SQLException, IOException, ServletException{
	RequestDispatcher dispatcher = request.getRequestDispatcher("/ProductsList.jsp");

	List<Product> listProducts = daoProduct.findAll();
	request.setAttribute("listProducts", listProducts);
	dispatcher.forward(request, response);
	
}
		
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
