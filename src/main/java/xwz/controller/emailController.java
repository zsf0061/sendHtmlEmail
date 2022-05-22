package xwz.controller;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xwz.util.MailUtil;

import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

@CrossOrigin
@RestController
@RequestMapping("/email")
public class emailController {

    @RequestMapping("/send")
    public String send(String html, HttpServletResponse rep) throws Exception {
        rep.setHeader("Access-Control-Allow-Origin", "*");
//        Document doc = Jsoup.parse(URLDecoder.decode(String.valueOf(Base64.decode(html)), "UTF-8"));
        Document doc = Jsoup.parse(html);
        doc.getElementById("subTitle").html("么么哒");
        String resulet = doc.toString();

        String mailHost = "smtp.qq.com";
        String from = "178@qq.com";
        String fromName = "xie";
        String fromPwd = "nnid";
        String to = "1052@qq.com";
        String mailTitle = "hello 邮件来了";
        MailUtil.sendMail(mailHost, from, fromName, fromPwd, to, mailTitle, resulet);
        return "发送成功";
    }
}
