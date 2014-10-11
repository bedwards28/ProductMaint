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
            
            ProductBean product = null;
            String errmsg;
            String url;
            Double priceDouble = 0.00;
            
            // Get user input
            String code = request.getParameter("code");
            String description = request.getParameter("description");
            String price = request.getParameter("price");
            LocalDate releaseDate = LocalDate.now();
            
            try {
                priceDouble = Double.parseDouble(price);
                
//                product = new ProductBean(code, description, 
//                    priceDouble, releaseDate);
            }
            catch (NumberFormatException nfe) {
                errmsg = "Please enter a valid number for the price.";
                url = "/AddProduct.jsp";
                
                request.setAttribute("errmsg", errmsg);
                getServletContext()
                        .getRequestDispatcher(url)
                        .forward(request, response);
            }
            
            product = new ProductBean(code, description, 
                    priceDouble, releaseDate);
            
            // Validate the user entered a value in each field
            if(code == null || description == null || price == null ||
                    code.isEmpty() || description.isEmpty() || price.isEmpty()) {
                errmsg = "Please fill out all of the fields.";
                request.setAttribute("errmsg", errmsg);
                url = "/AddProduct.jsp";
            } 
            else {
                errmsg = "";
                url = "/ProductDump.jsp";
                catalog.insertProduct(product);
//                product = new ProductBean(code, description,
//                    Double.parseDouble(price), releaseDate);
            }
            
//            catalog.insertProduct(product);
            
            request.setAttribute("products", catalog.findAllProducts());
            
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);
            
//            request.getRequestDispatcher("/ProductDump.jsp")
//                    .forward(request, response);
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
            
            // Comment out as catalog already exists
//            if(catalog != null) {
//                CatalogUtility.generateCatalog(catalog, NUM_0F_PRODUCTS);
//            }
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
