package violation.com.jabasoft.controller;

import violation.com.jabasoft.domain.model.DomainModel;
import violation.com.jabasoft.domain.service.DomainService;
import violation.com.jabasoft.adapter.DomainRepositoryAdapter;
import violation.com.jabasoft.api.ApplicationApi;
import violation.com.jabasoft.application.Application;
import violation.com.jabasoft.spi.DomainRepository;

@SuppressWarnings("findbugs:NP_UNWRITTEN_FIELD")
public class Controller {

	private ApplicationApi applicationApi;

	private DomainService domainService;
	private Application application;
	private DomainRepository domainRepository;
	private DomainRepositoryAdapter domainRepositoryAdapter;
	private DomainModel domainModel;

	public int find() {

		return applicationApi.find();
	}
}
