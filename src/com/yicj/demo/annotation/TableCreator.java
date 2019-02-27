package com.yicj.demo.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.yicj.demo.annotation.db.Constrains;
import com.yicj.demo.annotation.db.DBTable;
import com.yicj.demo.annotation.db.SQLInteger;
import com.yicj.demo.annotation.db.SQLString;

public class TableCreator {
	
	public static void main(String[] args) throws Exception {
		
		if(args.length < 1) {
			System.out.println("arguments : annotated classes");
			System.exit(0);
		}
		for(String className : args) {
			Class<?> cl = Class.forName(className) ;
			DBTable dbTable = cl.getAnnotation(DBTable.class) ;
			if(dbTable == null) {
				System.out.println("No DBTable annotations is class " + className);
				continue;
			}
			String tableName = dbTable.name() ;
			// If the name is empty ,use the Class name
			if(tableName.length() < 1) {
				tableName = cl.getName().toUpperCase() ;
			}
			List<String> columnDefs = new ArrayList<String>() ;
			for(Field field : cl.getDeclaredFields()) {
				String colmunName = null ;
				Annotation [] anns =  field.getDeclaredAnnotations() ;
				if(anns.length < 1) {
					continue ; //Not a db table column
				}
				if(anns[0] instanceof SQLInteger) {
					SQLInteger sInt = (SQLInteger) anns[0] ;
					// Use field name if name not specified
					if(sInt.name().length() < 1 ) {
						colmunName = field.getName().toUpperCase() ;
					}else {
						colmunName = sInt.name() ;
					}
					columnDefs.add(colmunName + " INT" + getConstraints(sInt.constrains())) ;
				}
				if(anns[0] instanceof SQLString) {
					SQLString sString = (SQLString)anns[0] ;
					if(sString.name().length() < 1) {
						colmunName = field.getName().toUpperCase() ;
					}else {
						colmunName = sString.name() ;
					}
					columnDefs.add(colmunName + " VARCHAR(" +sString.value() +")" + 
					getConstraints(sString.constrains())) ;
				}
				StringBuilder createCommand = new StringBuilder(
					"Create table" + tableName +"(") ;
					for(String colmunDef : columnDefs) {
						createCommand.append("\n " + colmunDef + " , ") ;
					}
					String tableCreate = createCommand.substring(
							0,createCommand.length() -1) + ");"   ;
					System.out.println("Table Creating SQL for " + className +" is : \n"+ tableCreate);
			}
			
			
		}
		
		
	}
	
	private static String getConstraints(Constrains con) {
		String constraints = "" ;
		if(!con.allowNull()) {
			constraints += " NOT NULL" ;
		}
		if(con.primaryKey()) {
			constraints += " PRIMARY KEY" ;
		}
		if(con.unique()) {
			constraints += "UNIQUE" ;
		}
		return constraints ;
	}

}
