package scene.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class Menu extends HBox {
	public Menu() {
		super();
		this.setPadding(new Insets(20, 20, 20, 20));
		this.setSpacing(20);
		this.setAlignment(Pos.TOP_RIGHT);
	}
}
