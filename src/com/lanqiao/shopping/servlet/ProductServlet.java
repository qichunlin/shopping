package com.lanqiao.shopping.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanqiao.shopping.domain.Product;
import com.lanqiao.shopping.service.ProductService;
import com.lanqiao.shopping.service.impl.ProductServiceImpl;
import com.lanqiao.shopping.utils.PageUtils;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//����ҵ������
	ProductService productService = new ProductServiceImpl();
	
	//����pid����ָ������Ʒ����
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.��������
		String pid = request.getParameter("pid");
		//2.����ҵ��
		Product product = productService.findProductByPid(pid);
		System.out.println(product);
		
		//3.��������   �����򱣴�     session�˷ѿռ�   ���Ǻܷ���
		request.setAttribute("product", product);
		//4.��תҳ��
		return "jsp/product_info.jsp";
	}
	

	
	//���ҳ
	public String findProductByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			//��ȡcid����
			String cid = request.getParameter("cid");
			//��ȡnum����
			int num  = Integer.parseInt(request.getParameter("num"));
			
			//����service���еķ�ҳ����
			PageUtils page = productService.findProductByCidWithPage(cid, num);
			System.out.println(page.getList().size());
			
			//����request����    ֻ����page����
			request.setAttribute("page", page);	
			
			return "jsp/product_list.jsp";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	//������Ʒ����id δ��ҳ
	public String findProductByCid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			//��ȡcid
			String cid = request.getParameter("cid");
			//����ҵ���߼���
			List<Product> pList = productService.findProductByCid(cid);
			System.out.println(pList.size());
			//System.out.println(pList.get(1).getPIMAGE());
			
			//��������
			request.setAttribute("pList", pList);
			//��תҳ��
			return "jsp/product_list.jsp";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}
}
