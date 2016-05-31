package test;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import model.User;

public class UserTest {

	@Test
	public void shouldSetCpfWhenValid() {
		String validCpf = "02364001005";
		User testUser = new User();
		
		testUser.setCpf(validCpf);
		Assert.assertEquals(validCpf, testUser.getCpf());
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldReturnExceptionWhenInvalidCPFIsSet() {
		String invalidCpf = "12345678901";
		User testUser = new User();
		
		testUser.setCpf(invalidCpf);
	}

}
