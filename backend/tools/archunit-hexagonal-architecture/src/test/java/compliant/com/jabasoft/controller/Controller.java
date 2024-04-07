package compliant.com.jabasoft.controller;

import compliant.com.jabasoft.api.ApplicationApi;

@SuppressWarnings("findbugs:NP_UNWRITTEN_FIELD")
public class Controller {

	private ApplicationApi applicationApi;

	public int find() {

		return applicationApi.find();
	}
}
