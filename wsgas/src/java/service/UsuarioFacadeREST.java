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
import kuhn.daniel.kreturn.ctrl.KReturn;
import kuhn.daniel.ws.DAO.UsuarioDAO;
import kuhn.daniel.ws.entity.Usuario;

/**
 *
 * @author dani
 */
@Stateless
@Path("kuhn.daniel.ws.entity.usuario")
public class UsuarioFacadeREST extends AbstractFacade<Usuario> {

    private static final Logger LOG = Logger.getLogger(UsuarioFacadeREST.class.getName());

    @PersistenceContext(unitName = "WSC3PU")
    private EntityManager em;

    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    @GET
    @Path("/cadastro/{email}/{nome}/{senha}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String cadastro(@PathParam("email") String email, @PathParam("nome") String nome, @PathParam("senha") String senha) {
        
        if (email != null && !email.isEmpty() && nome != null && !nome.isEmpty() && senha != null && !senha.isEmpty()) 
        {   
            if (email.contains("@")) 
            {
                Usuario u = new Usuario();
                u.setEmail(email);
                u.setNome(nome);
                u.setSenha(senha);

                UsuarioDAO ud = new UsuarioDAO();

                if (ud.emailDisponivel(email)) {
                    if (ud.adicionarUsuario(u) != null) {
                        return KReturn.retorna(true);
                    }
                    return KReturn.retorna(false, "Algo deu errado");
                } else {
                    return KReturn.retorna(false, "E-mail já cadastrado");
                }
            } else {
                return KReturn.retorna(false, "E-mail invalido");
            }
        } else {
            return KReturn.retorna(false, "Falta informação, velho");
        }
    }
    
    
    @GET
    @Path("/login/{email}/{senha}")
    @Produces(MediaType.APPLICATION_JSON)
    public String login (@PathParam("email") String email, @PathParam("senha") String senha) {
         
        try {
            
            if (!email.isEmpty() && !senha.isEmpty()) {
                UsuarioDAO ud = new UsuarioDAO();

                // E-mail não disponível == E-mail cadastrado
                if (!ud.emailDisponivel(email)) 
                {
                    Usuario u = ud.logar(email, senha);

                    if (u != null) {
                        return KReturn.retorna(true, u.getId().toString());
                    }
                    return KReturn.retorna(false, "Ops");
                }
                return KReturn.retorna(false, "Email não cadastrado");
            } else {
                return KReturn.retorna(false, "Informe login e senha");
            }
        } catch (Exception e) {
            System.out.println(LOG+" "+e.getMessage());
            return KReturn.retorna(false, "Ops");
        }
    }
    
//    @POST
//    @Path("/atualiza-cadastro")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String atualiza_senha(RequestBodyUsJuridico requestbodyusjuridico) {
//        
//        String nome         = requestbodyusjuridico.nome;
//        String razao_social = requestbodyusjuridico.razaoSocial;
//        String bairro = requestbodyusjuridico.bairro;
//        String numero = requestbodyusjuridico.numero;
//        String rua    = requestbodyusjuridico.rua;
//        
//        UsJuridico uj = new UsJuridico();
//        uj.setNome(nome);
//        uj.setRazaoSocial(razao_social);
//        uj.setBairro(bairro);
//        uj.setNumero(numero);
//        uj.setRua(rua);
//        
//        UsuarioController uc = new UsuarioController();
//        uc.atualizarDadosGerais(uj);
//        
//        return "";
//    }
//       
    
//    @POST
//    @Path("/altera-senha")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String altera_senha (RequestBodyUsuario requestbodyusuario) {
//        
//        String email = requestbodyusuario.email;
//        String senha = requestbodyusuario.password;
//        String senha_antiga = requestbodyusuario.oldpassword;
//        
//        UsuarioController uc = new UsuarioController();
//                
//        Integer id_user = uc.validaSenha(email, senha_antiga);
//        
//        if(id_user != null) 
//        {
//            if (uc.alterarSenha(email, senha_antiga, senha)) {
//                return KReturn.retorna(true);
//            }
//        }else {
//            return KReturn.retorna(false, "Senha atual não confere");
//        }
//        
//        return KReturn.retorna(false, "Ops, algo deu errado");
//    }
    
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
