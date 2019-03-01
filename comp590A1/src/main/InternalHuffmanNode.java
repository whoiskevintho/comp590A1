package main;

public class InternalHuffmanNode implements HuffmanNode {

	public HuffmanNode _left;
	public HuffmanNode _right;
	//private boolean _endleaf;

	public InternalHuffmanNode() {
		_left = null;
		_right = null;
	}

	public InternalHuffmanNode(HuffmanNode left, HuffmanNode right) {
		_left = left;
		_right = right;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return _left.count() + _right.count();
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int symbol() {
		// TODO Auto-generated method stub
		//return 0;
		throw new RuntimeException("No symbol on internal nodes");
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return Math.max(_left.height() + 1,  _right.height() + 1);
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		/*
		if (_endleaf) {
			return true;
		} else if (_left == null || _right == null) {
			_endleaf = true;
			return _endleaf;
			
		} else if (_left.isLeaf() && _right.isLeaf()) {
			
		}
		
		*/
		
		if (_left == null || _right == null) {
			return false;
		} else {
			return _left.isFull() && _right.isFull();
		}
	}

	@Override
	public boolean insertSymbol(int length, int symbol) {
		// TODO Auto-generated method stub
		
		if (_left != null) {
			if (!_left.isFull()) {
				return _left.insertSymbol(length - 1,  symbol);
			} else if(_right != null) {
				if (!_right.isFull()) {
					return _right.insertSymbol(length - 1, symbol);
				} else {
					return false;
				}
			} else {
				if (length == 1) {
					_right = new LeafHuffmanNode(symbol, 0);
					return true;
				} else {
					_right = new InternalHuffmanNode();
					return _right.insertSymbol(length - 1,  symbol);
				}
			}
		} else {
			if (length == 1) {
				_left = new LeafHuffmanNode(symbol, 0);
				return true;
			} else {
				_left = new InternalHuffmanNode();
				return _left.insertSymbol(length - 1, symbol);
			}
		}
	}

		/*
		if (_left == null) {
			 if (!_left.isFull()) {
			if (length == 1) {
				return false;
			}	if (length > 1) {
			_left.insertSymbol(length - 1, symbol);
			}
		} else if ( _right == null) {
			_right = new LeafHuffmanNode(symbol, length);
		} else if (length > 1) {
			_right = new InternalHuffmanNode();
			_right.insertSymbol(length - 1, symbol);
		} else if (_right != null && !_right.isFull()) {
		if (length == 1) {
			return false;
		} if (length > 1) {
			_right.insertSymbol(length - 1, symbol);
		}
	}
	return true;
}
	*/
			
	@Override
	public HuffmanNode left() {
		// TODO Auto-generated method stub
		return _left;
	}

	@Override
	public HuffmanNode right() {
		// TODO Auto-generated method stub
		return _right;
	}

}
