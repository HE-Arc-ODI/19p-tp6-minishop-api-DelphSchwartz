/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HEG Arc.
 */

package ch.hearc.ig.odi.minishop.restresources;

import ch.hearc.ig.odi.minishop.business.Customer;
import ch.hearc.ig.odi.minishop.business.Order;
import ch.hearc.ig.odi.minishop.business.Product;
import ch.hearc.ig.odi.minishop.exception.*;
import ch.hearc.ig.odi.minishop.exception.NotFoundException;
import ch.hearc.ig.odi.minishop.services.PersistenceService;
import java.text.ParseException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
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
                throw new NotFoundException("the customer does not exist");
            }
        }

        @POST
        public Customer customerPost(@FormParam("username") String username,
                                     @FormParam("firstName") String firstname,
                                     @FormParam("lastName") String lastname,
                                     @FormParam("email") String email,
                                     @FormParam("phone") String phone) throws ParseException, CustomerException {
            return persistenceService.createAndPersistCustomer(
                    username,
                    firstname,
                    lastname,
                    email,
                    phone);
        }


        @DELETE
        @Path("{id}")
        @Consumes(MediaType.APPLICATION_JSON)
        public void deleteCustomer(@PathParam("id") Long customerId) {
            try {
                persistenceService.deleteProduct(id);
            } catch (CustomerException e) {
                e.printStackTrace();
                throw new NullFormException("customer not deleted.");
            }
        }

        @PUT
        @Path("{id}")
        @Consumes(MediaType.APPLICATION_JSON)
        public Customer updateCustomer(@PathParam("id") Long customerId, Customer customer) {
            try {
                return persistenceService.updateCustomer(customerId, customer);
            } catch (CustomerException e) {
                e.printStackTrace();
                throw new NullFormException("customer couldn't have been updated.");
            }
        }
    }
}

