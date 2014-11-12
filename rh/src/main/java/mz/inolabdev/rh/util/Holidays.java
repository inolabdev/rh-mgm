package mz.inolabdev.rh.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public enum Holidays {

	DFU("Dia da Fraternidade Universal", new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),0,1).getTime()),
	DHM("Dia dos Heróis Moçambicanos", new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),1,3).getTime()),
	DMM("Dia da Mulher Moçambicana", new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),3,7).getTime()),
	DIT("Dia Internacional dos Trabalhadores", new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),4,1).getTime()),
	DIN("Dia da Independência Nacional", new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),5,25).getTime()),
	DV("Dia da Vitória", new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),8,7).getTime()),
	DFA("Dia das Forças Armadas", new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),8,25).getTime()),
	DPR("Dia da Paz e Reconciliação", new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),9,4).getTime()),
	DF("Dia da Família", new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),11,25).getTime());
	
	private static final Map<String, Holidays> holidayMap = new HashMap<String, Holidays>();
	
	static
    {
        for (Holidays h : values())
        {
        	holidayMap.put(h.getTitle(), h);
        }
    }
	private final String title;
    private final Date date;
    
    private Holidays(String title, Date date)
    {
        this.title = title;
        this.date = date;
    }
    
    public String getTitle()
    {
        return title;
    }   
    
    public Date getDate()
    {
        return date;
    }
   }
