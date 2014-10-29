package mz.inolabdev.rh.viewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TimeSheetViewModel extends AbstractViewModel {

	private Locale ptBr = new Locale("pt", "BR");
	
	private List<String> week;

	@Wire("#mainInclude")
	private Include mainInclude;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Init
	public void init() {

		week = generateWeek();
	}

	@Command
	@NotifyChange("mainInclude")
	public void currentTimesheet() {
		mainInclude.setSrc("views/times/timesheet/index.zul");
	}

	private List<String> generateWeek() {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("EEE dd", ptBr);

		List<String> days = new ArrayList<String>();
		int delta = -cal.get(GregorianCalendar.DAY_OF_WEEK) + 2;
		cal.add(Calendar.DAY_OF_MONTH, delta);
		for (int i = 0; i < 7; i++) {
			days.add(sdf.format(cal.getTime()));
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}

		return days;
	}

	public List<String> getWeek() {
		return week;
	}

	public void setWeek(List<String> week) {
		this.week = week;
	}
}
