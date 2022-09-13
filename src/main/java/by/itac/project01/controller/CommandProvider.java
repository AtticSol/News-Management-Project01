package by.itac.project01.controller;

import java.util.HashMap;
import java.util.Map;

import by.itac.project01.controller.impl.DoAddNews;
import by.itac.project01.controller.impl.DoCancel;
import by.itac.project01.controller.impl.DoChangeLocal;
import by.itac.project01.controller.impl.DoDeleteNews;
import by.itac.project01.controller.impl.DoEditNews;
import by.itac.project01.controller.impl.DoRegistration;
import by.itac.project01.controller.impl.DoSignIn;
import by.itac.project01.controller.impl.DoSignOut;
import by.itac.project01.controller.impl.GoToAddNews;
import by.itac.project01.controller.impl.GoToBasePage;
import by.itac.project01.controller.impl.GoToEditNews;
import by.itac.project01.controller.impl.GoToErrorPage;
import by.itac.project01.controller.impl.GoToNewsList;
import by.itac.project01.controller.impl.GoToRegistrationPage;
import by.itac.project01.controller.impl.GoToViewNews;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.GO_TO_NEWS_LIST, new GoToNewsList());
		commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
		commands.put(CommandName.GO_TO_ADD_NEWS, new GoToAddNews());
		commands.put(CommandName.GO_TO_EDIT_NEWS, new GoToEditNews());
		commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPage());
	
		commands.put(CommandName.DO_SIGN_IN, new DoSignIn());
		commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
		commands.put(CommandName.DO_ADD_NEWS, new DoAddNews());
		commands.put(CommandName.DO_EDIT_NEWS, new DoEditNews());
		commands.put(CommandName.DO_DELETE_NEWS, new DoDeleteNews());
		commands.put(CommandName.DO_CANCEL, new DoCancel());
		commands.put(CommandName.DO_CHANGE_LOCAL, new DoChangeLocal());
		
	}
	
	public Command getCommand(String name) {
		CommandName commandName = CommandName.valueOf(name.toUpperCase());
		Command command = commands.get(commandName);
		return command;
	}
	

}
