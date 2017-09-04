package WP

import griffon.core.artifact.GriffonController
import griffon.core.controller.ControllerAction
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Threading
import javafx.event.ActionEvent

import javax.annotation.Nonnull


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