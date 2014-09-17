package mz.inolabdev.rh.services;

import mz.inolabdev.rh.entity.Log;
import java.util.List;

public interface MyService {

	Log addLog(Log log);

	List<Log> getLogs();

	void deleteLog(Log log);
}
