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
import kuhn.daniel.ws.DAO.EnderecoDAO;
import kuhn.daniel.ws.entity.Endereco;
import kuhn.daniel.ws.entity.Usuario;

/**
 *
 * @author dani
 */
@Stateless
@Path("kuhn.daniel.ws.entity.endereco")
public class EnderecoFacadeREST extends AbstractFacade<Endereco> {

    private static final Logger LOG = Logger.getLogger(EnderecoFacadeREST.class.getName());
    
    @PersistenceContext(unitName = "WSC3PU")
    private EntityManager em;

    public EnderecoFacadeREST() {
        super(Endereco.class);
    }

    
    @GET
    @Path("/adicionar/{usuario_id}/{cep}/{cidade}/{rua}/{numero}/{bairro}/{complemento}")
    @Produces(MediaType.APPLICATION_JSON)
    public String adicionar(
            @PathParam("usuario_id") Integer usuario_id,
            @PathParam("cep") Integer cep,
            @PathParam("cidade") String cidade,
            @PathParam("rua") String rua,
            @PathParam("numero") String numero,
            @PathParam("bairro") String bairro,
            @PathParam("complemento") String complemento) {
        
        if (usuario_id != null 
                    && cep != null
                        && rua != null
                            && cidade != null
                                && numero != null
                                    && bairro != null) 
        {
            Endereco e = new Endereco();
            e.setUsuario(new Usuario(usuario_id));
            e.setCep(cep);
            e.setRua(rua);
            e.setCidade(cidade);
            e.setNumero(numero);
            e.setBairro(bairro);
            
            e.setComplemento(complemento);
            
            EnderecoDAO edao = new EnderecoDAO();
            
            if (edao.adicionarEndereco(e) != null) {
                return KReturn.retorna(true);
            } else {
                return KReturn.retorna(false);
            }
        }else {
            return KReturn.retorna(false, "Faltam informações");
        }
    }
    
    @GET
    @Path("/listar/{usuario_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Endereco> listar (@PathParam("usuario_id") Integer id) {
        
        List<Endereco> enderecos = new ArrayList<>();
        
        EnderecoDAO edao = new EnderecoDAO();
        enderecos = edao.selecionaEnderecosUsuario(id);

        return enderecos;
    }
    
    
    @GET
    @Path("/excluir/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String excluir(@PathParam("id") Integer id) {
    
        EnderecoDAO edao = new EnderecoDAO();
        
        if (edao.excluirEndereco(new Endereco(id))) {
            return KReturn.retorna(true);
        }
        return KReturn.retorna(false);
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Endereco entity) {
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
    public Endereco find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Endereco> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Endereco> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
