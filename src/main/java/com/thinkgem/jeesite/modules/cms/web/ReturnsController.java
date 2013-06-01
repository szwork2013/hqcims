/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.web;


import com.thinkgem.jeesite.modules.cms.entity.*;
import com.thinkgem.jeesite.modules.cms.service.ReturnListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.cms.service.ReturnsService;

import java.util.List;

/**
 * 退货车Controller
 * @author wharlookingfor
 * @version 2013-05-31
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/returns")
public class ReturnsController extends BaseController {

	@Autowired
	private ReturnsService returnsService;
    @Autowired
    private ReturnListService returnListService;
	
	@ModelAttribute
	public Returns get(@RequestParam(required=false) Long id) {
		if (id != null){
			return returnsService.get(id);
		}else{
			return new Returns();
		}
	}

    /**
     *
     * 退货车的查询
     * @param model
     * @param redirectAttributes
     * @return
     */
	@RequestMapping(value = {"list", ""})
	public String list(Model model, RedirectAttributes redirectAttributes) {
        User currentUser = UserUtils.getUser();
        Returns result= returnsService.getReturnsByUser(currentUser.getId());
        if(result!=null){
            List<ReturnList>  list= returnsService.findList(currentUser.getId());
            if(list.size()>0){
                model.addAttribute("list", list);
                model.addAttribute("returns", result);
                return "modules/cms/returnsList";
            }else{
                addMessage(redirectAttributes, "页面加载失败,当前没有退货信息,自动跳转至退货管理");
                return "redirect:"+Global.ADMIN_PATH+"/cms/main/returnlist";
            }
        }else{
            addMessage(redirectAttributes, "页面加载失败,当前没有退货信息,自动跳转至退货管理");
            return "redirect:"+Global.ADMIN_PATH+"/cms/main/returnlist";
        }

	}


    /**
     *
     * 退货车的添加数据
     * @param goods_ids
     * @param sales
     * @param nums
     * @param consumer_id
     * @return
     */
    @ResponseBody
	@RequestMapping(value = "save")
    public String save(String goods_ids,String sales,String nums,Long consumer_id) {

        try {
            User currentUser = UserUtils.getUser();
            Returns result=returnsService.getReturnsByUser(currentUser.getId());
            Returns returns=new Returns();
            if(result!=null){
                returns=result;
                if(consumer_id!=result.getConsumer().getId()){
                    Consumer consumer=new Consumer();
                    consumer.setId(consumer_id);
                    returns.setConsumer(consumer);
                    returns=returnsService.save(returns);
                }
            }else{
                Consumer consumer=new Consumer();
                consumer.setId(consumer_id);
                returns.setConsumer(consumer);
                returns.setUser_id(currentUser.getId());
                returns=returnsService.save(returns);
            }
            goods_ids=goods_ids.substring(0, goods_ids.length()-1);
            sales=sales.substring(0, sales.length()-1);
            nums=nums.substring(0, nums.length()-1);
            String[] good_=goods_ids.split("@");
            String[] sale_=sales.split("@");
            String[] num_=nums.split("@");

            for(int i=0;i<good_.length;i++){
                long goods_id=Long.parseLong(good_[i]);
                float sale=Float.parseFloat(sale_[i]);
                int num=Integer.parseInt(num_[i]);
                ReturnList list=new ReturnList();
                Goods good=new Goods();
                good.setId(goods_id);
                list.setGoods(good);
                list.setNum(num);
                list.setSale(sale);
                list.setReturns(returns);
                returnListService.save(list);
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "fail";
        }
        return "success";
	}


    /**
     * @Title: saveList
     * @author lookingfor
     * @Description:退货车信息的修改保存
     * @param ids
     * @param sales
     * @param nums
     * @param consumer_id
     * @param id
     * @return   
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "saveList")
    public String saveList(String ids,String sales,String nums,Long consumer_id,Long id) {

        try {
            User currentUser = UserUtils.getUser();
            Returns returns=new Returns();
            Consumer consumer=new Consumer();
            consumer.setId(consumer_id);
            returns.setConsumer(consumer);
            returns.setUser_id(currentUser.getId());
            returns.setId(id);
            returnsService.save(returns);
            if(ids.length()>0){

                ids=ids.substring(0, ids.length()-1);
                sales=sales.substring(0, sales.length()-1);
                nums=nums.substring(0, nums.length()-1);
                String[] id_=ids.split("@");
                String[] sale_=sales.split("@");
                String[] num_=nums.split("@");
                for(int i=0;i<id_.length;i++){
                    long detail_id=Long.parseLong(id_[i]);
                    float sale=Float.parseFloat(sale_[i]);
                    int num=Integer.parseInt(num_[i]);
                    ReturnList list=new ReturnList();
                    list.setId(detail_id);
                    list.setNum(num);
                    list.setSale(sale);
                    list.setReturns(returns);
                    returnListService.update(list);
                }

            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

    /**
     * 退货车明细数据的删除
     * @param id
     * @param redirectAttributes
     * @return
     */
	@RequestMapping(value = "deleteDetail")
	public String deleteDetail(Long id, RedirectAttributes redirectAttributes) {
        returnListService.delete(id);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.ADMIN_PATH+"/modules/cms/returns/?repage";
	}

    /**
     * 清空退货车
     * @param id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "deleteAll")
    public String deleteAll(Long id, RedirectAttributes redirectAttributes) {
        returnsService.deleteAll(id);
        addMessage(redirectAttributes, "操作成功");
        return "redirect:"+Global.ADMIN_PATH+"/cms/main/returnlist";
    }

    /**
     * 退货数据的保存
     * @param id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "saveOrder")
    public String saveOrder(Long id,RedirectAttributes redirectAttributes){
        User currentUser = UserUtils.getUser();
        returnsService.saveOrder(id,currentUser);
        addMessage(redirectAttributes, "操作成功");
        return "redirect:"+Global.ADMIN_PATH+"/cms/main/returnlist";
    }

}
