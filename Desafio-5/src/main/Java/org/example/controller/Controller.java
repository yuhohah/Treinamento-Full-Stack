package org.example.controller;

import org.example.Entidades.ControlException;
import org.example.Entidades.Pessoa;
import org.example.infra.repository.pessoa.PessoaRepository;
import org.example.infra.repository.pessoa.PessoaRepositoryImpl;
import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/start")
public class Controller {

    PessoaRepository pessoaRepository = new PessoaRepositoryImpl();

    @GET
    @Path("/pessoa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() {

        JSONArray pessoas = new JSONArray();
        for(Pessoa pessoa: pessoaRepository.getList()){
            pessoas.put(pessoa.toJSON());
        }
        return Response.ok(pessoas.toString()).build();
    }

    @GET
    @Path("/pessoa/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findId(@PathParam("id") Integer id){
        Pessoa pessoa = pessoaRepository.findById(id);
        if(pessoa == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else{
            return Response.ok(pessoa.toJSON().toString()).build();
        }
    }

    @PUT
    @Path("/pessoa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(String data){

        try{
            Pessoa pessoa = new Pessoa(data);
            pessoaRepository.merge(pessoa);
            return Response.ok(pessoa.getId()).build();
        }
        catch(ControlException exception){
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
        }
    }

    @POST
    @Path("/pessoa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(String data){

        try{
            Pessoa pessoa = new Pessoa(data);
            pessoaRepository.insert(pessoa);
            return Response.ok(pessoa.getId()).build();
        }
        catch (ControlException exception){
             return Response.status(Response.Status.NOT_ACCEPTABLE).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
        }
    }

    @DELETE
    @Path("/pessoa/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") Integer id){

        Pessoa pessoa = pessoaRepository.findById(id);
        if(pessoa == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else {
            pessoaRepository.remove(pessoa);
            return Response.status(Response.Status.OK).build();
        }
    }
}