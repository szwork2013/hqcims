/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.cms.dao.BalanceDao;
import com.thinkgem.jeesite.modules.cms.dao.CollectingDao;
import com.thinkgem.jeesite.modules.cms.dao.ReceivableDao;
import com.thinkgem.jeesite.modules.cms.entity.Balance;
import com.thinkgem.jeesite.modules.cms.entity.Collecting;
import com.thinkgem.jeesite.modules.cms.entity.Receivable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 欠款Service
 * @author wharlookingfor
 * @version 2013-05-18
 */
@Component
@Transactional(readOnly = true)
public class BalanceService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(BalanceService.class);
	
	@Autowired
	private BalanceDao balanceDao;
    @Autowired
    private ReceivableDao receivableDao;
    @Autowired
    private CollectingDao collectingDao;
	
	
	public Balance get(Long id) {
		return balanceDao.findOne(id);
	}

	
	@Transactional(readOnly = false)
	public void save(Balance balance) {
		balanceDao.save(balance);
	}

	@Transactional(readOnly = false)
    public void addBalance(Long id) {
        Receivable receivable=receivableDao.findOne(id);
        //新增空的实收数据
        Collecting c=new Collecting();
        c.setAmount1(0);
        c.setAmount(0);
        c.setReceivable(receivable);
        c.setConsumer(receivable.getConsumer());

        c.setFlag(0);
        collectingDao.save(c);
        //新增欠款数据
        Balance b=new Balance();
        b.setConsumer(receivable.getConsumer());
        b.setAmount(receivable.getAmount());
        balanceDao.save(b);
        //应收状态的改变
        receivable.setStatus(1);
        receivableDao.save(receivable);
    }
}
