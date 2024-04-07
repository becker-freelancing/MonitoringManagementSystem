package violation.com.jabasoft.domain.service;

import violation.com.jabasoft.adapter.DomainRepositoryAdapter;
import violation.com.jabasoft.api.ApplicationApi;
import violation.com.jabasoft.application.Application;
import violation.com.jabasoft.domain.model.DomainModel;
import violation.com.jabasoft.spi.DomainRepository;

public class DomainService {

	private Application application;
	private ApplicationApi applicationApi;
	private DomainRepository domainRepository;
	private DomainRepositoryAdapter domainRepositoryAdapter;

	public DomainModel findModel() {

		return new DomainModel();
	}
}
