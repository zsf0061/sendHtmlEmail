package xwz.util;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailUtil {

    public static void sendMail(String mailHost, String from, String fromName, String fromPwd, String to,
            String mailTitle, String content) throws Exception {
        String[] toArr = null;
        if (to != null && !to.equals("")) {
            toArr = to.split(",");
        } else {
            throw new Exception("邮件发送人不能为空");
        }

        Properties props = new Properties();
        props.put("mail.host", mailHost);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props);
        for (String t: toArr) {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(t, fromName));
            msg.setSubject(mailTitle);
            msg.setContent(content, "text/html;charset=UTF-8");
            msg.setSentDate(new Date());
            msg.saveChanges();

            Transport transport = session.getTransport();
            transport.connect(from, fromPwd);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        }
    }
}
