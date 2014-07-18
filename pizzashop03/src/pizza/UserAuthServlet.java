package pizza;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.http.*;  //追加
import javax.servlet.*;


@SuppressWarnings("serial")
	public class UserAuthServlet extends HttpServlet {
	    public void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws IOException {
	        resp.setContentType("text/plain;charset=UTF-8");
	 
	        //ユーザーサービスの取得
	        UserService us = UserServiceFactory.getUserService();
	        User user = us.getCurrentUser();
	        
	 
	        if( us.isUserLoggedIn() == false ){
	            resp.getWriter().println("ログインしていません");
	            resp.getWriter().println("hello");
	            String url = us.createLoginURL("/userauth");
	            resp.getWriter().println("<a herf ='"+ url + "'>ログイン</a>");
	        }
	 
	        if(user == null){
	        	PrintWriter out;
	            resp.setContentType("text/html; charset=Shift_JIS");
	            out = resp.getWriter();
	            out=resp.getWriter();
	    		out.println("<html><head>");
	    		out.println("<title>ログイン失敗</title>");
	    		out.println("</head>");
	    		out.println("</body>");
	    		out.println("<h1>");
	    		out.println("ログインに失敗しました");
	    		out.println("<h1>");
	    		out.println("<form action='index.html'>");
	    		out.println("<input type='submit' value='戻る'>");
	    		out.println("</form>");   		
	    		out.println("</body><html>");
	        }else{
	        	PrintWriter out;
	            resp.setContentType("text/html; charset=Shift_JIS");
	            out = resp.getWriter();
	            out=resp.getWriter();
	    		out.println("<html><head>");
	    		out.println("<title>ログイン成功</title>");
	    		out.println("</head>");
	    		out.println("</body>");
	    		out.println("<h1>");
	    		out.println("ログインに成功しました");
	    		out.println("<h1>");
	    		out.println("<h4>");
	    		out.println(user.getEmail());
	    		out.println("でログインしました");
	    		out.println("</h4>");
	    		out.println("<form action='staff.html'>");
	    		out.println("<input type='submit' value='進む'>");
	    		out.println("</form>");   		
	    		out.println("</body><html>");
	            
	        }
	    }

}
