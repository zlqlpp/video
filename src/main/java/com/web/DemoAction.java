package com.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.BaseDao;


/**
 * Created by Administrator on 2016-03-27.
 */
@Controller
public class DemoAction {

    private static final String prefix = "/demo/";
    @Autowired
    BaseDao bd;


    @RequestMapping("/a")
    public String upload(){
        return prefix + "upload";
    }


}
