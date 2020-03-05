package city2.gwt.cabinet.client.ui;


import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import city2.gwt.cabinet.client.City2_Cabinet;
import city2.gwt.cabinet.client.ui.base.UIField;
import city2.gwt.cabinet.client.ui.base.UILabel;
import city2.gwt.cabinet.client.ui.controllers.IUIForm;
import city2.gwt.cabinet.client.ui.controllers.UIControllerBase;
import city2.gwt.cabinet.shared.conditions.GwtConditionType;
import city2.gwt.cabinet.shared.conditions.GwtSingleColCompare;
import city2.gwt.cabinet.shared.data.common.GwtINI;

/*
 * 07.2015 / kommunale Informationssysteme Gmbh
 *
 * implemented: E. Loseva
 * 
 */


public class Cabinet_Form extends VerticalPanel  implements IUIForm 
{
	private UIControllerBase uiController;
	//
	public UIControllerBase getUIController()
	{
	return uiController;
	}
	public void setUIController(UIControllerBase _uiController)
	{
		uiController = _uiController;
	}
	//
	TabLayoutPanel tabLayoutPanel;
	HorizontalPanel infoPanel;
	HorizontalPanel infoPanel1;
	//
	public UIField fldAccount;
	public UIField fldAddress;
	public UIField fldAccBalance;
	//
	public Cabinet_Form()
	{
		initialize();
	}
	private void initialize()
	{
		setSize("1080px", "550px");
		add(getInfoPanel());
		//setBorderWidth(10);
		//add(getTblList()); 
	}
	public HorizontalPanel getInfoPanel()
	{
		if(infoPanel == null)
		{
			infoPanel = new HorizontalPanel();
			infoPanel.setSize("50px", "50px");
			
			infoPanel.add(new UILabel("Счет:"));
			fldAccount = new UIField("Account");
			fldAccount.setEnabled(false);
			infoPanel.add(fldAccount);
			//
			infoPanel.add(new UILabel("Адрес:"));
			fldAddress = new UIField("Address");
			fldAddress.setEnabled(false);
			fldAddress.setWidth("300px");

			infoPanel.add(fldAddress);
			
			infoPanel.add(new UILabel("Баланс")); //петя
			fldAccBalance = new UIField("AccBalance");
			fldAccBalance.setEnabled(false);
			fldAccBalance.setWidth("50px");
			infoPanel.add(fldAccBalance); //
		}
		return infoPanel;	
	}
	public TabLayoutPanel getTabLayoutPanel()
	{
		return tabLayoutPanel;
	}
	public void initTabs()
	{
		if(tabLayoutPanel == null)
		{
			tabLayoutPanel = new TabLayoutPanel(1.5, Unit.EM);
			tabLayoutPanel.setSize(GwtINI.tblList_Width, GwtINI.tblList_Height);
			tabLayoutPanel.add(((Cabinet_FormUIController)getUIController()).getDevicesUIController().getUI(), "Ввод показаний");
			tabLayoutPanel.add(((Cabinet_FormUIController)getUIController()).getPaymentsUIController().getUI(), "Платежи");
			tabLayoutPanel.add(((Cabinet_FormUIController)getUIController()).getProfitUIController().getUI(), "Начисления");
			//
			Devices_FormListUIController uic = ((Cabinet_FormUIController)getUIController()).getDevicesUIController();
			//
			// ContextConsts.LIST_MODE_FOR_DEVICE = "@ListModeForDevice@";
			// EListModeForDevice. GET_DEVICES_BY_OBJECT_WITH_LAST_MEASURES(8)
			uic.getInContext().putValue("@ListModeForDevice@", 8);
			//
			uic.getInContext().putValue("ObjGIDSbj", City2_Cabinet.getSecurityData().getObjectKey().getSbj());
			uic.getInContext().putValue("ObjGID", City2_Cabinet.getSecurityData().getObjectKey().getGid());
			uic.getInContext().putValue("ObjSID", City2_Cabinet.getSecurityData().getObjectKey().getSid());
			//
			uic.setRowCondition(new GwtSingleColCompare("ObjGID", GwtConditionType.CND_EQUAL_TO, City2_Cabinet.getSecurityData().getObjectKey().getGid()));
			//
			uic.execute(false);
			//		
			//callDisplayMessage();
			tabLayoutPanel.addSelectionHandler(new SelectionHandler<Integer>() 
			{
			@Override
				public void onSelection(SelectionEvent<Integer> event) 
				{
					switch(event.getSelectedItem())
					{
						case 0:
							Devices_FormListUIController uic = ((Cabinet_FormUIController)getUIController()).getDevicesUIController();							
							// ContextConsts.LIST_MODE_FOR_DEVICE = "@ListModeForDevice@";
							// EListModeForDevice. GET_DEVICES_BY_OBJECT_WITH_LAST_MEASURES(8)
							uic.getInContext().putValue("@ListModeForDevice@", 8);
							uic.getInContext().putValue("ObjGIDSbj", City2_Cabinet.getSecurityData().getObjectKey().getSbj());
							uic.getInContext().putValue("ObjGID", City2_Cabinet.getSecurityData().getObjectKey().getGid());
							uic.getInContext().putValue("ObjSID", City2_Cabinet.getSecurityData().getObjectKey().getSid());
							uic.setRowCondition(new GwtSingleColCompare("ObjGID", GwtConditionType.CND_EQUAL_TO, City2_Cabinet.getSecurityData().getObjectKey().getGid()));
							uic.execute(false);
						break;
						case 1://Платежи
							Payments_FormListUIController uicPayments = ((Cabinet_FormUIController)getUIController()).getPaymentsUIController();
							//public static final String LIST_MODE_FOR_TRANSACTIONS = "@TransactionListMode@";
							//PAYMENTS(6),
							/*uicPayments.getInContext().putValue("BeginDate", DateApi.getFirstMonthDate(DateApi.takePrevDayInMonths(DateApi.getFirstMonthDate(DateApi.takeCurrentDate()), INI.I_MONTHS_IN_CUSTOMER_INFO)));
							uicPayments.getInContext().putValue("EndDate", DateApi.getYear(DateApi.takeCurrentYearEnd()));
							*///
					     	uicPayments.getInContext().putValue("@TransactionListMode@", 6);//EListModeForTransaction.BY_DOCUMENT
							uicPayments.getInContext().putValue("AccGIDSbj", City2_Cabinet.getSecurityData().getAccountKey().getSbj());
							uicPayments.getInContext().putValue("AccGID", City2_Cabinet.getSecurityData().getAccountKey().getGid());
							uicPayments.setRowCondition(new GwtSingleColCompare("AccGID", GwtConditionType.CND_EQUAL_TO, City2_Cabinet.getSecurityData().getAccountKey().getGid()));
							uicPayments.execute(false);
						break;
						case 2 :
							Profit_FormListUIController uicPr = ((Cabinet_FormUIController)getUIController()).getProfitUIController();
							uicPr.getInContext().putValue("@TransactionListFilterMode@", 3);
							uicPr.getInContext().putValue("ObjGIDSbj", City2_Cabinet.getSecurityData().getObjectKey().getSbj());
							uicPr.getInContext().putValue("ObjGID", City2_Cabinet.getSecurityData().getObjectKey().getGid());
							uicPr.getInContext().putValue("ObjSID", City2_Cabinet.getSecurityData().getObjectKey().getSid());
												  //
							uicPr.getInContext().putValue("AccGIDSbj", City2_Cabinet.getSecurityData().getAccountKey().getSbj());
							uicPr.getInContext().putValue("AccGID", City2_Cabinet.getSecurityData().getAccountKey().getGid());
												 //
							uicPr.getInContext().putValue("CliGIDSbj", City2_Cabinet.getSecurityData().getClientKey().getSbj());
							uicPr.getInContext().putValue("CliGID", City2_Cabinet.getSecurityData().getClientKey().getGid());
							uicPr.getInContext().putValue("CliSID", City2_Cabinet.getSecurityData().getClientKey().getSid());
											// todo //
							//uicPr.getInContext().putValue("BeginDate", DateApi.getFirstMonthDate(DateApi.takePrevDayInMonths(DateApi.getFirstMonthDate(DateApi.takeCurrentDate()), INI.I_MONTHS_IN_CUSTOMER_INFO)));
							//uicPr.getInContext().putValue("EndDate", DateApi.getYear(DateApi.takeCurrentYearEnd()));
							//String CUSTOMER_PERSON_TYPE = "@CustomerPersonType@";
							//EPersonType.PHYSICAL(1)
							uicPr.getInContext().putValue("@CustomerPersonType@", 1);
							uicPr.getInContext().putValue("checkNeedGetDataForAbonent", 1);							
							//uicPr.setRowCondition(new GwtSingleColCompare("ObjGID", GwtConditionType.CND_EQUAL_TO, City2_Cabinet.getSecurityData().getObjectKey().getGid()));
							uicPr.execute(false);
						break;
					}
				}
			});
		}		
		add(tabLayoutPanel);		
	}
	@Override
	public Widget getMainUIwidget() {
		// TODO Auto-generated method stub
		return null;
	}	

}
