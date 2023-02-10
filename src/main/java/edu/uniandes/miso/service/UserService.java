package edu.uniandes.miso.service;

import edu.uniandes.miso.dto.InputServiceDto;
import edu.uniandes.miso.dto.ResponseService;
import edu.uniandes.miso.entity.Service;
import edu.uniandes.miso.repository.ServiceRepository;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

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
import java.util.Optional;

@Path("service")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    @Inject
    Logger log;

    @Inject
    ServiceRepository repository;
    ResponseService responseService= new ResponseService();
    @POST
    public Response create(InputServiceDto input) {
        if (StringUtils.isNotEmpty(input.getName())) {
            Service service = new Service();
            service.setName(input.getName());
            service.setCategory(input.getCategory());
            repository.save(service);
            responseService.setSuccess(true);
            responseService.setMessage("Created");
            responseService.setResult(input);
            return Response.status(Response.Status.OK).entity(responseService).build();
        }
        responseService.setSuccess(false);
        responseService.setMessage("Fail to created");
        return Response.status(Response.Status.BAD_REQUEST).entity(responseService).build();
    }
    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long idService) {
        Optional<Service> findService = repository.findById(idService);
        InputServiceDto inputServiceDto = new InputServiceDto();
        if(findService.isPresent()) {
            Service service = findService.get();
            inputServiceDto.setName(service.getName());
            inputServiceDto.setCategory(service.getCategory());
            responseService.setSuccess(true);
            responseService.setMessage("Created");
            responseService.setResult(inputServiceDto);
            return Response.status(Response.Status.OK).entity(responseService).build();
        }
        responseService.setSuccess(true);
        responseService.setMessage("Not found");
        return Response.status(Response.Status.NO_CONTENT).entity(responseService).build();
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") Long idService, InputServiceDto input) {
        Optional<Service> findService = repository.findById(idService);
        if(findService.isPresent() && StringUtils.isNotEmpty(input.getName())) {
            Service service = findService.get();
            service.setName(input.getName());
            service.setCategory(input.getCategory());
            repository.save(service);
            responseService.setSuccess(true);
            responseService.setMessage("updated");
            responseService.setResult(input);
            return Response.status(Response.Status.OK).entity(responseService).build();
        }
        responseService.setSuccess(false);
        responseService.setMessage("Fail to update");
        return Response.status(Response.Status.BAD_REQUEST).entity(responseService).build();
    }
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long idService) {
        Optional<Service> findService = repository.findById(idService);
        if(findService.isPresent()) {
            repository.deleteById(findService.get().getId());
            responseService.setSuccess(true);
            responseService.setMessage("updated");
            return Response.status(Response.Status.NO_CONTENT).entity(responseService).build();
        }
        responseService.setSuccess(false);
        responseService.setMessage("Fail to update");
        return Response.status(Response.Status.BAD_REQUEST).entity(responseService).build();
    }

    @GET
    public Response getAll(){
        responseService.setSuccess(true);
        responseService.setMessage("success");
        responseService.setResult(repository.findAll());
        return Response.status(Response.Status.OK).entity(responseService).build();
    }
}
