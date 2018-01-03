package com.dihardmg.kayrawanapp.dao;

import com.dihardmg.kayrawanapp.entity.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Otorus
 * @since : 1/4/18
 */
@Repository
public interface KaryawanDao extends PagingAndSortingRepository<Karyawan, String> {
    Page<Karyawan>findByNamaContainingIgnoreCase(String nama, Pageable pageable);
}
