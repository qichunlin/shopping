package com.lanqiao.shopping.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanqiao.shopping.dao.UserDao;
import com.lanqiao.shopping.dao.impl.UserDaoImpl;
import com.lanqiao.shopping.domain.Users;
import com.lanqiao.shopping.service.UsersService;
import com.lanqiao.shopping.service.impl.UsersServiceImpl;
import com.lanqiao.shopping.utils.MD5Util;
import com.lanqiao.shopping.utils.MyBeanUtils;
import com.lanqiao.shopping.utils.UUIDUtils;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	// ����service����
	UsersService usersService = new UsersServiceImpl();

	// ��ת�û�ע��Ľ���
	public String registerUI(HttpServletRequest request, HttpServletResponse response) {
		return "jsp/register.jsp";// ǰ�治�ܼ�б�� /
	}

	// ��ת�û���¼�Ľ���
	public String loginUI(HttpServletRequest request, HttpServletResponse response) {
		// �ж�Cookie�Ƿ����,���������ֱ�ӽ��е�¼����������¼����(login.jsp)
		Cookie[] cookies = request.getCookies();// Ĭ�ϴ���session ID��cookie autoLogin
		for (int i = 0; i < cookies.length; i++) {
			// ���������û����Զ���¼ �����˺ź�����
			if (cookies[i].getName().equals("autoLogin")) {
				// ����session��username�ı���
				request.getSession().setAttribute("username", cookies[i].getValue().split("#")[0]);// ȡ���û��� session�����ֵ

				// ���뵽��½��Ľ���
				return "/IndexServlet?method=loadProduct";
			}

			// ��ס�û���
			if (cookies[i].getName().equals("remUser")) {
				// ��cookie�л�ȡֵ
				request.getSession().setAttribute("remUser", cookies[i].getValue());
				// ���뵽��½�Ľ��� ���Ǳ������û���
				return "jsp/login.jsp";
			}
		}
		return "jsp/login.jsp";// ǰ�治�ܼ�б�� /
	}

	// �û���¼
	public String userLogin(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");

		String pwd = request.getParameter("password");
		String md5Pwd = MD5Util.string2MD5(pwd);

		Users users = usersService.userLogin(username, md5Pwd);
		// �ж��û��Ƿ�Ϊ��
		if (users != null) {
			// �ж��û��ļ����Ƿ�Ϊ1
			if (users.getState() == 1) {

				// �����û���,session�����򱣴�
				request.getSession().setAttribute("users", users);

				// �ж��Ƿ���Ҫ�Զ���¼
				String autoLogin = request.getParameter("autoLogin");
				if ("yes".equals(autoLogin)) {
					// ����Cookie���� ͨ�����췽�� cookie������ֵ�ַ��� session�Ƕ���
					Cookie ck = new Cookie("autoLogin", users.getUsername() + "#" + users.getPassword());
					// ����cookie��Чʱ��
					ck.setMaxAge(1 * 24 * 60 * 60);// һ�� ����
					// ����Cookie add�������
					response.addCookie(ck);

				}

				// �жϼ�ס�û����Ƿ񱻵��
				String remUser = request.getParameter("remUser");
				if ("yes".equals(remUser)) {
					// ����Cookie���� ͨ�����췽��
					Cookie ck = new Cookie("remUser", users.getUsername());// �û�����Ϣ
					ck.setMaxAge(1 * 24 * 60 * 60);// һ��
					// ����Cookie add�������
					response.addCookie(ck);
				}

				return "/IndexServlet?method=loadProduct";
			} else {
				System.out.println("�û�״̬Ϊ����");
			}
		} else {
			System.out.println("users����Ϊ��");
		}
		return "jsp/info.jsp";
	}

	// �˳���¼
	public String loginOut(HttpServletRequest request, HttpServletResponse response) {
		// ע���û���ʹsessionʧЧ��
		request.getSession().invalidate();
		return "/IndexServlet?method=loadProduct";
	}

	// �û�ע��
	public String userRegister(HttpServletRequest request, HttpServletResponse response) {
		// 1.�������� (��������---->��������) ��һ��������javabean�����class �ڶ��������ǻ�ȡname����ļ�ֵ��
		Users users = MyBeanUtils.populate(Users.class, request.getParameterMap());

		// ����ע�����Ϣ���� ���Ƕ�������ȥ�� (�ǲ��� javabean�е������ж��ٸ�����Ҫ�����ٸ�����)
		users.setUuid(UUIDUtils.getCode());// ����ID
		users.setCode(UUIDUtils.getUUID64());// ������
		users.setState(0);// 0������δ���� 1:������

		// �Ż�����Ϊ���������ݿ���ֱ����ʾΪ�˰�ȫ������Ҫ����
		String pwd = request.getParameter("password");
		String md5Pwd = MD5Util.string2MD5(pwd);
		users.setPassword(md5Pwd);

		// ͨ��ҵ���߼�ȥ����ע�᷽��
		usersService.userRegister(users);
		return "jsp/login.jsp";
	}

	// �û�����
	public String userActive(HttpServletRequest request, HttpServletResponse response) {
		// ����code������ ͨ��������ȥ���û�����
		String code = request.getParameter("code");

		// ͨ��������ȥ�����û�����(��Ҫ����һ���û�����)
		Users user = usersService.userActive(code);

		System.out.println(user);
		// �޸�state�ĵ�״̬ ���ﻹû���޸����ݿ���������� ��Ҫ����jdbc�޸�
		user.setState(1);

		// ����jdbcʵ��״̬�޸� (�����ݿ���������ݿ�)
		usersService.updateUsers(user);// �޸��û�����
		return "jsp/login.jsp";
	}
