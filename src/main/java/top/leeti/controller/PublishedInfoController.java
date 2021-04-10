package top.leeti.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.leeti.entity.PublishedInfo;
import top.leeti.service.PublishedInfoService;

import javax.annotation.Resource;

@Slf4j
@Controller
public class PublishedInfoController {

    @Resource
    private PublishedInfoService publishedInfoService;

    @GetMapping("/admin/publishedInfo")
    public String findMixedList(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                Model model) {
        PageInfo<PublishedInfo> pageInfo = publishedInfoService.listReportedPublishedInfo(pageNum);
        model.addAttribute("pageInfo", pageInfo);
        return "published-info/published-info";
    }

    @ResponseBody
    @GetMapping("/admin/publishedInfo/passAjax")
    public String dealWithPassAjax(@RequestParam String id, @RequestParam Integer mark, @RequestParam String reportId) {


        String msg = publishedInfoService.dealWithPass(id, mark, reportId);
        return JSON.toJSONString(msg);
    }

}