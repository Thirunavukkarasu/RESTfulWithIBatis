/* Article Title: Serializing a POJO to json using built in jersey support
 * Reference: https://github.com/jasonray/jersey-starterkit/wiki/Serializing-a-POJO-to-json-using-built-in-jersey-support 
 */

package com.rest;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.codehaus.jettison.json.JSONObject;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.pojo.Product;

@SuppressWarnings("unchecked")
@Path("TestImpl")
public class TestImpl {
	
	public SqlMapClient getSqlMapClient() throws IOException{
		 Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
		 SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		 return smc;
	}
	
	@GET
	@Path("SelectProduct")
	@Produces({"application/json"})
	public  List<Product> getProducts() throws IOException, SQLException { 
		 List<Product> products = (List<Product>)getSqlMapClient().queryForList("Product.selectAll",null);
		 return products;
	}
	
	@GET
	@Path("SelectProduct/{product_id}")
	@Produces({"application/json"})
	public List<Product> getProduct(@PathParam("product_id") int product_id) throws IOException, SQLException { 
		 List<Product> listProducts = (List<Product>)getSqlMapClient().queryForList("Product.select",product_id);
		 return listProducts;
	}

	@POST
	@Path("InsertProduct")
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public List<Product> insertProduct(Product newProduct) throws IOException, SQLException {
		 getSqlMapClient().insert("Product.insert",newProduct);
		 return (List<Product>)getSqlMapClient().queryForList("Product.selectAll",null);
	}
	
	@PUT
	@Path("UpdateProduct/{product_id}")
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public List<Product> updateProduct(@PathParam("product_id") int product_id,Product updatedProduct) throws IOException, SQLException { 
		updatedProduct.setProduct_id(product_id);
		 getSqlMapClient().update("Product.update",updatedProduct);
		 return (List<Product>)getSqlMapClient().queryForList("Product.selectAll",null);
	}
	
	@DELETE
	@Path("DeleteProduct/{product_id}")
	@Produces({"application/json"})
	public List<Product> deleteProduct(@PathParam("product_id") int product_id) throws IOException, SQLException { 
		 getSqlMapClient().delete("Product.delete",product_id);
		 return (List<Product>)getSqlMapClient().queryForList("Product.selectAll",null);
	}	
}