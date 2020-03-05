package city2.gwt.cabinet.client.ui;

import java.util.Date;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import city2.gwt.cabinet.client.ui.controllers.IUIForm;
import city2.gwt.cabinet.client.ui.controllers.UIControllerBase;
import city2.gwt.cabinet.shared.data.common.GwtINI;
import city2.gwt.cabinet.shared.data.model.GwtDataRow;


/*
 * 07.2015 / kommunale Informationssysteme Gmbh
 *
 * implemented: E. Loseva
 * 
 */


public class Readings_FormList extends VerticalPanel implements IUIForm 
{
	public interface MyStyle extends DataGrid.Style 
	{
	}	
	 interface TableResources extends DataGrid.Resources 
	 {
	    @Override
	    @Source(value = {DataGrid.Style.DEFAULT_CSS, "ReadingsTableStyle.css"})
	    MyStyle dataGridStyle();
	  }	 
	DataGrid<GwtDataRow> tblList2;
	private UIControllerBase uiController;	
	@Override
	public UIControllerBase getUIController() 
	{
		return uiController;
	}
	@Override
	public void setUIController(UIControllerBase _uiController) 
	{
		uiController = _uiController;		
	}
	@Override
	public Widget getMainUIwidget() 
	{
		return getTblList2();
	}	
	public Readings_FormList() 
	{
		super();
		initialize();
	}
	private void initialize()
	{
		setBorderWidth(10);
		add(getTblList2());
		tblList2.setSize(GwtINI.tblList_Width, "300px");
	}
	 //ТАБЛИЦА ПОКАЗАНИЙ
	 private DataGrid<GwtDataRow> getTblList2()
	 {
		if (tblList2 == null) 
		{
			tblList2 = new DataGrid<GwtDataRow>(1000,GWT.<TableResources> create(TableResources.class));
			tblList2.setLoadingIndicator(null);
			//----1.Дата начала-----		    
			DateCell dateCell1 = new DateCell(DateTimeFormat.getFormat("dd.MM.yyyy"));
			Column<GwtDataRow, Date> column1StartDate = new Column<GwtDataRow, Date>(dateCell1) {
			  @Override
			  public Date getValue(GwtDataRow object) {
				  return (Date)object.takeValue("MSRBEGINDATE");
			  }
			};
			tblList2.addColumn(column1StartDate, "Дата начала");
			tblList2.setColumnWidth(column1StartDate, 50, Unit.PX);
			//----------------------
		    
			//----2.Дата окончания-----		    
			DateCell dateCell2 = new DateCell(DateTimeFormat.getFormat("dd.MM.yyyy"));
			Column<GwtDataRow, Date> column2StopDate = new Column<GwtDataRow, Date>(dateCell2) {
			  @Override
			  public Date getValue(GwtDataRow object) {
				  return (Date)object.takeValue("MSRENDDATE");
			  }
			};
			tblList2.addColumn(column2StopDate, "Дата окончания");
			tblList2.setColumnWidth(column2StopDate, 50, Unit.PX);
			//----------------------
		    // ----3.Статус показаний------------------
			TextColumn<GwtDataRow> column3Status = new TextColumn<GwtDataRow>() {
				@Override
				public String getValue(GwtDataRow object)
				{
					return (String)object.takeValue("MsrStateValueAsString");
				}
			};
			tblList2.addColumn(column3Status, "Статус показания");
			tblList2.setColumnWidth(column3Status, 70, Unit.PX);
			// ----------------------		
			// ----4.Значение------------------
			Column<GwtDataRow, Number> column4Value = new Column<GwtDataRow, Number>(new NumberCell()) {
		        @Override
		        public Number getValue(GwtDataRow object) {		      	
		        return (Number)object.takeValue("RESULT");
		        }
		      };
		    tblList2.addColumn(column4Value, "Значение,м3");
			tblList2.setColumnWidth(column4Value, 50, Unit.PX);
			//----------------------			
			// ----5.Объем------------------
			Column<GwtDataRow, Number> column5Volume = new Column<GwtDataRow, Number>(new NumberCell()) {
		        @Override
		        public Number getValue(GwtDataRow object) {
		          return (Number)object.takeValue("VOLUME1");
		        }
		      };
		    tblList2.addColumn(column5Volume, "Объем,м3");
			tblList2.setColumnWidth(column5Volume, 50, Unit.PX);
			//----------------------			
			// ----6.Тип съема------------------
			TextColumn<GwtDataRow> column5Type = new TextColumn<GwtDataRow>() {
				@Override
				public String getValue(GwtDataRow object) {
					return (String)object.takeValue("TakeTypeAsString");
				}
			};
			tblList2.addColumn(column5Type, "Тип съема");
			tblList2.setColumnWidth(column5Type, 50, Unit.PX);
			// ----------------------			
			//----7.Год-----
			TextColumn<GwtDataRow> column6Year = new TextColumn<GwtDataRow>() {
		        @Override
		        public String getValue(GwtDataRow object) {
		          return ((Number)object.takeValue("FINYEAR")).toString();
		        }
		      };
		    tblList2.addColumn(column6Year, "Год");
			tblList2.setColumnWidth(column6Year, 30, Unit.PX);
			//----------------
			//----8.Месяц-----		    
			TextColumn<GwtDataRow> column7Month = new TextColumn<GwtDataRow>() {
			  @Override
			  public String getValue(GwtDataRow object) {
				  return ((Number)object.takeValue("FINMONTH")).toString();
			  }
			};
			tblList2.addColumn(column7Month, "Месяц");
			tblList2.setColumnWidth(column7Month, 40, Unit.PX);
			//----------------------			
			// ----9.Пользователь------------------
			TextColumn<GwtDataRow> column8User = new TextColumn<GwtDataRow>() {
				@Override
				public String getValue(GwtDataRow object) {
					return (String)object.takeValue("USRALIAS");
				}
			};
			tblList2.addColumn(column8User, "Пользователь");
			tblList2.setColumnWidth(column8User, 80, Unit.PX);
			// ----------------------			
			//----10.Дата ввода-----		    
			DateCell dateCell5 = new DateCell(DateTimeFormat.getFormat("dd.MM.yyyy"));
			Column<GwtDataRow, Date> column10InputDate = new Column<GwtDataRow, Date>(dateCell5) {
			  @Override
			  public Date getValue(GwtDataRow object) {
				  return (Date)object.takeValue("MSRCALENDARDATE");
			  }
			};
			tblList2.addColumn(column10InputDate, "Дата ввода");
			tblList2.setColumnWidth(column10InputDate, 60, Unit.PX);
			//----------------------			
			//----11.Время ввода-----		    
			DateCell dateCell6 = new DateCell(DateTimeFormat.getFormat("hh.mm"));
			Column<GwtDataRow, Date> column11InputTime = new Column<GwtDataRow, Date>(dateCell6) {
			  @Override
			  public Date getValue(GwtDataRow object) {
				  return (Date)object.takeValue("MSRCALENDARTIME");
			}
		};
			tblList2.addColumn(column11InputTime, "Время ввода");
			tblList2.setColumnWidth(column11InputTime, 60, Unit.PX);
			//----------------------		
			tblList2.setEmptyTableWidget(new Label("Empty"));
		}
		return tblList2;
		}
	 }
