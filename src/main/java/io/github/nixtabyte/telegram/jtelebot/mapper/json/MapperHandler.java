
/**
 *
 * Copyright (C) 2015 Roberto Dominguez Estrada and Juan Carlos Sedano Salas
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 */
package io.github.nixtabyte.telegram.jtelebot.mapper.json;


import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
/**
*
* This object presents the jackson mapper using a singleton
*
* @since 0.0.1
*/
public enum MapperHandler {
	INSTANCE;
	
	private ObjectMapper objectMapper;
	
	private MapperHandler(){
		initMapper();
	}
	
	private void initMapper(){
		objectMapper = new ObjectMapper();
		objectMapper.enable(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	}
	
	/**
	 * <p>Getter for the field <code>objectMapper</code>.</p>
	 *
	 * @return a {@link org.codehaus.jackson.map.ObjectMapper} object.
	 */
	public ObjectMapper getObjectMapper(){
		if(objectMapper==null){
			initMapper();
		}
		return objectMapper;
	}

}
