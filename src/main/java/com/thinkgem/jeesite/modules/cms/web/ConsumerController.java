/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
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
import com.thinkgem.jeesite.common.utils.PinyinUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Consumer;
import com.thinkgem.jeesite.modules.cms.entity.Goods;
import com.thinkgem.jeesite.modules.cms.service.ConsumerService;
import com.thinkgem.jeesite.modules.sys.utils.ToolsUtils;

/**
 * 客户维护Controller
 * @author wharlookingfor
 * @version 2013-04-30
 */
@Controller
@RequestMapping(value = Global.ADMIN_PATH+"/cms/consumer")
public class ConsumerController extends BaseController {

	@Autowired
	private ConsumerService consumerService;
	
	@ModelAttribute
	public Consumer get(@RequestParam(required=false) Long id) {
		if (id != null){
			return consumerService.get(id);
		}else{
			return new Consumer();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Consumer consumer, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Consumer> page = consumerService.find(new Page<Consumer>(request, response), consumer); 
        model.addAttribute("page", page);
		return "modules/cms/consumerList";
	}

	@RequestMapping(value = "selectList")
	public String selectList(Consumer consumer, HttpServletRequest request, HttpServletResponse response, Model model) {
        list(consumer, request, response, model);
		return "modules/cms/consumerSelectList";
	}
	

	@RequestMapping(value = "form")
	public String form(Consumer consumer, Model model) {
		model.addAttribute("consumer", consumer);
		return "modules/cms/consumerForm";
	}

	@RequestMapping(value = "save")
	public String save(Consumer consumer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, consumer)){
			return form(consumer, model);
		}
		consumerService.save(consumer);
		addMessage(redirectAttributes, "保存客户'" + consumer.getName() + "'成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/consumer/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(Long id, RedirectAttributes redirectAttributes) {
		consumerService.delete(id);
		addMessage(redirectAttributes, "删除客户成功");
		return "redirect:"+Global.ADMIN_PATH+"/cms/consumer/?repage";
	}

    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Consumer> list = ei.getDataList(Consumer.class);
			for (Consumer consumer : list){
				try{
					if (StringUtils.isBlank(consumer.getCode())&&StringUtils.isNotBlank(consumer.getName())) {
						consumer.setCode(PinyinUtils.getPinYinHeadChar(consumer.getName()));
					}
						BeanValidators.validateWithException(validator, consumer);
						consumerService.save(consumer);
						successNum++;
					
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>客户名 "+consumer.getName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					failureNum++;
					for (String message : messageList){
						failureMsg.append(message+"; ");
						
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>客户名 "+consumer.getName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条数据，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条数据"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.ADMIN_PATH+"/cms/consumer/?repage";
    }
	
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "客户数据导入模板.xlsx";
    		List<Consumer> list = Lists.newArrayList();
    		Consumer goods=new Consumer();
    		list.add(goods);
    		new ExportExcel("客户数据", Consumer.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.ADMIN_PATH+"/cms/consumer/?repage";
    }
    
}
