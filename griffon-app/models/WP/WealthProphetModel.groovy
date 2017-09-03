package WP

import griffon.core.artifact.GriffonModel
import javafx.collections.ObservableArray
import griffon.transform.Observable
import groovy.beans.Bindable
import groovyx.javafx.beans.FXBindable
import griffon.transform.PropertyListener
import javafx.collections.FXCollections
import griffon.transform.FXObservable
import griffon.metadata.ArtifactProviderFor
import java.math.RoundingMode
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.beans.property.StringProperty
import javax.annotation.Nonnull

@ArtifactProviderFor(GriffonModel)
class WealthProphetModel {

    @FXObservable clickCount = "0" //TESTING AREA_________________________
    //____________________________________
    //_____________________________________
    //_____________________________________

    //_________INITIALIZE!!!!!!!!!!_______________




    @FXObservable ObservableList<Person> data = FXCollections.observableArrayList(
            a, b, c, d, e
    )

    @FXObservable def f = new MonthlyExpense("Rent", "1500")
    @FXObservable ObservableList<MonthlyExpense> data2 = FXCollections.observableArrayList(
            f, f, f, f, f
    )
    @FXObservable def g = new YearlyIncome("typr1", "55000")
    @FXObservable ObservableList<YearlyIncome> dataYinc = FXCollections.observableArrayList(
            g, g, g
    )

    @FXObservable def a = new Person("0.03", "0.02", "0.01")
    @FXObservable def b = new Person("Isabella", "Johnson", "isabella.johnson@example.com")
    @FXObservable def c = new Person("Ethan", "Williams", "ethan.williams@example.com")
    @FXObservable def d = new Person("Emma", "Jones", "emma.jones@example.com")
    @FXObservable def e = new Person("Michael", "Brown", "michael.brown@example.com")



    //____________RATES TABLE____________________

    @FXObservable def globalVarsR = new GlobalVarsREAL(0.02, 0.04, 0.05, 0.05, 0.05, 0.05, 0.16, 0.04, 0.075)
    @FXObservable def varsG = new GlobalVarsRCol("0.02", "0.04", "0.05", "0.05", "0.05", "0.05", "0.16", "0.04", "0.075")
    @FXObservable def varsG_str = new Gvars_str("0.02", "0.035", "0.05", "0.05", "0.05", "0.05", "0.16", "0.04", "0.075")
    @FXObservable def varsG_num = varsG.gv


    @FXObservable ObservableList<Gvars_str> data1 = FXCollections.observableArrayList(
            varsG_str,  varsG_str,  varsG_str,  varsG_str
   )

}

@FXBindable
class MonthlyExpense{

    String type
    String value

    public MonthlyExpense(String typeIN, String valueIN){
        this.type = typeIN
        this.value = valueIN
    }

}


@FXBindable
class YearlyIncome{

    String type
    String value

    public YearlyIncome(String typeIN, String valueIN){
        this.type = typeIN
        this.value = valueIN
    }

}

@FXBindable
class Infl{

    String type
    String value

    public YearlyIncome(String typeIN, String valueIN){
        this.type = typeIN
        this.value = valueIN
    }

}
@FXBindable
class I{

    String type
    String value

    public YearlyIncome(String typeIN, String valueIN){
        this.type = typeIN
        this.value = valueIN
    }

}

@FXBindable
class ILoC{

    String type
    String value

    public YearlyIncome(String typeIN, String valueIN){
        this.type = typeIN
        this.value = valueIN
    }

}
@FXBindable
class ILoan{

    String type
    String value

    public YearlyIncome(String typeIN, String valueIN){
        this.type = typeIN
        this.value = valueIN
    }

}
@FXBindable
class IStLoan{

    String type
    String value

    public YearlyIncome(String typeIN, String valueIN){
        this.type = typeIN
        this.value = valueIN
    }

}
@FXBindable
class ICarLoan{

    String type
    String value

    public YearlyIncome(String typeIN, String valueIN){
        this.type = typeIN
        this.value = valueIN
    }

}

@FXBindable
class Icc{

    String type
    String value

    public YearlyIncome(String typeIN, String valueIN){
        this.type = typeIN
        this.value = valueIN
    }

}

@FXBindable
class RBond{

    String type
    String value

    public YearlyIncome(String typeIN, String valueIN){
        this.type = typeIN
        this.value = valueIN
    }

}

@FXBindable
class RStock{

    String type
    String value

    public YearlyIncome(String typeIN, String valueIN){
        this.type = typeIN
        this.value = valueIN
    }

}



// set the global variables
public class GlobalVarsREAL {

