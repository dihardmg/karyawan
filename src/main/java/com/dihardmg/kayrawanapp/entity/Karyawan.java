package com.dihardmg.kayrawanapp.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Otorus
 * @since : 1/4/18
 */
@Entity
@Data
public class Karyawan {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String nama;

    @NotEmpty
    @Size(max = 255)
    @Column(nullable = false)
    private String keterangan;
    
}
