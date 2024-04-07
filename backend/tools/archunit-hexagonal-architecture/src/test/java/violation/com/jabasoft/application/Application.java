package violation.com.jabasoft.application;

import violation.com.jabasoft.adapter.DomainRepositoryAdapter;
import violation.com.jabasoft.controller.Controller;
import violation.com.jabasoft.api.ApplicationApi;

public class Application extends ApplicationApi {

	private Controller controller;
	private DomainRepositoryAdapter domainRepositoryAdapter;

	@Override
	public int find() {

		return 1;
	}
}
