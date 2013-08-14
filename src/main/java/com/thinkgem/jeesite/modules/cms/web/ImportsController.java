/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.cms.entity.Consumer;
import com.thinkgem.jeesite.modules.cms.entity.Goods;
import com.thinkgem.jeesite.modules.cms.entity.Imports;
import com.thinkgem.jeesite.modules.cms.service.ConsumerService;
import com.thinkgem.jeesite.modules.cms.service.GoodsService;
import com.thinkgem.jeesite.modules.cms.service.ImportsService;

/**
 * 进货管理Controller
 * @author wharlookingfor
 * @version 2013-06-01
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/imports")
public class ImportsController extends BaseController {

	@Autowired
	private ImportsService importsService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ConsumerService consumerService;
	
	@ModelAttribute
	public Imports get(@RequestParam(required=false) Long id) {
		if (id != null){
			return importsService.get(id);
		}else{
			return new Imports();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Imports imports, HttpServletRequest request, HttpServletResponse response, Model model) {

        Page<Imports> page = importsService.find(new Page<Imports>(request, response), imports); 
        model.addAttribute("page", page);
		return "modules/cms/importsList";
	}


	@RequestMapping(value = "form")
	public String form(Imports imports, Model model) {
		model.addAttribute("imports", imports);
		return "modules/cms/importsForm";
	}
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "进货导入模板.xlsx";
    		List<Imports> list = Lists.newArrayList();
    		Imports imports=new Imports();
    		list.add(imports);
    		new ExportExcel("进货数据", Imports.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.ADMIN_PATH+"/cms/imports/?repage";
    }
    

    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Imports> list = ei.getDataList(Imports.class);
			User currentUser = UserUtils.getUser();
			for (Imports imports : list){
				try{
					System.out.println(imports.getConsumer()+"ddddddddddddddddd");
						Consumer consumer=consumerService.get(imports.getConsumer_id());
						System.out.println(consumer.getName()+"dddddddddddd");
						Goods goods=goodsService.get(imports.getGoods_id());
						if(imports.getSale()==null&&imports.getPurchase()!=null&&imports.getRate()!=null){
							imports.setSale(imports.getPurchase()*(1+imports.getRate()));
						}
						int num=goods.getNum();
						goods.setNum(num+imports.getNum());
						goods.setPurchase(imports.getPurchase());
						goods.setRate(imports.getRate());
						goods.setSale(imports.getSale());
						goodsService.save(goods);
						
						System.out.println(imports.toString());
						imports.setConsumer(consumer);
						imports.setGoods(goods);
						imports.setUser(currentUser);
						BeanValidators.validateWithException(validator, imports);
						importsService.save(imports);
						successNum++;
					
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>商品 "+imports.getId()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					failureNum++;
					for (String message : messageList){
						failureMsg.append(message+"; ");
						
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>商品名 "+imports.getId()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条数据，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条数据"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.ADMIN_PATH+"/cms/imports/?repage";
    }
    
    

	@RequestMapping(value = "save")
	public String save(Imports imports, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, imports)){
			return form(imports, model);
		}
		importsService.save(imports);
		return "redirect:"+Global.ADMIN_PATH+"/cms/imports/?repage";
	}
	

	@RequestMapping(value = "delete")
	public String delete(Long id, RedirectAttributes redirectAttributes) {
		importsService.delete(id);
		addMessage(redirectAttributes, "删除进货管理成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/imports/?repage";
	}

}
