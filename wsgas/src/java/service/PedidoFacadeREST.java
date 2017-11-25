/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
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
import kuhn.daniel.kreturn.ctrl.KReturn;
import kuhn.daniel.ws.DAO.PedidoDAO;
import kuhn.daniel.ws.entity.Endereco;
import kuhn.daniel.ws.entity.Pedido;
import kuhn.daniel.ws.entity.Produto;
import kuhn.daniel.ws.entity.Usuario;

/**
 *
 * @author dani
 */
@Stateless
@Path("kuhn.daniel.ws.entity.pedido")
public class PedidoFacadeREST extends AbstractFacade<Usuario> {

    private static final Logger LOG = Logger.getLogger(PedidoFacadeREST.class.getName());

    
    @PersistenceContext(unitName = "WSC3PU")
    private EntityManager em;

    public PedidoFacadeREST() {
        super(Usuario.class);
    }

    
    @GET
    @Path("/adicionar/{usuario_id}/{produto_id}/{endereco_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String adicionar(@PathParam("usuario_id") Integer usuario_id, @PathParam("produto_id") Integer produto_id, @PathParam("endereco_id") Integer endereco_id) {
        
        if (usuario_id != null 
                && produto_id != null
                    && endereco_id != null) {
            
            Pedido p = new Pedido();
            p.setUsuario(new Usuario(usuario_id));
            p.setProduto(new Produto(produto_id));
            p.setEndereco(new Endereco(endereco_id));
            
            PedidoDAO pdao = new PedidoDAO();
            
            if (pdao.adicionarPedido(p) != null) {
                return KReturn.retorna(true);
            }
            return KReturn.retorna(false);
        }
        return KReturn.retorna(false, "Faltam parâmetros");
    }
    
    
    
    @GET
    @Path("/listar/{usuario_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pedido> listar (@PathParam("usuario_id") Integer id) {
        
        List<Pedido> pedidos = new ArrayList<>();
        
        PedidoDAO edao = new PedidoDAO();
        pedidos = edao.selecionaPedidos(id);

        return pedidos;
    }
    
    
    @GET
    @Path("/excluir/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String excluir(@PathParam("id") Integer id) {
    
        PedidoDAO pdao = new PedidoDAO();
        
        if (pdao.excluirPedido(new Pedido(id))) {
            return KReturn.retorna(true);
        }
        return KReturn.retorna(false);
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
