/*
 * Jay Patel
 * file:// UserInteraction.java
 * contains following methods:
 *								contains main()
 * 								
 */

import java.util.*;
import java.lang.String;

public class UserInteraction
{
	public CardPile useless;			// a useless deck, it is just created to assign values for others
	public LayDownPile original_deck; 	// contains 34 cards after distrubuting 7 cards to user, 7 to computer, 4 to each pile 1-4
	public LayDownPile user_deck;
	public LayDownPile computer_deck;
	public LayDownPile pile1;
	public LayDownPile pile2;
	public LayDownPile pile3;
	public LayDownPile pile4;
	public LayDownPile pile5; 			// king's corner
	public LayDownPile pile6; 			// king's corner
	public LayDownPile pile7; 			// king's corner
	public LayDownPile pile8; 			// king's corner
	private int computer_serve;			// 1 for user 0 for computer
	private int computer_points;
	private int user_points;

	//constructor
	UserInteraction(int in)
	{
		useless = new CardPile("other_deck");
		original_deck = new LayDownPile(useless.ORIGINAL_DECK);
		user_deck = new LayDownPile(useless.USER_DECK);
		computer_deck = new LayDownPile(useless.USER_DECK);
		pile1 = new LayDownPile(useless.OTHER_DECK);
		pile2 = new LayDownPile(useless.OTHER_DECK);
		pile3 = new LayDownPile(useless.OTHER_DECK);
		pile4 = new LayDownPile(useless.OTHER_DECK);
		pile5 = new LayDownPile(useless.KING_DECK);
		pile6 = new LayDownPile(useless.KING_DECK);
		pile7 = new LayDownPile(useless.KING_DECK);
		pile8 = new LayDownPile(useless.KING_DECK);
		computer_serve = in;
		computer_points = 0;
		user_points = 0;
	}

	/*
	 * returns a bool to check if it is computers turn to serve
	 * initially, computer serves
	 * then it alternates
	 */
	public boolean is_computer_serve()
	{
		if(computer_serve == 1)
			return true;
		else
			return false;
	}

	/*
	 * flips the users turn
	 */
	public void flip_it()
	{
		if(computer_serve == 1)
			computer_serve = 0;
		else
			computer_serve = 1;
	}

	/*
	 * shows all the available commands to the user
	 * function gets called when the user enters H commands
	 */
	public void show_commands()
	{
		System.out.println("The commands for this project are:");
		System.out.println("Q - Quit the program");
		System.out.println("H - Help");
		System.out.println("A - About");
		System.out.println("D - Draw a Card from the Draw Pile");
		System.out.println("L <Card> <Pile> - Lay a Card on a Pile");
		System.out.println("M <Pile1> <Pile2> - Move One Pile on top of Another Pile");
	}

	/*
	 * prints out all the cards that are on the deck
	 * user_deck
	 * computer_deck
	 * pile1 - pile8
	 * prints out the status of all the code after each move 
	 */
	public void show_progress()
	{
		System.out.println("-----------------------------------------------------------------");
		System.out.print("Pile 1: ");
		(pile1.get_pile()).print_CardPile();
		System.out.print("Pile 2: ");
		(pile2.get_pile()).print_CardPile();
		System.out.print("Pile 3: ");
		(pile3.get_pile()).print_CardPile();
		System.out.print("Pile 4: ");
		(pile4.get_pile()).print_CardPile();
		System.out.print("Pile 5: ");
		(pile5.get_pile()).print_CardPile();
		System.out.print("Pile 6: ");
		(pile6.get_pile()).print_CardPile();
		System.out.print("Pile 7: ");
		(pile7.get_pile()).print_CardPile();
		System.out.print("Pile 8: ");
		(pile8.get_pile()).print_CardPile();
		System.out.println("Computer player has " + (computer_deck.get_pile()).get_total_cards() + " cards");
		System.out.println("Your hand: ");
		(user_deck.get_pile()).print_CardPile();
		System.out.println("-----------------------------------------------------------------");

	}

	/*
	 * prints out about command 
	 * this 
	 */
	public void show_about()
	{
		System.out.println("Programmer: Jay Patel");
		System.out.println("The game is Kings in the corner \n" + 
							"Objective of the game is to get all the kings in the corner");
	}

	/*
	 * command D was entered by the user
	 * draw a card
	 */
	public void draw_card()
	{
		Card to_add = (original_deck.get_pile()).pop();
		if(to_add == null)
			System.out.println("No more cards left to draw");
		else
			(user_deck.get_pile()).push(to_add);
	}

