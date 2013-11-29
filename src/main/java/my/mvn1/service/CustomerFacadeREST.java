/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.mvn1.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import my.mvn1.Customer;
import my.mvn1.MicroMarket;
import org.glassfish.jersey.server.JSONP;

/**
 *
 * @author msaiki
 */
@Stateless
@Path("my.mvn1.customer")
public class CustomerFacadeREST extends AbstractFacade<Customer> {
	@PersistenceContext(unitName = "my_mvn1_war_1.0-SNAPSHOTPU")
	private EntityManager em;

	public CustomerFacadeREST() {
		super(Customer.class);
	}

	@POST
        @Override
        @Consumes({"application/xml", "application/json"})
	public void create(Customer entity) {
		//MicroMarketFacadeREST microMarketFacadeREST = new MicroMarketFacadeREST();
		//MicroMarket micromarket = microMarketFacadeREST.find(entity.getZip().getZipCode());
		//entity.setZip(micromarket);
		super.create(entity);
	}

	@PUT
        @Path("{id}")
        @Consumes({"application/xml", "application/json"})
	public void edit(@PathParam("id") Integer id, Customer entity) {
		super.edit(entity);
	}

	@DELETE
        @Path("{id}")
	public void remove(@PathParam("id") Integer id) {
		super.remove(super.find(id));
	}

	@GET
        @Path("{id}")
        @Produces({"application/xml", "application/json"})
	public Customer find(@PathParam("id") Integer id) {
		return super.find(id);
	}

	@GET
        @Produces("application/x-javascript")
        @Path("greeting/{id}")
	@JSONP(queryParam=JSONP.DEFAULT_CALLBACK) // JSONP.DEFAULT_CALLBACK=="callback"
	public Customer greeting(@QueryParam(JSONP.DEFAULT_CALLBACK) String callback, @PathParam("id") Integer id) {
		return super.find(id);
	}

	@GET
        @Override
        @Produces({"application/xml", "application/json"})
	public List<Customer> findAll() {
		return super.findAll();
	}

	@GET
        @Path("{from}/{to}")
        @Produces({"application/xml", "application/json"})
	public List<Customer> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
		return super.findRange(new int[]{from, to});
	}

	@GET
        @Path("count")
        @Produces("text/plain")
	public String countREST() {
		return String.valueOf(super.count());
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
}
