package compliant.com.jabasoft.autoconfigure;

import compliant.com.jabasoft.adapter.DomainRepositoryAdapter;
import compliant.com.jabasoft.api.ApplicationApi;
import compliant.com.jabasoft.controller.Controller;
import compliant.com.jabasoft.spi.DomainRepository;
import compliant.com.jabasoft.application.Application;
import compliant.com.jabasoft.domain.service.DomainService;

public class AutoConfiguration {

	private Application application;
	private ApplicationApi applicationApi;
	private DomainService domainService;
	private DomainRepositoryAdapter domainRepositoryAdapter;
	private DomainRepository domainRepository;
	private Controller controller;
}
