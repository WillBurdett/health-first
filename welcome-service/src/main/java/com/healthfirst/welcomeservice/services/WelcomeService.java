package com.healthfirst.welcomeservice.services;

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
import com.healthfirst.welcomeservice.enums.Interest;
import com.healthfirst.welcomeservice.models.ClassInfo;
import com.healthfirst.welcomeservice.models.Member;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Properties;

import java.util.Set;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WelcomeService {

    private static final String HEALTH_FIRST_EMAIL = "health.first.app.v1@gmail.com";
    private final Gmail gmailService;

    public WelcomeService() throws Exception {
        // send email to yourself
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        gmailService = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
            .setApplicationName("welcome-service-mailer")
            .build();
    }

    public static List<ClassInfo> allClasses = new ArrayList<>();
    static {
        allClasses.add(
                new ClassInfo(
                        1L, "Rhythmic Aerobics",
                        "Mr.Tickles", Interest.DANCE,
                        LocalDateTime.of(2023, 2,
                                2,
                                14,
                                0)
                )
        );
        allClasses.add(
                new ClassInfo(
                        2L, "Amateur 5-a-side",
                        "Mr.Beckham", Interest.TEAMSPORTS,
                        LocalDateTime.of(2023, 3,
                                3,
                                15,
                                0)
                )
        );
        allClasses.add(
                new ClassInfo(
                        3L, "Aqua-fit",
                        "Mrs.Doubtfire", Interest.SWIMMING,
                        LocalDateTime.of(2022, 4,
                                4,
                                16,
                                0)
                )
        );
    }

    public List<ClassInfo> handleNewMember(Member member) throws MessagingException, IOException {
        System.out.println(member);
        List<ClassInfo> classInfo = getRelevantClasses(member.getInterest());

        /** do not use in dev - it will send real emails to test members
        sendMail("Relevant classes", classInfo.toString(), member.getEmail()); **/

        sendMail("Hello hello", "congrats\n\nit worked!", HEALTH_FIRST_EMAIL);

        return classInfo;
    }

    public List<ClassInfo> getRelevantClasses(Interest interest){
        List <ClassInfo> relevantClasses = new ArrayList<>();
        // TODO: 16/01/2023 GET method for all classes from class-service

        // TODO: 10/01/2023 this will be to handle List <Interest>
//        for (Interest i:
//             interests) {
//            for (ClassInfo c:
//                 allClasses) {
//                if (i.equals(c.getClassType())){
//                    relevantClasses.add(c);
//                }
//            }
//        }

        // filtering all classes to match Members Interest
        for (ClassInfo c:
            allClasses) {
            if (interest.equals(c.getClassType())){
                    relevantClasses.add(c);
                }
            }

        return relevantClasses;
    }

    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
        throws IOException {
        // Load client secrets.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory,
            new InputStreamReader(WelcomeService.class.getResourceAsStream("/secret/client_secret_1072835117677-mnc6u244hdr86fq8p3vu59uj35hlkp1f.apps.googleusercontent.com.json")));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            httpTransport, jsonFactory, clientSecrets, Set.of(GmailScopes.GMAIL_SEND))
            .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
            .setAccessType("offline")
            .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }

    public void sendMail(String subject, String message, String memberEmail)
        throws IOException, MessagingException {
        // Encode as MIME message
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(HEALTH_FIRST_EMAIL));
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
}
