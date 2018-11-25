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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016-03-27.
 */
@Controller

public class vedioNoLogInController {

    @Autowired
    BaseDao bd;
    
    //private static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger("vedioController");
/*    @RequestMapping("/test")
    public String test(ModelMap model,HttpSession session){
    	bd.insertAndSave();
        return  "main";
    }*/

    @RequestMapping("/mainNoLogIn")
    public String main(ModelMap model,HttpSession session){
    	List list = (List) session.getAttribute("fileShortNameList");
        if(null == session.getAttribute("fileShortNameList")||"".equals(session.getAttribute("fileShortNameList"))){
        	getVideoList(session);
        } 
        return  "mainNoLogIn";
    }
    
    @RequestMapping("/goVideo")
    public String goVideo(ModelMap model,HttpServletRequest request,HttpSession session){
    	
    	String query_video = request.getParameter("video");
    	Map map = (Map) session.getAttribute("fileFulllNameMap");
    	model.addAttribute("video", map.get(query_video));
    	
        return  "video";
        
    }


//---------------------------------------工具方法-------------------------

    private void getVideoList(HttpSession session){
    	 
    	String path3 = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"config.properties"; 
        System.out.println(path3);
        //logger.info(path3);
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
