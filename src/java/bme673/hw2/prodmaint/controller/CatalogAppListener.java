/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bme673.hw2.prodmaint.controller;

import edu.saintpaul.csci2466.prodmaint.data.ProductCatalog;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Blake
 */
public class CatalogAppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final ServletContext sc = sce.getServletContext();
        String catalogPath = sc.getInitParameter("catalogPath");
        
        catalogPath = sc.getRealPath(catalogPath);
        
        if(catalogPath != null && ProductCatalog.init(catalogPath)){
            
            sc.log("catalog initialized");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("context destroyed");
    }
}
