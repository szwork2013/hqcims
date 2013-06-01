/*
 * Copyright (c) 2013.
 * whatlookingfor@gmail.com
 */

/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Consumer;
import com.thinkgem.jeesite.modules.cms.entity.Goods;
import com.thinkgem.jeesite.modules.cms.entity.Querys;
import com.thinkgem.jeesite.modules.cms.entity.Returns;
import com.thinkgem.jeesite.modules.cms.service.MybatisService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品Controller
 * @author wharlookingfor
 * @version 2013-05-01
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/main")
public class MybatisController extends BaseController {

	@Autowired
	private MybatisService service;
	
	@ModelAttribute
	public Querys get() {
		return new Querys();
	}

    @RequestMapping(value = "returnlist")
    public String returnList(Querys querys, HttpServletRequest request, HttpServletResponse response, Model model) {
        User currentUser = UserUtils.getUser();
        Long user_id=currentUser.getId();
        querys.setUser_id(user_id);
        Page<Goods> page = service.findReturns(new Page<Goods>(request, response), querys);
        model.addAttribute("page", page);
        Returns carts=service.getReturnsByUser(user_id);
        if(carts!=null){
            model.addAttribute("cart_id", carts.getId());
        }else{
            model.addAttribute("cart_id", -1);
        }

        int cart_num=service.getReturnsCountByUser(user_id);
        model.addAttribute("cart_num", cart_num);
        Long id=1L;
        if(querys.getConsumer_id()>1){
            id=querys.getConsumer_id();
        }
        Consumer consumer=service.queryConsumerById(id);
        querys.setConsumer_name(consumer.getName());
        querys.setConsumer_id(consumer.getId());
        return "modules/cms/addReturns";
    }


	
}
