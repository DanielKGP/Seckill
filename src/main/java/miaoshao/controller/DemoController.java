package miaoshao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    @RequestMapping("/")
    @ResponseBody
    String home(){
        return "Hello world!!!";
    }
    //1.json 2.page

}
