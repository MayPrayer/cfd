package com.hbnu.controller;

import com.hbnu.entity.Informs;
import com.hbnu.entity.Result;
import com.hbnu.entity.Users;
import com.hbnu.service.IQryService;
import com.hbnu.service.IUpdateService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: InformsInfoController <br/>
 * Description: <br/>
 * date: 2020/3/14 13:21<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */

@Controller
@RequestMapping("/informs")
public class InformsInfoController {

    @Autowired
    private IUpdateService ius;
    @Autowired
    private IQryService iqs;



    /*
     * 新增公告信息 提交表单显示信息
     * */
    @RequestMapping("/addoneinform")
    @ResponseBody
    public Result addInform(HttpSession session, @RequestParam("title")String title,@RequestParam("important")int important,@RequestParam("content") String content) {
        System.out.println("title:\n"+title+"\nimportant:\n"+important+"\ncontent\n"+content);

        int shopid = (int)session.getAttribute("shopid");
        ius.insertOneInform(title,content,shopid,important);
        return Result.success();
    }

/*
* 删除公告信息
* */
@RequestMapping("/deloneinform")
@ResponseBody
public Result delOneInform(@Param("id")int id){
    int code = ius.delOneInformById(id);
    return Result.success();
}



    /*
     * 跳转公告显示信息页  这里直接返回视图
     * */
    @RequestMapping("/toinform")
    public String toInform(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("userinfo");
        List<Informs> informs =  iqs.selectInformSortBytime(user.getId());

        model.addAttribute("informs",informs);
        System.out.println("视图为"+model);
        return "showinform";
    }


    /*
     * 跳转公告新增页面
     * */
    @RequestMapping("/toaddinform")
    public String toaddInform() {
        return "addinform";
    }


}
