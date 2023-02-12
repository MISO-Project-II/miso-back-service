package edu.uniandes.miso.service;

import edu.uniandes.miso.repository.ServiceRepository;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("backend")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BackendService {

	@Inject
	Logger log;

	@Inject
	ServiceRepository repository;

	@GET
	@Path(("list"))
	public Response getAllResponse() {
		return Response.status(Response.Status.OK).entity(repository.findAll()).build();
	}

	@POST
	@Path(("listById"))
	public Response getListById(List<Long> list) {
		return Response.ok(repository.findAllById(list)).build();
	}

	@GET
	@Path("{id}")
	public Response getListByUserId(@PathParam("id") Long idUser) {
		log.error("consumed!" + idUser);
		log.info(repository.findByIdUserCreator(idUser));
		return Response.ok(repository.findByIdUserCreator(idUser)).build();
	}
}
