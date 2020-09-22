package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.DBUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDAOTest {

	private final ProductDAO DAO = new ProductDAO();

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
		final Product created = new Product(1L,"coke", 2.99);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void test2ReadAll() {
		List<Product> expected = new ArrayList<>();
		expected.add(new Product(1L, "coke", 2.99));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void test3Read() {
		final long ID = 1L;
		assertEquals(new Product(ID, "coke", 2.99), DAO.readProduct(ID));
	}

	@Test
	public void test4Update() {
		final Product updated = new Product(1L, "7up", 2.49);
		assertEquals(updated, DAO.update(updated));
	}
	
	@Test
	public void test5ReadLatest() {
		assertEquals(new Product(1L, "7up",  2.49), DAO.readLatest());
	}

	@Test
	public void test6Delete() {
		assertEquals(1, DAO.delete(1));
	}
}
