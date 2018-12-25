package by.etc.samodumkina.service.factory;

import java.util.HashMap;
import java.util.Map;

import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.CommandName;
import by.etc.samodumkina.service.impl.AddUserLikedBookCommand;
import by.etc.samodumkina.service.impl.ChangeToENLocale;
import by.etc.samodumkina.service.impl.ChangeToRULocale;
import by.etc.samodumkina.service.impl.GetLikedBooksPageCommand;
import by.etc.samodumkina.service.impl.RegistrationCommand;
import by.etc.samodumkina.service.impl.SignInCommand;
import by.etc.samodumkina.service.impl.SignOutCommand;
import by.etc.samodumkina.service.impl.TakeAllBookCommand;
import by.etc.samodumkina.service.impl.TakeAllUserLikedBookCommand;

public class ServiceFactory {
	private final static ServiceFactory instance = new ServiceFactory();
	
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();

	private ServiceFactory() {
		commands.put(CommandName.SIGN_IN, new SignInCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.TAKE_ALL_BOOKS, new TakeAllBookCommand());
		commands.put(CommandName.EN, new ChangeToENLocale());
		commands.put(CommandName.RU, new ChangeToRULocale());
		commands.put(CommandName.ADD_AS_LIKED_BOOK, new AddUserLikedBookCommand());
		commands.put(CommandName.SIGN_OUT, new SignOutCommand());
		commands.put(CommandName.GET_LIKED_BOOKS_PAGE, new GetLikedBooksPageCommand());
		commands.put(CommandName.GET_USER_LIKED_BOOKS, new TakeAllUserLikedBookCommand());
	}
	
	public static ServiceFactory getInstance() {
		return instance;
	}
	
	public Command getCommand(String commandName) {
		CommandName name = CommandName.valueOf(commandName);
		
		Command command;
		if (name != null) {
			command = commands.get(name);
		}else {
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}
		return command;
	}
}
