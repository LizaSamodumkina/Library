package by.etc.samodumkina.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.util.Mailer;
import by.etc.samodumkina.specification.Specification;
import by.etc.samodumkina.specification.impl.TakeUserEmailNeedToNotificateSpecification;

public class SendNotificationToUser extends Thread{
	
	private final static Logger log = LogManager.getLogger(SendNotificationToUser.class);

	public SendNotificationToUser() {}
	
	@Override
	public void run() {
		while(true) {
			try {
				sendMail();
				TimeUnit.HOURS.sleep(24);
			} catch (InterruptedException | DAOException e) {
					log.error(e.getStackTrace());
					Thread.currentThread().interrupt();
			}
		}
	}
	
	private void sendMail() throws DAOException {
		List<String> emails = getEmailsToNotificate();
		
		Mailer mailer = new Mailer();
		String subject = "Оповещение об истечении срока пользования";
		String text = "Обращаем ваше внимание, что у вас есть заказы в нашей библиотеке, срок которых скоро истечет.\n"
				+ "Просим вас вернуть книги до истечения срока заказа.\n"
				+ "С уваженим, библиотека.";

		for (String email: emails) {
			mailer.send(subject, text, email);
		}
	}
	
	private List<String> getEmailsToNotificate() throws DAOException{
		Specification specification = new TakeUserEmailNeedToNotificateSpecification();
		
		return DAOFactory.getInstance().takeReadUserToNotificate().read(specification);
	}

}
