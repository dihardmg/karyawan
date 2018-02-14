package com.dihardmg.kayrawanapp.unitTest;

import com.dihardmg.kayrawanapp.dao.KaryawanDao;
import com.dihardmg.kayrawanapp.entity.Karyawan;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author : Otorus
 * @since : 2/14/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "/data/karyawan.sql"
)
public class karyawanTest {

    @Autowired
    private KaryawanDao karyawanDao;

    @Autowired
    private DataSource dataSource;


    @After
    public void hapusData() throws Exception {
        String sql = "delete from karyawan where id = '4'";
        try (Connection c = dataSource.getConnection()) {
            c.createStatement().executeUpdate(sql);
        }
    }


    @Test
    public void testInsert() throws SQLException {
        Karyawan karyawan = new Karyawan();

        karyawan.setNama("Nami");
        karyawan.setKeterangan("Navigator kapal");
        karyawan.setId("4");
        karyawanDao.save(karyawan);

        String sql = "select count(*) as jumlah "
                + "from karyawan "
                + "where nama = 'Nami'";

        try (Connection c = dataSource.getConnection()) {
            ResultSet rs = c.createStatement().executeQuery(sql);
            Assert.assertTrue(rs.next());

            Long jumlahRow = rs.getLong("jumlah");
            Assert.assertEquals(1L, jumlahRow.longValue());
        }

    }

    @Test
    public void testHitung() {
        Long jumlah = karyawanDao.count();
        Assert.assertEquals(9L, jumlah.longValue());
    }

    @Test
    public void testCariById() {
        Karyawan karyawan = karyawanDao.findOne("4");
        Assert.assertNotNull(karyawan);
        Assert.assertEquals("Nami", karyawan.getNama());
        Assert.assertEquals("Navigator kapal", karyawan.getKeterangan());

        Karyawan px = karyawanDao.findOne("jgjgjgj");
        Assert.assertNull(px);
    }

}
