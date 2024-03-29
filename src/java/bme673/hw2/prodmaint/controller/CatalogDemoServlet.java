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
import java.time.format.DateTimeParseException;
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
        
        log("CatalogDemoServlet");
        
        String action = request.getParameter("action");
        log("action = " + action);
        
        String viewProducts = request.getParameter("viewProductsButton");
        String addProduct = request.getParameter("addProductButton");
        String editProduct = request.getParameter("editProductButton");
        String deleteProduct = request.getParameter("deleteProductButton");
        log("viewProducts = " + viewProducts);
        log("addProduct = " + addProduct);
        log("editProduct = " + editProduct);
        log("deleteProduct = " + deleteProduct);
        
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String releaseDate2 = request.getParameter("releaseDate");
        log("code = " + code);
        log("description = " + description);
        log("price = " + price);
        log("releaseDate2 = " + releaseDate2);
        
        //log(releaseDate2);
        //        LocalDate releaseDate = new 
        LocalDate releaseDate = LocalDate.now();
        
        String url = null;
        String errmsg = null;
        
        log("Local variables declared");
        
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
//        
//        try {
////            Date date = formatter.parse(releaseDate2);
//            releaseDate = LocalDate.parse(releaseDate2, formatter);
//        } catch (DateTimeParseException ex) {
//            errmsg = "Please enter a valid date as YYYY-MM-DD.";
//            url = "/AddProduct.jsp";
//
//            request.setAttribute("code", code);
//            request.setAttribute("description", description);
//            request.setAttribute("errmsg", errmsg);
//
//            getServletContext()
//                    .getRequestDispatcher(url)
//                    .forward(request, response);
//        }
        
