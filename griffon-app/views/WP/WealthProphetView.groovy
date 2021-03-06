package WP

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import griffon.transform.FXObservable
import javafx.scene.control.cell.TextFieldListCell
import javafx.scene.paint.Color

import javax.annotation.Nonnull
import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import griffon.transform.ChangeListener
import javax.annotation.Nonnull
import groovy.transform.InheritConstructors
import javafx.scene.control.cell.PropertyValueFactory
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.TableColumn.CellEditEvent
import javafx.scene.control.cell.TextFieldTableCell
import javafx.event.Event
import groovyx.javafx.beans.FXBindable
import java.math.RoundingMode




@ArtifactProviderFor(GriffonView)
class WealthProphetView {
    @MVCMember @Nonnull
    FactoryBuilderSupport builder
    @MVCMember @Nonnull
    WealthProphetModel model


    def txtinc
    def valinc
    def txtSexp
    def valSexp
    def txtBexp
    def valBexp
    def txtOexp
    def valOexp
    def txtAss
    def valAss
    def txtLiab
    def valLiab

    @FXBindable def bar
    @FXBindable def pi1, pi1L1, pi1L2, pi1L3, pi1L4
    @FXBindable def myscenes = []
    def myapp
    @FXBindable def mystage
    def rates

    void initUI() {
        myapp = builder.application(title: application.configuration['application.title'], sizeToScene: true, centerOnScreen: true, name: 'mainWindow') {

            scene(id: 'sc3', fill: "#FFCCE5") {

                v = vbox(prefHeight: 800.0, prefWidth: 1600.0){
                    menuBar(vgrow: 'NEVER'){

                        menu(text: "File"){
                            menuItem(text: "New")
                            menuItem(text: "Open…")
                            menuItem(text: "Open Recent")
                            separatorMenuItem()
                            menuItem(text: "Close")
                            menuItem(text: "Save")
                            menuItem(text: "Save As…")
                            menuItem(text: "Revert")
                            separatorMenuItem()
                            menuItem(text: "Preferences…")
                            separatorMenuItem()
                            menuItem(text: "Quit")

                        }
                        menu(text:"Edit"){
                            menuItem(text:"Undo")
                            menuItem(text:"Redo")
                            separatorMenuItem()
                            menuItem(text:"Cut")
                            menuItem(text:"Copy")
                            menuItem(text:"Paste")
                            menuItem(text:"Delete")
                            separatorMenuItem()
                            menuItem(text:"Select All")
                            menuItem(text:"Unselect All")

                        }


                        menu(text:"Help"){
                            menuItem(text:"About this App")
                        }


                    }
                    splitPane( dividerPositions: 0.3674832962138085, focusTraversable: true, prefHeight: -1.0, prefWidth: -1.0, vgrow: 'ALWAYS') {

                        anchorPane(){
                            label(alignment: 'CENTER', layoutX: 14.0, layoutY: 14.0, minWidth: 60.0, prefWidth: -1.0, style: "&#10;", text:"Master", textAlignment:'CENTER', wrapText: false)

                            splitPane( dividerPositions: 0.5, layoutX: 4.0, layoutY: 43.0, orientation: 'VERTICAL', prefHeight: 504.0, prefWidth: 319.0, bottomAnchor: 0.0, leftAnchor: 0.0,  rightAnchor: 0.0, topAnchor:0.0) {
                                splitPane( dividerPositions: 0.4707692307692308, layoutY: 37.0, prefHeight: 255.0, prefWidth: 327.0){
                                    anchorPane( minHeight: 0.0 ,minWidth: 0.0 ,prefHeight: 290.0, prefWidth: 463.0) {
                                        hbox(layoutX: 6.0, layoutY: 1.0, prefHeight: 25.0, prefWidth: 150.0)
                                        accordion(layoutY: 25.0, prefHeight: 253.0, prefWidth: 150.0, bottomAnchor: 0.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 0.0) {
                                            titledPane(animated: true, text: "Yearly Income") {
                                                anchorPane(minHeight: 0.0, minWidth: 0.0, prefHeight: 190.0, prefWidth: 90.0) {

                                                    vbox(layoutX: 31.6, layoutY: 10.6, prefHeight: 152.0, prefWidth: 200.0, bottomAnchor: 0.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 0.0) {
                                                        yInc = tableView(layoutX: 31.6, layoutY: 10.6, prefHeight: 125.0, prefWidth: 200.0, bottomAnchor: 0.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 0.0, editable: true) {
                                                            tcTxtInc = tableColumn(minWidth: 0.0, prefWidth: 125.0, text: "Description")
                                                            tcTxtInc.setCellValueFactory(
                                                                    new PropertyValueFactory<YearlyIncome, String>("type"))
                                                            tcValInc = tableColumn(prefWidth: 150.0, text: "Yearly Income (\$)")
                                                            tcValInc.setCellFactory(TextFieldTableCell.forTableColumn())
                                                            tcValInc.setCellValueFactory(
                                                                    new PropertyValueFactory<YearlyIncome, String>("value"))
                                                            tcValInc.setOnEditCommit(
                                                                    new EventHandler<CellEditEvent<YearlyIncome, String>>() {
                                                                        @Override
                                                                        public void handle(CellEditEvent<YearlyIncome, String> t) {
                                                                            ((YearlyIncome) t.getTableView().getItems().get(
                                                                                    t.getTablePosition().getRow())
                                                                            ).value = t.getNewValue()
                                                                        }
                                                                    })
                                                        }
                                                        yInc.setItems(model.dataYinc)

                                                        label(text: "Enter Description")
                                                        this.txtinc = textField()
                                                        txtinc.setPromptText("ex. 'Rental Income'")
                                                        label(text: "Enter Value")
                                                        this.valinc = textField()
                                                        valinc.setPromptText("ex. '18000'  (1500*12)")
                                                        butADDinc = button(text: "ADD", prefWidth: 200.0, addIncomeAction)
                                                    }
                                                }


                                            }
                                            titledPane(animated: true, text: "Capital Income") {
                                                anchorPane(minHeight: 0.0, minWidth: 0.0, prefHeight: 180.0, prefWidth: 200.0) {
                                                    vbox(layoutX: 31.6, layoutY: 10.6, prefHeight: 152.0, prefWidth: 200.0, bottomAnchor: 0.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 0.0) {
                                                        yInc = tableView(layoutX: 31.6, layoutY: 10.6, prefHeight: 125.0, prefWidth: 200.0, bottomAnchor: 0.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 0.0, editable: true) {
                                                            tcTxtInc = tableColumn(minWidth: 0.0, prefWidth: 125.0, text: "Description")
                                                            tcTxtInc.setCellValueFactory(
                                                                    new PropertyValueFactory<YearlyIncome, String>("type"))
                                                            tcValInc = tableColumn(prefWidth: 150.0, text: "Yearly Income (\$)")
                                                            tcValInc.setCellFactory(TextFieldTableCell.forTableColumn())
                                                            tcValInc.setCellValueFactory(
                                                                    new PropertyValueFactory<YearlyIncome, String>("value"))

                                                        }
                                                        yInc.setItems(model.dataFinInc)

                                                    }

                                                }

                                            }

                                        }
                                    }
                                    anchorPane( minHeight: 0.0 ,minWidth: 0.0 ,prefHeight: 290.0, prefWidth: 463.0) {

                                        hbox(prefHeight: 25.0, prefWidth: 169.0)
                                        accordion(layoutY: 24.0, prefHeight: 253.0, prefWidth: 169.0, bottomAnchor: 0.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 0.0) {
                                            titledPane(animated: true, text: "Survival Expenses") {
                                                anchorPane(minHeight: 0.0, minWidth: 0.0, prefHeight: 190.0, prefWidth: 90.0) {
                                                    vbox(layoutX: 31.6, layoutY: 10.6, prefHeight: 152.0, prefWidth: 200.0, bottomAnchor: 0.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 0.0){
                                                        exp = tableView(editable: true) {
                                                            tableColumn(id: 'typeCol')
                                                            typeCol.setMinWidth(100);
                                                            typeCol.setCellValueFactory(
                                                                    new PropertyValueFactory<MonthlyExpense, String>("type"))


                                                            tableColumn(id: 'valueCol')
                                                            valueCol.setMinWidth(100)
                                                            valueCol.setCellFactory(TextFieldTableCell.forTableColumn())
                                                            valueCol.setCellValueFactory(
                                                                    new PropertyValueFactory<MonthlyExpense, String>("value"))
                                                            valueCol.setOnEditCommit(
                                                                    new EventHandler<CellEditEvent<MonthlyExpense, String>>() {
                                                                        @Override
                                                                        public void handle(CellEditEvent<MonthlyExpense, String> t) {
                                                                            ((MonthlyExpense) t.getTableView().getItems().get(
                                                                                    t.getTablePosition().getRow())
                                                                            ).value = t.getNewValue()
                                                                        }
                                                                    })
                                                        }
                                                        exp.setItems(model.dataMEsurv)

                                                        label(text: "Enter Description")
                                                        this.txtSexp = textField()
                                                        txtSexp.setPromptText("ex. 'survival exp'")
                                                        label(text: "Enter Value")
                                                        this.valSexp = textField()
                                                        valSexp.setPromptText("ex. '150")
                                                        butADD = button(text: "ADD", prefWidth: 200.0, addSexpAction)

                                                    }

                                                }

                                            }
                                            titledPane(animated: true, text: "Basic Expenses") {
                                                anchorPane(minHeight: 0.0, minWidth: 0.0, prefHeight: 180.0, prefWidth: 200.0) {
                                                    vbox(layoutX: 31.6, layoutY: 10.6, prefHeight: 152.0, prefWidth: 200.0, bottomAnchor: 0.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 0.0){
                                                        exp = tableView(editable: true) {
                                                            tableColumn(id: 'typeCol')
                                                            typeCol.setMinWidth(100);
                                                            typeCol.setCellValueFactory(
                                                                    new PropertyValueFactory<MonthlyExpense, String>("type"))


                                                            tableColumn(id: 'valueCol')
                                                            valueCol.setMinWidth(100)
                                                            valueCol.setCellFactory(TextFieldTableCell.forTableColumn())
                                                            valueCol.setCellValueFactory(
                                                                    new PropertyValueFactory<MonthlyExpense, String>("value"))
                                                            valueCol.setOnEditCommit(
                                                                    new EventHandler<CellEditEvent<MonthlyExpense, String>>() {
                                                                        @Override
                                                                        public void handle(CellEditEvent<MonthlyExpense, String> t) {
                                                                            ((MonthlyExpense) t.getTableView().getItems().get(
                                                                                    t.getTablePosition().getRow())
                                                                            ).value = t.getNewValue()
                                                                        }
                                                                    })
                                                        }
                                                        exp.setItems(model.dataMEbasic)

                                                        label(text: "Enter Description")
                                                        this.txtBexp = textField()
                                                        txtBexp.setPromptText("ex. 'survival exp'")
                                                        label(text: "Enter Value")
                                                        this.valBexp = textField()
                                                        valBexp.setPromptText("ex. '150")
                                                        butADD = button(text: "ADD", prefWidth: 200.0, addBexpAction)

                                                    }

                                                }

                                            }
                                            titledPane(animated: true, text: "Other Expenses") {
                                                anchorPane(minHeight: 0.0, minWidth: 0.0, prefHeight: 180.0, prefWidth: 200.0) {
                                                    vbox(layoutX: 31.6, layoutY: 10.6, prefHeight: 152.0, prefWidth: 200.0, bottomAnchor: 0.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 0.0){
                                                        exp = tableView(editable: true) {
                                                            tableColumn(id: 'typeCol')
                                                            typeCol.setMinWidth(100);
                                                            typeCol.setCellValueFactory(
                                                                    new PropertyValueFactory<MonthlyExpense, String>("type"))


                                                            tableColumn(id: 'valueCol')
                                                            valueCol.setMinWidth(100)
                                                            valueCol.setCellFactory(TextFieldTableCell.forTableColumn())
                                                            valueCol.setCellValueFactory(
                                                                    new PropertyValueFactory<MonthlyExpense, String>("value"))
                                                            valueCol.setOnEditCommit(
                                                                    new EventHandler<CellEditEvent<MonthlyExpense, String>>() {
                                                                        @Override
                                                                        public void handle(CellEditEvent<MonthlyExpense, String> t) {
                                                                            ((MonthlyExpense) t.getTableView().getItems().get(
                                                                                    t.getTablePosition().getRow())
                                                                            ).value = t.getNewValue()
                                                                        }
                                                                    })
                                                        }
                                                        exp.setItems(model.dataMEother)

                                                        label(text: "Enter Description")
                                                        this.txtOexp = textField()
                                                        txtOexp.setPromptText("ex. 'survival exp'")
                                                        label(text: "Enter Value")
                                                        this.valOexp = textField()
                                                        valOexp.setPromptText("ex. '150")
                                                        butADD = button(text: "ADD", prefWidth: 200.0, addOexpAction)

                                                    }

                                                }
                                            }
                                        }
                                    }

                                }
                                anchorPane(minHeight: 0.0, minWidth: 0.0, prefHeight: 100.0, prefWidth: 160.0) {
                                    tabPane(layoutX: 1.0, layoutY: 21.0, prefHeight: 228.0, prefWidth: 317.0, tabClosingPolicy: 'UNAVAILABLE', bottomAnchor: 0.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 0.0) {

                                        tab(text: "Assets/Liabilities") {
                                            anchorPane(minHeight: 0.0, minWidth: 0.0, prefHeight: 180.0, prefWidth: 200.0) {

                                                splitPane(dividerPositions: 0.473015873015873, prefHeight: 219.0, prefWidth: 317.0, bottomAnchor: 0.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 0.0) {
                                                    anchorPane(minHeight: 0.0, minWidth: 0.0, prefHeight: 160.0, prefWidth: 100.0) {
                                                        vbox(layoutX: 31.6, layoutY: 10.6, prefHeight: 152.0, prefWidth: 200.0, bottomAnchor: 0.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 0.0){
                                                            text(text: 'Assets', font: '12pt sanserif') {
                                                                fill linearGradient(endX: 0, stops: [PALEGREEN, SEAGREEN])
                                                            }
                                                            ass = tableView(editable: true) {
                                                                tableColumn(id: 'typeCol')
                                                                typeCol.setMinWidth(100);
                                                                typeCol.setCellValueFactory(
                                                                        new PropertyValueFactory<Asset, String>("type"))


                                                                tableColumn(id: 'valueCol')
                                                                valueCol.setMinWidth(100)
                                                                valueCol.setCellFactory(TextFieldTableCell.forTableColumn())
                                                                valueCol.setCellValueFactory(
                                                                        new PropertyValueFactory<Asset, String>("value"))
                                                                valueCol.setOnEditCommit(
                                                                        new EventHandler<CellEditEvent<Asset, String>>() {
                                                                            @Override
                                                                            public void handle(CellEditEvent<Asset, String> t) {
                                                                                ((Asset) t.getTableView().getItems().get(
                                                                                        t.getTablePosition().getRow())
                                                                                ).value = t.getNewValue()
                                                                            }
                                                                        })
                                                            }
                                                            ass.setItems(model.dataAss)

                                                            label(text: "Enter Description")
                                                            this.txtAss = textField()
                                                            txtAss.setPromptText("ex. 'new car'")
                                                            label(text: "Enter Value")
                                                            this.valAss = textField()
                                                            valAss.setPromptText("ex. '40000")
                                                            butADD = button(text: "ADD", prefWidth: 200.0, addAssAction)

                                                        }
                                                    }
                                                    anchorPane(minHeight: 0.0, minWidth: 0.0, prefHeight: 160.0, prefWidth: 100.0) {

                                                        vbox(layoutX: 31.6, layoutY: 10.6, prefHeight: 152.0, prefWidth: 200.0, bottomAnchor: 0.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 0.0){
                                                            text(text: 'Liabilities', font: '12pt sanserif') {
                                                                fill linearGradient(endX: 0, stops: [RED, PURPLE])
                                                            }
                                                            liab = tableView(editable: true) {
                                                                tableColumn(id: 'typeCol')
                                                                typeCol.setMinWidth(100);
                                                                typeCol.setCellValueFactory(
                                                                        new PropertyValueFactory<Liability, String>("type"))


                                                                tableColumn(id: 'valueCol')
                                                                valueCol.setMinWidth(100)
                                                                valueCol.setCellFactory(TextFieldTableCell.forTableColumn())
                                                                valueCol.setCellValueFactory(
                                                                        new PropertyValueFactory<Liability, String>("value"))
                                                                valueCol.setOnEditCommit(
                                                                        new EventHandler<CellEditEvent<Liability, String>>() {
                                                                            @Override
                                                                            public void handle(CellEditEvent<Liability, String> t) {
                                                                                ((Liability) t.getTableView().getItems().get(
                                                                                        t.getTablePosition().getRow())
                                                                                ).value = t.getNewValue()
                                                                            }
                                                                        })
                                                            }
                                                            liab.setItems(model.dataLiab)

                                                            label(text: "Enter Description")
                                                            this.txtLiab = textField()
                                                            txtLiab.setPromptText("ex. 'new Car Loan'")
                                                            label(text: "Enter Value")
                                                            this.valLiab = textField()
                                                            valLiab.setPromptText("ex. '10000")
                                                            butADD = button(text: "ADD", prefWidth: 200.0, addLiabAction)

                                                        }
                                                    }

                                                }
                                            }
                                        }
                                        tab(text = "Real-Estate") {
                                            anchorPane(minHeight: 0.0, minWidth: 0.0, prefHeight: 180.0, prefWidth: 200.0) {
                                                tableView(layoutX: 2.0, prefHeight: 200.0, prefWidth: 157.0) {
                                                    tableColumn(prefWidth: 75.0, text: "C1")
                                                    tableColumn(prefWidth: 75.0, text: "C2")
                                                }
                                                button(layoutX: 159.0, layoutY: 160.0, mnemonicParsing: false, text: "Button")
                                                button(layoutX: 159.0, layoutY: 135.0, mnemonicParsing: false, text: "Button")
                                                button(layoutX: 159.0, layoutY: 110.0, mnemonicParsing: false, text: "Button")
                                            }

                                        }

                                    }
                                    hbox(layoutY: 3.0, prefHeight: 16.0, prefWidth: 317.0)
                                }
                            }


                        }
                        anchorPane( prefHeight: -1.0,  prefWidth: -1.0){
                            effect dropShadow(color: CYAN, radius: 15, spread: 0.075)
                            hbox{

                                vbox{
                                    pi1 = pieChart(data: model.budgetPi, title: "Budget Pie Chart")
                                    pi1L1 = label(text: "Survival costs = " + model.budgetPi[0].pieValue.toString() + "  (" +  (model.budgetPi[0].pieValue.toBigDecimal().divide(model.budgetPi.pieValue.sum(), 1, RoundingMode.HALF_EVEN)*100).toString() + "%)",
                                            font: '14pt verdana')
                                    pi1L2 = label(text: "Basic costs = " + model.budgetPi[1].pieValue.toString() + "  (" +  (model.budgetPi[1].pieValue.toBigDecimal().divide(model.budgetPi.pieValue.sum(), 1, RoundingMode.HALF_EVEN)*100).toString() + "%)",
                                            font: '14pt verdana')
                                    pi1L3 = label(text: "Other costs = " + model.budgetPi[2].pieValue.toString() + "  (" +  (model.budgetPi[2].pieValue.toBigDecimal().divide(model.budgetPi.pieValue.sum(), 1, RoundingMode.HALF_EVEN)*100).toString() + "%)",
                                            font: '14pt verdana')
                                    pi1L4 = label(text: "% of Income Saved = (" +  (model.budgetPi[3].pieValue.toBigDecimal().divide(model.budgetPi.pieValue.sum(), 1, RoundingMode.HALF_EVEN)*100).toString() + "%)",
                                            font: '14pt verdana')
                                }
                                vbox{
                                    model.nwBar[0].name = "Assets"
                                    model.nwBar[1].name = "Debts"
                                    model.nwBar[2].name = "Net Wealth"
                                    bar = barChart(data: model.nwBar, title: "NW Bar Chart")


                                    button(prefHeight: 55.0, prefWidth: 350.0, text: "NEXT YEAR", nextYearAction)
                                }
                            }

                            vbox(alignment: 'BOTTOM_LEFT', layoutX:2.0, layoutY: 524.0, prefHeight: 27.0, prefWidth=444.0, bottomAnchor: 0.0, leftAnchor: 2.0, rightAnchor: 0.0, topAnchor: 524.0){
                                hbox(padding: 60) {
                                    text(text: '                FOR YEAR:       ', font: '32pt sanserif') {
                                        fill linearGradient(endX: 0, stops: [PALEGREEN, SEAGREEN])
                                    }
                                    text(text: (Calendar.getInstance().get(Calendar.YEAR)).toString(), font: '46pt sanserif') {
                                        fill linearGradient(endX: 0, stops: [CYAN, DODGERBLUE])
                                        effect dropShadow(color: BLACK, radius: 25, spread: 0.25)
                                    }
                                }

                            }

                            vbox(alignment: 'BOTTOM_LEFT', layoutX:2.0, layoutY: 524.0, prefHeight: 27.0, prefWidth=444.0, bottomAnchor: 0.0, leftAnchor: 2.0, rightAnchor: 0.0, topAnchor: 524.0){
                                button(prefHeight: 25.0, prefWidth: 439.0, text: "UPDATE IT!!!!!!!!!", updatePieAction)

                            }

                        }

                    }

                    hbox( id:'HBox', alignment: 'CENTER_LEFT', spacing: 5.0, vgrow: 'NEVER'){
                        label( maxHeight: 1.7976931348623157E308, maxWidth: -1.0, text: "Left status", hgrow: 'ALWAYS')
                        pane(prefHeight: -1.0,  prefWidth: -1.0, hgrow: 'ALWAYS' )
                        label( maxWidth: -1.0, text: "Right status", hgrow:'NEVER' )

                    }
                }
            }

            masterPane = borderPane()
            myPane = pane{

                rectangle(fill: WHITE, width: 300, height: 150)

                hbox(padding: 60) {
                    text(text: 'sdegsdgsdgs', font: '80pt sanserif') {
                        fill linearGradient(endX: 0, stops: [PALEGREEN, SEAGREEN])
                    }
                    text(text: 'FX', font: '80pt sanserif') {
                        fill linearGradient(endX: 0, stops: [CYAN, DODGERBLUE])
                        effect dropShadow(color: DODGERBLUE, radius: 25, spread: 0.25)
                    }
                }
            }
            myGrid = gridPane {
                label(id: 'clickLabel', row: 0, column: 0,
                        text: bind(model.clickCountProperty()))
                button(row: 1, column: 0, prefWidth: 200,
                        id: 'clickActionTarget', switchScAction)

                vbox (row: 2, column: 0) {
                    label(id: 'clickLabel1', text: bind(model.clickCountProperty()))
                    button( prefWidth: 200, id: 'clickActionTarget1', clickAction)

                    hbox (id: 'vbox1'){
                        button(id: 'clickActionTarget2', text: "fasfasf")

                        textField(id: 'clickLabel2', text: "fdsgdsg"){
                            //println(Calc.getReal(model.xx, model.xx[3].value))
                            //println(model.budgetPi[2].pieValue)
                            //println(model.dataFinInc[0].value)
                        }

                    }

                }

            }


            masterPane.setLeft(myGrid)
            masterPane.setRight(myPane)

            scene(id: 'sc1') {

                rectangle(fill: WHITE, width: 800, height: 150)

                hbox(padding: 60) {
                    text(text: 'Groovy', font: '80pt sanserif') {
                        fill linearGradient(endX: 0, stops: [PALEGREEN, SEAGREEN])
                    }
                    text(text: 'FX', font: '80pt sanserif') {
                        fill linearGradient(endX: 0, stops: [CYAN, DODGERBLUE])
                        effect dropShadow(color: DODGERBLUE, radius: 25, spread: 0.25)
                    }
                    z = tableView(editable: true) {
                        tableColumn(id: 'firstNameCol')
                        firstNameCol.setMinWidth(100);
                        firstNameCol.setCellValueFactory(
                                new PropertyValueFactory<Person, String>("firstName"))
                        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
                        firstNameCol.setOnEditCommit(
                                new EventHandler<CellEditEvent<Person, String>>() {
                                    @Override
                                    public void handle(CellEditEvent<Person, String> t) {
                                        ((Person) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).firstName = t.getNewValue()
                                    }
                                }
                        );

                        tableColumn(id: 'lastNameCol')
                        lastNameCol.setMinWidth(100);
                        lastNameCol.setCellValueFactory(
                                new PropertyValueFactory<Person, String>("lastName"))

                        tableColumn(id: 'emailCol')
                        emailCol.setMinWidth(200);
                        emailCol.setCellValueFactory(
                                new PropertyValueFactory<Person, String>("email"))

                    }
                    z.setItems(model.data)

                    zz = tableView(editable: true) {
                        tableColumn(id: 'typeCol')
                        typeCol.setMinWidth(100);
                        typeCol.setCellValueFactory(
                                new PropertyValueFactory<MonthlyExpense, String>("type"))


                        tableColumn(id: 'valueCol')
                        valueCol.setMinWidth(100)
                        valueCol.setCellFactory(TextFieldTableCell.forTableColumn())
                        valueCol.setCellValueFactory(
                                new PropertyValueFactory<MonthlyExpense, String>("value"))
                        valueCol.setOnEditCommit(
                                new EventHandler<CellEditEvent<MonthlyExpense, String>>() {
                                    @Override
                                    public void handle(CellEditEvent<MonthlyExpense, String> t) {
                                        ((MonthlyExpense) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).value = t.getNewValue()
                                    }
                                })
                    }
                    zz.setItems(model.dataMEsurv)


                    vbox(prefWidth: 175.0) {
                        hbox(prefHeight: 225.0) {
                            l1 = listView(editable: true, prefWidth: 125)
                            l1.getItems().addAll("Inflation", "Interest (Secured)", "Interest (LoC)", "Interest (Loan)", "Interest (Student)", "Interest (Car)", "Interest (Credit Card)", "Return (Bonds)", "Return (Stocks)")
                            l2 = listView(editable: true, prefWidth: 50)
                            l2.getItems().addAll(model.varsG_str.infl, model.varsG_str.i, model.varsG_str.iLoC, model.varsG_str.iLoan, model.varsG_str.iStLoan, model.varsG_str.iCarLoan, model.varsG_str.icc, model.varsG_str.rBond, model.varsG_str.rStock)
                        }
                    }




                }
                v = vbox() {
                    final addFirstName = textField()
                    addFirstName.setPromptText("First Name")
                    addFirstName.setMaxWidth(firstNameCol.getPrefWidth())
                    final addLastName = textField()
                    addLastName.setMaxWidth(lastNameCol.getPrefWidth())
                    addLastName.setPromptText("Last Name")
                    final addEmail = textField()
                    addEmail.setMaxWidth(emailCol.getPrefWidth())
                    addEmail.setPromptText("Email")
                    but1 = button().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            model.data.add(new Person(addFirstName.getText(), addLastName.getText(), addEmail.getText()))
                            addFirstName.clear()
                            addLastName.clear()
                            addEmail.clear()
                        }
                    })

                }
            }

            s11 = scene(){

                pane(masterPane)


            }
            s22 = scene(){

                pane(){

                    vbox(0) {
                        this.rates = tableView(editable: true) {
                            tcTxt = tableColumn(text: "Description")
                            tcTxt.setCellValueFactory(
                                    new PropertyValueFactory<RatesStr, String>("type"))
                            tcVal = tableColumn(text: "Nominal Rate ")
                            tcVal.setCellFactory(TextFieldTableCell.forTableColumn())
                            tcVal.setCellValueFactory(
                                    new PropertyValueFactory<RatesStr, String>("value"))
                            tcVal.setOnEditCommit(
                                    new EventHandler<CellEditEvent<RatesStr, String>>() {
                                        @Override
                                        public void handle(CellEditEvent<RatesStr, String> t) {
                                            ((RatesStr) t.getTableView().getItems().get(
                                                    t.getTablePosition().getRow())
                                            ).value = t.getNewValue()
                                        }
                                    })
                        }
                        this.rates.setItems(model.dataRates)

                    }

                }

            }
            s33 = scene(){

                pane()

            }


            this.myscenes = [sc1, sc3, s22, s33, s11 ]


            this.mystage = stage(title: 'GroovyFX Hello World', visible: false)
            this.mystage.setScene(myscenes[model.currScene])


            //st1.hide()

        }
        myapp.setScene(myscenes[1])
    }

}

/*class WealthProphetView {
    @MVCMember @Nonnull
    FactoryBuilderSupport builder
    @MVCMember @Nonnull
    WealthProphetModel model

    void initUI() {
        builder.application(title: application.configuration['application.title'],
                sizeToScene: true, centerOnScreen: true, name: 'mainWindow') {
            scene(fill: WHITE, width: 200, height: 60) {
                gridPane {
                    label(id: 'clickLabel', row: 0, column: 0,
                            text: bind(model.clickCountProperty()))
                    button(row: 1, column: 0, prefWidth: 200,
                            id: 'clickActionTarget', clickAction)
                }
            }
        }
    }*/