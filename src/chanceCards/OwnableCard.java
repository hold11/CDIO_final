package chanceCards;

import fields.Ownable;
import models.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by awo on 09/01/17.
 */
public abstract class OwnableCard extends ChanceCard {
    private Player owner;

    public OwnableCard(int count) {
        super(count);
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    public void removeOwner() {
        this.owner = null;
    }

    public static OwnableCard[] getOwnedCards() {
        List<OwnableCard> ownedCards = new ArrayList<>();
        for (ChanceCard c : ChanceCard.getChanceCards()) {
            if (c instanceof OwnableCard) {
                if (((OwnableCard) c).owner != null) {
                    ownedCards.add(((OwnableCard) c));
                }
            }
        }
        return ownedCards.toArray(new OwnableCard[ownedCards.size()]);
    }

    public static OwnableCard[] getPlayersCards(Player player) {
        List<OwnableCard> ownedCards = new ArrayList<>();
        for (OwnableCard o : getOwnedCards()) {
            if (o.owner.equals(player)) {
                ownedCards.add(o);
            }
        }
        return ownedCards.toArray(new OwnableCard[ownedCards.size()]);
    }

    public static boolean playerHasCard(Player player, Class C) {
        for (OwnableCard o : getPlayersCards(player))
            if (o.getClass() == C)
                return true;
        return false;
    }
}
