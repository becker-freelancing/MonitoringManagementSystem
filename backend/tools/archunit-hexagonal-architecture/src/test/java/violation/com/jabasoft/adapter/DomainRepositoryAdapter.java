package violation.com.jabasoft.adapter;

import violation.com.jabasoft.controller.Controller;
import violation.com.jabasoft.domain.model.DomainModel;
import violation.com.jabasoft.domain.service.DomainService;
import violation.com.jabasoft.api.ApplicationApi;
import violation.com.jabasoft.application.Application;
import violation.com.jabasoft.spi.DomainRepository;

public class DomainRepositoryAdapter extends DomainRepository {

	private DomainService domainService;
	private Application application;
	private ApplicationApi applicationApi;
	private Controller controller;

	@Override
	public DomainModel find() {

		return new DomainModel();
	}
}
