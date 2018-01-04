package com.dihardmg.kayrawanapp.controller;

import com.dihardmg.kayrawanapp.dao.KaryawanDao;
import com.dihardmg.kayrawanapp.entity.Karyawan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author : Otorus
 * @since : 1/4/18
 */
@Controller
public class KaryawanController {
    @Autowired
    private KaryawanDao karyawanDao;


    @GetMapping("/karyawan/list")
    public ModelMap karyawan(@PageableDefault(size = 5) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model){
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("karyawan", karyawanDao.findByNamaContainingIgnoreCase(value, pageable));
        } else {
            return new ModelMap().addAttribute("karyawan", karyawanDao.findAll(pageable));
        }
    }

    @GetMapping("/karyawan/form")
    public ModelMap tampilkanForm(@RequestParam(value = "id", required = false) Karyawan karyawan ) {
        if (karyawan == null) {
            karyawan = new Karyawan();
        }
        return new ModelMap("karyawan", karyawan);
    }


    @PostMapping("/karyawan/form")
    public String simpan(@Valid @ModelAttribute("karyawan") Karyawan karyawan , BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "karyawan/form";

        }
        karyawanDao.save(karyawan);
        status.setComplete();
        return "redirect:/karyawan/list";
    }



    @GetMapping("/karyawan/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Karyawan karyawan ) {
        return new ModelMap("karyawan", karyawan);
    }

    @PostMapping("/karyawan/delete")
    public Object delete(@ModelAttribute Karyawan karyawan , SessionStatus status) {
        try{

            karyawanDao.delete(karyawan);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", karyawan.getNama())
                    .addObject("entityName", "Karyawan")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/karyawan/list");
        }
        status.setComplete();
        return "redirect:/karyawan/list";
    }

}


