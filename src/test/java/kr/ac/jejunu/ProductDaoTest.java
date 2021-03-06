package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProductDaoTest {
    private ProductDao productDao;
    private DaoFactory daoFactory;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        productDao = applicationContext.getBean("productDao", ProductDao.class);
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        Long id = insertProductTest(product);

        product.setId(id);
        product.setTitle("제주감귤");
        product.setPrice(15000);
        productDao.update(product);

        Product updateProduct = productDao.get(id);
        assertEquals(updateProduct.getId(), id);
        assertEquals(updateProduct.getTitle(), product.getTitle());
        assertEquals(updateProduct.getPrice(), product.getPrice());
    }

    private Long insertProductTest(Product product) throws SQLException, ClassNotFoundException {
        product.setTitle("제주감귤");
        product.setPrice(15000);

        return productDao.insert(product);
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        Long id = insertProductTest(product);

        productDao.delete(id);

        Product deleteProduct = productDao.get(id);
        assertThat(deleteProduct, nullValue());
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = productDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }

    @Test
    public void add() throws ClassNotFoundException, SQLException {
        Product product = new Product();
        Long id = insertProductTest(product);

        Product insertProduct = productDao.get(id);
        assertEquals(insertProduct.getId(), id);
        assertEquals(insertProduct.getTitle(), product.getTitle());
        assertEquals(insertProduct.getPrice(), product.getPrice());
    }
}
