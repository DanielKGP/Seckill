package miaosha.controller;

import miaosha.domain.MiaoshaUser;
import miaosha.redis.RedisService;
import miaosha.service.GoodsService;
import miaosha.service.MiaoshaUserService;
import miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/to_list")
    public String toLogin(Model model, MiaoshaUser user){
        //query goods list
        List<GoodsVo> goodsList = goodsService.listGoodsVo();

        model.addAttribute("goodsList",goodsList);
        //System.out.println(user.getNickname());
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, MiaoshaUser user,
                         @PathVariable("goodsId")long goodsId){
        //query goods list
        model.addAttribute("user",user);

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods",goods);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;

        if(now < startAt){//do not start counting time
            miaoshaStatus = 0;
            remainSeconds = (int)((startAt-now)/1000);
        }else if(now > endAt){//seckill end
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else{
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainSeconds",remainSeconds);

        //System.out.println(user.getNickname());
        return "goods_detail";
    }
}
