package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProductDaoTest {
    private ProductDao productDao;
    private DaoFactory daoFactory;

    @Before
    public void setup() {
        daoFactory = new DaoFactory();
        productDao = daoFactory.getProductDao();
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
        product.setTitle("제주감귤");
        product.setPrice(15000);
        Long id = productDao.insert(product);

        Product insertProduct = productDao.get(id);
        assertEquals(insertProduct.getId(), id);
        assertEquals(insertProduct.getTitle(), product.getTitle());
        assertEquals(insertProduct.getPrice(), product.getPrice());
    }

    @Test
    public void hallGet() throws SQLException, ClassNotFoundException {
        Long id = 1L;

        Product product = productDao.get(id);
        assertThat(product.getId(), is(id));
        assertThat(product.getTitle(), is("제주감귤"));
        assertThat(product.getPrice(), is(15000));
    }

    @Test
    public void hallaAdd() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        product.setTitle("제주감귤");
        product.setPrice(15000);
        Long id = productDao.insert(product);

        Product insertProduct = productDao.get(id);
        assertEquals(insertProduct.getId(), id);
        assertEquals(insertProduct.getTitle(), product.getTitle());
        assertEquals(insertProduct.getPrice(), product.getPrice());
    }
}
