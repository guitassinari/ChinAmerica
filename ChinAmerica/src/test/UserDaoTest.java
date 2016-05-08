package test;

import static org.junit.Assert.*;

import org.junit.Test;

import database.UserDAO;
import model.User;

public class UserDaoTest {

	@Test
	public void testAddUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserByCpf() {
		User user = new User();
		String cpf = "02364001005";
		String name = "Guilherme Tassinari";
		user.setCpf(cpf);
		user.setName(name);
		
		UserDAO dao = new UserDAO();
		
		dao.addUser(user);
		
		User retrievedUser = dao.getUserByCpf(cpf);
		assertEquals(cpf, retrievedUser.getCpf());
		assertEquals(name, retrievedUser.getName());
	}

}
