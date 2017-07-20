package utils;


import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.Random;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Utils class
 */
public class Utils {
    /**
     * Symbols available for encoding url
     */
    public final static String symbols =
            "A2B3C4D5E6F7G8H9J1K0LMNPQRSTUVWXYZ";

    public final static int prefix = 2;

    public Utils() {
    }

    public static int randInt(int min, int max) {
        // Usually this can be a field rather than a method variable
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    /**
     * Generate short url
     *
     * @param Id int id
     * @return short url code
     */
    public String generateShortUrl(int Id) {
        Random rand = new Random();
        int randomNum = rand.nextInt((33) + 1);
        String str = "" + symbols.charAt(randomNum);
        randomNum = rand.nextInt((33) + 1);
        str += symbols.charAt(randomNum);
        int provide = Id;
        while ((provide / 33) > 0) {
            str += symbols.charAt(provide % 33);
            provide = provide / 33;
        }
        str += symbols.charAt(provide % 33);
        return str;
    }

    /**
     * Send mail
     *
     * @param recipient recipient
     * @param subject   subject
     * @param emailBody message
     * @throws Exception bad address
     */
    public void sendMail(String recipient, String subject, String emailBody) throws Exception {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage generateMailMessage;
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        // Step2
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        generateMailMessage.setSubject(subject);
        generateMailMessage.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");

        // Step3
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.gmail.com", "breizhlinkjava@gmail.com", "esgi$4a1");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

}
