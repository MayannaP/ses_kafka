package br.com.mayanna.ses_kafka.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.RawMessage;
import software.amazon.awssdk.services.ses.model.SendRawEmailRequest;
import software.amazon.awssdk.services.ses.model.SesException;

public class SES {
	public static void sendEmail(String message) { 
			
	    String bodyText = "Email de teste!";
	
		 try {
			 Auth auth = new Auth(); 
			 SesClient client = auth.clientProvider();
	         send(client, "mayannaporto@gmail.com","mayannaporto@gmail.com", "Email de teste",
	                 bodyText);
	         client.close();
	
	         System.out.println("Email enviado.");
	
	     } catch (IOException | MessagingException e) {
	         e.getStackTrace();
	     }
	}
	 
	 public static void send(SesClient client,
	            String sender,
	            String recipient,
	            String subject,
	            String bodyText) throws AddressException, MessagingException, IOException {

	        Session session = Session.getDefaultInstance(new Properties());
	        MimeMessage message = new MimeMessage(session);

	        message.setSubject(subject, "UTF-8");
	        message.setFrom(new InternetAddress(sender));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

	        MimeBodyPart textPart = new MimeBodyPart();
	        textPart.setContent(bodyText, "text/plain; charset=UTF-8");

	        MimeMultipart msgBody = new MimeMultipart();
	        msgBody.addBodyPart(textPart);

	        message.setContent(msgBody);

	        try {
	            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	            message.writeTo(outputStream);
	            ByteBuffer buf = ByteBuffer.wrap(outputStream.toByteArray());

	            byte[] arr = new byte[buf.remaining()];
	            buf.get(arr);

	            SdkBytes data = SdkBytes.fromByteArray(arr);
	            RawMessage rawMessage = RawMessage.builder()
	                    .data(data)
	                    .build();

	            SendRawEmailRequest rawEmailRequest = SendRawEmailRequest.builder()
	                    .rawMessage(rawMessage)
	                    .build();

	            client.sendRawEmail(rawEmailRequest);

	        } catch (SesException e) {
	            System.err.println(e.awsErrorDetails().errorMessage());
	        }
	    }
}
