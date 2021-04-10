package top.leeti.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.leeti.service.CollegeService;
import top.leeti.entity.College;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Controller
public class CollegeController {

    @Resource
    private CollegeService collegeService;

    @GetMapping("/admin/college")
    public String findColleges(Model model){
        model.addAttribute("list", collegeService.findColleges());
        return "college/college";
    }

    @GetMapping("/admin/college/add")
    public String addCollege(Model model){
        model.addAttribute("college", new College());
        return "college/college-input";
    }

    @GetMapping("/admin/college/modify/{id}")
    public String modifyCollege(@PathVariable("id") Integer id, Model model){
        model.addAttribute("college", collegeService.findCollegeById(id));
        return "college/college-input";
    }

    @PostMapping("/admin/college")
    public String insertCollege(College college, RedirectAttributes attributes){
        operateTable(college, "insert", attributes);
        return "redirect:/admin/college/add";
    }

    @PostMapping("/admin/college/{id}")
    public String updateCollege(College college, RedirectAttributes attributes){
        operateTable(college, "update", attributes);
        return "redirect:/admin/college/add";
    }

    private void operateTable(College college, String option, RedirectAttributes attributes){
        String collegeId = college.getCollegeId();
        String name = college.getCollegeName();
        if(isDigit(collegeId) && name != null && name.length() > 0){
            boolean isExist = verifyExistOfCollegeInfo(collegeId, name);
            if (isExist) {
                attributes.addFlashAttribute("msg", "学院编号或学院名的信息已存在！");
            } else {
                if (option.equals("insert")) {
                    collegeService.insertCollege(college);
                    attributes.addFlashAttribute("msg", college.getCollegeName() + " 添加成功！");
                }
                if (option.equals("update")) {
                    collegeService.updateCollege(college);
                    attributes.addFlashAttribute("msg", college.getCollegeName() + " 修改成功！");
                }
            }
        } else {
            attributes.addFlashAttribute("msg", "输入不合法！请检查输入");
        }
    }

    private boolean verifyExistOfCollegeInfo(String collegeId, String name) {
        List<College> colleges = collegeService.findColleges();
        boolean isExist = false;
        for (College item : colleges) {
            if (item.getCollegeId().equals(collegeId) || item.getCollegeName().equals(name)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    private boolean isDigit(String s){
        if (s == null || s.length() == 0) {
            return false;
        }
        for(char c : s.toCharArray()){
            if(!Character.isDigit(c)) return false;
        }
        return true;
    }
}
