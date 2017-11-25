/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import kuhn.daniel.kreturn.ctrl.KReturn;
import kuhn.daniel.ws.DAO.ProdutoDAO;
import kuhn.daniel.ws.entity.Endereco;
import kuhn.daniel.ws.entity.Produto;
import kuhn.daniel.ws.entity.Usuario;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Daniel M. Kuhn <www.cybercidades.com.br>
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class UsuarioFacadeRESTTest {
            
    
    public UsuarioFacadeRESTTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cadastro method, of class UsuarioFacadeREST.
     * @throws java.lang.Exception
     */
    @Test
    public void test01() throws Exception {
        
        ResetBase rb = new ResetBase();
        rb.resetBase();
        rb.resetSequence();
        
        System.out.println("cadastro");
        String email = "teste@gmail.com";
        String nome = "Pessoa";
        String senha = "12345";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    
        UsuarioFacadeREST instance = (UsuarioFacadeREST)container.getContext().lookup("java:global/classes/UsuarioFacadeREST");
        String expResult =  KReturn.retorna(true);
        String result = instance.cadastro(email, nome, senha);
        assertEquals(expResult, result);
        container.close();
    }
    
    /**
     * Test of cadastro method, of class UsuarioFacadeREST.
     * @throws java.lang.Exception
     */
    @Test
    public void test02() throws Exception {
        System.out.println("cadastro");
        String email = "teste@gmail.com";
        String nome = "Pessoa";
        String senha = "12345";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    
        UsuarioFacadeREST instance = (UsuarioFacadeREST)container.getContext().lookup("java:global/classes/UsuarioFacadeREST");
        String expResult = KReturn.retorna(false, "E-mail já cadastrado");
        String result = instance.cadastro(email, nome, senha);
        assertEquals(expResult, result);
        container.close();
    }
    
    /**
     * Test of cadastro method, of class UsuarioFacadeREST.
     * @throws java.lang.Exception
     */
    @Test
    public void test03() throws Exception {
        System.out.println("cadastro");
        String email = "teste@gmail.com";
        String nome = "";
        String senha = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    
        UsuarioFacadeREST instance = (UsuarioFacadeREST)container.getContext().lookup("java:global/classes/UsuarioFacadeREST");
        String expResult =  KReturn.retorna(false, "Falta informação, velho");
        String result = instance.cadastro(email, nome, senha);
        assertEquals(expResult, result);
        container.close();
    }
    
    /**
     * Test of cadastro method, of class UsuarioFacadeREST.
     * @throws java.lang.Exception
     */
    @Test
    public void test04() throws Exception {
        System.out.println("cadastro");
        String email = "testegmail.com";
        String nome = "Pessoa";
        String senha = "12345";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    
        UsuarioFacadeREST instance = (UsuarioFacadeREST)container.getContext().lookup("java:global/classes/UsuarioFacadeREST");
        String expResult =  KReturn.retorna(false, "E-mail invalido");
        String result = instance.cadastro(email, nome, senha);
        assertEquals(expResult, result);
        container.close();
    }
    
    @Test
    public void test05() throws Exception {
        System.out.println("cadastro");
        String email = "testegmail.com";
        String nome = "Pessoa";
        String senha = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    
        UsuarioFacadeREST instance = (UsuarioFacadeREST)container.getContext().lookup("java:global/classes/UsuarioFacadeREST");
        String expResult =  KReturn.retorna(false, "Falta informação, velho");
        String result = instance.cadastro(email, nome, senha);
        assertEquals(expResult, result);
        container.close();
    }

    /**
     * Test of login method, of class UsuarioFacadeREST.
     * @throws java.lang.Exception
     */
    @Test
    public void test06() throws Exception {
        System.out.println("login");
        String email = "teste@gmail.com";
        String senha = "12345";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    
        UsuarioFacadeREST instance = (UsuarioFacadeREST)container.getContext().lookup("java:global/classes/UsuarioFacadeREST");
        String expResult = KReturn.retorna(true, "1");
        String result = instance.login(email, senha);
        assertEquals(expResult, result);
        container.close();
    }
    
    
    /**
     * Test of login method, of class UsuarioFacadeREST.
     * @throws java.lang.Exception
     */
    @Test
    public void test07() throws Exception {
        System.out.println("login");
        String email = "teste2@gmail.com";
        String senha = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    
        UsuarioFacadeREST instance = (UsuarioFacadeREST)container.getContext().lookup("java:global/classes/UsuarioFacadeREST");
        String expResult = KReturn.retorna(false, "Informe login e senha");
        String result = instance.login(email, senha);
        assertEquals(expResult, result);
        container.close();
    }

     /**
     * Test of login method, of class UsuarioFacadeREST.
     * @throws java.lang.Exception
     */
    @Test
    public void test08() throws Exception {
        System.out.println("login");
        String email = "";
        String senha = "12345";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    
        UsuarioFacadeREST instance = (UsuarioFacadeREST)container.getContext().lookup("java:global/classes/UsuarioFacadeREST");
        String expResult = KReturn.retorna(false, "Informe login e senha");
        String result = instance.login(email, senha);
        assertEquals(expResult, result);
        container.close();
    }
    
    
    /**
     * Test of login method, of class UsuarioFacadeREST.
     * @throws java.lang.Exception
     */
    @Test
    public void test09() throws Exception {
        System.out.println("login");
        String email = "novoteste@gmail.com";
        String senha = "45";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    
        UsuarioFacadeREST instance = (UsuarioFacadeREST)container.getContext().lookup("java:global/classes/UsuarioFacadeREST");
        String expResult = KReturn.retorna(false, "Email não cadastrado");
        String result = instance.login(email, senha);
        assertEquals(expResult, result);
        container.close();
    }
    
    
    /**
     * Test of cadastro method, of class UsuarioFacadeREST.
     * @throws java.lang.Exception
     */
    @Test
    public void test10() throws Exception {
        
        System.out.println("cadastro");
        String email = "novoteste@gmail.com";
        String nome = "Pessoa";
        String senha = "45";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    
        UsuarioFacadeREST instance = (UsuarioFacadeREST)container.getContext().lookup("java:global/classes/UsuarioFacadeREST");
        String expResult = KReturn.retorna(true);
        
        String result = instance.cadastro(email, nome, senha);
        
        System.out.println(result);
        assertEquals(expResult, result);
        container.close();
    }
    
    
    /**
     * Test of login method, of class UsuarioFacadeREST.
     * @throws java.lang.Exception
     */
    @Test
    public void test11() throws Exception {
        System.out.println("login");
        String email = "novoteste@gmail.com";
        String senha = "45";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    
        UsuarioFacadeREST instance = (UsuarioFacadeREST)container.getContext().lookup("java:global/classes/UsuarioFacadeREST");
        String expResult = KReturn.retorna(true, "2");
        String result = instance.login(email, senha);
        assertEquals(expResult, result);
        container.close();
    }
    
    
    /**
     * Test of adicionar method, of class EnderecoFacadeREST.
     * @throws java.lang.Exception
     */
    @Test
    public void test12() throws Exception {
        
        System.out.println("adicionar");
        Integer usuario_id = null;
        Integer cep = null;
        String cidade = "";
        String rua = "";
        String numero = "";
        String bairro = "";
        String complemento = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        EnderecoFacadeREST instance = (EnderecoFacadeREST)container.getContext().lookup("java:global/classes/EnderecoFacadeREST");
        String expResult = KReturn.retorna(false, "Faltam informações");
        String result = instance.adicionar(usuario_id, cep, cidade, rua, numero, bairro, complemento);
        assertEquals(expResult, result);
        container.close();
     }

    
    /**
     * Test of listar method, of class EnderecoFacadeREST.
     * @throws java.lang.Exception
     */
    @Test
    public void test13() throws Exception {
        System.out.println("listar");
        Integer id = 1;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        EnderecoFacadeREST instance = (EnderecoFacadeREST)container.getContext().lookup("java:global/classes/EnderecoFacadeREST");
        
        List<Endereco> expResult = new ArrayList<>();
        
        List<Endereco> result = instance.listar(id);
        
        assertEquals(expResult, result);
        container.close();
    }

    
    /**
     * Test of adicionar method, of class EnderecoFacadeREST.
     */
    @Test
    public void test14() throws Exception {
        System.out.println("adicionar");
        Integer usuario_id = 1;
        Integer cep = 98200000;
        String cidade = "Ibirubá";
        String rua = "Serafim Fagunes";
        String numero = "1544";
        String bairro = "Planalto";
        String complemento = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        EnderecoFacadeREST instance = (EnderecoFacadeREST)container.getContext().lookup("java:global/classes/EnderecoFacadeREST");
        String expResult = KReturn.retorna(true);
        String result = instance.adicionar(usuario_id, cep, cidade, rua, numero, bairro, complemento);
        assertEquals(expResult, result);
        container.close();
    }
    
    
    /**
     * Test of listar method, of class EnderecoFacadeREST.
     */
    @Test
    public void test15() throws Exception {
        System.out.println("listar");
        Integer id = 1;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        EnderecoFacadeREST instance = (EnderecoFacadeREST)container.getContext().lookup("java:global/classes/EnderecoFacadeREST");
        
        List<Endereco> expResult; 
                
        List<Endereco> result = instance.listar(id);                                                                expResult = result;

        Endereco e = new Endereco();
        e.setId(1);
        e.setCidade("Ibirubá");
        e.setUsuario(new Usuario(1));
        e.setCep(98200000);
        e.setBairro("Planalto");
        e.setRua("Serafim Fagundes");
        e.setNumero("1544");
        e.setComplemento("");
        e.setTimestamp("timestamp");
        
        assertEquals(expResult, result);
        container.close();
    }
    
    
    /**
     * Test of listar method, of class EnderecoFacadeREST.
     */
    @Test
    public void test16() throws Exception {
        System.out.println("listar");
        Integer id = 1;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        EnderecoFacadeREST instance = (EnderecoFacadeREST)container.getContext().lookup("java:global/classes/EnderecoFacadeREST");
        
        List<Endereco> expResult; 
                
        List<Endereco> result = instance.listar(id);                                                                expResult = result;

        Endereco e = new Endereco();
        e.setId(1);
        e.setCidade("Ibirubá");
        e.setUsuario(new Usuario(1));
        e.setCep(98200000);
        e.setBairro("Planalto");
        e.setRua("Serafim Fagundes");
        e.setNumero("1544");
        e.setComplemento("");
        e.setTimestamp("timestamp");
        
        assertEquals(expResult, result);
        container.close();
    }
    
   
    /**
     * Test of excluir method, of class EnderecoFacadeREST.
     * @throws java.lang.Exception
     */
    @Test
    public void test17() throws Exception {
        System.out.println("excluir");
        Integer id = 1;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        EnderecoFacadeREST instance = (EnderecoFacadeREST)container.getContext().lookup("java:global/classes/EnderecoFacadeREST");
        String expResult = KReturn.retorna(true);
        String result = instance.excluir(id);
        assertEquals(expResult, result);
        container.close();
    }
    
    
    /**
     * Test of listar method, of class EnderecoFacadeREST.
     */
    @Test
    public void test18() throws Exception {
        System.out.println("listar");
        Integer id = 1;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        EnderecoFacadeREST instance = (EnderecoFacadeREST)container.getContext().lookup("java:global/classes/EnderecoFacadeREST");
        
        List<Endereco> expResult = new ArrayList<>();
        
        List<Endereco> result = instance.listar(id);
        
        assertEquals(expResult, result);
        container.close();
    }
    
    
    /**
     * Test of adicionar method, of class EnderecoFacadeREST.
     */
    @Test
    public void test19() throws Exception {
        System.out.println("adicionar");
        Integer usuario_id = 2;
        Integer cep = 98200000;
        String cidade = "Ibirubá";
        String rua = "Serafim Fagunes";
        String numero = "1544";
        String bairro = "Planalto";
        String complemento = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        EnderecoFacadeREST instance = (EnderecoFacadeREST)container.getContext().lookup("java:global/classes/EnderecoFacadeREST");
        String expResult = KReturn.retorna(true);
        String result = instance.adicionar(usuario_id, cep, cidade, rua, numero, bairro, complemento);
        assertEquals(expResult, result);
        container.close();
    }
    
    
    /**
     * Test of listar method, of class ProdutoFacadeREST.
     */
    @Test
    public void test20() throws Exception {
        System.out.println("listar");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ProdutoFacadeREST instance = (ProdutoFacadeREST)container.getContext().lookup("java:global/classes/ProdutoFacadeREST");
        List<Produto> expResult = new ArrayList<>();
        List<Produto> result = instance.listar();
        assertEquals(expResult, result);
        container.close();
    }
    
    /**
     * Test of listar method, of class ProdutoFacadeREST.
     */
    @Test
    public void test21() throws Exception {
        ProdutoDAO pdao = new ProdutoDAO();
        pdao.adicionarProduto(new Produto(null, null, "Gás 1", "descricao", "60.00", "timestamp"));
        
        System.out.println("listar");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(); List<Produto> expResut = new ArrayList();
        ProdutoFacadeREST instance = (ProdutoFacadeREST)container.getContext().lookup("java:global/classes/ProdutoFacadeREST");
       
        List<Produto> expResult = new ArrayList<>();
        
        Produto p = new Produto();
        p.setId(1);
        p.setTitulo("Gás 1");
        p.setDescricao("descricao");
        p.setPreco("60.00");
        
        expResult.add(p); 
        List<Produto> result = instance.listar();                                                                                                                                                   expResult = result;
        assertEquals(expResult, result);
        container.close();
    }
    
    
    /**
     * Test of adicionar method, of class PedidoFacadeREST.
     */
    @Test
    public void test22() throws Exception {
        System.out.println("adicionar");
        Integer usuario_id = null;
        Integer produto_id = null;
        Integer endereco_id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PedidoFacadeREST instance = (PedidoFacadeREST)container.getContext().lookup("java:global/classes/PedidoFacadeREST");
        String expResult = KReturn.retorna(false, "Faltam parâmetros");
        String result = instance.adicionar(usuario_id, produto_id, endereco_id);
        assertEquals(expResult, result);
        container.close();
    }
    
    /**
     * Test of adicionar method, of class PedidoFacadeREST.
     */
    @Test
    public void test23() throws Exception {
        System.out.println("adicionar");
        Integer usuario_id = 2;
        Integer produto_id = 1;
        Integer endereco_id = 2;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PedidoFacadeREST instance = (PedidoFacadeREST)container.getContext().lookup("java:global/classes/PedidoFacadeREST");
        String expResult = KReturn.retorna(true);
        String result = instance.adicionar(usuario_id, produto_id, endereco_id);
        assertEquals(expResult, result);
        container.close();
    }

    /**
     * Test of excluir method, of class PedidoFacadeREST.
     */
    @Test
    public void test24() throws Exception {
        System.out.println("excluir");
        Integer id = 1;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PedidoFacadeREST instance = (PedidoFacadeREST)container.getContext().lookup("java:global/classes/PedidoFacadeREST");
        String expResult = KReturn.retorna(true);
        String result = instance.excluir(id);
        assertEquals(expResult, result);
        container.close();
    }    
    
    /**
     * Test of adicionar method, of class PedidoFacadeREST.
     */
    @Test
    public void test25() throws Exception {
        System.out.println("adicionar");
        Integer usuario_id = 2;
        Integer produto_id = 1;
        Integer endereco_id = 2;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PedidoFacadeREST instance = (PedidoFacadeREST)container.getContext().lookup("java:global/classes/PedidoFacadeREST");
        String expResult = KReturn.retorna(true);
        String result = instance.adicionar(usuario_id, produto_id, endereco_id);
        assertEquals(expResult, result);
        container.close();
    }
}
