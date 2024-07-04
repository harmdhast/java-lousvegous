package fr.esgi.lousvegous.intro;

import com.almasb.fxgl.animation.Animation;
import javafx.scene.layout.Pane;

import java.util.List;

public interface Splash {
    Pane getPane();

    List<Animation<?>> getAnimations();
}
