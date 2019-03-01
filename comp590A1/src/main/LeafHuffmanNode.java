package main;

public class LeafHuffmanNode implements HuffmanNode {
	
	private int _symbol;
	private int _count;
	
	public LeafHuffmanNode(int symbol, int count) {
			_symbol = symbol;
			_count = count;
	}
	

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return _count;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int symbol() {
		// TODO Auto-generated method stub
		return _symbol;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean insertSymbol(int length, int symbol) {
		// TODO Auto-generated method stub
		throw new RuntimeException("No new symbol for leaf");
	}

	@Override
	public HuffmanNode left() {
		// TODO Auto-generated method stub
		//throw new RuntimeException("leafs dont have children");
		return null;
	}

	@Override
	public HuffmanNode right() {
		// TODO Auto-generated method stub
		//throw new RuntimeException("leafs dont have children");
		return null;
	}

}
