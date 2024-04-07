package violation.com.jabasoft.spi;

import violation.com.jabasoft.adapter.DomainRepositoryAdapter;
import violation.com.jabasoft.application.Application;
import violation.com.jabasoft.controller.Controller;
import violation.com.jabasoft.domain.model.DomainModel;
import violation.com.jabasoft.domain.service.DomainService;
import violation.com.jabasoft.api.ApplicationApi;

public abstract class DomainRepository {

	private ApplicationApi applicationApi;
	private Application application;
	private DomainService domainService;
	private Controller controller;
	private DomainRepositoryAdapter domainRepositoryAdapter;

	public abstract DomainModel find();
}
