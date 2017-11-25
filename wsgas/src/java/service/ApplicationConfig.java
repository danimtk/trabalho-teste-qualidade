/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Daniel M. Kuhn <www.cybercidades.com.br>
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        resources.add(kuhn.daniel.ws.cors.ResponseCorsFilter.class);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.EnderecoFacadeREST.class);
        resources.add(service.GenericResource.class);
        resources.add(service.PedidoFacadeREST.class);
        resources.add(service.ProdutoFacadeREST.class);
        resources.add(service.UsuarioFacadeREST.class);
    }
    
}
