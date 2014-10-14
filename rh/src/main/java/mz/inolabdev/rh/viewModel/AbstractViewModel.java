package mz.inolabdev.rh.viewModel;

import org.zkoss.zk.ui.select.annotation.WireVariable;

import mz.inolabdev.rh.entity.Log;
import mz.inolabdev.rh.services.LogService;

public class AbstractViewModel {

	protected String CURRENT_PAGE_TITLE;
	protected String CURRENT_PAGE_ACTION;
	private Log log;

	@WireVariable
	private LogService logService;

	public String getCURRENT_PAGE_TITLE() {
		return CURRENT_PAGE_TITLE;
	}

	public void setCURRENT_PAGE_TITLE(String cURRENT_PAGE_TITLE) {
		CURRENT_PAGE_TITLE = cURRENT_PAGE_TITLE;
	}

	public String getCURRENT_PAGE_ACTION() {
		return CURRENT_PAGE_ACTION;
	}

	public void setCURRENT_PAGE_ACTION(String cURRENT_PAGE_ACTION) {
		CURRENT_PAGE_ACTION = cURRENT_PAGE_ACTION;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public void log(String msg) {

		log = new Log();
		log.setMessage(msg);
		logService.create(log);
	}

}
