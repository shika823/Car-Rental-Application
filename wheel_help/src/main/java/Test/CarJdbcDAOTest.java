package Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.CarDAO;
import DAO.CarJdbcDAO;
import Domain.Car;
import java.math.BigDecimal;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author shika823
 */
public class CarJdbcDAOTest {

	public CarJdbcDAOTest() {
	}

	/**
	 *
	 * @author shika823
	 */
	public class JDBCDAOTest {

		private CarDAO dao = new CarJdbcDAO("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/schema.sql'");

		private Car car1;

		private Car car2;

		private Car car3;

//	String carName, int carId, String carType, String seatNumber, BigDecimal hourlyCharge, String location) {
		@BeforeEach
		public void setUp() {
			car1 = new Car("2006 Ford Mondeo", "Hatchnack", "1", new BigDecimal("1.00"), "30 Dundas Street");
			car2 = new Car("2007 Honda Civic", "Hatchback", "1", new BigDecimal("5.00"), "301 Great King Street");
			car3 = new Car("2001 Toyota Prado", "4x4", "1", new BigDecimal("4.00"), "30  duke Street");

			dao.saveCar(car2);

		}

		@AfterEach
		public void tearDown() {
			dao.removeCar(car1);
			dao.removeCar(car2);
		}

		@Test
		public void testSaveCar() {
			dao.saveCar(car3);

			assertThat(dao.getCar(), hasItem(car1));
			assertThat(dao.getCar(), hasItem(car2));
			assertThat(dao.getCar(), hasItem(car3));

		}

		@Test
		public void testGetCar() {
			assertThat(dao.getCar(), hasItem(car1));
			assertThat(dao.getCar(), hasItem(car2));
		}

		@Test
		public void testRemoveProduct() {
			dao.removeCar(car1);
			assertThat(dao.getCar(), hasSize(1));
			assertThat(dao.getCar(), not(hasItem(car1)));
		}
	}

}