package violation.com.jabasoft.api;

import violation.com.jabasoft.adapter.DomainRepositoryAdapter;
import violation.com.jabasoft.application.Application;
import violation.com.jabasoft.controller.Controller;
import violation.com.jabasoft.domain.model.DomainModel;
import violation.com.jabasoft.domain.service.DomainService;
import violation.com.jabasoft.spi.DomainRepository;

public abstract class ApplicationApi {

	private Application application;
	private DomainRepository domainRepository;
	private DomainRepositoryAdapter domainRepositoryAdapter;
	private DomainService domainService;
	private DomainModel domainModel;
	private Controller controller;

	public abstract int find();
}
