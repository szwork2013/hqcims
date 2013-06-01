/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.cms.service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.cms.dao.*;
import com.thinkgem.jeesite.modules.cms.entity.*;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 退货车Service
 * @author wharlookingfor
 * @version 2013-05-31
 */
@Component
@Transactional(readOnly = true)
public class ReturnsService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ReturnsService.class);
	
	@Autowired
	private ReturnsDao returnsDao;
    @Autowired
    private ReturnListDao returnListDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderListDao orderListDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private ReceivableDao receivableDao;
    @Autowired
    private CollectingDao collectingDao;
	
	public Returns get(Long id) {
		return returnsDao.findOne(id);
	}

	@Transactional(readOnly = false)
	public Returns save(Returns returns) {
		Returns returns1=returnsDao.save(returns);
        return returns1;
	}

	
	@Transactional(readOnly = false)
	public void deleteAll(Long id) {
        returnListDao.deleteByPid(id);
		returnsDao.deleteById(id);
	}


    /**
     * @Title: get
     * @author lookingfor
     * @Description: 退物车的查询
     * @return   
     * @throws
     */
    public Returns getReturnsByUser(Long user_id) {
        String sql="select * from cms_return where del_flag=0 and user_id="+user_id+" order by create_date desc";
        List<Returns> list=returnsDao.findBySql(sql);
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }

    }



    /**
     * @Title: get
     * @author lookingfor
     * @Description: 退物车的查询
     * @return   
     * @throws
     */
    public Long getReturnsCountByUser(Long user_id) {
        Long num=returnListDao.getCountReturns(user_id);
        return num;

    }

    public List<ReturnList> findList(Long user_id) {
        String sql="select d.* from cms_return_detail d,cms_return r\n" +
                   "  where r.user_id="+user_id+" and r.id=d.return_id\n" +
                   "  and r.del_flag=0";
        List<ReturnList> list=returnListDao.findBySql(sql);
        return list;
    }


    public void saveOrder(Long id, User user) {
        //第一步 根据ID从购物车查询数据
        Returns returns=returnsDao.findOne(id);
        //往订单中新增数据，并修改物品数量
        float total=0;
        Order order=new Order();
        order.setConsumer(returns.getConsumer());
        order.setStatus(2);
        order.setType(1);
        order.setUser(user);
        order =orderDao.save(order);
        List<ReturnList> childList=returns.getChildList();
        for(int i=0;i<childList.size();i++){
            ReturnList cartList=childList.get(i);
            OrderList child=new OrderList();
            child.setGoods(cartList.getGoods());
            child.setNum(cartList.getNum());
            child.setOrder(order);
            child.setSale(0-cartList.getSale());
            child=orderListDao.save(child);
            total+=child.getNum()*child.getSale();
            //修改物品的存量
            Goods good=child.getGoods();
            int num=good.getNum();
            good.setNum(num+child.getNum());
            good.setUpdate_date(new Date());
            goodsDao.save(good);

        }

        //修改订单总金额
        order.setTotal(total);
        orderDao.updateTotal(order.getId());
        //进入应收
        Receivable receivable=new Receivable();
        receivable.setAmount(order.getTotal());
        receivable.setConsumer(order.getConsumer());
        receivable.setOrder(order);
        receivable.setStatus(1);
        receivable=receivableDao.save(receivable);

        //更新实收
        Collecting c=new Collecting();
        c.setAmount1(0);
        c.setReceivable(receivable);
        c.setConsumer(receivable.getConsumer());
        c.setAmount(receivable.getAmount());
        c.setFlag(2);
        collectingDao.save(c);

        //第三步 清空购物车
        returnListDao.deleteByPid(id);
        returnsDao.deleteById(id);

    }
}
