/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bme673.hw2.prodmaint.controller;

import bme673.hw2.prodmaint.model.ProductBean;
import edu.saintpaul.csci2466.prodmaint.data.ProductCatalog;
import edu.saintpaul.csci2466.prodmaint.model.Product;
import java.io.IOException;
import java.time.LocalDate;
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
        
        String action = request.getParameter("action");
        
        String viewProducts = request.getParameter("viewProductsButton");
        String addProduct = request.getParameter("addProductButton");
        
        // Check if the "View Products" button was pressed on the AddProduct.jsp
        if(viewProducts != null) {
            if(catalog != null) {
                request.setAttribute("products", catalog.findAllProducts());
            }
            request.getRequestDispatcher("/ProductDump.jsp").forward(request, response);
        }
        // Check if "Add Product" button was pressed on AddProduct.jsp
        else if(addProduct != null) {
            String code = request.getParameter("code");
            String description = request.getParameter("description");
            Double price = Double.parseDouble(request.getParameter("price"));
            LocalDate releaseDate = LocalDate.now();
            
            ProductBean product = 
                    new ProductBean(code, description, price, releaseDate);
            
            catalog.insertProduct(product);
            
            request.setAttribute("products", catalog.findAllProducts());
            
            request.getRequestDispatcher("/ProductDump.jsp")
                    .forward(request, response);
        }
        else if(action.equals("viewProducts")) {
            if(catalog != null) {
                // Store attribute for product catalog
                request.setAttribute("products", catalog.findAllProducts());
            }

            // Forward control
            request.getRequestDispatcher("/ProductDump.jsp").forward(request, response);
        }
        else if(action.equals("addProduct")) {
            request.getRequestDispatcher("/AddProduct.jsp").forward(request, response);
        }
    
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
        
        if(catalogPath != null && ProductCatalog.init(catalogPath)){
            catalog = ProductCatalog.getInstance();
            if(catalog != null) {
                CatalogUtility.generateCatalog(catalog, NUM_0F_PRODUCTS);
            }
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
