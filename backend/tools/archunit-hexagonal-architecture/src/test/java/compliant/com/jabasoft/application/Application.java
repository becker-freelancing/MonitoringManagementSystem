package compliant.com.jabasoft.application;

import compliant.com.jabasoft.api.ApplicationApi;
import compliant.com.jabasoft.spi.DomainRepository;
import compliant.com.jabasoft.domain.model.DomainModel;
import compliant.com.jabasoft.domain.service.DomainService;

public class Application implements ApplicationApi {

	private DomainService domainService;
	private DomainRepository domainRepository;

	@Override
	public int find() {

		DomainModel domainModel = new DomainModel();
		return 1;
	}
}
