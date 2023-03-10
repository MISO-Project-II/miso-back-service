package edu.uniandes.miso.service;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import edu.uniandes.miso.dto.InputServiceDto;
import edu.uniandes.miso.dto.Reply;
import edu.uniandes.miso.entity.Service;
import edu.uniandes.miso.repository.ServiceRepository;

@Path("service")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    @Inject
    Logger log;

    @Inject
    ServiceRepository repository;

    @POST
    public Response create(InputServiceDto input) {
        if (StringUtils.isNotEmpty(input.getName())) {
            Service service = new Service();
            service.setName(input.getName());
			service.setDescription(input.getDescription());
			service.setIdUserCreator(input.getIdUserCreator());
			service.setIdSport(input.getIdSport());
			service.setPrice(input.getPrice());
			service.setContractType(input.getContractType());
			service.setEventType(input.getEventType());
			Service getService = repository.save(service);
			return Reply.ok(getService);
        }
		return Reply.notFound(null);
    }
    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long idService) {
        Optional<Service> findService = repository.findById(idService);
        InputServiceDto inputServiceDto = new InputServiceDto();
        if(findService.isPresent()) {
            Service service = findService.get();
            inputServiceDto.setName(service.getName());
			inputServiceDto.setDescription(service.getDescription());
			inputServiceDto.setIdUserCreator(service.getIdUserCreator());
			inputServiceDto.setIdSport(service.getIdSport());
			inputServiceDto.setPrice(service.getPrice());
			inputServiceDto.setContractType(service.getContractType());
			inputServiceDto.setEventType(service.getEventType());
			return Reply.ok(inputServiceDto);
        }
		return Reply.notFound(null);
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") Long idService, InputServiceDto input) {
        Optional<Service> findService = repository.findById(idService);
        if(findService.isPresent() && StringUtils.isNotEmpty(input.getName())) {
            Service service = findService.get();
            service.setName(input.getName());
			service.setDescription(input.getDescription());
			service.setIdUserCreator(input.getIdUserCreator());
			Service getService = repository.save(service);
			return Reply.ok(getService);
        }
		return Reply.notFound(null);
    }
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long idService) {
        Optional<Service> findService = repository.findById(idService);
        if(findService.isPresent()) {
            repository.deleteById(findService.get().getId());
			return Reply.ok(null);
        }
		return Reply.notFound(null);
    }

    @GET
    public Response getAll(){
		return Reply.ok(repository.findAll());
    }
}
