package com.dihardmg.kayrawanapp.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author : Otorus
 * @since : 1/10/18
 */
@Entity
@Data
public class Alamat {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;


    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_karyawan")
    private Karyawan karyawan;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 150)
    private String nama;
    @NotNull
    @NotEmpty
    private String alamat;


}
