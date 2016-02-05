/*
 * Jay Patel
 * file:// Card.java
 *
 * an individual card
 */

import java.lang.String;

public class Card
{
	// instance variable for comparision purposes
	private static final char[] compare_rank = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'W'};

	// instance variable for individual cards
	private char 	rank;		//A,2,3,4,5,6,7,8,9,T,J,Q,K
								//1,2,3,4,5,6,7,8,9,10,11,12,13
	private char 	suit;		// C,D,H,S
	private String 	color; 		// red or black
	private int 	strength; 	// range from 0-3
	private int 	point;		// 10 for king, 1 for the rest

	// constructors
	Card()
	{}

	Card(Card in)
	{
		rank = in.rank;
		suit = in.suit;
		color = in.color;
		strength = in.strength;
		point = in.point;
	}

	// setters
	public void set_rank(char c)
	{
		rank = c;
	}

	public void set_suit(char c)
	{
		suit = c;
	}

	public void set_color(String col)
	{
		color = col;
	}

	public void set_strength(int val)
	{
		strength = val;
	}

	public void set_point(int val)
	{
		point = val;
	}

	// getters
	public char get_rank()
	{
		return rank;
	}

	public char get_suit()
	{
		return suit;
	}

	public String get_color()
	{
		return color;
	}

	public int get_strength()
	{
		return strength;
	}

	public int get_point()
	{
		return point;
	}

	/*
	 * compares two cards and sees if to_place card can go on top of the current card
	 * return true
	 * else false
	 */
	public boolean valid_move(Card to_place)
	{
		if((this.color).equals(to_place.color))
			return false;
		if(this.rank == to_place.rank)
			return false;
		if(!valid_move_helper(to_place.rank))
			return false;

		// at this point, everything checks out and returns true
		return true;
	}

	/*
	 * helper function for valid_move
	 * checks the rank of the previous card
	 * if it matches to_place
	 * returns true
	 * else false
	 */
	public boolean valid_move_helper(char to_place)
	{
		for(int i = 0; i < 13; i++)
		{
			if(compare_rank[i] == to_place)
			{
				//checks the one after the to_place
				if(compare_rank[i+1] == this.rank)
					return true;
				else 
					return false;
			}
		}
		return false;
	}
}