	public void lay_card_helper(int pile, char s, char r)
	{
		if(user_deck.is_card_available(s, r))
		{
			// gets the card to be placed
			Card to_pass = (user_deck.get_pile()).remove_card(s,r);

			// checks if the pile is valid for the card
			if(user_deck.is_valid_pile(to_pass, get_CardPile(pile)))
				get_CardPile(pile).push(to_pass);
			else // just puts the card back to original deck
				(user_deck.get_pile()).push(to_pass);
		}
	}

	/*
	 * command L <card> <pile> was entered by the user
	 * lay cards down
	 * the method performs all the error checking before calling the helper 
	 * helper method does all the needy greedy work
	 * helper method is created so that the computer_user can also call it
	 */
	public void lay_card(Scanner sc)
	{
		// if the card is available, then remove it
		// if the card is not success full, then push it back
		String temp = sc.next();
		int pile = sc.nextInt();


		if(pile > 8 || pile < 1)
		{
			System.out.println("invalid input for pile");
			return;
		}

		char[] temp_arr = temp.toCharArray();

		char r = temp_arr[0];
		char s = temp_arr[1];

		// calls the helper function that will process all the information
		lay_card_helper(pile, s, r);
	}

	/*
	 * returns a CardPile object based on the input value
	 * 1 = pile1
	 * 2 = pile2
	 * 3 = pile3
	 * 4 = pile4
	 * 5 = pile5
	 * 6 = pile6
	 * 7 = pile7
	 * 8 = pile8
	 */
	public CardPile get_CardPile(int val)
	{
		if(val == 1)
			return pile1.get_pile();
		else if(val == 2)
			return pile2.get_pile();
		else if(val == 3)
			return pile3.get_pile();
		else if(val == 4)
			return pile4.get_pile();
		else if(val == 5)
			return pile5.get_pile();
		else if(val == 6)
			return pile6.get_pile();
		else if(val == 7)
			return pile7.get_pile();

		/*
		 * all the error checking has been done already
		 * at this point, it has to be 8
		 */
		return pile8.get_pile();
	}

	public void move_card_helper(int pile_one, int pile_two)
	{
		CardPile p1 = get_CardPile(pile_one);
		CardPile p2 = get_CardPile(pile_two);

		if(p1.is_pile_empty())
		{
			System.out.println("invlaid move: pile" + pile_one + " is already empty");
			return;
		}

		/*
		 * checks if the bottom card (soon to be top) is null
		 * if it is, then no point in advancing
		 */
		Card bottom_card = (p1.get_last_node()).get_card();
		if(bottom_card == null)
		{
			System.out.println("invalid move: pile" + pile_one + " is already empty");
			return;
		}

		if((p2.get_pile_type()).equals(p2.KING_DECK))
		{
			if(p2.is_pile_empty())
			{
				if((p1.get_last_node()).get_rank() != 'K')
				{
					System.out.println("invalid move: bottom card of pile" + pile_one + " must be of KING's rank");
					return;
				}
			}
		}

		Card top_card = p2.get_top_card();
		if(top_card == null)
		{
			// cardpile is empty, so just push the entire thing on it
			if(p1.is_pile_empty())
			{
				System.out.println("invalid move: both of the piles are empty");
				return;
			}

			/*
			 * at this point, only pile two is empty
			 * so just point pile one's content to pile 2
			 * and make pile 1 null
			 */
			MyNode p_1 = p1.get_head_node();
			p2.set_head_node(p_1);
			p1.set_head_node_null();
			return;
		}

		/*
		 * if the condition checks out then the whole pile is valid
		 * gets last node in pile1 and sets it node to pile2's first
		 * sets node in pile1 to null
		 */
		if(top_card.valid_move(bottom_card))
		{
			MyNode last = p1.get_last_node();
			last.next = p2.get_head_node();
			p2.set_head_node(last);
			p1.set_head_node_null();
		}
		else
			System.out.println("invalid move: pile" + pile_two+ " can't go on top of pile");
	}

	/*
	 * command M <pile1> <pile2> was entered by the user
	 * move the card for pile1 to pile 2
	 */
	public void move_card(Scanner sc)
	{
		int pile_one = sc.nextInt();
		int pile_two = sc.nextInt();

		if(pile_one == pile_two)
		{
			System.out.println("invalid entry: pile 1 and pile 2 have same entry");
			return;
		}

		if(pile_one > 4 || pile_one < 1)
		{
			System.out.println("invalid entry for pile1");
			return;
		}

		if(pile_two > 8 || pile_two < 1)
		{
			System.out.println("invalid entry for pile2");
			return;
		}

		move_card_helper(pile_one, pile_two);
	}

