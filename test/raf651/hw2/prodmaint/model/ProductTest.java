package raf651.hw2.prodmaint.model;

import edu.saintpaul.csci2466.prodmaint.model.Product;
import java.time.LocalDate;
import java.util.Random;
import junit.framework.Assert;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author rfoy
 */
public class ProductTest {

    
    private static final int MIN_YEAR = 1970;
    private static final int MAX_YEAR = 2013;
    private static final int DAYS_IN_YEAR = 365; // oblivious to leap years


    private final static long SEED = 2014080601L;
    private final static Random RAND = new Random(SEED);
    private final static String ALPHAS;

    static {
        StringBuilder sb = new StringBuilder();
        for (char c = 'A'; c <= 'Z'; c++) {
            sb.append(c);
        }
        ALPHAS = sb.toString();
    }

    public ProductTest() {
    }

    /**
     * Test of getCode method, of class Product.
     */
    @Test
    public void testSetGetCode() {
        System.out.println("set/getCode");
        Product instance = new ProductImpl(); // TODO replace with your Product Bean

        String prodCode = genProdCode();
        instance.setCode(prodCode);
        String result = instance.getCode();
        assertEquals(prodCode, result);
    }

    /**
     * Test of getDescription method, of class Product.
     */
    @Test
    public void testSedGetDescription() {
        System.out.println("set/getDescription");
        Product instance = new ProductImpl(); // TODO replace with your Product Bean
        String prodDescr = "Wonderful product " + genProdCode();
        instance.setDescription(prodDescr);
        String result = instance.getDescription();
        Assert.assertEquals(prodDescr, result);
    }

    /**
     * Test of getPrice method, of class Product.
     */
    @Test
    public void testSetGetPrice() {
        System.out.println("set/getPrice");
        Product instance = new ProductImpl(); // TODO replace with your Product Bean
        double prodPrice = RAND.nextDouble();
        instance.setPrice(prodPrice);
        double result = instance.getPrice();
        assertEquals(prodPrice, result, 0.0);
    }

    /**
     * Test of getReleaseDate method, of class Product.
     */
    @Test
    public void testSetGetReleaseDate() {
        System.out.println("set/getReleaseDate & yearsReleased");
        Product instance = new ProductImpl(); // TODO replace with your Product Bean
        LocalDate prodReleaseDate;
        prodReleaseDate = LocalDate.ofYearDay(intBetween(MIN_YEAR, MAX_YEAR), intBetween(1, DAYS_IN_YEAR));
        
        instance.setReleaseDate(prodReleaseDate);
        LocalDate result = instance.getReleaseDate();
        assertEquals(prodReleaseDate, result);
        
        int yearsAgo = prodReleaseDate.until(LocalDate.now()).getYears();
        
        assertEquals(yearsAgo, instance.getYearsReleased());
    }

    private int intBetween(int start, int end) {
        return Math.abs(start + RAND.nextInt(end - start));
    }

    private String genProdCode() {
        return String.format("%c%c-%04d"
                , ALPHAS.charAt(RAND.nextInt(ALPHAS.length()))
                , ALPHAS.charAt(RAND.nextInt(ALPHAS.length()))
                , RAND.nextInt(9999));
    }

    public class ProductImpl implements Product {

        public String getCode() {
            return "";
        }

        public String getDescription() {
            return "";
        }

        public double getPrice() {
            return 0.0;
        }

        public LocalDate getReleaseDate() {
            return null;
        }

        public int getYearsReleased() {
            return 0;
        }

        public void setCode(String code) {
        }

        public void setDescription(String description) {
        }

        public void setPrice(double price) {
        }

        public void setReleaseDate(LocalDate date) {
        }
    }

}