//        if(isValidDate(releaseDate2))
//        {
//            releaseDate = LocalDate.now();
//        }
//        else 
//        {
//            errmsg = "Please enter a valid date as YYYY-MM-DD.";
//            url = "/AddProduct.jsp";
//
//            request.setAttribute("code", code);
//            request.setAttribute("description", description);
//            request.setAttribute("errmsg", errmsg);
//
//            getServletContext()
//                    .getRequestDispatcher(url)
//                    .forward(request, response);
//        }

        
//        String url = null;
//        String errmsg = null;
//        
        // Check if the "View Products" button was pressed on the AddProduct.jsp
        if(viewProducts != null) {
            log("ViewProducts != null");
            if(catalog != null) {
                request.setAttribute("products", catalog.findAllProducts());
            }
            request.getRequestDispatcher("/ProductDump.jsp").forward(request, response);
        }
        
        // Check if "Add Product" button was pressed on AddProduct.jsp
        else if(addProduct != null) {
            log("addProduct != null");
            ProductBean product = null;
//            String errmsg;
//            String url;
            Double priceDouble = 0.00;
            
            // Get user input
//            String code = request.getParameter("code");
//            String description = request.getParameter("description");
//            String price = request.getParameter("price");
//            LocalDate releaseDate = LocalDate.now();
            
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

            try {
                priceDouble = Double.parseDouble(price);
            }
            catch (NumberFormatException nfe) {
                errmsg = "Please enter a valid number for the price.";
                url = "/AddProduct.jsp";
                
                request.setAttribute("code", code);
                request.setAttribute("description", description);
                request.setAttribute("releaseDate", releaseDate);
                request.setAttribute("errmsg", errmsg);
                
                getServletContext()
                        .getRequestDispatcher(url)
                        .forward(request, response);
                return;
            }
            
            try {
//                log("Release Date: " + releaseDate.toString());
//                log("Release Date 2: " + releaseDate2);
                releaseDate = LocalDate.parse(releaseDate2);
            } catch (DateTimeParseException ex) {
//                log(releaseDate.toString());
                errmsg = "Please enter a valid date as YYYY-MM-DD.";
                url = "/AddProduct.jsp";

                request.setAttribute("code", code);
                request.setAttribute("description", description);
                request.setAttribute("price", price);
                request.setAttribute("errmsg", errmsg);

                getServletContext()
                        .getRequestDispatcher(url)
                        .forward(request, response);
                return;
            }
            
            // Validate the user entered a value in each field
            if(code == null || description == null || price == null ||
                    code.isEmpty() || description.isEmpty() || price.isEmpty()) {
                errmsg = "Please fill out all of the fields.";
                url = "/AddProduct.jsp";
            } 
            // Check if produt already exists in catalog
            else if(catalog.exists(code)) {
                errmsg = "Product already exists in catalog.";
                url = "/AddProduct.jsp";
                
            }
            else {
                errmsg = "";
                url = "/ProductDump.jsp";
                
                product = new ProductBean(code, description, 
                    priceDouble, releaseDate);
                
                catalog.insertProduct(product);
            }
            
            request.setAttribute("code", code);
            request.setAttribute("description", description);
            request.setAttribute("price", price);
            request.setAttribute("releaseDate", releaseDate);
            request.setAttribute("errmsg", errmsg);
            request.setAttribute("products", catalog.findAllProducts());
            
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);
            return;
        }
        // Edit button on AddProduct.jsp
        else if(editProduct != null) {
            log("editProduct != null");
            Double priceDouble = 0.00;
            
            try {
                priceDouble = Double.parseDouble(price);
            }
            catch (NumberFormatException nfe) {
                errmsg = "Please enter a valid number for the price.";
                url = "/AddProduct.jsp";
                
                request.setAttribute("code", code);
                request.setAttribute("description", description);
                request.setAttribute("errmsg", errmsg);
                request.setAttribute("releaseDate", releaseDate2);
                request.setAttribute("editProduct", "editProduct");
                
                getServletContext()
                        .getRequestDispatcher(url)
                        .forward(request, response);
                return;
            }
            
            try {
//                log("Release Date: " + releaseDate.toString());
//                log("Release Date 2: " + releaseDate2);
                releaseDate = LocalDate.parse(releaseDate2);
            } catch (DateTimeParseException ex) {
//                log(releaseDate.toString());
                errmsg = "Please enter a valid date as YYYY-MM-DD.";
                url = "/AddProduct.jsp";

                request.setAttribute("code", code);
                request.setAttribute("description", description);
                request.setAttribute("price", price);
                request.setAttribute("errmsg", errmsg);

                getServletContext()
                        .getRequestDispatcher(url)
                        .forward(request, response);
                return;
            }
            
            // Get product from catalog
            log(description);
            log(code);
            log(price);
            code = code.toUpperCase();
            log(code);
            Product product = catalog.selectProduct(code);
            product.setDescription(description);
            product.setPrice(priceDouble);
            product.setReleaseDate(releaseDate);
            log(product.getCode());
            log(product.getDescription());
            log(product.toString());
            
            catalog.updateProduct(product);
            
            request.setAttribute("products", catalog.findAllProducts());
            
            request.getRequestDispatcher("/ProductDump.jsp")
                    .forward(request, response);
            
            return;
        }
        // View Product button on index.jsp page
        else if(action != null && action.equals("viewProducts")) {
            log("action.equals(viewProducts)");
            if(catalog != null) {
                // Store attribute for product catalog
                request.setAttribute("products", catalog.findAllProducts());
            }

            // Forward control
            request.getRequestDispatcher("/ProductDump.jsp")
                    .forward(request, response);
            
            return;
        }
        // Add product button on ProductDump.jsp
        else if(action != null && action.equals("addProduct")) {
            log("action.equals(addProduct)");
            request.getRequestDispatcher("/AddProduct.jsp")
                    .forward(request, response);
            
            return;
        }
        // Edit button on product page
        else if(action != null && action.equals("editProduct")){
            log("action.equals(editProduct)");
            request.setAttribute("code", code);
            request.setAttribute("description", description);
            request.setAttribute("price", price);
            request.setAttribute("releaseDate", releaseDate2);
            request.setAttribute("editProduct", "editProduct");
            
            request.getRequestDispatcher("/AddProduct.jsp")
                    .forward(request, response);
            
            return;
        }
        // Delete button on ProductDump.jsp
        else if(action != null && action.equals("deleteProduct")) {
            log("action.equals(deleteProduct)");
            Product product = catalog.selectProduct(code);
            
            request.setAttribute("product", product);
            
            request.getRequestDispatcher("/DeleteProduct.jsp")
                    .forward(request, response);
            
            return;
        }
        // Delete button on DeleteProduct.jsp
        else if(deleteProduct != null) {
            log("deleteProduct != null");
            code = code.toUpperCase();
            Product product = catalog.selectProduct(code);
            catalog.deleteProduct(product);
            
            if(catalog != null) {
                // Store attribute for product catalog
                request.setAttribute("products", catalog.findAllProducts());
            }

            // Forward control
            request.getRequestDispatcher("/ProductDump.jsp")
                    .forward(request, response);
            
            return;
            
        }
        else
        {
            log("passed all else ifs");
        }
        
        log("End servlet");
        
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
        
        catalog = ProductCatalog.getInstance();
        
//        final ServletContext sc = getServletContext();
//        String catalogPath = sc.getRealPath("/WEB-INF/catalog/catalog2.dat");
//        
//        if(catalogPath != null && ProductCatalog.init(catalogPath)){
//            catalog = ProductCatalog.getInstance();
            
//             Comment out as catalog already exists
//            if(catalog != null) {
//                CatalogUtility.generateCatalog(catalog, NUM_0F_PRODUCTS);
//            }
//        }
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
