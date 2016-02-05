/*
 * Jay Patel
 * file:// MyNode.java
 */

public class MyNode
{
	private Card card;
	MyNode next;

	MyNode(Card in)
	{
		card = new Card(in);
		next = null;
	}

	// getters
	public char get_rank()
	{
		return card.get_rank();
	}

	public char get_suit()
	{
		return card.get_suit();
	}

	public String get_color()
	{
		return card.get_color();
	}

	public int get_strength()
	{
		return card.get_strength();
	}

	public int get_point()
	{
		return card.get_point();
	}

	public Card get_card()
	{
		return card;
	}
}
