package by.etc.samodumkina.service.factory;

import java.util.HashMap;
import java.util.Map;

import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.CommandName;
import by.etc.samodumkina.service.impl.RegistrationCommand;
import by.etc.samodumkina.service.impl.SignInCommand;

public class ServiceFactory {
	private final static ServiceFactory instance = new ServiceFactory();
	
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();

	private ServiceFactory() {
		commands.put(CommandName.SIGN_IN, new SignInCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
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
