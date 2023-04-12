package drop;

import player.BasePlayer;

public class DropGold {
	public DropGold(BasePlayer p1, int money) {
		p1.earnMoney(money);
	}
}
