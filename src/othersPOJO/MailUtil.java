package othersPOJO;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

public class MailUtil {
    private JavaMailSenderImpl mailSender; 

    /**
    *   JavaMailSenderImpl֧��MimeMessages��SimpleMailMessages��
    * MimeMessagesΪ�����ʼ�ģ�壬֧���ı���������html��ͼƬ�ȡ�
    * SimpleMailMessagesʵ����MimeMessageHelper��Ϊ��ͨ�ʼ�ģ�壬֧���ı�
    */
    private SimpleMailMessage simpleMailMessage;

    /**
     * ������Spring ����ע��
     * @author wanghaoyu
     * @date 
     * @version 1.0
     * @param mailSender
     * @since 1.8
     *
     */
    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * ������Spring ����ע��
     * @author wanghaoyu
     * @date 
     * @version 1.0
     * @param simpleMailMessage void
     * @since 1.8
     *
     */
    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    /**
     * ����
     *
     * @param recipient �ռ���
     * @param subject ����
     * @param content ����
     */
    public void send(String recipient,String subject,String content){
        System.out.println(simpleMailMessage);
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        System.out.println(simpleMailMessage);
        mailSender.send(simpleMailMessage);
        System.out.println("�ʼ����ͳɹ���");
    }

    /**
     * Ⱥ��
     *
     * @param recipients �ռ���
     * @param subject ����
     * @param content ����
     */
    public void send(List<String> recipients,String subject,String content){
        simpleMailMessage.setTo(recipients.toArray(new String[recipients.size()]));
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        mailSender.send(simpleMailMessage);
    }

    /**
     * ���ʹ��������ʼ�
     * @author malizhi
     * @param recipient
     * @param subject
     * @param content
     * @param file void
     * @version 1.0
     * @exception Nothing
     */
    public void sendWithFile(String recipient,String subject,String content,MultipartFile file){
        //ʹ��JavaMail��MimeMessage��֧�����Ӹ��ӵ��ʼ���ʽ������  
        MimeMessage msg = mailSender.createMimeMessage();
        try {
            //����MimeMessageHelper���󣬴���MimeMessage�ĸ�����  
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);  
            //ʹ�ø�����MimeMessage�趨����  
            helper.setFrom(mailSender.getUsername());  
            helper.setTo(recipient);  
            helper.setSubject(subject);  
            helper.setText(content);
            //���븽��  
            helper.addAttachment(file.getOriginalFilename(), file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }  
        // �����ʼ�  
        mailSender.send(msg);
    }
}
