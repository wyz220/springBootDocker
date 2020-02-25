package com.wyz.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyz.entity.WxUser;
import com.wyz.service.IShoeService;
import com.wyz.service.IWxUserService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-02-14
 */
@RestController
@RequestMapping("/shoe")
public class ShoeController {

	
	@Autowired
    IShoeService shoe;

	@Autowired
	IWxUserService userService;

	
    
    @RequestMapping("/list")
    @ResponseBody
    public List<WxUser> list(@RequestParam(defaultValue = "0") Integer type) {
        //insert
    	List<WxUser> data = new ArrayList<WxUser>();
    	
    	try {
    		
    		data = this.userService.list();
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return data;
    }
    
    @RequestMapping("/page")
    @ResponseBody
    public IPage<WxUser> page(HttpServletRequest request) {
        //insert
    	List<WxUser> data = new ArrayList<WxUser>();
    	
    	try {
    		
            
    		QueryWrapper<WxUser> wrapper = new QueryWrapper();
    		Page<WxUser> page = new Page<WxUser>(1,2);
    		
    		return userService.page(page, wrapper);
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return null;
    }

    
}
