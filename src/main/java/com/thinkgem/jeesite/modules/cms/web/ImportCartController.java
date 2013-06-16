/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.cms.entity.Collecting;
import com.thinkgem.jeesite.modules.cms.entity.Consumer;
import com.thinkgem.jeesite.modules.cms.entity.Count;
import com.thinkgem.jeesite.modules.cms.entity.Goods;
import com.thinkgem.jeesite.modules.cms.entity.ImportCart;
import com.thinkgem.jeesite.modules.cms.entity.ImportList;
import com.thinkgem.jeesite.modules.cms.entity.OrderList;
import com.thinkgem.jeesite.modules.cms.entity.ReturnList;
import com.thinkgem.jeesite.modules.cms.entity.Returns;
import com.thinkgem.jeesite.modules.cms.service.CountService;
import com.thinkgem.jeesite.modules.cms.service.ImportCartService;
import com.thinkgem.jeesite.modules.cms.service.ImportListService;
import com.thinkgem.jeesite.modules.cms.service.ReturnsService;

/**
 * 统计Controller
 * @author wharlookingfor
 * @version 2013-06-16
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/importCart")
public class ImportCartController extends BaseController {

	@Autowired
	private ImportCartService service;
	@Autowired
	private ImportListService listService;

	@ModelAttribute
	public ImportCart get(@RequestParam(required=false) Long id) {
		if (id != null){
			return service.get(id);
		}else{
			return new ImportCart();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Model model, RedirectAttributes redirectAttributes) {
        User currentUser = UserUtils.getUser();
        ImportCart result= service.getImportCartByUser(currentUser.getId());
        if(result!=null){
            List<ImportList>  list= service.findList(currentUser.getId());
            if(list.size()>0){
                model.addAttribute("list", list);
                model.addAttribute("returns", result);
                return "modules/cms/importCartList";
            }else{
                addMessage(redirectAttributes, "页面加载失败,当前没有进货信息,自动跳转至进货导入");
                return "redirect:"+Global.ADMIN_PATH+"/cms/main/importlist";
            }
        }else{
            addMessage(redirectAttributes, "页面加载失败,当前没有进货信息,自动跳转至进货导入");
            return "redirect:"+Global.ADMIN_PATH+"/cms/main/importlist";
        }

	}
	
	
    @ResponseBody
	@RequestMapping(value = "save")
    public String save(String goods_ids,String sales,String nums,Long consumer_id,String purchases,String rates) {

        try {
            User currentUser = UserUtils.getUser();
            ImportCart result=service.getImportCartByUser(currentUser.getId());
            ImportCart returns=new ImportCart();
            if(result!=null){
                returns=result;
                if(consumer_id!=result.getConsumer().getId()){
                    Consumer consumer=new Consumer();
                    consumer.setId(consumer_id);
                    returns.setConsumer(consumer);
                    returns=service.save(returns);
                }
            }else{
                Consumer consumer=new Consumer();
                consumer.setId(consumer_id);
                returns.setConsumer(consumer);
                returns.setUser_id(currentUser.getId());
                returns=service.save(returns);
            }
            goods_ids=goods_ids.substring(0, goods_ids.length()-1);
            sales=sales.substring(0, sales.length()-1);
            nums=nums.substring(0, nums.length()-1);
            purchases=purchases.substring(0, purchases.length()-1);
            rates=rates.substring(0, rates.length()-1);
            String[] good_=goods_ids.split("@");
            String[] sale_=sales.split("@");
            String[] num_=nums.split("@");
            String[] rate_=rates.split("@");
            String[] purchase_=purchases.split("@");
            
            for(int i=0;i<good_.length;i++){
                long goods_id=Long.parseLong(good_[i]);
                float sale=Float.parseFloat(sale_[i]);
                float purchase=Float.parseFloat(purchase_[i]);
                float rate=Float.parseFloat(rate_[i]);
                int num=Integer.parseInt(num_[i]);
                ImportList list=new ImportList();
                Goods good=new Goods();
                good.setId(goods_id);
                list.setGoods(good);
                list.setNum(num);
                list.setSale(sale);
                list.setRate(rate);
                list.setPurchase(purchase);
                list.setImportCart(returns);
                listService.save(list);
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "fail";
        }
        return "success";
	}

    
    @ResponseBody
    @RequestMapping(value = "saveList")
    public String saveList(String ids,String sales,String nums,Long consumer_id,Long id,String purchases,String rates) {

        try {
            User currentUser = UserUtils.getUser();
            ImportCart returns=new ImportCart();
            Consumer consumer=new Consumer();
            consumer.setId(consumer_id);
            returns.setConsumer(consumer);
            returns.setUser_id(currentUser.getId());
            returns.setId(id);
            service.save(returns);
            if(ids.length()>0){

                ids=ids.substring(0, ids.length()-1);
                sales=sales.substring(0, sales.length()-1);
                nums=nums.substring(0, nums.length()-1);
                String[] id_=ids.split("@");
                String[] sale_=sales.split("@");
                String[] num_=nums.split("@");
                String[] rate_=rates.split("@");
                String[] purchase_=purchases.split("@");
                for(int i=0;i<id_.length;i++){
                    long detail_id=Long.parseLong(id_[i]);
                    float sale=Float.parseFloat(sale_[i]);
                    int num=Integer.parseInt(num_[i]);
                    float purchase=Float.parseFloat(purchase_[i]);
                    float rate=Float.parseFloat(rate_[i]);
                    ImportList list=new ImportList();
                    list.setId(detail_id);
                    list.setNum(num);
                    list.setSale(sale);
                    list.setRate(rate);
                    list.setPurchase(purchase);
                    list.setImportCart(returns);
                    listService.update(list);
                }

            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

    

    @RequestMapping(value = "deleteAll")
    public String deleteAll(Long id, RedirectAttributes redirectAttributes) {
    	service.deleteAll(id);
        addMessage(redirectAttributes, "操作成功");
        return "redirect:"+Global.ADMIN_PATH+"/cms/main/importlist";
    }
    
	@RequestMapping(value = "deleteDetail")
	public String deleteDetail(Long id, RedirectAttributes redirectAttributes) {
		listService.delete(id);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/importCart/?repage";
	}
	
    /**
     * 进货数据的保存
     * @param id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "saveOrder")
    public String saveOrder(Long id,RedirectAttributes redirectAttributes){
        User currentUser = UserUtils.getUser();
        service.saveOrder(id,currentUser);
        addMessage(redirectAttributes, "操作成功");
        return "redirect:"+Global.ADMIN_PATH+"/cms/main/importlist";
    }
    

}
