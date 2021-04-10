package top.leeti.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import top.leeti.service.AdministratorService;
import top.leeti.entity.Administrator;
import top.leeti.util.PasswordUtil;
import top.leeti.util.UuidUtil;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @GetMapping("/admin/administrator")
    public String findAdministrators(Model model){
        List<Administrator> list = administratorService.findAdministrators();
        model.addAttribute("list", list);
        return "administrator/administrator";
    }

    @GetMapping("/admin/administrator/modify/{id}")
    public String modifyAdministrator(@PathVariable("id")String id, Model model) {
        Administrator administrator = administratorService.findAdministratorById(id);
        model.addAttribute("administrator", administrator);
        return "administrator/administrator-input";
    }

    @PostMapping("/admin/administrator/modified")
    public String modifingAdministrator(Administrator administrator){
        // 传来的 administrator 中的 username 为空
        Administrator ad = administratorService.findAdministratorById(administrator.getId());

        administrator.setUsername(ad.getUsername());
        if(!ad.getEnPassword().equals(administrator.getEnPassword())){
            administrator.setEnPassword(
                    PasswordUtil.encrypt(ad.getUsername(), administrator.getEnPassword()));
        }
        administratorService.updateAdministrator(administrator);
        return "redirect:/admin/administrator";
    }

    @PostMapping("/admin/administrator/add")
    public String addAdministrator(Administrator administrator){
        administrator.setId(UuidUtil.acquireUuid());
        administrator.setEnPassword(PasswordUtil.encrypt(administrator.getUsername(), administrator.getEnPassword()));
        administrator.setGmtCreate(new Date());
        administratorService.insertAdministrator(administrator);
        return "redirect:/admin/administrator";
    }

    /**
     * 前往新增管理页（携带一个空信息）
     */
    @GetMapping("/admin/administrator/add")
    public String addGetAdministrator(Model model){
        model.addAttribute("administrator", new Administrator());
        return "administrator/administrator-input";
    }

    @GetMapping("/admin/administrator/delete/{id}")
    public String deleteAdministrator(@PathVariable("id")String id){
        administratorService.deleteAdministratorById(id);
        return "redirect:/admin/administrator";
    }
}
