package com.taskgo.services;

import com.taskgo.utilities.RandomsGeneratorUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String FROM;

    private static final String VERIFICATION_OR_NEW_PASSWORD_EMAIL_TEMPLATE = "verification-or-new-password-email";
    private static final String NOTIFICATION_EMAIL_TEMPLATE = "notification-email";


    /**
     * A method to set the email verification or new password email variables
     *
     * @param to The receiver email address
     * @param subject The subject (title) of the email
     * @param textMessage The HTML template file name
     * @param generatedCodeOrPassword The value of the variable to be set
     *
     * @throws MessagingException When there is an error in sending the email
     */
    private void sendEmailVerificationOrNewPasswordEmail(String to, String subject, String textMessage, String generatedCodeOrPassword) throws MessagingException {
        Context context = new Context();
        context.setVariable("subject", subject);
        context.setVariable("message", textMessage);
        context.setVariable("generatedCodeOrPassword", generatedCodeOrPassword);

        sendHtmlEmail(to, subject, context, VERIFICATION_OR_NEW_PASSWORD_EMAIL_TEMPLATE);
    }

    /**
     * A method to set the notification email variables
     *
     * @param to The receiver email address
     * @param subject The subject (title) of the email
     * @param textMessage The HTML template file name
     *
     * @throws MessagingException When there is an error in sending the email
     */
    private void sendNotificationEmail(String to, String subject, String textMessage) throws MessagingException {
        Context context = new Context();
        context.setVariable("subject", subject);
        context.setVariable("message", textMessage);

        sendHtmlEmail(to, subject, context, NOTIFICATION_EMAIL_TEMPLATE);
    }

    /**
     * A method to send an HTML template via the email instead of a simple message
     *
     * @param to The receiver email address
     * @param subject The subject (title) of the email
     * @param context The context (contains variables) to be sent
     *
     * @throws MessagingException When there is an error in sending the email
     */
    private void sendHtmlEmail(String to, String subject, Context context, String notificationEmailTemplate) throws MessagingException {
        String processHtml = templateEngine.process(notificationEmailTemplate, context);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setText(processHtml, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(FROM);
        mailSender.send(mimeMessage);
    }

    /**
     * A method to send an email verification email
     *
     * @param to The receiver email address
     * @throws MessagingException When there is an error in sending the email
     */
    public void sendEmailVerificationEmail(String to) throws MessagingException {
        sendEmailVerificationOrNewPasswordEmail(
                to,
                "Email Verification",
                "This is your email verification code. Please, enter it!",
                RandomsGeneratorUtil.generateFourDigitCode()
        );
    }

    /**
     * A method to send a new password email
     *
     * @param to The receiver email address
     * @throws MessagingException When there is an error in sending the email
     */
    public void sendNewPasswordEmail(String to) throws MessagingException {
        sendEmailVerificationOrNewPasswordEmail(
                to,
                "New Password",
                "This is your new password. Please, start using it!",
                RandomsGeneratorUtil.generateSecurePassword()
        );
    }

    /**
     * A method to send a notification email when a viewer is added to a workspace
     *
     * @param to The receiver email address
     * @throws MessagingException When there is an error in sending the email
     */
    public void sendNotificationEmailWhenViewerAddedToWorkspace(String to) throws MessagingException {
        sendNotificationEmail(
                to,
                "Added to a Workspace",
                "Your are added to a new workspace. Please, check your account!"
        );
    }

    /**
     * A method to send a notification email when a user is assigned to a task
     *
     * @param to The receiver email address
     * @throws MessagingException When there is an error in sending the email
     */
    public void sendNotificationEmailWhenUserAssignedToTask(String to) throws MessagingException {
        sendNotificationEmail(
                to,
                "Assigned to a Task",
                "You are assigned to a task. Please, check your account!"
        );
    }
}
