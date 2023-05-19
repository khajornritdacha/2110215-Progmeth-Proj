package scene.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Base frame
 */
public class Frame extends VBox {
	/**
	 * Create new frame
	 * @param color background color of this frame
	 */
	public Frame(Color color) {
		this.setAlignment(Pos.CENTER);
		this.setPrefHeight(300);
		this.setMaxHeight(300);
		this.setPrefWidth(200);
		this.setMaxWidth(200);
		this.setBackground(new Background(new BackgroundFill(color, new CornerRadii(25), null)));
		this.setPadding(new Insets(20, 20, 20, 20));
		this.setEffect(new DropShadow());
	}
}
