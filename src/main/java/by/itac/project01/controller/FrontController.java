package by.itac.project01.controller;

import java.io.IOException;

import by.itac.project01.connection.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String COMMAND = "command";

	private final CommandProvider provider = new CommandProvider();

	public FrontController() {
		super();
	}

	@Override
	public void init() throws ServletException {
		ConnectionPool.getInstanceCP();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(COMMAND);
		
		Command command = provider.getCommand(commandName);
		command.execute(request, response);		
	}
	
	@Override
	public void destroy() {
		ConnectionPool.getInstanceCP().clearConnectionQueue();;
	}

}
