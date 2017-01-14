package chanceCards;/*
           ,                                             |
          /#\         _         _     _    ___   ___     | Project: Matador - CDIO_final
         /###\       | |__   _ | | __| |  /_  | /_  |    | Version: v0.1.0
        /#####\      | '_ \ / \| |/ _  |    | |   | |    |
       /##,-,##\     | | | | O | | ( | |   _| |_ _| |_   | Anders Wiberg Olsen (s165241), Valentin Leon Christensen (s152735),
      /##(   )##\    |_| |_|\_/|_|\__,_|  |_____|_____|  | Iman Chelhi (s165228), Troels Just Christoffersen (s120052),
     /#.--   --.#\                                       | Sebastian Tibor Bakonyvári (s145918)
    /`           ´\                                      |
*/

import models.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class OwnableCard extends ChanceCard {
    private Player owner;

    public OwnableCard(int count) {
        super(count);
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    public Player getOwner() {
        return owner;
    }

    public void removeOwner() {
        this.owner = null;
    }

    public static OwnableCard[] getOwnedCards() {
        List<OwnableCard> ownableCards = new ArrayList<>();
        for (ChanceCard c : ChanceCard.getChanceCards()) {
            if (c instanceof OwnableCard)
                if (((OwnableCard) c).owner != null)
                    ownableCards.add(((OwnableCard) c));
        }
        return ownableCards.toArray(new OwnableCard[ownableCards.size()]);
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

    private static OwnableCard[] findCardsOfType(Player player, Class c) {
        List<OwnableCard> cards = new ArrayList<>();
        for (OwnableCard oc : getPlayersCards(player))
            if (oc.getClass() == c)
                cards.add(oc);
        return cards.stream().toArray(OwnableCard[]::new);
    }

    public static boolean playerHasCard(Player player, Class c) {
        for (OwnableCard o : getPlayersCards(player))
            if (o.getClass() == c)
                return true;
        return false;
    }

    public static void useChanceCard(Player player, Class c) {
        if (playerHasCard(player, c))
            findCardsOfType(player, c)[0].removeOwner();
    }
}
