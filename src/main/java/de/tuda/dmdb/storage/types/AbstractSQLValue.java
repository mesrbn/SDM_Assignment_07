package de.tuda.dmdb.storage.types;

import java.io.IOException;
import java.io.Serializable;

/**
 * Defines the interface for a value stored in a record
 * @author cbinnig
 *
 */
public abstract class AbstractSQLValue implements Comparable<AbstractSQLValue>, Cloneable, Serializable{

	private static final long serialVersionUID = 1L;
	protected EnumSQLType type;
	protected int maxLength;
	protected boolean isFixedLength = true;
	
	/**
	 * Creates a sql value with a given type and fixed length in bytes
	 * @param type SqlType
	 * @param length Fixed length in bytes
	 */
	public AbstractSQLValue(EnumSQLType type, int maxLength){
		this.type = type;
		this.maxLength = maxLength;
	}
	
	/**
	 * Creates a SQLValue with a given type and max. length in bytes
	 * and indicates if sql value is a fixed length or variable length 
	 * value
	 * @param type SqlType
	 * @param length Max. length in bytes
	 * @param fixedLength Fixed or variable length value
	 */
	public AbstractSQLValue(EnumSQLType type, int maxLength, boolean isFixedLength){
		this.type = type;
		this.maxLength = maxLength;
		this.isFixedLength = isFixedLength;
	}
	
	/**
	 * Returns SQLType
	 * @return
	 */
	public EnumSQLType getType() {
		return type;
	}
	
	/**
	 * Sets SQLType
	 * @param type
	 */
	public void setType(EnumSQLType type) {
		this.type = type;
	}
	
	/**
	 * Returns max. length in bytes
	 * @return
	 */
	public int getMaxLength() {
		return maxLength;
	}
	
	/**
	 * Sets max. length in bytes
	 * @param length
	 */
	public void setMaxLength(int length) {
		this.maxLength = length;
	}
	
	/**
	 * Returns fixed length of value in bytes
	 * @return
	 */
	public abstract int getFixedLength();
	
	/**
	 * Returns variable length of value in bytes
	 * @return
	 */
	public abstract int getVariableLength();
	
	/**
	 * Returns if a valus has fixed length
	 * @return
	 */
	public boolean isFixedLength() {
		return isFixedLength;
	}
	
	/**
	 * Serializes the value into y byte array
	 * @return
	 */
	public abstract byte[] serialize();
	
	
	 private void writeObject(java.io.ObjectOutputStream out)
		     throws IOException{
		// default serialization
		 out.defaultWriteObject();
		 out.write(this.serialize());
	 }
		
	private void readObject(java.io.ObjectInputStream in)
		     throws IOException, ClassNotFoundException{
		// default deserialization
	    in.defaultReadObject();
		byte[] serializedData = new byte[this.getMaxLength()];	 
		in.read(serializedData);
		this.deserialize(serializedData);
		 }
	
	/**
	 * Deserializes the value from a byte array
	 * @param data
	 */
	public abstract void deserialize(byte[] data);
	
	/**
	 * Constructs the value from a string representation
	 * @param data
	 */
	public abstract void parseValue(String data);
	
	@Override
	public abstract AbstractSQLValue clone();
}
