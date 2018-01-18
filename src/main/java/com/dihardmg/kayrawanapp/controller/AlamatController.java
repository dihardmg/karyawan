package com.dihardmg.kayrawanapp.controller;

import com.dihardmg.kayrawanapp.dao.AlamatDao;
import com.dihardmg.kayrawanapp.dao.KaryawanDao;
import com.dihardmg.kayrawanapp.entity.Alamat;
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
 * @since : 1/10/18
 */
@Controller
public class AlamatController {

    @Autowired
    private KaryawanDao karyawanDao;

    @Autowired
    private AlamatDao alamatDao;



    @RequestMapping("/alamat/list")
    public String alamat(Model model, @PageableDefault(size = 5) Pageable pageable,
                                @RequestParam(name = "value",required = false) String value) {

        if(value != null){
            model.addAttribute("key",value);
            model.addAttribute("data",alamatDao.findByNamaContainingIgnoreCase(value, pageable));
        }else{
            model.addAttribute("data", alamatDao.findAll(pageable));
        }
        return "alamat/list";
    }

    @GetMapping("/alamat/form")
    public String tampilkanForms(@RequestParam(value = "id", required = false) Alamat alamat, Model m) {
        if (alamat == null) {
            alamat = new Alamat();
        }
        m.addAttribute("alamat", alamat);
        m.addAttribute("karyawan", karyawanDao.findAll());
        return "alamat/form";
    }

    @PostMapping("/alamat/form")
    public String simpan(@ModelAttribute @Valid Alamat alamat , BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "alamat/form";
        }
        alamatDao.save(alamat);
        status.setComplete();
        return "redirect:/alamat/list";
    }

    @GetMapping("/alamat/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Alamat alamat) {
        return new ModelMap("alamat", alamat);
    }

    @PostMapping("/alamat/delete")
    public Object delete(@ModelAttribute Alamat alamat , SessionStatus status) {
        try{
            alamatDao.delete(alamat);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", alamat.getNama())
                    .addObject("entityName", "Alamat")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/alamat/list");
        }
        status.setComplete();
        return "redirect:/alamat/list";
    }
}