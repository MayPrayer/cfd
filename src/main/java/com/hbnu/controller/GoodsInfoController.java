package com.hbnu.controller;

import com.github.pagehelper.PageInfo;
import com.hbnu.entity.Goods;
import com.hbnu.entity.Result;
import com.hbnu.entity.Users;
import com.hbnu.service.IQryService;
import com.hbnu.service.IUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ClassName: GoodsInfoController <br/>
 * Description: <br/>
 * date: 2020/3/6 10:30<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */

@Controller
@RequestMapping("/curgoodsinfo")
public class GoodsInfoController {
    @Autowired
    private IUpdateService ius;

    @Autowired
    private IQryService iqs;
    //图片路径前缀
//    private static final  String PRE = "D:\\Github\\cfd\\src\\main\\webapp\\static\\";
    private static final  String PRE = "/file";
    /*
     * 输出全部商品信息
     * */
    @RequestMapping("/showcurgoodsinfo")
    @ResponseBody
    public Result showCurGoodsInfo(@RequestParam("pagenum") int pageNum, @RequestParam("pagesize") int pageSize, HttpSession session) {
//       获取当前用户的信息
        Users user = (Users) session.getAttribute("userinfo");
        int id = user.getId();
        Result result = Result.success();
        PageInfo pageInfo = iqs.selectCurUserGoods(pageNum, pageSize, id);
        result.setCount((int) pageInfo.getTotal());
        result.setData(pageInfo);
        System.out.println("返回结果为" + result);
        return result;

    }


    /*
     *跳转到商品页面
     * */
    @RequestMapping("/showgoods")
    public String showuser() {
        System.out.println("执行了");
        return "goods";
    }

    /*
     *  删除单个商品
     * */
    @RequestMapping("/delgood")
    @ResponseBody
    public Result delGood(@RequestParam("id") int id) {
        int code = ius.delGoodById(id);
        if (code != 1) {
            return Result.failed("删除失败");
        }
        return Result.success();

    }

    /*
     * 新增一个商品  describes,img,price,discountprice,sales,inventory,goodsstatus,goodstype
     * */
    @RequestMapping("/addgood")
    @ResponseBody
    public Result addGood( @RequestParam("name") String name, @RequestParam("describes") String describes, @RequestParam("image") String orginImg,
                          @RequestParam("price") double price, @RequestParam("discountprice") double discountprice, @RequestParam("sales") int sales, @RequestParam("inventory") int inventory,
                          @RequestParam("goodsstatus") String goodsstatus, @RequestParam("goodstype") String goodstype,HttpSession session) {
        int shopid = (int)session.getAttribute("shopid");
        String img =PRE+orginImg;
        int code = ius.addGood(shopid, name, describes, img, price, discountprice, sales, inventory, goodsstatus, goodstype);
        if (code != 1) {
            return Result.failed("删除失败");
        }
        return Result.success();

    }

    /*
     * 修改商品
     * */
    @RequestMapping("/editgood")
    @ResponseBody
    public Result editGood(@RequestParam("modifname") String name, @RequestParam("modifdescribes") String describes, @RequestParam("modifimage") String orginImg,
                           @RequestParam("modifprice") double price, @RequestParam("modifdiscountprice") double discountprice, @RequestParam("modifsales") int sales, @RequestParam("modifinventory") int inventory,
                           @RequestParam("modifgoodsstatus") String goodsstatus, @RequestParam("modifgoodstype") String goodstype, @RequestParam("id") int id) {
        String img =PRE+orginImg;
        int code = ius.editGood(name, describes, img, price, discountprice, sales, inventory, goodsstatus, goodstype,id);
        if (code != 1) {
            return Result.failed("删除失败");
        }
        return Result.success();
    }


    /*
     * 批量删除操作
     * */
    @RequestMapping("/delgoods")
    @ResponseBody
    public Result delGoods(@RequestParam("isStr") String isStr) {
//        判断传过来数据不为空
        if (isStr != null && !isStr.equals("")) {
            String[] ids = isStr.split(",");
//            遍历数组
            for (String id : ids) {
                if (id != null && !id.equals("")) {
//              调用删除服务
                    int parseid = Integer.parseInt(id);
                    ius.delGoodById(parseid);
                }
            }
            return Result.success();
        }


        return Result.failed("删除失败");
    }


    /*
     * 上传图片
     * */
    @RequestMapping("/upload")
    @ResponseBody
    public Result uploadImg(MultipartFile file, HttpServletRequest request) throws Exception {
        String prefix = "";
        String dateStr = "";
        //保存上传
        OutputStream out = null;
        InputStream fileInput = null;
        try {
            if (file != null) {
                String originalName = file.getOriginalFilename();
                prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
                Date date = new Date();
//                生成唯一随机码
                String uuid = UUID.randomUUID() + "";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
//                照片保存路径
                String filepath = "D:\\Github\\cfd\\src\\main\\webapp\\static\\upload" + "\\" + dateStr + "\\" + uuid + "." + prefix;


                File files = new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);

                Map<String, Object> map = new HashMap<>();
                map.put("src", "/upload/" + dateStr + "/" + uuid + "." + prefix);
                Result result = Result.success();
                result.setData(map);
                return result;
            }

        } catch (Exception e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        return Result.success();
    }


    /*
    * 模糊查询
    * */

    @RequestMapping("/selectlikegoods")
    @ResponseBody
    public Result selectLikeUser(@RequestParam("pagenum") int pageNum, @RequestParam("pagesize") int pageSize, @RequestParam("name") String name,HttpSession session) {
        int shopid = (int)session.getAttribute("shopid");
        System.out.println("开始页" + pageNum + "\n每页数据量" + pageSize + "\t模糊查询词为：" + name);
        Result result = Result.success();
        PageInfo pageInfo = iqs.selectLikeGoods(pageNum, pageSize, name,shopid);
        result.setCount((int) pageInfo.getTotal());
        result.setData(pageInfo);
        return result;
    }


}
