package com.websystique.springmvc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import com.google.gson.Gson;
import com.websystique.springmvc.model.GroupDet;


@Controller
@SessionAttributes("vlist")
public class HelloController {
	// @Autowired
   //public GroupdetValidator groupdetValidator;
	 HashMap<Object, Object> map2 = new HashMap<Object, Object>();
       FileWriter fw = null;    
	   File file = null;
	   String fname = "c:\\cs6711\\Magnus";
	 boolean error;
	 String s1 = null;
	   List <GroupDet> grplist ;
	   List <GroupDet> grplist1 ;
	   FileReader fileReader ;
		BufferedReader bufferedReader ;
		StringBuffer stringBuffer;
			
   @RequestMapping(value = "/groupdet", method = RequestMethod.GET)
   public String groupdet(Map model,WebRequest request, SessionStatus status) {
	   status.setComplete();
	   request.removeAttribute("vlist", WebRequest.SCOPE_SESSION);
	   GroupDet groupdet =  new GroupDet();
	   model.put("groupdet", groupdet);
       return "groupdet";
   }
    
  
 @SuppressWarnings("unchecked")
 @RequestMapping(value = "/groupdet", method = RequestMethod.POST)
    public ModelAndView addGroupdet(@Valid @ModelAttribute("groupdet") GroupDet groupdet,BindingResult result, ModelMap model, 
    		HttpSession session)
   {
	   grplist1  = new ArrayList<GroupDet>();
	   grplist = new ArrayList<GroupDet>();
	   if (grplist1.size() > 0)
	   {
	   grplist1.clear();
	   }
	   System.out.println("Session value" + session.getAttribute("vlist"));
	 if (session.getAttribute("vlist") == null || session.getAttribute("vlist").equals(""))
	 {
		model.remove("vlist");
	 }  
	 else
	 {
		 grplist = (List<GroupDet>) session.getAttribute("vlist");
			
	 }
        	   	   
     if(result.hasErrors()){
    	  System.out.println("Has Errors");
      	return new ModelAndView("groupdet");
      }     
      grplist1.addAll(grplist);
      grplist1.add(groupdet);
      grplist1.forEach((n)->System.out.println(n));
      grplist.forEach((n)->System.out.println(n));
      groupdet =  new GroupDet(groupdet.getGname(),groupdet.getVdate());    
      ModelAndView mnv = new ModelAndView("groupdet");
      model.addAttribute("groupdet", groupdet);
      mnv.addObject("vlist", grplist1);
      mnv.addObject("gname",groupdet.getGname());
      mnv.addObject("vdate",groupdet.getVdate());
      return mnv;
   }
 
 @ResponseBody
 @ResponseStatus(HttpStatus.OK)
 @RequestMapping(value = "/submitgroup", method =  RequestMethod.POST)
  public String submitGroup(Map model, HttpSession session)
   {
	Gson gson = new Gson();
	if (session.getAttribute("vlist") == null || session.getAttribute("vlist").equals(""))
	{  
		  return "Please add volunteer list before submitting the group - http://localhost:8080/MagnusProject/groupdet ";
	}
	List<GroupDet> grouplist = (List<GroupDet>) session.getAttribute("vlist");
	 try{
	  UUID uniqueKey = UUID.randomUUID();
      System.out.println (uniqueKey);
      file = new File(fname+uniqueKey+".log");
      System.out.println("File name" + file);
       if (!file.exists()) {
		   if(!file.isDirectory())
		   {    file.getParentFile().mkdir();
		   }
		    file.createNewFile();
	   			}
  			fw = new FileWriter(file.getAbsoluteFile(), true);
  			for (GroupDet temp : grouplist) {
  				System.out.println(temp);
  			  String jsonInString = gson.toJson(temp);
	   		//	s1 = "- " + groupdet.getFname() + " " + groupdet.getLname() + " - " + groupdet.getAddress() + " " +  groupdet.getCity() + " - " 
	   			//		+  groupdet.getState() + " " + groupdet.getZip() + System.getProperty("line.separator");
  			
	   			
	   			fw.write(jsonInString);
  			}
	      }
	      catch(IOException ex)
	      {
	    	  ex.printStackTrace();
	    	  return  "Exception occured while saving the file" + " " + HttpStatus.BAD_REQUEST ;
	      }
	      
	      finally
	      {
	    	  
	 			try
	 			{
	 			fw.flush();
	 			fw.close();
	 			
	 			}
	 			catch(Exception ex)
	 		      {
	 		    	  ex.printStackTrace();
	 		      }
	      }
	     session.invalidate();
	      return  HttpStatus.OK + " " + "Saved Successful" ;
 }
 
 @ExceptionHandler(NotFoundException.class)
 @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Not Found")
 void handleNotFound(NotFoundException exc)
 {
	 
 }
 class NotFoundException extends RuntimeException
 {
	 private static final long serialVersionUID = 1L;
 }
   
 }

