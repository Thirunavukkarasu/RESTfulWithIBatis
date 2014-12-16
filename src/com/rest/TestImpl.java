/* Article Title: Serializing a POJO to json using built in jersey support
 * Reference: https://github.com/jasonray/jersey-starterkit/wiki/Serializing-a-POJO-to-json-using-built-in-jersey-support 
 */

package com.rest;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.pojo.Products;


@Path("TestImpl")
public class TestImpl {
	
	@GET
	@Produces({"application/json"})
	public  List<Products> resourceMethodGET() throws IOException, SQLException { 
		 Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
		 SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		 List<Products> products = (List<Products>)smc.queryForList("Products.getAll");
		 return products;
	}
}