/*
	// ����UserDao����
	// UserDao userDao = new UserDaoImpl();

	// ͨ��ҵ���߼��� ʵ������ܹ�
	// ����service����
	UsersService usersService = new UsersServiceImpl();

	// �����Ĭ�ϵ��ύ��ʽ�� doGet

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);// ���������doPost����
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ��������
		request.setCharacterEncoding("UTF-8");

		// ע��ҳ���ϵĲ��� /UserServlet?method=registerUI
		String method = request.getParameter("method");
		if (method.equals("registerUI")) {
			// ���ݻ�õĲ�������תע��ҳ��
			request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
		} else if (method.equals("userRegister")) {
			// ͨ�������ع� ��װ��һ������
			userRegister(request);
		} else if (method.equals("userLogin")) {
			// �û���¼
		} else if (method.equals("userActive")) { // �û�����
			// ����code������ ͨ��������ȥ���û�����
			String code = request.getParameter("code");

			// ͨ��������ȥ�����û�����(��Ҫ����һ���û�����)
			Users user = usersService.userActive(code);

			System.out.println(user);
			// �޸�state�ĵ�״̬ ���ﻹû���޸����ݿ���������� ��Ҫ����jdbc�޸�
			user.setState(1);

			// ����jdbcʵ��״̬�޸� (�����ݿ���������ݿ�)
			usersService.updateUsers(user);// �޸��û�����
		}
	}

	// ��װ��һ���û�ע��ķ���������
	public void userRegister(HttpServletRequest request) {
		// ����������򻯽���jspҳ������� 7��input ���� bean������������� ����users����������� MyBeanUtils
		// 1.�������� (��������---->��������) ��һ��������javabean�����class �ڶ��������ǻ�ȡname����ļ�ֵ�� Users
		users = MyBeanUtils.populate(Users.class, request.getParameterMap());

		// ����ע�����Ϣ���� ���Ƕ�������ȥ�� (�ǲ��� javabean�е������ж��ٸ�����Ҫ�����ٸ�����)
		users.setUuid(UUIDUtils.getCode());// ����ID
		users.setCode(UUIDUtils.getUUID64());// ������
		users.setState(0);// 0������δ���� 1:������

		// ͨ��ҵ���߼�ȥ����
		usersService.userRegister(users);

		// ����һ�����û������ע����ύ��ť��ʱ��ͻ��ҳ��Ĳ���
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");

		// �������� ����õ����ݰ���Users��������
		Users users = new Users();
		// users.setUuid("halouqqq"); //���� �ֶ�����
		// ת�����������
		users.setUuid(UUIDUtils.getCode());

		users.setUsername(username);
		users.setPassword(password);
		users.setName(name);
		users.setEmail(email);
		users.setTelephone(telephone);
		users.setBirthday(birthday);
		users.setSex(sex);
		users.setState(0);// �˺��Ƿ񼤻� Ĭ����0

		// users.setCode("qqqwert");//������ �ֶ����ɵļ����� //�Ż�֮��� ���Զ����ɵ�
		users.setCode(UUIDUtils.getUUID64());

		// ����ע��ķ����������� //userDao.register(users); //ͨ��ҵ���߼�ȥ���� ���Գɹ�
		usersService.userRegister(users);

	}
*/	

}
