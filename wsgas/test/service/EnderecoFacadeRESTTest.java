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
import kuhn.daniel.ws.entity.Endereco;
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
public class EnderecoFacadeRESTTest {
    
    public EnderecoFacadeRESTTest() {
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
     * Test of adicionar method, of class EnderecoFacadeREST.
     * @throws java.lang.Exception
     */
    @Test
    public void test12() throws Exception {
        
        ResetBase rb = new ResetBase();
        rb.resetBase();
        rb.resetSequence();
        
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
     * Test of excluir method, of class EnderecoFacadeREST.
     */
    @Test
    public void test16() throws Exception {
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
    public void test17() throws Exception {
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
    public void test18() throws Exception {
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
    
}
