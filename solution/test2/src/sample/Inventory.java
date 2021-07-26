package sample;

import javafx.beans.property.*;


public class Inventory {

    private StringProperty Value = new SimpleStringProperty(this, "Value", "");
    public String getValue() {
        return Value.get();
    }
    public StringProperty valueProperty() {
        return Value;
    }
    public void setValue(String value) {
        this.Value.set(value);
    }

    private StringProperty serialnumber = new SimpleStringProperty(this, "serialnumber", "");
    public String getSerialnumber() {return serialnumber.get();}
    public StringProperty serialnumberProperty() {return serialnumber;}
    public void setSerialnumber(String serialnumber) {
        this.serialnumber.set(serialnumber);
    }

    private StringProperty inventorynumber = new SimpleStringProperty(this, "inventorynumber", "");
    public String getInventorynumber() {
        return inventorynumber.get();
    }
    public StringProperty inventorynumberProperty() {
        return inventorynumber;
    }
    public void setInventorynumber(String inventorynumber) {
        this.inventorynumber.set(inventorynumber);
    }

    private DoubleProperty gradepoint = new SimpleDoubleProperty(this, "gradePoint", 0.0);
    public double getGradepoint() {
        return gradepoint.get();
    }
    public DoubleProperty gradepointProperty() {
        return gradepoint;
    }
    public void setGradepoint(double gradepoint) {
        this.gradepoint.set(gradepoint);
    }

    private StringProperty uin = new SimpleStringProperty(this, "uin", "");
    public String getUin() {
        return uin.get();
    }
    public StringProperty uinProperty() {
        return uin;
    }
    public void setUin(String uin) {
        this.uin.set(uin);
    }

    private StringProperty netID = new SimpleStringProperty(this, "netID", "");
    public String getNetID() {
        return netID.get();
    }
    public StringProperty netIDProperty() {
        return netID;
    }
    public void setNetID(String netID) {
        this.netID.set(netID);
    }

    private IntegerProperty exportproperty = new SimpleIntegerProperty(this, "exportproperty", 0);
    public int getExportproperty() {
        return exportproperty.get();
    }
    public IntegerProperty exportpropertyProperty() {
        return exportproperty;
    }
    public void setExportproperty(int exportproperty) {
        this.exportproperty.set(exportproperty);
    }

    private StringProperty exportfile = new SimpleStringProperty(this, "exportfile", "");
    public String getExportfile() {
        return exportfile.get();
    }
    public StringProperty exportfileProperty() {
        return exportfile;
    }
    public void setExportfile(String exportfile) {
        this.exportfile.set(exportfile);
    }


    //for console printing purposes
    public String toString() {

        return "First Name: " + getValue() + " | Last Name: " + getSerialnumber() + " | UIN: " + getUin() + " | NetID: " + getNetID() + " | Major: " + getInventorynumber() + " | Age: " + getExportproperty() + " | GPA: " + getGradepoint() + " | Gender: " + getExportfile();
    }
}
