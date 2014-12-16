/* Article Title: Serializing a POJO to json using built in jersey support
 * Reference: https://github.com/jasonray/jersey-starterkit/wiki/Serializing-a-POJO-to-json-using-built-in-jersey-support 
 */

package com.rest;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.pojo.Products;

@SuppressWarnings("unchecked")
@Path("TestImpl")
public class TestImpl {
	
	public SqlMapClient getSqlMapClient() throws IOException{
		 Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
		 SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		 return smc;
	}
	
	@GET
	@Path("Products")
	@Produces({"application/json"})
	public  List<Products> getProducts() throws IOException, SQLException { 
		 List<Products> products = (List<Products>)getSqlMapClient().queryForList("Products.selectAll",null);
		 return products;
	}
	
	@GET
	@Path("Product/{product_id}")
	@Produces({"application/json"})
	public List<Products> getProduct(@PathParam("product_id") int product_id) throws IOException, SQLException { 
		 Products product = new Products();
		 product.setProduct_id(product_id);
		 List<Products> listProducts = (List<Products>)getSqlMapClient().queryForList("Products.select",product);
		 return listProducts;
	}

	@POST
	@Path("InsertProduct")
	public String insertProduct() throws IOException, SQLException { 
		 Products product = new Products();
		 product.setProduct_id(1234);
		 product.setProduct_name("Complan");
		 getSqlMapClient().insert("Products.insert",product);
		 return "Record inserted Successfully!";
	}
	
	@PUT
	@Path("UpdateProduct/{product_id}")
	@Produces({"text/plain"})
	public String updateProduct(@PathParam("product_id") int product_id) throws IOException, SQLException { 
		 Products product = new Products();
		 product.setProduct_id(product_id);
		 product.setProduct_name("Horlicks");
		 getSqlMapClient().update("Products.update",product);
		 return "Record updated Successfully!";
	}
	
	@DELETE
	@Path("DeleteProduct/{product_id}")
	@Produces({"text/plain"})
	public String deleteProduct(@PathParam("product_id") int product_id) throws IOException, SQLException { 
		 getSqlMapClient().delete("Products.delete",product_id);
		 return "Record deleted Successfully!";
	}	
}