package compliant.com.jabasoft.adapter;

import compliant.com.jabasoft.spi.DomainRepository;
import compliant.com.jabasoft.domain.model.DomainModel;

public class DomainRepositoryAdapter implements DomainRepository {

	@Override
	public DomainModel find() {

		return new DomainModel();
	}
}
