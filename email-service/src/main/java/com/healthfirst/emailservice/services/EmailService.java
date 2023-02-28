package com.healthfirst.emailservice.services;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import com.healthfirst.emailservice.configuration.Configuration;
import com.healthfirst.emailservice.models.ClassInfo;
import com.healthfirst.emailservice.models.Email;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private final Configuration configuration;
//  private final String EMAIL = "health.first.app.v1@gmail.com";
  private final Gmail gmailService;
  private static final String WELCOME_SUBJECT = "Welcome to Health First!";

  @Autowired
  public EmailService(Configuration configuration) throws IOException, GeneralSecurityException {
    final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
    this.configuration = configuration;
    gmailService = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
        .setApplicationName("email-service-mailer")
        .build();
  }

  public String getCompanyEmail(){
    return configuration.getCompanyEmail();
  }

  public void handleWelcomeEmailToNewMembers(List<ClassInfo> classes, String name, String email)
      throws MessagingException, IOException {
    /**
     * HEALTH_FIRST_EMAIL (configuration.getCompanyEmail()) will be replaced by 'email' in production
     */
    sendMail(WELCOME_SUBJECT, new Email(name, email, classes).formatEmail(), configuration.getCompanyEmail());
  }

  public void sendMail(String subject, String message, String memberEmail)
      throws IOException, MessagingException {
    // Encode as MIME message
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);
    MimeMessage email = new MimeMessage(session);
    email.setFrom(new InternetAddress(configuration.getCompanyEmail()));
    email.addRecipient(javax.mail.Message.RecipientType.TO,
        new InternetAddress(memberEmail));
    email.setSubject(subject);
    email.setText(message);

    // Encode and wrap the MIME message into a gmail message
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    email.writeTo(buffer);
    byte[] rawMessageBytes = buffer.toByteArray();
    String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
    Message msg = new Message();
    msg.setRaw(encodedEmail);

    try {
      // Create send message
      msg = gmailService.users().messages().send("me", msg).execute();
      System.out.println("Message id: " + msg.getId());
      System.out.println(msg.toPrettyString());
    } catch (GoogleJsonResponseException e) {
      // TODO(developer) - handle error appropriately
      GoogleJsonError error = e.getDetails();
      if (error.getCode() == 403) {
        System.err.println("Unable to send message: " + e.getDetails());
      } else {
        throw e;
      }
    }
  }

  private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
      throws IOException {
    // Load client secrets.
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory,
        new InputStreamReader(EmailService.class
            .getResourceAsStream("/secret/"
            + "client_secret_1072835117677-8t15nhdv0er7bo60vin4ca8flmpau9h9.apps.googleusercontent.com.json")));

    // Build flow and trigger user authorization request.
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        httpTransport, jsonFactory, clientSecrets, Set.of(GmailScopes.GMAIL_SEND))
        .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
        .setAccessType("offline")
        .build();
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8100).build();
    Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    //returns an authorized Credential object.
    return credential;
  }
}
