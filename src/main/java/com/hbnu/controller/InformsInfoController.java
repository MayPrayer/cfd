package com.hbnu.controller;

import com.hbnu.entity.Result;
import com.hbnu.service.IQryService;
import com.hbnu.service.IUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
    /*
     * 显示公告信息
     * */
    @RequestMapping("/showinforms")
    public Result showInforms() {
        return Result.success();
    }


    /*
     * 新增公告信息
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
     * 跳转公告显示信息页
     * */
    @RequestMapping("/toinform")
    public String toInform() {
        return "showinform";
    }


    /*
     * 跳转公告编辑页
     * */
    @RequestMapping("/toaddinform")
    public String toaddInform() {
        return "addinform";
    }


}
