package scene.InputPlayerName;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scene.components.DecoratedButton;
import scene.components.DecoratedText;
import utility.GameConfig;

public class AddPlayerContainer extends VBox{
	private Button addPlayerBtn;
	private TextField nameInput;
	private Text heading;
	private Text subHeading;
	
	public AddPlayerContainer() {
		heading = new DecoratedText("Enter Player Name", 40);
		subHeading = new DecoratedText(String.format("(No More than %d Characters)", GameConfig.MAXIMUM_PLAYER_CHARS), 18);
		
		nameInput = new TextField();
		nameInput.setPrefHeight(50);
		nameInput.setMaxHeight(50);
		nameInput.setFont(new Font(25));
		nameInput.textProperty().addListener((observable, oldValue, newValue) -> {
			updateAddPlayerBtn(newValue);
		});		
		
		addPlayerBtn = new DecoratedButton("Add Player", 320, 80, 40);
		addPlayerBtn.setDisable(true);
		addPlayerBtn.setOnAction((event) -> {
			InputPlayerNamePane.getPlayerNameContainer().addPlayer(this.nameInput.getText());
			nameInput.setText("");
		});
		
		this.setAlignment(Pos.CENTER_LEFT);
		
		this.setSpacing(20);
		
		this.getChildren().addAll(heading, subHeading, nameInput, addPlayerBtn);
	}
	
	public boolean validatePlayerName(String name) {
		return (!name.isEmpty() && name.length() <= GameConfig.MAXIMUM_PLAYER_CHARS);
	}
	
	public void updateAddPlayerBtn(String name) {
		int numberPlayer = InputPlayerNamePane.getPlayerNameContainer().getPlayerList().size();
		boolean canAddPlayer = (numberPlayer < GameConfig.MAXIMUM_PLAYER);
		if (name.isEmpty() || !validatePlayerName(name) || !canAddPlayer) {
			addPlayerBtn.setDisable(true);
		} else {
			addPlayerBtn.setDisable(false);
		}	
	}
	
	public void refreshAddPlayerBtn() {
		updateAddPlayerBtn(nameInput.getText());
	}
}
