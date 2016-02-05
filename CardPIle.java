/*
 * Jay Patel
 * file:// CardPile.java
 *
 * an individual deck of card
 * pile1, pile2, pile3, pile4, pile5, pile6, pile7, pile8
 * user_deck, computer_deck
 * original_deck
 */

import java.util.*;
import java.lang.String;

public class CardPile
{
	// instance variables for all the cards
	public static final int STRENGTH_CLUB = 0;
	public static final int STRENGTH_DIAMOND = 1;
	public static final int STRENGTH_HEARTS = 2;
	public static final int STRENGTH_SPADES = 3;
	public static final int MAX_AMOUNT_CARDS = 52;
	public static final String COLOR_RED = "red";
	public static final String COLOR_BLACK = "black";
	public static final String ORIGINAL_DECK = "deck_array";
	public static final String OTHER_DECK = "other_deck";
	public static final String KING_DECK = "king_deck";
	public static final String USER_DECK = "user_deck";

	private Card[] card_deck; // this will be useless onces we get the head node populated
	private MyNode head;
	private int total_cards;
	private String pile_type; // king or not a king

	// constructor
	CardPile(String input)
	{
		if(input.equals(ORIGINAL_DECK))
			card_deck = generate_deck();
		else
			head = null;
		total_cards = 0;
		pile_type = input;
	}

	/*
	 * returns the amount of cards in the deck
	 */
	public int get_total_cards()
	{
		return total_cards;
	}

	public String get_pile_type()
	{
		return pile_type;
	}

	public void set_head_node(MyNode node)
	{
		head = node;
	}

	public void set_head_node_null()
	{
		head = null;
	}

	public MyNode get_head_node()
	{
		return head;
	}

	/*
	 * returns true if head is null
	 * else false
	 */
	public boolean is_pile_empty()
	{
		if(head == null)
			return true;
		else 
			return false;
	}

	/*
	 * prints out all the cards in the pile
	 * 
	 * no return value
	 */
	public void print_all()
	{
		for(int i = 0; i < MAX_AMOUNT_CARDS; i++)
			System.out.println("Rank: " + card_deck[i].get_rank() + 
							   " Suit: " + card_deck[i].get_suit() + 
							   " Color: " + card_deck[i].get_color() +
							   " Strength: " + card_deck[i].get_strength() +
							   " Point(s): " + card_deck[i].get_point());
	}

	/*
	 * generates a deck of cards by using Cards class
	 * puts 52 Cards onto the deck
	 * only this class can generate cards
	 * no need to make it public
	 *
	 * returns a newly created deck of cards array
	 */
	private Card[] generate_deck()
	{
		char[] rank_arr = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
		char[] suit_arr = {'C', 'D', 'H', 'S'};

		Card[] cards = new Card[MAX_AMOUNT_CARDS];

		// sets the ranks and suits for all the cards
		int i = 0;
		for(int k = 0; k < 4; k++)
		{
			// first sets the suits
			for(int j = 0; j < 13; j++)
			{
				// initializing Card class
				cards[i] = new Card();

				if(k == 0)
					cards[i].set_suit(suit_arr[k]);
				else if(k == 1)
					cards[i].set_suit(suit_arr[k]);
				else if(k == 2)
					cards[i].set_suit(suit_arr[k]);
				else if(k == 3)
					cards[i].set_suit(suit_arr[k]);

				// sets the point(s) for each card
				if(rank_arr[j] == 'K')
					cards[i].set_point(10);
				else
					cards[i].set_point(1);

				// now sets the rank
				cards[i].set_rank(rank_arr[j]);
				i++;
			}
		}

		// sets the colors and strength for all the cards
		for(i = 0; i < MAX_AMOUNT_CARDS; i++)
		{
			// sets the color
			if(cards[i].get_suit() == 'C')
			{
				cards[i].set_color(COLOR_BLACK);
				cards[i].set_strength(STRENGTH_CLUB);
			}
			else if(cards[i].get_suit() == 'D')
			{
				cards[i].set_color(COLOR_RED);
				cards[i].set_strength(STRENGTH_DIAMOND);
			}
			else if(cards[i].get_suit() == 'H')
			{
				cards[i].set_color(COLOR_RED);
				cards[i].set_strength(STRENGTH_HEARTS);
			}
			else if(cards[i].get_suit() == 'S')
			{
				cards[i].set_color(COLOR_BLACK);
				cards[i].set_strength(STRENGTH_SPADES);
			}
		}

		return cards; // a newly created deck with 52 cards in it
	}

	/*
	 * shuffles the deck of cards
	 */
	public void shuffle_deck()
	{
		Random rand = new Random();

		for(int i = 0; i < MAX_AMOUNT_CARDS; i++)
		{
			int random_num = rand.nextInt(52);
			Card temp = new Card();

			temp = card_deck[random_num];
			card_deck[random_num] = card_deck[i];
			card_deck[i] = temp;
		}
	}

