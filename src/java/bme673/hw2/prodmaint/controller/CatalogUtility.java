/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bme673.hw2.prodmaint.controller;

import bme673.hw2.prodmaint.model.ProductBean;
import edu.saintpaul.csci2466.prodmaint.data.ProductCatalog;
import edu.saintpaul.csci2466.prodmaint.model.Product;
import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author Blake Edwards
 */
public class CatalogUtility {

    private static final long SEED = 2014091201L;
    private static final Random RAND = new Random(SEED);

    /**
     * Clears the provided catalog and fills with a specified number of dummy
     * products
     *
     * @param catalog a Product Catalog
     * @param numProducts the number of products to load into the catalog
     */
    public static void generateCatalog(ProductCatalog catalog, int numProducts) {
        
            catalog.clear();

            // Loop to create random test products to insert into the catalog
            for (int product = 1; product <= numProducts; product++) {
                Product pb = new ProductBean(Integer.toString(product + 10),
                    "Test Product " + product,
                    randomPrice(),
                    randomDate());

                catalog.insertProduct(pb);
            }
    }

    /**
     * Get a random price for a product in the catalog This is used to test
     * populating the catalog
     *
     * @return a random double between 0 and 100
     */
    private static double randomPrice() {
        Random rand = new Random();
        return rand.nextDouble() * 100;
    }

    /**
     * Generates a random date to populate test products in the catalog
     *
     * @return a random date between 1900 and 2020
     */
    private static LocalDate randomDate() {
        Random rand = new Random();
        int year;
        int month;
        int day;

        year = rand.nextInt(121) + 1900; // random year from 1900 - 2020
        month = rand.nextInt(12) + 1;    // random month
        day = rand.nextInt(28) + 1;      // random day from 1 - 28

        LocalDate date = LocalDate.of(year, month, day);
        return date;
    }

}
