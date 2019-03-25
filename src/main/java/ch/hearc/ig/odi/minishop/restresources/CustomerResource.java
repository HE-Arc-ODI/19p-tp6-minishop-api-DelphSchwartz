/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HEG Arc.
 */

package ch.hearc.ig.odi.minishop.restresources;

import ch.hearc.ig.odi.minishop.business.Customer;
import ch.hearc.ig.odi.minishop.business.Order;
import ch.hearc.ig.odi.minishop.exception.CustomerException;
import ch.hearc.ig.odi.minishop.exception.NotFoundException;
import ch.hearc.ig.odi.minishop.exception.OrderException;
import ch.hearc.ig.odi.minishop.services.PersistenceService;
import java.text.ParseException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class CustomerResource {

    @Path("customer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public class OrderResource {

        @Inject
        private PersistenceService persistenceService;

        @GET
        public List<Customer> customerGet() {
            return persistenceService.getAllCustomers();
        }

        @GET
        @Path("{id}")
        public Customer customerIdGet(@PathParam("id") Long customerid) {
            try {
                return persistenceService.getCustomerByID(customerid);
            } catch (CustomerException e) {
                e.printStackTrace();
                throw new NotFoundException("the order does not exist");
            }
        }

        @GET
        @Path("{username}")
        public Customer usernameGet(@PathParam("username") Long username) {
            try {
                return persistenceService.getCustomerByUsername(username);
            } catch (CustomerException e) {
                e.printStackTrace();
                throw new NotFoundException("the order does not exist");
            }
        }

        @POST
        public Customer customerPost(@FormParam("cartid") Long cartid) throws ParseException, CustomerException {
            return persistenceService.createCustomer(cartid);
        }
}

