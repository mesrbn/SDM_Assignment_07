package de.tuda.dmdb.access.exercise;

import de.tuda.dmdb.access.AbstractBitmapIndex;
import de.tuda.dmdb.access.AbstractTable;
import de.tuda.dmdb.storage.AbstractRecord;
import de.tuda.dmdb.storage.types.AbstractSQLValue;

import java.util.*;

/**
 * Bitmap index that uses the range encoded approach (still one bitmap for each distinct value)
 * @author lthostrup
 *
 ** @param <T> Type of the key index by the index. While all abstractSQLValues subclasses can be used,
 * the implementation currently only support for SQLInteger type is guaranteed.
 */
public class RangeEncodedBitmapIndex<T extends AbstractSQLValue> extends AbstractBitmapIndex<T>{

	/*
	 * Constructor of RangeEncodedBitmapIndex
	 * @param table Table for which the bitmap index will be build
	 * @param keyColumnNumber: index of the column within the passed table that should be indexed
	 */
	public RangeEncodedBitmapIndex(AbstractTable table, int keyColumnNumber) {
		super(table, keyColumnNumber);
		this.bitMaps = new TreeMap<T, BitSet>(); //Use TreeMap to get an ordered map impl.
		this.bitmapSize = this.getTable().getRecordCount();
		this.bulkLoadIndex();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void bulkLoadIndex() {
		// TODO implement this method
	}

	@Override
	public List<AbstractRecord> rangeLookup(T startKey, T endKey) {
		// TODO implement this method
		return null;
	}

}
