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

}