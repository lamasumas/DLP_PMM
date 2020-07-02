package semantic.symboltable;

import java.util.*;
import ast.definitions.Definition;

public class SymbolTable {
	
	private int scope;
	private List<Map<String, Definition>> table;
	public SymbolTable()  {
		table = new ArrayList<Map<String, Definition>>();
		table.add(new HashMap<String, Definition>());
		scope=0;
	}

	public void set() {
		table.add(new HashMap<>());
		scope++;
	}
	
	public void reset() {
		table.remove(table.size()-1);
		scope--;
	}
	
	public boolean insert(Definition definition) {
		if( findInCurrentScope( definition.getName()) == null) {
			definition.setScope(scope);
			table.get(scope).put(definition.getName(), definition);
			return true;
		}
		return false;
	}
	
	public Definition find(String id) {
		Definition x = null;
		for( int i = table.size()-1; i>=0; i--){
			x = table.get(i).get(id);
			if (x!= null) break;
		}
		return x;

	}

	public Definition findInCurrentScope(String id) {
		return table.get(scope).get(id);
	}
}
