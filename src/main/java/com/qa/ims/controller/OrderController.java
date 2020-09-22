package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderLineDAO;
import com.qa.ims.persistence.dao.ProductDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderLine;
import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();



	private OrderDAO orderDAO;
	private OrderLineDAO orderLinesDAO;
	private CustomerDAO customerDAO;
	private ProductDAO productDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO,OrderLineDAO orderLineDAO,CustomerDAO custDAO, ProductDAO prodDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.orderLinesDAO = orderLineDAO;
		this.customerDAO = custDAO;
		this.productDAO = prodDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			order.setOrderLines(orderLinesDAO.readAll(order.getId()));
			LOGGER.info(order.toString() + " order total: "+ order.getTotal());
		}
		return orders;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter a Customer ID");
		Long customerID = utils.getLong();
		//check customer exists
		Customer customer = customerDAO.readCustomer(customerID);
		if(customer == null) {
			LOGGER.info("Customer does not exist");
			return create();
		}else {		
			Order order = orderDAO.create(new Order (customerID));
			LOGGER.info("Order created for " + customer.getFirstName()+ " "+ customer.getSurname());
			AddNewLines(order);
			return order;
		}
	}
	
	private void AddNewLines(Order order) {
		LOGGER.info("Please enter a Product ID");
		Long productID = utils.getLong();
		// check product exists
		Product product = productDAO.readProduct(productID);
		if(product == null) {
			LOGGER.info("Product does not exist");
			AddNewLines(order);
		}else {	
			OrderLine line = order.AddLine(productID, product.getPrice());
			orderLinesDAO.create(line);
			LOGGER.info(product.getProductName() + " added to order for " + product.getPrice());
			LOGGER.info("would you like to add another product? (Y or N)");
			String result = utils.getString();
			if(result.equals("Y")) {
				AddNewLines(order);
			}
		}
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		Order order = orderDAO.readOrder(id);
		if(order == null) {
			LOGGER.info("Order does not exist");
			return update();
		}else {			
			// list out all the lines
			List<OrderLine> ordersLines = orderLinesDAO.readAll(id);
			for (OrderLine line : ordersLines) {
				LOGGER.info(line.toString());
			}
			//ask user to choose one to delete
			LOGGER.info("Please enter the id of the line you want to delete");
			Long lineID = utils.getLong();
			OrderLine orderline = orderLinesDAO.readOrderLine(lineID);
			if(orderline == null) {
				LOGGER.info("Line does not exist");
			}else{
				orderLinesDAO.delete(lineID);
				LOGGER.info("Order Updated");
			}
			return order;
		}
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public long delete() {
		LOGGER.info("Please enter the id of the order");
		Long id = utils.getLong();
		Order order = orderDAO.readOrder(id);
		if(order == null) {
			LOGGER.info("Order does not exist");
			return delete();
		}else {			
			//delete lines
			orderLinesDAO.delete(id);
			return orderDAO.delete(id);
		}
	}

}
