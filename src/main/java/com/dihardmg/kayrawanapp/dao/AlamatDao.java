package com.dihardmg.kayrawanapp.dao;

import com.dihardmg.kayrawanapp.entity.Alamat;
import com.dihardmg.kayrawanapp.entity.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Otorus
 * @since : 1/10/18
 */
@Repository
public interface AlamatDao extends PagingAndSortingRepository<Alamat, String> {
    List<Alamat> findByKaryawan(Karyawan karyawan);

    Page<Alamat> findByNamaContainingIgnoreCase(String nama, Pageable pageable);
}