    def infl
    def i
    def iLoC
    def iLoan
    def iStLoan
    def iCarLoan
    def icc
    def rBond
    def rStock


    private static getReal(nom, infl){
        if(nom.getClass()==ArrayList.class){
            nom.eachWithIndex { nomi, i ->
                nom[i] = (nomi+1).divide(infl +1, 2, RoundingMode.HALF_EVEN) - 1
            }
            return nom
        }
        else{
            return (nom+1).divide(infl +1, 2, RoundingMode.HALF_EVEN) - 1
        }

    }

    GlobalVarsREAL(infl, i, iLoC, iLoan, iStLoan, iCarLoan, icc, rBond, rStock){
        this.infl = infl
        this.i = i
        this.iLoC = iLoC
        this.iLoan = iLoan
        this.iStLoan = iStLoan
        this.iCarLoan = iCarLoan
        this.icc = icc
        this.rBond = rBond
        this.rStock = rStock

    }

    GlobalVarsREAL(){
        this.infl = (0.02).toBigDecimal()
        this.i = getReal(0.04, infl)
        this.iLoC = getReal(0.05, infl)
        this.iLoan = getReal(0.05, infl)
        this.iStLoan = getReal(0.05, infl)
        this.iCarLoan = getReal(0.05, infl)
        this.icc = getReal(0.17, infl)
        this.rBond = getReal(0.04, infl)
        this.rStock = getReal(0.075, infl)

    }



}

@FXObservable public class Gvars_str {

    public final SimpleStringProperty infl
    public final SimpleStringProperty i
    public final SimpleStringProperty iLoC
    public final SimpleStringProperty iLoan
    public final SimpleStringProperty iStLoan
    public final SimpleStringProperty iCarLoan
    public final SimpleStringProperty icc
    public final SimpleStringProperty rBond
    public final SimpleStringProperty rStock

    public Gvars_str(String Infl, String I, String ILoC, String ILoan, String IStLoan, String ICarLoan, String Icc, String RBond, String RStock) {
        this.infl = new SimpleStringProperty(Infl)
        this.i = new SimpleStringProperty(I)
        this.iLoC = new SimpleStringProperty(ILoC)
        this.iLoan = new SimpleStringProperty(ILoan)
        this.iStLoan = new SimpleStringProperty(IStLoan)
        this.iCarLoan = new SimpleStringProperty(ICarLoan)
        this.icc = new SimpleStringProperty(Icc)
        this.rBond = new SimpleStringProperty(RBond)
        this.rStock = new SimpleStringProperty(RStock)
    }
    public String getInfl() {
        return infl.get();
    }
    public void setInfl(infl1) {
        infl.set(infl1);
    }

    public String getI() {
        return i.get();
    }
    public void setI(i1) {
        i.set(i1);
    }

    public String getiLoC() {
        return iLoC.get();
    }
    public void setiLoC(iLoC1) {
        iLoC.set(iLoC1);
    }

    public String getiLoan() {
        return iLoan.get();
    }
    public void setiLoan(iLoan1) {
        iLoan.set(iLoan1);
    }

    public String getiStLoan() {
        return iStLoan.get();
    }
    public void setiStLoan(iStLoan1) {
        iStLoan.set(iStLoan1);
    }

    public String getiCarLoan() {
        return iCarLoan.get();
    }
    public void setiCarLoan(iCarLoan1) {
        iCarLoan.set(iCarLoan1);
    }

    public String getIcc() {
        return icc.get();
    }
    public void setIcc(icc1) {
        icc.set(icc1);
    }

    public String getrBond() {
        return rBond.get();
    }
    public void setrBond(rBond1) {
        rBond.set(rBond1);
    }

    public String getrStock() {
        return rStock.get();
    }
    public void setrStock( rStock1) {
        rStock.set(rStock1);
    }

}


@FXObservable public class GlobalVarsRCol{

    public GlobalVarsREAL gv
    public final SimpleStringProperty infl
    public final SimpleStringProperty i
    public final SimpleStringProperty iLoC
    public final SimpleStringProperty iLoan
    public final SimpleStringProperty iStLoan
    public final SimpleStringProperty iCarLoan
    public final SimpleStringProperty icc
    public final SimpleStringProperty rBond
    public final SimpleStringProperty rStock

