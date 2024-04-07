package violation.com.jabasoft.domain.model;

import violation.com.jabasoft.domain.service.DomainService;
import violation.com.jabasoft.adapter.DomainRepositoryAdapter;
import violation.com.jabasoft.api.ApplicationApi;
import violation.com.jabasoft.application.Application;
import violation.com.jabasoft.spi.DomainRepository;

public class DomainModel {

	private DomainService domainService;
	private Application application;
	private ApplicationApi applicationApi;
	private DomainRepository domainRepository;
	private DomainRepositoryAdapter domainRepositoryAdapter;
}
