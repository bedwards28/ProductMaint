package bme673.hw2.prodmaint.model;

import edu.saintpaul.csci2466.prodmaint.model.Product;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Blake Edwards
 */
public class ProductBean implements Product
{
    private String code;                      // Catalog ID, unique to each
                                              // catalog entry
    private String description;               // Album Title
    private double price;                     // Price of album
    private LocalDate releaseDate;            // Date album was released
    private int yearsReleased;                // Years from release date in 
                                              // whole numbers
    /**
     * Constructor to set defaults for variables
     * 
     * Sets string variables to empty strings and doubles and ints to 0
     */
    public ProductBean(){
        code = "";
        description = "";
        price = 0.0;
        yearsReleased = 0;
    }
    
    /**
     * This constructor takes in four parameters and sets the code,
     * description, price, and release date for a product
     * 
     * @param code Catalog code for a product
     * @param description Description of the product
     * @param price Price of the product
     * @param releaseDate Release date of the product
     */
    public ProductBean(String code, String description,
            double price, LocalDate releaseDate) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.releaseDate = releaseDate;
        this.yearsReleased = getYearsReleased();
    }
    
    /**
     * Set the value of the catalog code for a product
     * 
     * @param code New value for the catalog code of a product
     */
    @Override
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Get the catalog code for a product
     * 
     * @return the catalog code 
     */
    @Override
    public String getCode() {
        return code;
    }
    
    /**
     * Set the value of the description for a product
     * 
     * @param description New value for the product description
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Get the text description of a product
     * 
     * @return The description for a product
     */
    @Override
    public String getDescription() {
        return description;
    }
    
    /**
     * Set the price of a product
     * 
     * @param price New value for the price of a product
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Get the price for a product
     * 
     * @return the price of a product
     */
    @Override
    public double getPrice() {
        return price;
    }
    
    /**
     * Set the release date of a product
     * 
     * @param releaseDate New value for the release date of a product
     */
    @Override
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    /**
     * Get the release date for a product
     * 
     * @return the release date of a product
     */
    @Override
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    
    /**
     * Get the number of years since an album was released in whole numbers
     * 
     * @return the number of years released
     */
    @Override
    public int getYearsReleased() {
        if(releaseDate.getYear() >= LocalDate.now().getYear())
        { 
            return 0;
        }
        else
        {
            return LocalDate.now().getYear() - releaseDate.getYear();
        }
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        return super.hashCode(); 
    }
    

    /**
     * Compares two product objects for equality
     * 
     * @param obj
     * @return True if the two product objects have the same catalog code
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductBean other = (ProductBean) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns a string representing a catalog item
     * 
     * @return String representing a catalog item
     */
    @Override
    public String toString() {
        return "Product Code : " + code + 
                " ; Description: " + description + 
                " ; Price: " + price + 
                " ; Release Date: " + releaseDate + 
                " ; Years Released: " + yearsReleased;
        
    }
}
