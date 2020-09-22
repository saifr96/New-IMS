package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerDAOTest {

	private final CustomerDAO DAO = new CustomerDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "google96");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/main/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void test1Create() {
		final Customer created = new Customer(1L, "chris", "perrins");
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void test2ReadAll() {
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(1L, "chris", "perrins"));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void test3Read() {
		final long ID = 1L;
		assertEquals(new Customer(ID, "chris", "perrins"), DAO.readCustomer(ID));
	}

	@Test
	public void test4Update() {
		final Customer updated = new Customer(1L, "jordan", "harrison");
		assertEquals(updated, DAO.update(updated));

	}
	
	@Test
	public void test5ReadLatest() {
		assertEquals(new Customer(1L, "jordan", "harrison"), DAO.readLatest());
	}

	@Test
	public void test6Delete() {
		assertEquals(1, DAO.delete(1));
	}
}