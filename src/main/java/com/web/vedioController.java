package com.web;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.BaseDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016-03-27.
 */
@Controller
public class vedioController {

    @Autowired
    BaseDao bd;


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

    @RequestMapping("/login")
    public String login(ModelMap model,HttpServletRequest request,HttpSession session){
    	
    	String userName = request.getParameter("userName");
    	String passWord = request.getParameter("passWord");
    	
    	if("1".equals(userName)||"1".equals(passWord)){
    		session.setAttribute("userName", userName);
    		session.setAttribute("gold", "-1");
    		
            model.addAttribute("userName", userName);
            model.addAttribute("gold", "-1");
    		
            List filesList = null;
            if(null == session.getAttribute("files") ||"".equals(session.getAttribute("files"))){
            	filesList = getVideoList();
                session.setAttribute("files", filesList);
                model.addAttribute("files", filesList);
            }else{
            	model.addAttribute("files", session.getAttribute("files"));
            }
            
    		return  "main";
    	}else{
    		return "login";
    	}
    }


    private List<String> getVideoList(){
    	List<String> files = new ArrayList<String>();
    	
    	File file = new File("D:\\eclipse_workspace\\video\\WebContent");
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
