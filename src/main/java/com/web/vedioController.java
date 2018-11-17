package com.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.BaseDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016-03-27.
 */
@Controller

public class vedioController {

    @Autowired
    BaseDao bd;
    
/*    @RequestMapping("/test")
    public String test(ModelMap model,HttpSession session){
    	bd.insertAndSave();
        return  "main";
    }*/

    @RequestMapping("/main")
    public String main(ModelMap model,HttpSession session){
    	List list = (List) session.getAttribute("fileShortNameList");
        if(null == session.getAttribute("fileShortNameList")||"".equals(session.getAttribute("fileShortNameList"))){
        	getVideoList(session);
        } 
        return  "main";
    }
    @RequestMapping("/login")
    public String login(ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
    	
    	String query_userName = request.getParameter("userName");
    	String query_passWord = request.getParameter("passWord");
    	if(StringUtils.isBlank(query_userName)||StringUtils.isBlank(query_passWord)){
    		return "login";
    	}
    	List ret_list = null;
    	Map map = (Map) session.getAttribute("user");
    	if(null!=map&&query_userName.equals(map.get("userName"))){
    		return "main";
    	}else{
    		
    		ret_list = bd.findUser(query_userName, query_passWord);
    		if(null!=ret_list&&ret_list.size()>0){
    			map = (Map) ret_list.get(0);
    			session.setAttribute("user", map);
    			return "main";
    		}else{
    			model.addAttribute("err", "帐号错误，请联系VX:salinahk");
    			return "login";
    		}
    	}
    }
    
    @RequestMapping("/goVideo")
    public String goVideo(ModelMap model,HttpServletRequest request,HttpSession session){
    	
    	String query_video = request.getParameter("video");
    	
    	Map user  = (Map) session.getAttribute("user");
    	if(null == user||"".equals(user)){
    		return "login";
    	}else{
    		boolean b = bd.updateGoldReduceAndInsertSpendHistory(user.get("id").toString(),user.get("user_name").toString(),query_video,user.get("total_gold").toString());
    		if(b){
    			int tmp_total_gold = Integer.parseInt(user.get("total_gold").toString())-1;
    			user.put("total_gold", tmp_total_gold);
    			session.setAttribute("user", user);
    			Map map = (Map) session.getAttribute("fileFulllNameMap");
        		model.addAttribute("video", map.get(query_video));
                return  "video";
    		}
    		model.addAttribute("err","支付失败，联系qq11111");
    		return "main";
    	}
        
    }


//---------------------------------------工具方法-------------------------

    private void getVideoList(HttpSession session){
    	 
    	String path3 = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"resources/config.properties"; 
        
        Properties prop = new Properties();
        //读取资源文件
        try {
			prop.load(new FileInputStream(path3));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        String videoPath = prop.getProperty("videoPath");

    	File file = new File(videoPath);
        File[] fileNamesArray = file.listFiles();
        if(null == fileNamesArray){return;}
        List<String> fileShortNameList = new ArrayList<String>();
        Map<String,String>  fileFulllNameMap  = new HashMap<String,String>();
        for (int i = 0; i < fileNamesArray.length; i++) {
            if (fileNamesArray[i].isFile()&&fileNamesArray[i].getName().split("---").length==3) {
                  fileShortNameList.add( fileNamesArray[i].getName().split("---")[0]);
                  fileFulllNameMap.put(fileNamesArray[i].getName().split("---")[0], fileNamesArray[i].getName());
            }
        }

        session.setAttribute("fileShortNameList", fileShortNameList);
        session.setAttribute("fileFulllNameMap", fileFulllNameMap);
        
    }
}
