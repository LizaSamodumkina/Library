package by.etc.samodumkina.service.factory;

import java.util.HashMap;
import java.util.Map;

import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.CommandName;
import by.etc.samodumkina.service.impl.AddNewBookCommand;
import by.etc.samodumkina.service.impl.AddUserLikedBookCommand;
import by.etc.samodumkina.service.impl.ChangeToENLocale;
import by.etc.samodumkina.service.impl.ChangeToRULocale;
import by.etc.samodumkina.service.impl.CreateOrderByAdminCommand;
import by.etc.samodumkina.service.impl.GetAdminAddNewBookPageCommand;
import by.etc.samodumkina.service.impl.GetAdminDeleteBookPageCommand;
import by.etc.samodumkina.service.impl.GetAdminMainPageCommand;
import by.etc.samodumkina.service.impl.GetAdminOrderStoryPageCommand;
import by.etc.samodumkina.service.impl.GetCatalogPageCommand;
import by.etc.samodumkina.service.impl.GetLikedBooksPageCommand;
import by.etc.samodumkina.service.impl.GetOrderPageCommand;
import by.etc.samodumkina.service.impl.GetOrderStoryPageCommand;
import by.etc.samodumkina.service.impl.RegistrationCommand;
import by.etc.samodumkina.service.impl.SignInCommand;
import by.etc.samodumkina.service.impl.SignOutCommand;
import by.etc.samodumkina.service.impl.TakeAllBookCommand;
import by.etc.samodumkina.service.impl.TakeAllUserLikedBookCommand;
import by.etc.samodumkina.service.impl.TakeBookToHomeCommand;
import by.etc.samodumkina.service.impl.TakeBookToReadingRoomCommand;
import by.etc.samodumkina.service.impl.TakeListOfAdminOrderStoryCommand;
import by.etc.samodumkina.service.impl.TakeListOfBookNeedSendOutToHomeCommand;
import by.etc.samodumkina.service.impl.TakeListOfBookNeedSendOutToReadingRoomCommand;
import by.etc.samodumkina.service.impl.TakeUserOrderCommand;
import by.etc.samodumkina.service.impl.TakeUserOrderStoryCommand;

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
		commands.put(CommandName.GET_CATALOG_PAGE, new GetCatalogPageCommand());
		commands.put(CommandName.GET_ORDERS_STORY_PAGE, new GetOrderStoryPageCommand());
		commands.put(CommandName.TAKE_BOOK_TO_HOME, new TakeBookToHomeCommand());
		commands.put(CommandName.TAKE_BOOK_TO_READING_ROOM, new TakeBookToReadingRoomCommand());
		commands.put(CommandName.GET_ORDERS_PAGE, new GetOrderPageCommand());
		commands.put(CommandName.NEED_SEND_OUT_BOOKS_TO_HOME, new TakeListOfBookNeedSendOutToHomeCommand());
		commands.put(CommandName.NEED_SEND_OUT_BOOKS_TO_READING_ROOM, new TakeListOfBookNeedSendOutToReadingRoomCommand());
		commands.put(CommandName.CREATE_ORDER, new CreateOrderByAdminCommand());
		commands.put(CommandName.GET_ADMIN_ORDER_STORY_PAGE, new GetAdminOrderStoryPageCommand());
		commands.put(CommandName.GET_ADMIN_MAIN_PAGE, new GetAdminMainPageCommand());
		commands.put(CommandName.TAKE_ADMIN_ORDER_STORY, new TakeListOfAdminOrderStoryCommand());
		commands.put(CommandName.TAKE_USER_ORDERS, new TakeUserOrderCommand());
		commands.put(CommandName.TAKE_USER_ORDER_STORY, new TakeUserOrderStoryCommand());
		commands.put(CommandName.GET_ADD_NEW_BOOK_PAGE, new GetAdminAddNewBookPageCommand());
		commands.put(CommandName.GET_DELETE_BOOK_PAGE, new GetAdminDeleteBookPageCommand());
		commands.put(CommandName.ADD_NEW_BOOK, new AddNewBookCommand());
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
