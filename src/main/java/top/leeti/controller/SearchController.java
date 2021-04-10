package top.leeti.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin/search")
public class SearchController {
//
//    @Autowired
//    private SingleService singleService;
//
//    @Autowired
//    private EcardService ecardService;
//
//    @Autowired
//    private MixedDataService mixedDataService;
//
//    @GetMapping
//    public String search(){
//        return "search/search";
//    }
//
//    /**
//     * /admin/search（POST）
//     * 处理提交的表单数据
//     * @param form 表单
//     * @param model 传递的数据
//     * @param attributes 重定向时，传递的数据
//     * @return 前往某个页面
//     */
//    @PostMapping
//    public String goToSearchDetailPage(Form form, Model model, RedirectAttributes attributes) {
//
//        if(form.getTypeId() == null){
//            attributes.addFlashAttribute("msg", "typeId 为空");
//        } else {
//            // 查询脱单信息，按发布者的学号
//            if("6".equals(form.getTypeId())){
//                if(form.getSingleStuId() != null){
//                    SingleModel single = singleService.findSingleByStuId(form.getSingleStuId());
//                    if (single == null) {
//                        attributes.addFlashAttribute("msg", "暂无脱单信息");
//                        return "redirect:/admin/search";
//                    }
//                    single.setPictureUrlList(FileUtil.obtainPictureNameList(
//                            single.getTypeId().toString(), single.getId()));
//                    model.addAttribute("single", single);
//                    return "detail/single-detail";
//                }
//                attributes.addFlashAttribute("msg", "发布者id 为空");
//
//            // 查询一卡通信息
//            } else if("7".equals(form.getTypeId())){
//                EcardModel ecard = null;
//                if (form.getEcardStuId() != null && form.getEcardStuId().length() != 0) {
//                    ecard = ecardService.findEcardByStuId(form.getEcardStuId());
//                } else if(form.getEcardName() != null) {
//                    ecard = ecardService.findEcardByStuName(form.getEcardName());
//                } else {
//                    attributes.addFlashAttribute("msg", "一卡通的失主学号或姓名为空");
//                    return "redirect:/admin/search";
//                }
//                if(ecard == null){
//                    attributes.addFlashAttribute("msg", "暂无该一卡通的信息");
//                    return "redirect:/admin/search";
//                }
//                List<EcardModel> list = Arrays.asList(ecard);
//                model.addAttribute("pageInfo", new PageInfo<>(list));
//                return "ecard/ecard-one";
//
//            // 查询其他信息
//            } else if("1345".contains(form.getTypeId())){
//                if(form.getOtherStuId() == null && form.getOtherName() == null){
//                    attributes.addFlashAttribute("msg", "发布者的学号与主题/物品名都为空");
//                } else {
//                    Map<String, String> map = new Hashtable<>();
//                    map.put("typeId", form.getTypeId());
//                    if (form.getOtherName() != null && form.getOtherName().length() > 0)
//                        map.put("otherName", form.getOtherName());
//                    if (form.getOtherStuId() != null && form.getOtherStuId().length() > 0)
//                        map.put("otherStuId", form.getOtherStuId());
//
//                    PageInfo<MixedDataModel> pageInfo = mixedDataService.findSearchList(map, 1);
//
//                    if(pageInfo.getList().size() == 0){
//                        attributes.addFlashAttribute("msg", "暂无信息");
//                        return "redirect:/admin/search";
//                    }
//
//                    // 获取图片地址
//                    for (MixedDataModel mdm : pageInfo.getList()) {
//                        if (!(new Integer(0).equals(mdm.getPictureNum())))
//                            mdm.setPictureUrlList(FileUtil.obtainPictureNameList(
//                                    mdm.getTypeId().toString(), mdm.getId()));
//                    }
//
//                    model.addAttribute("pageInfo", pageInfo);
//                    return "mixed-management/mixed-management";
//                }
//            } else {
//                attributes.addFlashAttribute("msg", "分类号错误");
//            }
//        }
//        return "redirect:/admin/search";
//    }
//
//    @Data
//    private class Form implements Serializable {
//        private String typeId;
//        private String singleStuId;
////        private String singleDescription;
//        private String ecardStuId;
//        private String ecardName;
//        private String otherStuId;
//        private String otherName;
////        private String otherDescription;
//    }
}
