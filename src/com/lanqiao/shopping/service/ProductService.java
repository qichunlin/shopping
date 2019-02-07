package com.lanqiao.shopping.service;

import java.util.List;

import com.lanqiao.shopping.domain.Product;
import com.lanqiao.shopping.utils.PageUtils;

public interface ProductService {
	//����id���Ҷ�Ӧ����Ʒ��������Ϣ
	public List<Product> findProductByCid(String cid) throws Exception;
	
	//������ҳ����  �����Ǽ��ϵ�  ���ڻ��ɹ�����
	PageUtils findProductByCidWithPage(String cid,int curPageNo)throws Exception;
	
	//����������Ʒ
	public List<Product> findHot() throws Exception;

	//����pid������ָ����Ʒ�Ķ���
	public Product findProductByPid(String pid) throws Exception;
	//����������Ʒ
	public List<Product> findNews() throws Exception;

}
