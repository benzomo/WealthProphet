package WP

import griffon.core.artifact.ArtifactManager
import griffon.core.test.GriffonUnitRule
import griffon.core.test.TestFor
import org.junit.Rule
import org.junit.Test

import javax.inject.Inject

import static org.awaitility.Awaitility.await

@TestFor(WealthProphetController)
class WealthProphetControllerTest {
    static {
        System.setProperty('org.slf4j.simpleLogger.defaultLogLevel', 'trace')
        // force initialization JavaFX Toolkit
        new javafx.embed.swing.JFXPanel()
    }

    @Inject
    private ArtifactManager artifactManager

    private WealthProphetController controller

    @Rule
    public final GriffonUnitRule griffon = new GriffonUnitRule()

    @Test
    void executeClickAction() {
        // given:
        controller.model = artifactManager.newInstance(WealthProphetModel)

        // when:
        controller.invokeAction('click')
        await().until { controller.model.clickCount != "0" }

        // then:
        assert "1" == controller.model.clickCount
    }
}
