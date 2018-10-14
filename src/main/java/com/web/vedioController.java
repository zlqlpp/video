package com.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
    
    @RequestMapping("/test")
    public String test(ModelMap model,HttpSession session){
    	
    	bd.insertAndSave();
        
        return  "main";
    }

    @RequestMapping("/main")
    public String main(ModelMap model,HttpSession session){
    	List filesList = null;
        if(null == session.getAttribute("files") ||"".equals(session.getAttribute("files"))){
        	filesList = getVideoList();
            session.setAttribute("files", filesList);
            model.addAttribute("files", filesList);
        }else{
        	model.addAttribute("files", session.getAttribute("files"));
        }
        
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("gold", 	   session.getAttribute("gold"));
        
        return  "main";
    }
    @RequestMapping("/login")
    public String login(ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
    	
    	String userName = request.getParameter("userName");
    	String passWord = request.getParameter("passWord");
    	
    	List resultList = null;
    	if(null!=session.getAttribute("userName")&&"userName".equals(session.getAttribute("userName"))){
    		return "main";
    	}else{
    		resultList = bd.findUser(userName,passWord);
    		if(){}
    		if(){
    			session.setAttribute("userName", userName);
        		session.setAttribute("gold", "-1");
        		
        		model.addAttribute("userName", userName);
                model.addAttribute("gold", "-1");
    		}else{
    			request.getRequestDispatcher("main").forward(request, response);
    		}
    		 
    	}
    }
    @RequestMapping("/goVideo")
    public String goVideo(ModelMap model,HttpServletRequest request,HttpSession session){
    	
    	String video = request.getParameter("video");
    	
    	String userName = (String) session.getAttribute("userName");
    	if(null == userName||"".equals(userName)){
    		return "login";
    	}
        model.addAttribute("video", video);
        return  "video";
    }


//---------------------------------------工具方法-------------------------

    private List<String> getVideoList(){
    	List<String> files = new ArrayList<String>();
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
        File[] fileArray = file.listFiles();
        for (int i = 0; i < fileArray.length; i++) {
            if (fileArray[i].isFile()) {
                  System.out.println( fileArray[i].getName());
                  files.add( fileArray[i].getName());
            }
        }

        return files;
    }
}