    public GlobalVarsRCol(String INFL, String I, String ILOC, String ILOAN, String ISTLOAN, String ICARLOAN, String ICC, String RBOND, String RSTOCK) {
        this.gv = new GlobalVarsREAL(INFL.toBigDecimal(),  I.toBigDecimal(),  ILOC.toBigDecimal(),  ILOAN.toBigDecimal(),  ISTLOAN.toBigDecimal(),  ICARLOAN.toBigDecimal(), ICC.toBigDecimal(),  RBOND.toBigDecimal(),  RSTOCK.toBigDecimal())
        this.infl = new SimpleStringProperty(INFL)
        this.i = new SimpleStringProperty(I)
        this.iLoC = new SimpleStringProperty(ILOC)
        this.iLoan = new SimpleStringProperty(ILOAN)
        this.iStLoan = new SimpleStringProperty(ISTLOAN)
        this.iCarLoan = new SimpleStringProperty(ICARLOAN)
        this.icc = new SimpleStringProperty(ICC)
        this.rBond = new SimpleStringProperty(RBOND)
        this.rStock = new SimpleStringProperty(RSTOCK)
    }


    public getinfl() {
        return infl.get();
    }
    public void setinfl(infl1) {
        infl.set(infl1);
    }

    public geti() {
        return i.get();
    }
    public void seti(i1) {
        i.set(i1);
    }

    public getiLoC() {
        return iLoC.get();
    }
    public void setiLoC(iLoC1) {
        iLoC.set(iLoC1);
    }

    public getiLoan() {
        return iLoan.get();
    }
    public void setiLoan(iLoan1) {
        iLoan.set(iLoan1);
    }

    public getiStLoan() {
        return iStLoan.get();
    }
    public void setiStLoan(iStLoan1) {
        iStLoan.set(iStLoan1);
    }

    public getiCarLoan() {
        return iCarLoan.get();
    }
    public void setiCarLoan(iCarLoan1) {
        iCarLoan.set(iCarLoan1);
    }

    public geticc() {
        return icc.get();
    }
    public void seticc(icc1) {
        icc.set(icc1);
    }

    public getrBond() {
        return rBond.get();
    }
    public void setrBond(rBond1) {
        rBond.set(rBond1);
    }

    public getrStock() {
        return rStock.get();
    }
    public void setrStock( rStock1) {
        rStock.set(rStock1);
    }



}



class InputModel {

    //Inputs for Income:

    String salary = "55000"
    String rentalGrossI = "0"
    String businessDiv = "0"
    String businessNI = "0"


    //Inputs for Expenses:

    //Monthly Expenses:
    ArrayList Mexp = ["800", "550", "700"]
    ArrayList Mexpid = ["Rent", "Food", "Other"]

    //Yearly Expenses:
    ArrayList Yexp = ["1000", "800", "1500"]
    ArrayList Yexpid = ["Taxes", "Christmas", "Travel"]

    //One Time Expenses:
    ArrayList buyCar = ["20000", "20000"]
    ArrayList buyCarYear = ["10", "25"]
    ArrayList buyHouse = ["300000", "300000"]
    ArrayList buyHouseYear = ["5", "50"]
    ArrayList weddingExp = ["10000", "5000", "2500"]
    ArrayList weddingExpType = ["Ring", "Wedding", "Honey Moon"]
    String weddingExpYear = "2"

    //Inputs for Assets:
    ArrayList vehicles = ["8000", "0", "0"]
    ArrayList vehiclesDepr = ["0.08", "0.08", "0.08"]
    String equityPrimary = "0"
    ArrayList equityOther = ["0", "0", "0", "0"]
    ArrayList equityOtherType = ["Houses", "Town-Houses", "Condos", "Commercial/Appartment Buildings"]
    ArrayList portfolioFin = ["23000", "1", "0"]
    ArrayList portfolioFinType = ["Total", "%Stock", "%Bonds"]


    //Inputs for Liabilities:
    ArrayList creditC = ["0", "0", "0"]
    ArrayList creditCid = ["Bank1", "Bank2", "Bank3"]
    ArrayList lineofC = ["27000", "0", "0"]
    ArrayList lineofCid = ["Bank1", "Bank2", "Bank3"]
    ArrayList sLoan = ["12000", "9000", "0", "0"]
    ArrayList sLoanType = ["Federal", "Provincial", "Bank", "Other"]
    ArrayList carLoan = ["0", "0", "0"]
    ArrayList carLoanid = ["Car1", "Car2", "Car3"]
    // CALCULATED-----------------*******
    String netW = "0"
    ArrayList mortOwing = ["0", "0", "0", "0"]
    ArrayList mortOwingType = ["Houses", "Town-Houses", "Condos", "Commercial/Appartment Buildings"]
}

@FXObservable public class Person {
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;

    private Person(String fName, String lName, String email) {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
    }

    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String fName) {
        firstName.set(fName);
    }

    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String fName) {
        lastName.set(fName);
    }

    public String getEmail() {
        return email.get();
    }
    public void setEmail(String fName) {
        email.set(fName);
    }

}