    /*
	 * prints all the cards in the pile
	 * only used for testing purposes
	 */
	public void print_all_stack()
	{
		if(head == null)
		{
			System.out.println("no cards in the pile");
			return;
		}

		MyNode tmp = head;
		while(tmp != null)
		{
			System.out.println("Rank: " + tmp.get_rank() + 
							   " Suit: " + tmp.get_suit() + 
							   " Color: " + tmp.get_color() +
							   " Strength: " + tmp.get_strength() +
							   " Point(s): " + tmp.get_point());
			tmp = tmp.next;
		}
	}

	/*
	 * pushes the object at the end of the list
	 * iterate through the list and then adds it at the end
	 * 
	 * no return value
	 */
	public void push(Card in)
	{
		total_cards++;
		if(head == null)
		{
			MyNode tmp = new MyNode(in);
			tmp.next = head;
			head = tmp;
			return;
		}

		MyNode tmp = new MyNode(in);
		// at this point, the node is not empty
		MyNode curr = head;

		// going to add it at the end of the list
		while(curr != null)
			curr = curr.next;

		/* 
		 * new node gets added to the list
		 */
		tmp.next = head;
		head = tmp;
	}

	/*
	 * pops the value that is on the top
	 * returns the object that is being popped off
	 */
	public Card pop()
	{
		if(head == null)
			return null;

		// the deck is not empty, begin the procedure to pop

		Card ret = head.get_card();
		head = head.next;

		total_cards--;

		return ret;
	}

	/*
	 * returns the card that was just removed
	 * does not removed the card though
	 */
	public MyNode get_last_node()
	{
		if(head == null)
		{
			return null;
		}

		MyNode curr = head;

		while(curr.next != null)
			curr = curr.next;

		return curr;
	}

	/*
	 * removes a card from the pile based on suit and rank
	 * returns the card that gets removed from the pile
	 * this method gets called after card_exist method
	 * if card_exist returns false, then this method does not get called
	 * when this method gets called, the card definitely exist
	 */
	public Card remove_card(char s, char r)
	{
		if(head == null)
		{
			System.out.println("deck is already empty: remove_card");
			return null;
		}

		/*
		 * at this point, we know that the deck is not empty
		 * so we can begin with the removal process
		 */
		MyNode curr = head;
		MyNode prev = null;

		// this checks if we are trying to get the first card from the deck
		if(curr.get_rank() == r && curr.get_suit() == s)
		{
			return pop();
		}

		Card ret = null;

		// performs a linear search on the linked list
		while(curr != null)
		{
			if(curr.get_rank() == r && curr.get_suit() == s)
			{
				ret = curr.get_card();
				prev.next = curr.next;
				break;
			}

			prev = curr;
			curr = curr.next;
		}

		total_cards--;

		return ret;
	}

	/*
	 * the method checks to see if the card exist in the pile or not
	 * linear time
	 */
	public boolean card_exist(char s, char r)
	{
		if(head == null)
		{
			System.out.println("deck is already empty");
			return false;
		}

		MyNode temp = head;
		while(temp != null)
		{
			if(temp.get_rank() == r && temp.get_suit() == s)
				return true;
			temp = temp.next;
		}

		// at this point, the card requested does not exist
		System.out.println("Card with suit value: " + s + " and rank value: " + r + " does not exist");
		return false;
	}

	/*
	 * method gets called by the AI algorithim 
	 * checks to see if there are kings present in the deck
	 * returns true if kings are present
	 * false otherwise
	 */
	public char king_exist()
	{
		if(head == null)
			return 'K';

		MyNode curr = head;
		while(curr != null)
		{
			if(curr.get_rank() == 'K')
				return curr.get_suit();
			curr = curr.next;
		}

		// at this point, king is not present in the deck
		return 'K';
	}

	/*
	 * does not pop the card of the pile
	 * just returns a copy of the card that is at the top
	 */
	public Card get_top_card()
	{
		if(head == null)
		{
			return null;
		}

		return head.get_card();
	}

	/*
	 * pushes one object at a time
	 * at the end, we have a dynamica card deck with 52 cards
	 */
	public void populate()
	{
		head = null;

		for(int i = 0; i < MAX_AMOUNT_CARDS; i++)
		{
			push(card_deck[i]);
		}
	}

	/*
	 * checks to see if the move is valid or not
	 * makes a call to valid_move method in Card class
	 *
	 * method will also be used to move one pile on top of the other
	 * if the bottom card and the card to be placed checks out, then the whole pile is valid
	 * 
	 * returns true
	 * else false
	 */
	public boolean is_valid_move(Card to_place)
	{
		Card at_bottom = get_top_card();
		if(at_bottom == null)
			return false;

		// if not true
		if(!at_bottom.valid_move(to_place))
			return false;

		return true;
	}

	/*
	 * prints out all the cards in the deck
	 * output for the user to see when they press A commond for about
	 */
	public void print_CardPile()
	{
		if(head == null)
		{
			System.out.print(" ");
			System.out.println("");
			return;
		}

		print_CardPile_helper(head);

		System.out.println("");
	}

	/*
	 * prints the list out backwards
	 * output is in descending order
	 */
	public void print_CardPile_helper(MyNode p)
	{
		if(p == null)
			return;
		print_CardPile_helper(p.next);
		System.out.print(p.get_rank());
		System.out.print(p.get_suit() + " ");
	}
}
