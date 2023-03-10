package edu.uniandes.miso.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.uniandes.miso.dto.InputServiceDto;
import edu.uniandes.miso.entity.Service;
import edu.uniandes.miso.repository.ServiceRepository;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
@QuarkusTestResource(value = H2DatabaseTestResource.class)
@QuarkusTest
class UserServiceTest {

	@InjectMock
	ServiceRepository repository;

	@Inject
	UserService userService;

	Service service;

	@BeforeEach
	void setUp() {
		service = new Service();
		service.setId(1L);
		service.setName("Example Name");
		service.setDescription("Example Category");
	}

	@Test
	void createOk() {
		Mockito.when(repository.save(service)).thenReturn(service);
		InputServiceDto inputServiceDto = new InputServiceDto();
		inputServiceDto.setName("name");
		inputServiceDto.setDescription("category");
		inputServiceDto.setIdUserCreator(1L);
		Response response = userService.create(inputServiceDto);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void createFail() {
		Mockito.when(repository.save(service)).thenReturn(service);
		InputServiceDto inputServiceDto = new InputServiceDto();
		inputServiceDto.setDescription("category");
		inputServiceDto.setIdUserCreator(1L);
		Response response = userService.create(inputServiceDto);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	void getOk() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(service));
		Response response = userService.get(1L);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
	@Test
	void getFail() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(service));
		Response response = userService.get(5L);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	void deleteOk() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(service));
		Response response = userService.delete(1L);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
	@Test
	void deleteFail() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(service));
		Response response = userService.delete(5L);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	void getAll() {
		List<Service> services = new ArrayList<>();
		Mockito.when(repository.findAll()).thenReturn(services);
		Response response = userService.getAll();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void putOk() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(service));
		Mockito.when(repository.save(service)).thenReturn(service);
		InputServiceDto inputServiceDto = new InputServiceDto();
		inputServiceDto.setName("name");
		inputServiceDto.setDescription("category");
		inputServiceDto.setIdUserCreator(1L);
		Response response = userService.put(1L,inputServiceDto);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
	@Test
	void putOkEmpty() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(service));
		Mockito.when(repository.save(service)).thenReturn(service);
		InputServiceDto inputServiceDto = new InputServiceDto();
		inputServiceDto.setName("name");
		inputServiceDto.setDescription("category");
		inputServiceDto.setIdUserCreator(1L);
		Response response = userService.put(1L,inputServiceDto);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void putFail() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(service));
		Mockito.when(repository.save(service)).thenReturn(service);
		InputServiceDto inputServiceDto = new InputServiceDto();
		inputServiceDto.setName("");
		inputServiceDto.setDescription("category");
		inputServiceDto.setIdUserCreator(1L);
		Response response = userService.put(5L,inputServiceDto);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}
}