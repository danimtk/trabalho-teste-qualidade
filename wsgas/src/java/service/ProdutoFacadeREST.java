/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import kuhn.daniel.ws.DAO.ProdutoDAO;
import kuhn.daniel.ws.entity.Produto;
import kuhn.daniel.ws.entity.Usuario;

/**
 *
 * @author dani
 */
@Stateless
@Path("kuhn.daniel.ws.entity.produto")
public class ProdutoFacadeREST extends AbstractFacade<Usuario> {

    private static final Logger LOG = Logger.getLogger(ProdutoFacadeREST.class.getName());
    
    @PersistenceContext(unitName = "WSC3PU")
    private EntityManager em;

    public ProdutoFacadeREST() {
        super(Usuario.class);
    }
    
    @GET
    @Path("/listar/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produto> listar () {
        
        ProdutoDAO pdao = new ProdutoDAO();
        
        List<Produto> produtos = pdao.selecionaProdutos();
        
        return produtos;
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Usuario entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuario find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
