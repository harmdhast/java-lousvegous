package fr.esgi.lousvegous.intro;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class IntroScene extends com.almasb.fxgl.app.scene.IntroScene {
    private final Rectangle bg = new Rectangle(FXGL.getAppWidth(), FXGL.getAppHeight());
    private List<Animation<?>> animations = new ArrayList<>();

    public IntroScene() {
        SplashDev splashDev = new SplashDev();
        showSplash(splashDev, this::showSplashApp);
    }

    public void showSplashApp() {
        SplashApp splashApp = new SplashApp();
        showSplash(splashApp, this::finishIntro);
    }

    // Show splash screen
    public void showSplash(@NotNull Splash splash, Runnable onFinished) {
        getContentRoot().getChildren().clear();
        getContentRoot().getChildren().addAll(bg, splash.getPane());
        animations = splash.getAnimations();
        splash.getAnimations().forEach(Animation::start);
        splash.getAnimations().getLast().setOnFinished(
                onFinished
        );
    }

    @Override
    protected void onUpdate(double tpf) {
        animations.forEach(a -> a.onUpdate(tpf));
    }

    @Override
    public void startIntro() {
    }
}