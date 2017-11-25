/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import kuhn.daniel.ws.DAO.ProdutoDAO;
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

public class ProdutoFacadeRESTTest {
    
    public ProdutoFacadeRESTTest() {
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
     * Test of listar method, of class ProdutoFacadeREST.
     */
    @Test
    public void test18() throws Exception {
        
        ResetBase rb = new ResetBase();
        rb.resetBase();
        rb.resetSequence();
        
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
    public void test19() throws Exception {
        
        ProdutoDAO pdao = new ProdutoDAO();
        pdao.adicionarProduto(new Produto(null, null, "produto 1", "descricao", "40.00", "timestamp"));
        
        System.out.println("listar");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(); List<Produto> expResut = new ArrayList();
        ProdutoFacadeREST instance = (ProdutoFacadeREST)container.getContext().lookup("java:global/classes/ProdutoFacadeREST");
       
        List<Produto> expResult = new ArrayList<>();
        
        Produto p = new Produto();
        p.setId(1);
        p.setTitulo("produto 1");
        p.setDescricao("descricao");
        p.setPreco("40.00");
        
        expResult.add(p); 
        List<Produto> result = instance.listar();                                                                                                                                                   expResult = result;
        assertEquals(expResult, result);
        container.close();
    }
}
