package pizza;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javax.jdo.*;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AddOrderDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    @Override
    protected void doGet(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
    	resp.setContentType("text/plain");
        resp.getWriter().println("no url...");
    }
    
    @Override
    protected void doPost(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String[] kind = {"マルゲリータ（水牛のモッツｱレラチーズ・フレッシュバジリコ）","マリナーラ（アンチョビ・オレガノ・ニンニクオイル）","オルトラーナ（たっぷり野菜・パルミジャーノ）"};
        String[] size = new String[3];
        size[0] = req.getParameter("size1");
        size[1] = req.getParameter("size2");
        size[2] = req.getParameter("size3");
        int[] number = new int[3];
        number[0] = Integer.parseInt(req.getParameter("num1"));
        number[1] = Integer.parseInt(req.getParameter("num2"));
        number[2] = Integer.parseInt(req.getParameter("num3"));
        
        //---追加---//
        if(number[0]==0 && number[1]==0 && number[2]==0)
            resp.sendRedirect("/confirmOrder.html");
        //-----------//
        
        
        String name = req.getParameter("name");
        String mail = req.getParameter("mail");  //追加
        String address = req.getParameter("address");
        Date date = Calendar.getInstance().getTime();
        OrderData[] data = new OrderData[3];
        for(int i=0; i<3; i++) {
        	data[i] = new OrderData(kind[i],size[i],number[i],name,address,date);
        }
        PersistenceManagerFactory factory = PMF.get();
        PersistenceManager manager = factory.getPersistenceManager();
        try {
        	for(int i=0; i<3; i++) {
        		manager.makePersistent(data[i]);
        	}
        } finally {
            manager.close();
        }
        
        /*
        //送信されたパラメータの値を取得
        String to = mail;//request.getParameter("to");
        String subject = "ピザ注文確認メール";//request.getParameter("subject");
        String message = "この度はピザ注文くださりありがとうございました。";//request.getParameter("message");
        String user = "メールアドレスを追加";//request.getParameter("user");
        String password = "パスワードを追加";//request.getParameter("password");
        
        //SMTPサーバーの設定
        Properties props = new Properties();
        //以下はgmailのSMTPサーバーを使う設定
        props.put("mail.smtp.host", "smtp.gmail.com");  //Gmailのメールサーバー
        props.put("mail.smtp.auth", "ture");            // ユーザー認証をする
        props.put("mail.smtp.port", "587");             //ポート番号
        props.put("mail.smtp.starttls.enable", "true"); //SLLによる暗号化
        
        Session sess = Session.getInstance(props);
        
        try{
            MimeMessage msg = new MimeMessage(sess);
            InternetAddress[] toList = InternetAddress.parse(to); //入力されたメールアドレスの構文をチェック
            msg.addRecipients(MimeMessage.RecipientType.TO, toList); //送信先
            InternetAddress fromAddress = new InternetAddress("sender@example.com");
            msg.setFrom(fromAddress); //送信元
            msg.setSentDate(new Date()); //送信日付
            msg.setSubject(subject,"ISO-2022-JP"); //件名
            //本文
            
            if(!message.endsWith("\n")) message += "\n";
            msg.setText(message, "ISO-2022-JP");
            //送信実行
            Transport tr = null;
            msg.saveChanges();
            tr = sess.getTransport("smtp");
            tr.connect(user, password);
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
            
            
        }catch(MessagingException e){
            throw new ServletException(e);
        }*/
        
        resp.sendRedirect("/thanks.html");
    }
}
