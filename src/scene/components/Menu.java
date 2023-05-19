package scene.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 * Menu, decorated HBox for top right position
 * @author JomnoiZ
 */
public class Menu extends HBox {
	/**
	 * Create new menu
	 */
	public Menu() {
		super();
		this.setPadding(new Insets(20, 20, 20, 20));
		this.setSpacing(20);
		this.setAlignment(Pos.TOP_RIGHT);
	}
}
