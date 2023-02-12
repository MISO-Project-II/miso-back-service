package edu.uniandes.miso.service;

import edu.uniandes.miso.entity.Service;
import edu.uniandes.miso.repository.ServiceRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class BackendServiceTest {
	@InjectMock
	ServiceRepository repository;

	@Inject
	BackendService backendService;
	Service service;

	@BeforeEach
	void setUp() {
		service = new Service();
		service.setId(1L);
		service.setName("Example Name");
		service.setDescription("Example Category");
	}

	@Test
	void getAllResponse() {
		List<Service> list = new ArrayList<>();
		list.add(service);
		Mockito.when(repository.findAll()).thenReturn(list);
		Response response = backendService.getAllResponse();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void getListById() {
		List<Service> list = new ArrayList<>();
		list.add(service);
		List<Long> ids = new ArrayList<>();
		ids.add(1L);
		Mockito.when(repository.findAllById(ids)).thenReturn(list);
		Response response = backendService.getListById(new ArrayList<>());
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void getListByUserId() {
		List<Service> list = new ArrayList<>();
		list.add(service);
		Mockito.when(repository.findByIdUserCreator(1L)).thenReturn(list);
		Response response = backendService.getListByUserId(1L);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
}