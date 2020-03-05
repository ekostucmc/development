package city2.gwt.cabinet.client.ui.controllers;

import com.google.gwt.user.client.ui.Widget;

public interface IUIForm 
{
	public UIControllerBase getUIController();
	public void setUIController(UIControllerBase _uiController);
	public Widget getMainUIwidget();
}
/////////////////////////////////////////////////////
package city2.gwt.cabinet.client.ui;

import java.util.ArrayList;

import city2.gwt.cabinet.client.ui.base.UIFilterPanelWithDateFromAndTo;

public class UIFilterPanelList 
{
	static ArrayList<UIFilterPanelWithDateFromAndTo> List = new ArrayList<UIFilterPanelWithDateFromAndTo>();
	
	public static void Add(UIFilterPanelWithDateFromAndTo fp)
	{
		List.add(fp);
	}
	
	public static String getValueFrom(String label)
	{
		for(UIFilterPanelWithDateFromAndTo fp : List)
			if(fp.getLabel() == label)
				return fp.getDateFrom();
		
		return null;
	}
	
	public static String getValueTo(String label)
	{
		for(UIFilterPanelWithDateFromAndTo fp : List)
			if(fp.getLabel() == label)
				return fp.getDateTo();
		
		return null;
	}
}
/////////////////////////////////////////// переход в функцию (UIFilterPanelWithDateFromAndTo  ////////////////////////////////////
package city2.gwt.cabinet.client.ui.base;

import java.util.Date;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;

import city2.common.configuration.ini.INI;
/**
 * 07.2015 / kommunale Informationssysteme Gmbh
 *
 * implemented: E. Loseva
 * 
 * Filter Panel with Date "from" and "to"
 */
public class UIFilterPanelWithDateFromAndTo extends HorizontalPanel
{
	private String label;
	private UIField txtBoxYearFrom, txtBoxYearTo;
	private UIListBoxMonth listBoxMonthFrom, listBoxMonthTo;
	private UILabel labelFrom, labelTo;
	
	public UIFilterPanelWithDateFromAndTo(String label)
	{
		this.label = label;
		labelFrom = new UILabel("От ");
		labelTo = new UILabel("До");
		txtBoxYearFrom = new UIField();
		txtBoxYearTo = new UIField();
		listBoxMonthFrom = new UIListBoxMonth();
		listBoxMonthTo = new UIListBoxMonth();
		
		int dt_D;
		int dt_M;
		int dt_y;
		String ddt_y;
		
		Date dt = new Date();
		dt_D = dt.getDay();
		dt_M = dt.getMonth();
		dt_y = dt.getYear();
		
		ddt_y = Integer.toString(1900 + dt_y);
		txtBoxYearFrom.setText(ddt_y);
		txtBoxYearTo.setText(ddt_y);
		listBoxMonthFrom.setSelectedIndex(dt_M - INI.I_MONTHS_IN_CUSTOMER_INFO); // INI.I_MONTHS_IN_CUSTOMER_INFO = 2 (количество месяцев)
		listBoxMonthTo.setSelectedIndex(dt_M);
		
		listBoxMonthTo.setHeight("25px");
		listBoxMonthFrom.setHeight("25px");
		
		this.add(labelFrom);
		this.add(txtBoxYearFrom);
		this.add(listBoxMonthFrom);
		this.add(labelTo);
		this.add(txtBoxYearTo);
		this.add(listBoxMonthTo);
		txtBoxYearFrom.setMaxLength(4);
		txtBoxYearTo.setMaxLength(4);
		
		txtBoxYearFrom.addKeyPressHandler(new KeyPressHandler() 
		{
			public void onKeyPress(KeyPressEvent event)
			{
				if(!Character.isDigit(event.getCharCode())){
					((TextBox)event.getSource()).cancelKey();
				}
			}
		});
		
		txtBoxYearTo.addKeyPressHandler(new KeyPressHandler() 
		{
			public void onKeyPress(KeyPressEvent event)
			{
				if(!Character.isDigit(event.getCharCode())){
					((TextBox)event.getSource()).cancelKey();
				}
			}
		});
	}
	public String getDateFrom()
	{
	  String Year = txtBoxYearFrom.getText();
	  String Month;
	  /** Вся эта конструкция для добавления ведущего 0 для чисел меньше 10 */
	  if(listBoxMonthFrom.getSelectedIndex() + 1 > 9)
		  Month = Integer.toString(listBoxMonthFrom.getSelectedIndex() + 1);
	  else
		  Month = "0" + Integer.toString(listBoxMonthFrom.getSelectedIndex() + 1);
	  
	  return Year + "-" + Month + "-01";
	  }	
	
	 public String getDateTo()
	 {
		String Year = txtBoxYearTo.getText();
		String Month;
		/** Вся эта конструкция для добавления ведущего 0 для чисел меньше 10 */
		if(listBoxMonthTo.getSelectedIndex() + 1 > 9)
			Month = Integer.toString(listBoxMonthTo.getSelectedIndex() + 1);
		else
			Month = "0" + Integer.toString(listBoxMonthTo.getSelectedIndex() + 1);
		return Year + "-" + Month + "-01";
	 }
	 public String getLabel()
	 {
		return label;
	 }
}