	/*
	 * processes all the commands
	 */
	public void process_commands()
	{
		Scanner sc = new Scanner(System.in);

		while(sc.hasNext())
		{
			String command = sc.next();

			if(command.equals("Q"))
			{
				System.out.println("Goodbye");
				System.exit(-1);
			}
			else if(command.equals("H"))
				show_commands();
			else if(command.equals("A"))
				show_about();
			else if(command.equals("D"))
			{	
				draw_card();
				return;
			}
			else if(command.equals("L"))
			{
				lay_card(sc);
				show_progress();
			}
			else if(command.equals("M"))
			{
				move_card(sc);
				show_progress();
			}
			else
				System.out.println("Unknown command");
			sc.nextLine();
		}
	}

	/*
	 * this is the first thing that gets run before giving 
	 * commands to the user
	 * automatically creates 52 cards and shuffles them and puts them in a stack
	 * distribute cards to each user
	 * 7 for computer and 7 for user
	 * put 1 card in pile from 1-4
	 */
	public void run_first()
	{
		// populates the user_deck and computer_deck
		if(is_computer_serve())
		{
			for(int i = 0; i < 7; i++)
			{
				(user_deck.get_pile()).push((original_deck.get_pile()).pop());
				(computer_deck.get_pile()).push((original_deck.get_pile()).pop());
			}
		}
		else
		{
			for(int i = 0; i < 7; i++)
			{
				(computer_deck.get_pile()).push((original_deck.get_pile()).pop());
				(user_deck.get_pile()).push((original_deck.get_pile()).pop());
			}
		}

		// put one card in pile1, pile2, pile3, pile4
		(pile1.get_pile()).push((original_deck.get_pile()).pop());
		(pile2.get_pile()).push((original_deck.get_pile()).pop());
		(pile3.get_pile()).push((original_deck.get_pile()).pop());
		(pile4.get_pile()).push((original_deck.get_pile()).pop());
	}

	/*
	 * this is the method that handles all the computer moves
	 */
	public void computer_user_al()
	{
		char get = (computer_deck.get_pile()).king_exist();
		// checks to see if there are any kings present in computer deck
		if(get != 'K')
		{
			// checks each pile one a time to find if they are empty
			for(int i = 5; i < 9; i++)
			{
				Card get_card = (computer_deck.get_pile()).remove_card(get, 'K');

				if(get_card == null)
					return;

				if(get_CardPile(i).get_head_node() == null)
				{
					get_CardPile(i).push(get_card);
					System.out.println("added 'K" + get_card.get_suit() + "' to pile " + i);
					show_progress();
				}
				else
					(computer_deck.get_pile()).push(get_card);
			}
		}

		/*
		 * if a pile can be moved onto another pile, do so
		 * if successful, repeat
		 */

		for(int i = 1; i < 5; i++)
		{
			CardPile comp = get_CardPile(i);

			if(comp == null)
				return;

			if((comp.get_head_node()).get_rank() == 'K')
			{
				for(int j = 5; j < 9; j++)
				{
					CardPile comp_two = get_CardPile(i);
					if(comp_two.get_head_node() == null)
					{
						move_card_helper(i, j);
						System.out.println("moving pile" + i + " to pile" + j);
						j = 10; // breaks out of the loop instead of using break 
						show_progress();
					}
				}
			}
		}

		// calling step_two
		while(true)
		{
			ai_step_two();
			if(ai_step_three())
			{
				show_progress();
				continue;
			}
			if(ai_step_four())
			{
				show_progress();
				continue;
			}

			// checks if the computer's deck is empty or not
			if(!(computer_deck.get_pile()).is_pile_empty())
			{
				Card to_push = (original_deck.get_pile()).pop();
				(computer_deck.get_pile()).push(to_push);
				break;
			}
			else
				break;
		}
	}

	/*
	 * step four for the computer
	 */
	public boolean ai_step_four()
	{
		for(int i = 1; i < 5; i++)
		{
			CardPile check = get_CardPile(i);

			if(check.is_pile_empty())
			{
				Card get_card = (computer_deck.get_pile()).get_top_card();

				if(get_card == null)
					return false;

				char s = get_card.get_suit();
				char r = get_card.get_rank();

				Card to_push = (computer_deck.get_pile()).remove_card(s, r);

				check.push(to_push);

				return true;
			}
		}

		// at this point, nothing worked, so return false
		return false;
	}

