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
	// 创建service对象
	UsersService usersService = new UsersServiceImpl();

	// 跳转用户注册的界面
	public String registerUI(HttpServletRequest request, HttpServletResponse response) {
		return "jsp/register.jsp";// 前面不能加斜干 /
	}

	// 跳转用户登录的界面
	public String loginUI(HttpServletRequest request, HttpServletResponse response) {
		// 判断Cookie是否存在,如果存在则直接进行登录，否则进入登录界面(login.jsp)
		Cookie[] cookies = request.getCookies();// 默认存在session ID的cookie autoLogin
		for (int i = 0; i < cookies.length; i++) {
			// 遍历查找用户的自动登录 保存账号和密码
			if (cookies[i].getName().equals("autoLogin")) {
				// 设置session的username的保存
				request.getSession().setAttribute("username", cookies[i].getValue().split("#")[0]);// 取到用户名 session保存的值

				// 进入到登陆后的界面
				return "/IndexServlet?method=loadProduct";
			}

			// 记住用户名
			if (cookies[i].getName().equals("remUser")) {
				// 从cookie中获取值
				request.getSession().setAttribute("remUser", cookies[i].getValue());
				// 进入到登陆的界面 但是保存了用户名
				return "jsp/login.jsp";
			}
		}
		return "jsp/login.jsp";// 前面不能加斜干 /
	}

	// 用户登录
	public String userLogin(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");

		String pwd = request.getParameter("password");
		String md5Pwd = MD5Util.string2MD5(pwd);

		Users users = usersService.userLogin(username, md5Pwd);
		// 判断用户是否为空
		if (users != null) {
			// 判断用户的激活是否为1
			if (users.getState() == 1) {

				// 保存用户名,session作用域保存
				request.getSession().setAttribute("users", users);

				// 判断是否需要自动登录
				String autoLogin = request.getParameter("autoLogin");
				if ("yes".equals(autoLogin)) {
					// 创建Cookie对象 通过构造方法 cookie操作的值字符串 session是对象
					Cookie ck = new Cookie("autoLogin", users.getUsername() + "#" + users.getPassword());
					// 设置cookie有效时间
					ck.setMaxAge(1 * 24 * 60 * 60);// 一天 毫秒
					// 保存Cookie add方法添加
					response.addCookie(ck);

				}

				// 判断记住用户名是否被点击
				String remUser = request.getParameter("remUser");
				if ("yes".equals(remUser)) {
					// 创建Cookie对象 通过构造方法
					Cookie ck = new Cookie("remUser", users.getUsername());// 用户名信息
					ck.setMaxAge(1 * 24 * 60 * 60);// 一天
					// 保存Cookie add方法添加
					response.addCookie(ck);
				}

				return "/IndexServlet?method=loadProduct";
			} else {
				System.out.println("用户状态为激活");
			}
		} else {
			System.out.println("users对象为空");
		}
		return "jsp/info.jsp";
	}

	// 退出登录
	public String loginOut(HttpServletRequest request, HttpServletResponse response) {
		// 注销用户，使session失效。
		request.getSession().invalidate();
		return "/IndexServlet?method=loadProduct";
	}

	// 用户注册
	public String userRegister(HttpServletRequest request, HttpServletResponse response) {
		// 1.拷贝数据 (接收数据---->拷贝数据) 第一个参数是javabean对象的class 第二个参数是获取name里面的键值对
		Users users = MyBeanUtils.populate(Users.class, request.getParameterMap());

		// 由于注册的信息不够 这是额外填充进去的 (是不是 javabean中的属性有多少个就需要给多少个属性)
		users.setUuid(UUIDUtils.getCode());// 生成ID
		users.setCode(UUIDUtils.getUUID64());// 激活码
		users.setState(0);// 0：代表未激活 1:代表激活

		// 优化：因为密码在数据库是直接显示为了安全所以需要加密
		String pwd = request.getParameter("password");
		String md5Pwd = MD5Util.string2MD5(pwd);
		users.setPassword(md5Pwd);

		// 通过业务逻辑去调用注册方法
		usersService.userRegister(users);
		return "jsp/login.jsp";
	}

	// 用户激活
	public String userActive(HttpServletRequest request, HttpServletResponse response) {
		// 接收code激活码 通过激活码去找用户对象
		String code = request.getParameter("code");

		// 通过激活码去查找用户对象(需要返回一个用户对象)
		Users user = usersService.userActive(code);

		System.out.println(user);
		// 修改state的的状态 这里还没有修改数据库里面的数据 需要连接jdbc修改
		user.setState(1);

		// 连接jdbc实现状态修改 (在数据库里面的数据库)
		usersService.updateUsers(user);// 修改用户对象
		return "jsp/login.jsp";
	}
