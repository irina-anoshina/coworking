package com.regr.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * Created by maratische on 22.03.16.
 */
@Controller
@RequestMapping({"/", "/index"})
public class IndexController  {

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ParkingService parkingService;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(HttpServletRequest request, ModelMap modelMap) {
        UUID uuid = (UUID) request.getSession().getAttribute("userId");
//        if(uuid!=null){
//            modelMap.put("user", userService.getUser(uuid));
//        }

        return "index";
    }

}
