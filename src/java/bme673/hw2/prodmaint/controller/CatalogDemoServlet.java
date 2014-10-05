/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bme673.hw2.prodmaint.controller;

import edu.saintpaul.csci2466.prodmaint.data.ProductCatalog;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Blake Edwards
 */
public class CatalogDemoServlet extends HttpServlet {
    
    private ProductCatalog catalog;
    private final int NUM_0F_PRODUCTS = 10;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        String action = request.getParameter("addProduct");
//        
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet NewServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet NewServlet at " + action + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        
        if(catalog != null) {
            // Store attribute for product catalog
            request.setAttribute("products", catalog.findAllProducts());
        }
        
        // Forward control
        request.getRequestDispatcher("/ProductDump.jsp").forward(request, response);
    
    }

    /**
     * The init method creates a catalog if one doesn't exist and sets the
     * file path for the catalog's location
     * 
     * @throws ServletException 
     */
    @Override
    public void init() throws ServletException {
        super.init(); 
        final ServletContext sc = getServletContext();
        String catalogPath = sc.getRealPath("/WEB-INF/catalog/catalog2.dat");
        
        if(ProductCatalog.init(catalogPath)){
            catalog = ProductCatalog.getInstance();
            CatalogUtility.generateCatalog(catalog, NUM_0F_PRODUCTS);
        }
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