	/*
	 * step two for the computer
	 */
	public void ai_step_two()
	{
		// checks to see if a pile can be moved onto another pile
		for(int i = 1; i < 5; i++)
		{
			for(int j = 1; j < 5; j++)
			{
				System.out.println("Trying to move pile" + i + " onto pile" + j);
				move_card_helper(i, j);
			}
		}
	}

	/* 
	 * step three for the computer
	 */
	public boolean ai_step_three()
	{
		if(lay_card_ai())
			return true;
		else
			return false;
	}

	/*
	 * l command for the computer
	 */
	public boolean lay_card_ai()
	{
		Card to_lay = (computer_deck.get_pile()).get_top_card();

		if(to_lay == null)
			return false;

		CardPile to_pass = null;
		int i = 0;
		for(i = 1; i < 5; i++)
		{
			to_pass = get_CardPile(i);
			if(to_pass.is_pile_empty())
				continue;
			else
				break;
		}

		if(to_pass == null)
			return false;

		if(computer_deck.is_valid_pile(to_lay, to_pass))
		{
			System.out.print("moving '" + to_lay.get_rank());
			System.out.print(to_lay.get_suit() + "' on pile" + i);
			System.out.println("");

			Card add = (computer_deck.get_pile()).remove_card(to_lay.get_suit(), to_lay.get_rank());
			to_pass.push(add);

			return true;
		}

		// at this point, the above condition failed
		return false;

	}

	/*
	 * starts the game
	 */
	public int start_game(UserInteraction in)
	{
		UserInteraction interact = in;

       // shuffles the deck, populates the deck
       ((interact.original_deck).get_pile()).shuffle_deck();
       ((interact.original_deck).get_pile()).populate();

       /*
        * runs the pre-processor kind of the thing
        * commands for the user to interact
        * populates the user_deck and computer_deck with 7 cards
        * 1 in each pile from pile1 - pile4
        */
       interact.run_first();

       int count = 0;
       while(true)
       {
       		if(is_computer_serve())
       		{
       			interact.show_commands();
       			interact.process_commands();
       			count++;
       			flip_it(); // flips the turn
       		}
       		else
       		{
       			System.out.println("It's computer's turn right now");
       			computer_user_al();
       			count++;
       			flip_it(); // flips the turn
       		}

       		/*
       		 * when count == 2
       		 * it means that both of the users have taken their turns
       		 */
       		if(count == 2)
       		{
       			count = 0;

				System.out.print("User_player had " + user_points + " before the game");
				user_points = user_points + user_deck.count_point();
				System.out.println(" User_player has " + user_points + " after the game");
				System.out.println();

				System.out.print("Computer_player had " + computer_points + " before the game");
				computer_points = computer_points + computer_deck.count_point();
				System.out.print(" Computer_player has " + computer_points + " after the game");
				System.out.println();

				if(user_points > 25 && computer_points > 25)
				{
					System.out.println("No one won, both have over 25 penalty points");
					System.out.println("Would you like the game to continue? yes or no");

					if(play_again() > 0)
						return 111;
					else
						return -111;
				}

				if(user_points > 25)
				{
					System.out.println("Computer won this game");
					System.out.println("would you like to play again");

					if(play_again() > 0)
						return 111;
					else
						return -111;
				}

				if(computer_points > 25)
				{
					System.out.println("user won the game");
					System.out.println("would you like to play again");

					if(play_again() > 0)
						return 111;
					else
						return -111;
				}
       		}
       }
	}

	/*
	 * evalutaes if the user wants to play the game again
	 */
	public int play_again()
	{
		Scanner sc = new Scanner(System.in);

		int ret = 0;

		while(sc.hasNext())
		{
			String input = sc.next();

			if(input.equals("yes"))
			{
				return 111;
			}
			else if(input.equals("no"))
			{
				return -111;
			}
			else
				System.out.println("invalid command: enter yes or no");

			sc.nextLine();
		}

		return ret;
	}

	/*
	 * gets called by the object 
	 * instead of running everything from main
	 * this function gets called from the main
	 */
	public void do_everything(UserInteraction in)
	{
		int input = start_game(in);

		while(input > 0)
		{
			System.out.println("\n\n\n-------------------------------------------------------------------");
			System.out.println("Starting new game");

			// based on who served, computer or user serves the next round
			if(computer_serve == 1)
				computer_serve = 0;
			else
				computer_serve = 1;

			UserInteraction create = new UserInteraction(computer_serve);
			input = start_game(create);
		}
	}

	public static void main(String[] args)
	{
		UserInteraction send = new UserInteraction(1);
		send.do_everything(send);

       // goodbye message before exiting the program
       System.out.println("Good bye");
    }
}
