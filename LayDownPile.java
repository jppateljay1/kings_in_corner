/*
 * Jay Patel
 * file:// LayDownPile.java
 */

import java.util.*;
import java.lang.String;

public class LayDownPile
{
	private CardPile pile;

	// constructor, creates it based on the string type
	LayDownPile(String type)
	{
		pile = new CardPile(type);
	}

	// getter
	public CardPile get_pile()
	{
		return pile;
	}

	/*
	 * checks amongst users to make sure the pile is correct
	 * if it is not
	 * return false
	 * else
	 * true, otherwise
	 */
	public boolean is_valid_pile(Card c, CardPile pile_in)
	{
		// cheks to see if we are trying to insert a king card on king deck
		if(pile_in.get_pile_type().equals(pile.KING_DECK))
		{
			if(c.get_rank() == 'K')
			{
				if(pile_in.is_pile_empty())
					return true;
				else // the king's deck you are trying to put is already empty
				{
					System.out.println("This king's deck is already full");
					return false;
				}
			}
			else
			{
				// this is after KING's deck already has a King in it
				if(pile_in.is_valid_move(c))
					return true;
			}
			System.out.print("can not place card '" + c.get_rank());
			System.out.print(c.get_suit() + "' on king deck");
			System.out.println("");
			return false;
		} // checks if deck is already empty, if it is, then just add the card
		else if(pile_in.is_pile_empty())
			return true;

		if(pile_in.is_valid_move(c))
			return true;

		System.out.println("not a valid move");
		return false;
	}

	/*
	 * helper method to add_card_to_pile 
	 * checks to see if the card is actually on the deck for the user to move
	 */
	public boolean is_card_available(char s, char r)
	{
		if(pile.card_exist(s,r))
			return true;
		return false;
	}

	/*
	 * returns the amount of points in the deck
	 * could be called on any pile
	 * however, only user and computer user will call this method
	 */
	public int count_point()
	{
		int ret = 0;

		MyNode curr = pile.get_head_node();

		if(curr == null)
			return ret;

		while(curr != null)
		{
			ret = curr.get_strength() + ret;
			curr = curr.next;
		}

		return ret;
	}
}