/*
	// 创建UserDao对象
	// UserDao userDao = new UserDaoImpl();

	// 通过业务逻辑层 实现三层架构
	// 创建service对象
	UsersService usersService = new UsersServiceImpl();

	// 浏览器默认的提交方式是 doGet

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);// 调用下面的doPost方法
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 处理乱码
		request.setCharacterEncoding("UTF-8");

		// 注册页面上的参数 /UserServlet?method=registerUI
		String method = request.getParameter("method");
		if (method.equals("registerUI")) {
			// 根据获得的参数来跳转注册页面
			request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
		} else if (method.equals("userRegister")) {
			// 通过代码重构 包装成一个方法
			userRegister(request);
		} else if (method.equals("userLogin")) {
			// 用户登录
		} else if (method.equals("userActive")) { // 用户激活
			// 接收code激活码 通过激活码去找用户对象
			String code = request.getParameter("code");

			// 通过激活码去查找用户对象(需要返回一个用户对象)
			Users user = usersService.userActive(code);

			System.out.println(user);
			// 修改state的的状态 这里还没有修改数据库里面的数据 需要连接jdbc修改
			user.setState(1);

			// 连接jdbc实现状态修改 (在数据库里面的数据库)
			usersService.updateUsers(user);// 修改用户对象
		}
	}

	// 包装成一个用户注册的方法来调用
	public void userRegister(HttpServletRequest request) {
		// 方法二代码简化接收jsp页面的数据 7个input 对象 bean对象里面的属性 。往users对象填充数据 MyBeanUtils
		// 1.拷贝数据 (接收数据---->拷贝数据) 第一个参数是javabean对象的class 第二个参数是获取name里面的键值对 Users
		users = MyBeanUtils.populate(Users.class, request.getParameterMap());

		// 由于注册的信息不够 这是额外填充进去的 (是不是 javabean中的属性有多少个就需要给多少个属性)
		users.setUuid(UUIDUtils.getCode());// 生成ID
		users.setCode(UUIDUtils.getUUID64());// 激活码
		users.setState(0);// 0：代表未激活 1:代表激活

		// 通过业务逻辑去调用
		usersService.userRegister(users);

		// 方法一：当用户点击了注册的提交按钮的时候就获得页面的参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");

		// 创建对象 将获得的数据绑定在Users对象上面
		Users users = new Users();
		// users.setUuid("halouqqq"); //主键 手动生成
		// 转换成随机生成
		users.setUuid(UUIDUtils.getCode());

		users.setUsername(username);
		users.setPassword(password);
		users.setName(name);
		users.setEmail(email);
		users.setTelephone(telephone);
		users.setBirthday(birthday);
		users.setSex(sex);
		users.setState(0);// 账号是否激活 默认是0

		// users.setCode("qqqwert");//激活码 手动生成的激活码 //优化之后的 是自动生成的
		users.setCode(UUIDUtils.getUUID64());

		// 调用注册的方法插入数据 //userDao.register(users); //通过业务逻辑去调用 测试成功
		usersService.userRegister(users);

	}
*/	

}
