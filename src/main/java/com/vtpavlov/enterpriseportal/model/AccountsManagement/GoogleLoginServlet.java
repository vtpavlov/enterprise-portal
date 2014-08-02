package com.vtpavlov.enterpriseportal.model.AccountsManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;
import com.google.gson.Gson;
import com.vtpavlov.enterpriseportal.Repositories.DbCompanyRoleRepository;
import com.vtpavlov.enterpriseportal.Repositories.DbUserRepository;
import com.vtpavlov.enterpriseportal.dto.CompanyRole;
import com.vtpavlov.enterpriseportal.dto.User;

public class GoogleLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final HttpTransport TRANSPORT = new NetHttpTransport();
	private static final JacksonFactory JSON_FACTORY = new JacksonFactory();
	private static final String CLIENT_ID = "434671265291-ehat14ivagghg9b9gv035cv4os45vur6.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "le7EHgh0WssDMxVBxwd4Txpw";
	private static final String APPLICATION_NAME = "Enterprise Portal";
	private static final Gson GSON = new Gson();

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("application/json");

		// Getting the code from the client
		BufferedReader reader = request.getReader();
		String code = reader.readLine();

		Plus service = getAuthorizedApiClient(code, request, response);

		Person person = service.people().get("me").execute();
		System.out.println("Created successfully Api Client");
		response.setStatus(HttpServletResponse.SC_OK);

		try {
			loginUser(person.getId(), person.getId(), request);
		} catch (ServletException e) {
			RegisterUser(person.getId(), person.getId(), person.getId(), person
					.getName().getGivenName(), person.getName().getFamilyName());
			try {
				loginUser(person.getId(), person.getId(), request);
			} catch (ServletException e1) {
				e1.printStackTrace();
			}
		}

		response.getWriter().write(GSON.toJson(person));
	}

	private Plus getAuthorizedApiClient(String code,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		GoogleCredential credential = new GoogleCredential.Builder()
				.setJsonFactory(JSON_FACTORY)
				.setTransport(TRANSPORT)
				.setClientSecrets(CLIENT_ID, CLIENT_SECRET)
				.build()
				.setFromTokenResponse(
						JSON_FACTORY.fromString(
								getAccessToken(code, request, response),
								GoogleTokenResponse.class));

		Plus service = new Plus.Builder(TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();

		return service;
	}

	private String getAccessToken(String code, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String tokenData = (String) request.getSession().getAttribute("token");
		if (tokenData != null) {
			return tokenData;
		}

		// Upgrade the authorization code into an access and refresh token.
		GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
				TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, code,
				"postmessage").execute();

		// Store the token in the session for later use.
		request.getSession().setAttribute("token", tokenResponse.toString());
		return tokenResponse.toString();
	}

	private void loginUser(String username, String password, HttpServletRequest request)
			throws ServletException {
	/*	ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext
				.getRequest();*/
		System.out.println("Logging User");
		request.login(username, password);
	}

	private void RegisterUser(String username, String password, String email,
			String firstName, String lastName) {
		System.out.println("Registering user");
		DbCompanyRoleRepository rolesRepository = new DbCompanyRoleRepository();
		Set<CompanyRole> roles = new HashSet<CompanyRole>();
		roles.add(rolesRepository.get("JUNIOR"));
		User user = new User(username, password, email, firstName, lastName,
				roles);
		DbUserRepository usersRepository = new DbUserRepository();
		usersRepository.add(user);
	}
}
