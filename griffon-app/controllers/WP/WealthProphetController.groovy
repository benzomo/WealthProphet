package WP

import griffon.core.artifact.GriffonController
import griffon.core.controller.ControllerAction
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Threading
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.scene.chart.PieChart
import javafx.scene.chart.XYChart

import javax.annotation.Nonnull
import java.math.RoundingMode


@ArtifactProviderFor(GriffonController)
class WealthProphetController {
    @MVCMember @Nonnull
    WealthProphetModel model


    @MVCMember @Nonnull
    WealthProphetView view

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void click() {
        int count = model.clickCount.toInteger()
        model.clickCount = String.valueOf(count + 1)
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void addIncome() {
        model.dataYinc.add(new YearlyIncome(view.txtinc.getText(), view.valinc.getText()))
        view.txtinc.clear()
        view.valinc.clear()
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void addSexp() {
        model.dataMEsurv.add(new MonthlyExpense(view.txtSexp.getText(), view.valSexp.getText()))
        view.txtSexp.clear()
        view.valSexp.clear()
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void addBexp() {
        model.dataMEbasic.add(new MonthlyExpense(view.txtBexp.getText(), view.valBexp.getText()))
        view.txtBexp.clear()
        view.valBexp.clear()
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void addOexp() {
        model.dataMEother.add(new MonthlyExpense(view.txtOexp.getText(), view.valOexp.getText()))
        view.txtOexp.clear()
        view.valOexp.clear()
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void addAss() {
        model.dataAss.add(new Asset(view.txtAss.getText(), view.valAss.getText()))
        view.txtAss.clear()
        view.valAss.clear()
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void addLiab() {
        model.dataLiab.add(new Liability(view.txtLiab.getText(), view.valLiab.getText()))
        view.txtLiab.clear()
        view.valLiab.clear()
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void updatePie() {

        model.budgetPi = FXCollections.observableArrayList(

                new PieChart.Data("Survival", model.dataMEsurv[0..model.dataMEsurv.size()-1].value*.toBigDecimal().sum()),
                new PieChart.Data("Basic", model.dataMEbasic[0..model.dataMEbasic.size()-1].value*.toBigDecimal().sum()),
                new PieChart.Data("Other", model.dataMEother[0..model.dataMEother.size()-1].value*.toBigDecimal().sum()),
                new PieChart.Data("Savings", model.dataYinc[0..model.dataYinc.size()-1].value*.toBigDecimal().sum()/12 -
                        model.dataMEsurv[0..model.dataMEsurv.size()-1].value*.toBigDecimal().sum() -
                        model.dataMEbasic[0..model.dataMEbasic.size()-1].value*.toBigDecimal().sum() -
                        model.dataMEother[0..model.dataMEother.size()-1].value*.toBigDecimal().sum(),

                ),
        )
        view.pi1.data = model.budgetPi
        view.pi1L1.text =  "Survival costs = " + model.budgetPi[0].pieValue.toString() + "  (" +  (model.budgetPi[0].pieValue.toBigDecimal().divide(model.budgetPi.pieValue.sum(), 1, RoundingMode.HALF_EVEN)*100).toString() + "%)"
        view.pi1L2.text = "Basic costs = " + model.budgetPi[1].pieValue.toString() + "  (" +  (model.budgetPi[1].pieValue.toBigDecimal().divide(model.budgetPi.pieValue.sum(), 1, RoundingMode.HALF_EVEN)*100).toString() + "%)"

        view.pi1L3.text = "Other costs = " + model.budgetPi[2].pieValue.toString() + "  (" +  (model.budgetPi[2].pieValue.toBigDecimal().divide(model.budgetPi.pieValue.sum(), 1, RoundingMode.HALF_EVEN)*100).toString() + "%)"
        view.pi1L4.text = "% of Income Saved = (" +  (model.budgetPi[3].pieValue.toBigDecimal().divide(model.budgetPi.pieValue.sum(), 1, RoundingMode.HALF_EVEN)*100).toString() + "%)"

    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void nextYear() {

        model.addedYear = model.addedYear + 1
        BigDecimal nextYearAss
        BigDecimal nextYearLiab
        BigDecimal nextYearNW

        if(model.dataLiab[0].value.toBigDecimal() < 0.0){
            model.dataMEbasic[0].value = "0.0"
        }


        if(model.dataLiab[1].value.toBigDecimal() < 0.0){
            model.dataMEbasic[1].value = "0.0"
        }

        nextYearAss = model.nwAss[model.nwAss.size()-1].YValue + model.dataFinInc[0].value.toBigDecimal()*12 + model.budgetPi[3].pieValue*12

        if(model.nwLiab[model.nwLiab.size()-1].YValue < 0.0){

            nextYearLiab = model.nwLiab[model.nwLiab.size()-1].YValue
        }
        else{
            nextYearLiab = model.nwLiab[model.nwLiab.size()-1].YValue + model.nwLiab[model.nwLiab.size()-1].YValue*Calc.getReal(model.xx, model.xx[2].value) - model.dataMEbasic[0].value.toBigDecimal()*12 - model.dataMEbasic[1].value.toBigDecimal()*12
        }
        nextYearNW = nextYearAss - nextYearLiab

        model.nwAss.add(new XYChart.Data<String, BigDecimal>((Calendar.getInstance().get(Calendar.YEAR)+model.addedYear).toString(), nextYearAss))
        model.nwLiab.add(new XYChart.Data<String, BigDecimal>((Calendar.getInstance().get(Calendar.YEAR)+model.addedYear).toString(), nextYearLiab))
        model.nwNW.add(new XYChart.Data<String, BigDecimal>((Calendar.getInstance().get(Calendar.YEAR)+model.addedYear).toString(), nextYearNW))
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void switchSc() {
        def i
        i = model.currScene

        i++

        if(i == 3){
            i = 0
        }
        model.currScene = i
        view.mystage.setScene(view.myscenes[i])

    }

}