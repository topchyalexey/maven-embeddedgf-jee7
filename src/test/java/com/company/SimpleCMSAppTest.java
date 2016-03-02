package com.company;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.io.FileUtils;
import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.company.boundary.SimpleCMSService;
import com.company.model.Department;
import com.company.model.Employee;

public class SimpleCMSAppTest {
	Department dep1;
	Department dep2;
	Department dep3;
	private Employee emp1;
	private Employee emp2;
	private Employee emp3;
	private Employee emp4;

	private static final String MODULE_NAME = "embedded";
	private static final String TARGET_DIR = "target/" + MODULE_NAME;
	private static GlassFish glassFish;

	@BeforeClass
	public static void setup() throws Exception {
		GlassFishProperties glassFishProperties = new GlassFishProperties();
		glassFishProperties.setPort("http-listener", 8080);

		glassFish = GlassFishRuntime.bootstrap().newGlassFish(
				glassFishProperties);

		glassFish.start();

		Deployer deployer = glassFish.getDeployer();
		deployer.deploy(prepareModuleDirectory().toURI());
	}

	private static File prepareModuleDirectory() throws IOException {
		File result = new File(TARGET_DIR);
		FileUtils.copyDirectory(new File("target/classes"), result);
		FileUtils.copyFile(new File(
				"target/classes/META-INF/persistence.xml"), new File(
				TARGET_DIR + "/META-INF/persistence.xml"));
		return result;
	}

	private void populateTestData(SimpleCMSService service) {
		dep1 = service.createDeparment("Бизнес", "Зал 1", "Иванов");
		dep2 = service.createDeparment("IT", "Зал 2", "Петров");
		dep3 = service.createDeparment("Кадры", "Зал 3", "Сидоров");

		emp1 = service.createEmployee("Иванов Иван	Иваныч", dep1, "1030",
				100000);
		emp2 = service.createEmployee("Петров Петр	Петрович", dep2, "2030",
				110000);
		emp3 = service.createEmployee("Сидоров Сидор Сидорович", dep3, "3030",
				90000);
		emp4 = service.createEmployee("Мария Семенова", null, "1320", 60000);
	}

	@Test
	public void test() throws Exception {

		// service
		SimpleCMSService service = getFacade();
		populateTestData(service);

		assertEquals(3, service.findAllDepartments().size());
		assertEquals(1, service.findAllEmployeesForDepartment(dep1).size());
	}

	private SimpleCMSService getFacade() throws NamingException {
		InitialContext ctx = new InitialContext();

		SimpleCMSService service = (SimpleCMSService) ctx
				.lookup("java:global/embedded/SimpleCMSService!com.company.boundary.SimpleCMSService");
		return service;
	}

	@AfterClass
	public static void teardown() throws Exception {
		glassFish.stop();
	}
	
	public static void main(String[] args) throws Exception {
		setup();
		SimpleCMSAppTest test = new SimpleCMSAppTest();
		SimpleCMSService service = test.getFacade();
		test.populateTestData(service);
	}
}
